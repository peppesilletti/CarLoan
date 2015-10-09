package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.ConfigException;

/**
 * Classe per recuperare informazioni dai
 * file di configurazione.
 * */
public abstract class ConfigReader {

    protected Properties prop = new Properties();
    protected InputStream input = null;

    /**
     * Costruttore privato.
     * */
    protected ConfigReader() { }

    /**
    * Setta il percorso del file di configurazione.
    * @param fileName
    *      Path del file di configurazione
    *  @throws IOException
    *      Solleva un'eccezione se non trova
    *      il file di configurazione.
    **/

    protected final void setConfigPath(final String fileName)
            throws IOException {
        try {
            input = ConfigReader.class.getClassLoader().
                    getResourceAsStream(fileName);
            if (input == null) {
                    throw new IOException("Sorry, unable to find " + fileName);
            }

            prop.load(input);

        } catch (IOException ex) {
            handleExceptions(ex, ErrorHandlerInt.FATAL);
        } finally {
            if (input != null) {
                try {
                input.close();
            } catch (IOException e) {
                handleExceptions(e, ErrorHandlerInt.WARNING);
            }
            }
        }
    }

    /**
     * Restituisce il valore di una property
     * in base alla key passata come parametro.
     * @param key
     *      Key del valore.
     * @return
     *      Valore.
     * */
    public final String getProperty(final String key) {
        return prop.getProperty(key).trim();
    }

    /**
     * Gestisce le eccezioni DAO che si verificano.
     *
     * @param e
     *            eccezione verificatasi
     * @param t
     *            tipo di errore da sollevare
     * */
    protected final void handleExceptions(final Exception e, final Level t) {
        ConfigException ex = new ConfigException(e.getMessage());
        ErrorHandler er = ErrorHandler.getIstance();
        er.processError(this.getClass(), ex, t);
    }

}
