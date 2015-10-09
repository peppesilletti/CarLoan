package datastore.mySqlFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import transferObjects.entitiesTO.ExtraTO;
import datastore.entitiesDAO.ExtraDAO;
import datastore.mySqlDB.DbUtil;
import datastore.mySqlDB.MySqlConnectionFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Classe che si interfaccia con il database MySql per la memorizzazione e
 * reperimento dei dati di un extra. Implementa {@link ExtraDAO}.
 * */
public class MySqlExtraDAO extends MySqlDao implements ExtraDAO {

    @Override
    public final Boolean create(final ExtraTO extra) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {

            statement = con.prepareStatement(
                    sqlFac.getQuery("CreateExtra"));

            int i = 1;
            statement.setString(i++, extra.nome);
            statement.setString(i++, extra.descrizione);
            statement.setFloat(i++, extra.prezzo);

            result = statement.executeUpdate();

            if (result > 0) {
                response = true;
            }
        } catch (SQLException e) {
                handleExceptions(e, ErrorHandlerInt.WARNING);
        }  finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return response;
    }

    @Override
    public final ExtraTO research(final String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ExtraTO extra = new ExtraTO();
        ResultSet result = null;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ResearchExtra"));

            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeQuery();

            while (result.next()) {
                extra.nome = result.getString("nome");
                extra.descrizione = result.getString("descrizione");
                extra.prezzo = result.getFloat("prezzo");
                extra.id = id;
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return extra;
    }

    @Override
    public final Boolean delete(final String id) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("DeleteExtra"));
            statement.setInt(1, Integer.parseInt(id));
            result = statement.executeUpdate();

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
    public final Boolean update(final ExtraTO extra) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("UpdateExtra"));

            int i = 1;
            statement.setString(i++, extra.nome);
            statement.setString(i++, extra.descrizione);
            statement.setFloat(i++, extra.prezzo);
            statement.setInt(i++, Integer.parseInt(extra.id));

            result = statement.executeUpdate();

            if (result > 0) {
                response = true;
            }
        } catch (SQLException e) {
            if (result <= 0) {
                response = false;
            } else {
                handleExceptions(e, ErrorHandlerInt.WARNING);
            }
        }  finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return response;
    }

    @Override
    public final List<ExtraTO> getList() {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<ExtraTO> response = new ArrayList<ExtraTO>();

        ResultSet result = null;
        try {

            statement = con.prepareStatement(
                    sqlFac.getQuery("GetExtraList"));

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                while (result.next()) {
                    ExtraTO extra = new ExtraTO();
                    extra.nome = result.getString("nome");
                    extra.descrizione = result.getString("descrizione");
                    extra.prezzo = result.getFloat("prezzo");
                    extra.id = Integer.toString(result.getInt("id"));
                    response.add(extra);
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
    public Boolean existCheck(ExtraTO dati) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ResultSet result = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ExistExtra"));

            statement.setString(1, dati.nome);

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

}
