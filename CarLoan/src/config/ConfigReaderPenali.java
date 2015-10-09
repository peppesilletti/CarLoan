package config;

import errorsHandling.ErrorHandlerInt;

/**
 * Restituisce i valori delle penali applicate ai contratti.
 * */
public class ConfigReaderPenali extends ConfigReader{

    /**
     * Unica istanza della classe.
     * */
    private static ConfigReaderPenali instance =
            new ConfigReaderPenali();

    /**
     * Path del file di configurazione.
     * */
    private final String PATH = "config/penali.properties";

    /**
     * Costruttore privato della classe per realizzare
     * il singleton.
     * */
    ConfigReaderPenali() {
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
    public static ConfigReaderPenali getInstance() {
        return instance;
    }
}
