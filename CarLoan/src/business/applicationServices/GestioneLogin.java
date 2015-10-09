package business.applicationServices;

import transferObjects.entitiesTO.AnonimoTO;
import utility.Security;
import business.businessObjects.Anonimo;
import business.businessObjects.AnonimoInt;

/**
 * Classe che gestisce il caso d'uso del login di un utente anonimo.
 * */
public class GestioneLogin {

    /**
     * Business Object che fornisce i servizi riguardo un utente anonimo.
     * */
    private AnonimoInt utente;

    /**
	 *
	 * */

    /**
     * Costruttore della classe.
     * */
    public GestioneLogin() {
        utente = new Anonimo();
    }

    /**
     * Gestisce l'autenticazione di un utente anonimo.
     *
     * @param dati
     *            Contiene dati dell'utente che sta effettuando l'autenticazione
     * @return Ritorna true se l'utente � autenticato correttamente.
     * */
    public final Boolean autenticazione(final AnonimoTO dati) {

        dati.password = Security.cipher(dati.password);

        Boolean response = utente.autenticazione(dati);

        return response;
    }

    /**
     * Seleziona il tipo di utente dal datastore.
     * */
    public final AnonimoTO getTipoUtente(final String username) {
        return utente.getUserType(username);
    }

}
