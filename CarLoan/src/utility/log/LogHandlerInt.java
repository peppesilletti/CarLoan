package utility.log;

/**
 * Interfaccia che offre servizi per la gestione del log degli errori.
 * */
public interface LogHandlerInt {
    /**
     * Salva un errore nel file di log.
     * 
     * @param error
     *            Errore da salvare
     * */
    void log(String error);
}
