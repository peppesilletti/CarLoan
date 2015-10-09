package errorsHandling.exceptions;

/**
 * Questa classe rappresenta l'ecceziona sollevata da un problema di stampa.
 * */
public class PrintException extends RuntimeException {
    public PrintException(String message) {
        super(message);
    }
}
