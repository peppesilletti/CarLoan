package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.OperatoreTO;

/**
 * Business Object per l'entità Operatore.
 * */
public interface OperatoreInt {

    /**
     * Inserisce un'operatore.
     * @param operatore
     *      TO con i dati dell'operatore.
     * @return
     *      Il risultato dell'operazione.
     * */
    Boolean addOperatore(OperatoreTO operatore);

    /**
     * Restituisce un'operatore.
     * @param id
     *      Id dell'operatore da restituire.
     * @return
     *      TO con i dati dell'operatore.
     * */
    OperatoreTO getOperatore(String id);

    /**
     * Elimina un'operatore.
     * @param id
     *      Id dell'operatore da eliminare.
     * @return
     *      Il risultato dell'operazione.
     * */
    Boolean deleteOperatore(String id);

    /**
     * Modifica i dati di un'operatore.
     * @param operatore
     *      TO con i dati modificati dell'operatore.
     * @return
     *      Il risultato dell'operazione.
     * */
    Boolean updateOperatore(OperatoreTO operatore);

    /**
     * Restituisce tutte gli operatore.
     * @return
     *      Lista con tutte gli operatore.
     * */
    List<OperatoreTO> getAll();

    /**
     * Controlla se esiste già l'operatore nel sistema.
     * @param dati
     *      TO che contiene i dati dell'operatore da verificare.
     * @return
     *      Il risultato della verifica.
     * */
    Boolean existCheck(OperatoreTO dati);

    /**
     * Cerca un operatore per nome e cognome.
     * @param nome
     *      Nome dell' operatore
     * @param cognome
     *      Cognome dell'operatore
     * @return
     *      TO con gli operatori con il nome e cognome indicati.
     * */
    List<OperatoreTO> getByNomeCognome(String nome, String cognome);
}
