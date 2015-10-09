package datastore.mySqlDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;
/**
 * Classe di utility per il mysql DAO.
 * */
public class DbUtil {

    /**
     * Chiude una connessione al database sql.
     *
     * @param connection
     *            La connessione da chiudere.
     * */
    public static void close(final Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                handleExceptions(e);
            }
        }
    }

    /**
     * Chiude uno statement.
     *
     * @param statement
     *            Lo statement da chiudere.
     * */
    public static void close(final Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                handleExceptions(e);
            }
        }
    }

    /**
     * Chiude un result set.
     *
     * @param resultSet
     *            ResultSet da chiudere.
     * */
    public static void close(final ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                handleExceptions(e);
            }
        }
    }

    /**
     * Gestisce le eccezioni che si verificano.
     *
     * @param e
     *            eccezione verificatasi
     * */
    private static void handleExceptions(final Exception e) {
        DAOException ex = new DAOException(e.getMessage());
        ErrorHandler er = ErrorHandler.getIstance();
        er.processError(ex.getClass(), ex,
                ErrorHandlerInt.NO_MESSAGE);
    }
}
