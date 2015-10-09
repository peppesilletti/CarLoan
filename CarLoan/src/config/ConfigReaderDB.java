package config;

import errorsHandling.ErrorHandlerInt;

/**
 * Restituisce i parametri di connessione al
 * DB sql.
 * */
public class ConfigReaderDB extends ConfigReader {

    /**
     * Unica istanza della classe.
     * */
    private static ConfigReaderDB instance =
            new ConfigReaderDB();

    /**
     * Path del file di configurazione.
     * */
    private final String PATH = "config/mySqlDB.properties";

    /**
     * Costruttore privato della classe per realizzare
     * il singleton.
     * */
    ConfigReaderDB() {
        try {
            setConfigPath(PATH);
        } catch (Exception e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        }
    }

    /**
     * Restituisce l'unica istanza della classe.
     * @return
     *      Istanza della classe.
     * */
    public static ConfigReaderDB getInstance() {
        return instance;
    }

}
