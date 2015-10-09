package datastore.mySqlFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import transferObjects.entitiesTO.OperatoreTO;
import datastore.entitiesDAO.OperatoreDAO;
import datastore.mySqlDB.DbUtil;
import datastore.mySqlDB.MySqlConnectionFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Classe che si interfaccia con il database MySql per la memorizzazione e
 * reperimento dei dati di un operatore. Implementa {@link OperatoreDAO}.
 * */
public class MySqlOperatoreDAO extends MySqlDao implements OperatoreDAO {

    /**
     * Costruttore di default.
     * */
    public MySqlOperatoreDAO() {
        super();
    }

    @Override
    public final Boolean create(final OperatoreTO operatore) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement datiAccount = null;
        PreparedStatement datioperatore = null;
        Boolean response = false;

        try {
            con.setAutoCommit(false);

            datiAccount = con.prepareStatement(sqlFac
                    .getQuery("CreateOperatoreAccount"));
            datioperatore = con.prepareStatement(sqlFac
                    .getQuery("CreateOperatoreDati"));

            int i = 1;
            datiAccount.setString(i++, operatore.username);
            datiAccount.setString(i++, operatore.password);
            datiAccount.setString(i++, "operatore");
            datiAccount.setInt(i++, Integer.parseInt(
                    operatore.agenziaId));
            datiAccount.executeUpdate();

            i = 1;
            datioperatore.setString(i++, operatore.username);
            datioperatore.setString(i++, operatore.nome);
            datioperatore.setString(i++, operatore.cognome);

            datioperatore.executeUpdate();

            con.commit();
            response = true;
        } catch (SQLException e) {

            handleExceptions(e, ErrorHandlerInt.WARNING);
            response = false;
            try {
                con.rollback();
            } catch (SQLException e1) {

                handleExceptions(e1, ErrorHandlerInt.WARNING);
            }
        } finally {
            if (datiAccount != null) {
                DbUtil.close(datiAccount);
            }
            if (operatore != null) {
                DbUtil.close(datioperatore);
            }
            DbUtil.close(con);
        }
        return response;
    }

    @Override
    public final OperatoreTO research(
            final String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        OperatoreTO operatore = new OperatoreTO();
        ResultSet result = null;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ResearchOperatore"));

            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeQuery();

            while (result.next()) {
                operatore.nome = result.getString("nome");
                operatore.cognome = result.getString("cognome");
                operatore.username = result.getString("username");
                operatore.agenziaId = Integer.toString(
                        result.getInt("agenzia_id"));
                operatore.id = id;
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return operatore;
    }

    @Override
    public final Boolean delete(final String id) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("DeleteOperatore"));
            statement.setString(1, id);
            int result = statement.executeUpdate();

            if (result > 0) {
                response = true;
            }

        } catch (SQLException e) {
            return false;
        } finally {
            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return response;
    }

    @Override
    public final Boolean update(final OperatoreTO operatore) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("UpdateOperatore"));

            int i = 1;
            statement.setString(i++, operatore.nome);
            statement.setString(i++, operatore.cognome);
            statement.setString(i++, operatore.username);
            statement.setString(i++, operatore.id);

            int result = statement.executeUpdate();

            if (result > 0) {
                response = true;
            }
        } catch (SQLException e) {
                return false;
        }  finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return response;
    }

    @Override
    public final List<OperatoreTO> getList() {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<OperatoreTO> response =
                new ArrayList<OperatoreTO>();

        ResultSet result = null;
        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("GetOperatoreList"));

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                while (result.next()) {
                    OperatoreTO operatore = new OperatoreTO();
                    operatore.nome = result.getString("nome");
                    operatore.cognome = result.getString("cognome");
                    operatore.username = result.getString("username");
                    operatore.agenziaId =
                            Integer.toString(result.getInt("agenzia_id"));
                    operatore.id = Integer.toString(result.getInt("id"));
                    response.add(operatore);
                }
                return response;
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {
            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return response;
    }

    /**
     * Gestisce le eccezioni DAO che si verificano.
     *
     * @param e
     *            eccezione verificatasi
     * @param t
     *            tipo di errore da sollevare
     * */
    private void handleExceptions(final Exception e, final Level t) {
        DAOException ex = new DAOException(e.getMessage());
        ErrorHandlerInt er = ErrorHandler.getIstance();
        er.processError(ex.getClass(), ex, t);
    }

    @Override
    public Boolean existCheck(OperatoreTO dati) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ResultSet result = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ExistOperatore"));

            statement.setString(1, dati.username);

            result = statement.executeQuery();

            if (result.next()) {
                if (result.getInt("total") > 0) {
                    response = true;
                }
            }
          } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return response;
    }

    @Override
    public List<OperatoreTO> getByNomeCognome(String nome, String cognome) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<OperatoreTO> response = null;

        ResultSet result = null;
        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("GetOperatoreByNomeCognome"));

            int i = 1;
            statement.setString(i++, nome);
            statement.setString(i++, cognome);

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                response = new ArrayList<OperatoreTO>();
                while (result.next()) {
                    OperatoreTO operatore = new OperatoreTO();
                    operatore.nome = result.getString("nome");
                    operatore.cognome = result.getString("cognome");
                    operatore.username = result.getString("username");
                    operatore.agenziaId = Integer.toString(
                            result.getInt("agenzia_id"));
                    operatore.id = result.getString("id");
                    response.add(operatore);
                }
                return response;
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {
            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return response;
    }
}
