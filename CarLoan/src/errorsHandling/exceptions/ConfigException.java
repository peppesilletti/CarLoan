package errorsHandling.exceptions;

/**
 * Eccezione per gli errori di lettura nei file di configurazione.
 * */
public class ConfigException extends RuntimeException {

    public ConfigException() {
        // TODO Auto-generated constructor stub
    }

    public ConfigException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public ConfigException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public ConfigException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}