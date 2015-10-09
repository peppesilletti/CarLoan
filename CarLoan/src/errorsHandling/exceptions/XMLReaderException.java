package errorsHandling.exceptions;

/**
 * Eccezione per gli errori che si verificano nella
 * lettura di file xml.
 * */
public class XMLReaderException extends RuntimeException {

    public XMLReaderException() {
        // TODO Auto-generated constructor stub
    }

    public XMLReaderException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public XMLReaderException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public XMLReaderException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public XMLReaderException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
