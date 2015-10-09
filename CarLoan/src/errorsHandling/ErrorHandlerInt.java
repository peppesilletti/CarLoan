package errorsHandling;

import java.util.logging.Level;

/**
 * Interfaccia per gestire le situazioni di errore.
 * */
public interface ErrorHandlerInt {

    /**
     * Errore grave che termina il programma.
     * */
    Level FATAL = Level.SEVERE;
    
    /**
     * Errore grave che non termina il programma.
     * */
    Level WARNING = Level.WARNING;
    
    /**
     * Errore di bassa importanza.
     * Non viene mostrata alcun messaggio.
     * */
    Level NO_MESSAGE = Level.INFO;
    
   
    /**
     * Logga il messaggio.
     * @param c
     *      Classe che ha sollevato l'eccezione
     * @param exception
     *      Eccezione sollevata
     * @param level
     *      Livello di gravità dell'errore.
     * */
    void processError(Class c, Throwable exception,
            Level level);
    
}
