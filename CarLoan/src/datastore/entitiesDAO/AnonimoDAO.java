package datastore.entitiesDAO;

import transferObjects.entitiesTO.AnonimoTO;

/**
 * Data access object per l'entità anonimo.
 * */
public interface AnonimoDAO {
    /**
     * Preleva i dati dell'utente che si sta autenticando.
     *
     * @param dati
     *            Contiene le credenziali dell'utente.
     * @return Dati dell'utente autenticato
     * */
    Boolean accountExsist(AnonimoTO dati);

    /**
     * Restituisce un account utente dal suo username.
     * @param username
     *      Username dell'utente.
     * @return
     *      Transfer Object con i dati dell'utenteh
     * */
    AnonimoTO searchByUsername(String username);
}
