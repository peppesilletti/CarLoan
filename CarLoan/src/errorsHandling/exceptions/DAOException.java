package errorsHandling.exceptions;

/**
 * Questa classe rappresenta una generica eccezione DAO. Dovrebbe inglobare ogni
 * eccezione del datastore, come SQL exceptions.
 * */
public class DAOException extends RuntimeException {

    public DAOException(String message) {
        super(message);
    }
}
