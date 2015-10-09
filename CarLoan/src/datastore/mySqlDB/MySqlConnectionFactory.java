package datastore.mySqlDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import config.ConfigReader;
import config.ConfigReaderDB;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Produce una connessione al database SQL.
 * */
public final class MySqlConnectionFactory {

    /**
     * Unica istanza della classe.
     * */
    private static MySqlConnectionFactory
        instance = new MySqlConnectionFactory();

    private String url;
    private String username;
    private String password;
    private String driver_class;

    /**
     * Configuration reader per i parametri
     * di connessione al DB.
     * */
    private ConfigReader reader =
            ConfigReaderDB.getInstance();

    /**
     * Costruttore della classe. Viene caricata la configurazione dal file
     * corrispettivo e viene controllata l'esistenza del driver.
     * */
    private MySqlConnectionFactory() {
        connectionConfig();

        try {
            Class.forName(driver_class);
        } catch (ClassNotFoundException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        }
    }

    /**
     * Crea la connessione con il database mysql.
     *
     * @return La connessione stabilita
     * */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        }
        return connection;
    }

    /**
     * Restituisce la connessione stabilita.
     *
     * @return Connessione stabilita
     * */
    public static Connection getConnection() {
        return instance.createConnection();
    }

    /**
     * Recupera la configurazione per la connessione.
     * */
    private void connectionConfig() {
        try {
            this.url = reader.getProperty("url");
            this.username = reader.getProperty("username");
            this.password = reader.getProperty("password");
            this.driver_class = reader.getProperty("driver_class");
        } catch (Exception e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        }
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
        ErrorHandler er = ErrorHandler.getIstance();
        er.processError(ex.getClass(), ex, t);
    }

}
