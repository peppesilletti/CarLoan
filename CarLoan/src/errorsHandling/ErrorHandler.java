package errorsHandling;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import config.ConfigReader;
import config.ConfigReaderErrorMessages;

/**
 * Implementazione dell'interfaccia {@link ErrorHandlerInt}.
 * */
public final class ErrorHandler implements ErrorHandlerInt {

    /**
     * Unica istanza dell'error handler.
     * */
    private static ErrorHandler istance = new ErrorHandler();

    private Logger logger;
    private FileHandler handler;
    private SimpleFormatter formatter;
    private ConfigReader reader =
            ConfigReaderErrorMessages.getInstance();

    /**
     * Restituisce l'istanza della classe.
     *
     * @return L'istanza della classe.
     * */
    public static ErrorHandler getIstance() {
        return istance;
    }

    /**
     * Costruttore privato per realizzare il singleton.
     * */
    private ErrorHandler() {
        if (handler == null) {
            try {
                handler = new FileHandler("Log.log");
                handler.setLevel(Level.ALL);
                formatter = new SimpleFormatter();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Impossibile"
                        + "creare il file di log");
            }
        }
    }

    /**
     * Recuperare le informazioni sull'errore.
     * @param c
     *      Classe il cui oggetto ha rilevato un problema
     * @param exception
     *      Eccezione sollevata
     * @param level
     *      Livello di gravità del problema
     * */
    @Override
    public void processError(final Class c, final Throwable exception,
            final Level level) {
        logger = Logger.getLogger(c.getName());
        handler.setFormatter(formatter);
        logger.addHandler(handler);
        logger.log(level, exception.getMessage(), exception);
        if (!level.equals(ErrorHandlerInt.NO_MESSAGE)) {
            showErrorMessage(getMessage(c.getName()), level);
        }
    }

    /**
     * Mostra un messaggio di errore.
     *
     * @param message
     *            il messaggio di errore da salvare nel file di log.
     * @param level
     *        Livello di gravitò dell'errore.
     */
    private void showErrorMessage(final String message, final Level level) {
        ShowErrors.showError(level, message);
    }

    /**
     * Fornisce il testo del messaggio da visualizzare.
     * @param key la chiave da cui recuperare il messaggio.
     * @return il messaggio da visualizzare.
     */
    private String getMessage(final String key) {
        String message = reader.getProperty(key);
        return message;
    }
}
