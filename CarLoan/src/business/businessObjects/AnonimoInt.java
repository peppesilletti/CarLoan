package business.businessObjects;

import transferObjects.entitiesTO.AnonimoTO;

/**
 * Business Object per l'entit� Utente.
 * */
public interface AnonimoInt {
    /**
     * Permette ad un utente anonimo di autenticarsi al sistema.
     *
     * @param dati
     *            Dati dell'utente anonimo.
     * @return Risultato dell'operazione eseguita.
     * */
    Boolean autenticazione(AnonimoTO dati);

    /**
     * Restituisce il tipo dell'utente anonimo.
     * @param username
     *      Username dell'utente.
     * @return
     *      Tipo dell'utente.
     * */
    AnonimoTO getUserType(String username);
}
