package datastore.mySqlFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import transferObjects.entitiesTO.ManagerAgenziaTO;
import datastore.entitiesDAO.ManagerAgenziaDAO;
import datastore.mySqlDB.DbUtil;
import datastore.mySqlDB.MySqlConnectionFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Classe che si interfaccia con il database MySql per la memorizzazione e
 * reperimento dei dati di un manager agenzia Implementa
 * {@link ManagerAgenziaDAO}.
 * */
public class MySqlManagerAgenziaDAO extends MySqlDao
implements ManagerAgenziaDAO {

    /**
     * Costruttore di default.
     * */
    public MySqlManagerAgenziaDAO() {
        super();
    }

    @Override
    public final Boolean create(final ManagerAgenziaTO manager) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement datiAccount = null;
        PreparedStatement datiManager = null;
        Boolean response = false;

        try {
            con.setAutoCommit(false);

            datiAccount = con.prepareStatement(sqlFac
                    .getQuery("CreateManagerAgenziaAccount"));
            datiManager = con.prepareStatement(sqlFac
                    .getQuery("CreateManagerAgenziaDati"));

            int i = 1;
            datiAccount.setString(i++, manager.username);
            datiAccount.setString(i++, manager.password);
            datiAccount.setString(i++, "manager");
            datiAccount.setInt(i++, Integer.parseInt(manager.agenziaId));

            datiAccount.executeUpdate();

            i = 1;
            datiManager.setString(i++, manager.username);
            datiManager.setString(i++, manager.nome);
            datiManager.setString(i++, manager.cognome);

            datiManager.executeUpdate();

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
            if (manager != null) {
                DbUtil.close(datiManager);
            }
            DbUtil.close(con);
        }
        return response;
    }

    @Override
    public final ManagerAgenziaTO research(
            final String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ManagerAgenziaTO manager = new ManagerAgenziaTO();
        ResultSet result = null;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ResearchManagerAgenzia"));

            statement.setString(1, id);

            result = statement.executeQuery();

            while (result.next()) {
                manager.nome = result.getString("nome");
                manager.cognome = result.getString("cognome");
                manager.username = result.getString("username");
                manager.agenziaId = Integer.toString(
                        result.getInt("agenzia_id"));
                manager.id = id;
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return manager;
    }

    @Override
    public final Boolean delete(final String username) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("DeleteManagerAgenzia"));
            statement.setString(1, username);
            int result = statement.executeUpdate();

            if (result > 0) {
                response = true;
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.WARNING);
        } finally {
            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return response;
    }

    @Override
    public final Boolean update(final ManagerAgenziaTO manager) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("UpdateManagerAgenzia"));

            int i = 1;
            statement.setString(i++, manager.nome);
            statement.setString(i++, manager.cognome);
            statement.setInt(i++, Integer.parseInt(manager.agenziaId));
            statement.setString(i++, manager.username);
            statement.setString(i++, manager.id);

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
    public final List<ManagerAgenziaTO> getList() {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<ManagerAgenziaTO> response =
                new ArrayList<ManagerAgenziaTO>();

        ResultSet result = null;
        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("GetManagerAgenziaList"));

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                while (result.next()) {
                    ManagerAgenziaTO manager = new ManagerAgenziaTO();
                    manager.nome = result.getString("nome");
                    manager.cognome = result.getString("cognome");
                    manager.username = result.getString("username");
                    manager.agenziaId = Integer.toString(
                            result.getInt("agenzia_id"));
                    manager.id = result.getString("id");
                    response.add(manager);
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
    public final Boolean deleteByUsername(final String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Boolean existCheck(final ManagerAgenziaTO dati) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ResultSet result = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ExistManager"));

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
    public final List<ManagerAgenziaTO> getByNomeCognome(
            final String nome, final String cognome) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<ManagerAgenziaTO> response = null;

        ResultSet result = null;
        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("GetManagerByNomeCognome"));

            int i = 1;
            statement.setString(i++, nome);
            statement.setString(i++, cognome);

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                response = new ArrayList<ManagerAgenziaTO>();
                while (result.next()) {
                    ManagerAgenziaTO manager = new ManagerAgenziaTO();
                    manager.nome = result.getString("nome");
                    manager.cognome = result.getString("cognome");
                    manager.username = result.getString("username");
                    manager.agenziaId = Integer.toString(
                            result.getInt("agenzia_id"));
                    manager.id = result.getString("id");
                    response.add(manager);
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
