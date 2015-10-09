package config;

import errorsHandling.ErrorHandlerInt;

/**
 * Restituisce i messaggi di errore.
 * */
public class ConfigReaderErrorMessages extends ConfigReader {
    /**
     * Unica istanza della classe.
     * */
    private static ConfigReaderErrorMessages instance = 
            new ConfigReaderErrorMessages();
    
    /**
     * Path del file di configurazione.
     * */
    private static final String PATH = "config/errors.properties";
    
    /**
     * Costruttore privato della classe per realizzare
     * il singleton.
     * */
    ConfigReaderErrorMessages() {
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
    public static ConfigReaderErrorMessages getInstance() {
        return instance;
    }

}
