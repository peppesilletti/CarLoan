package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.ExtraTO;

/**
 * Business Object per l'entità Extra.
 * */
public interface ExtraInt {

    /**
     * Inserisce un extra.
     * @param extra
     *      TO con i dati dell'extra.
     * @return
     *      Il risultato dell'operazione.
     * */
    Boolean addExtra(ExtraTO extra);

    /**
     * Restituisce un extra.
     * @param id
     *      Id dell'extra da restituire.
     * @return
     *      TO con i dati dell'extra.
     * */
    ExtraTO getExtra(String id);

    /**
     * Elimina un extra.
     * @param id
     *      Id dell'extra da eliminare.
     * @return
     *      Il risultato dell'operazione.
     * */
    Boolean deleteExtra(String id);

    /**
     * Modifica i dati di un extra.
     * @param extra
     *      TO con i dati modificati dell'extra.
     * @return
     *      Il risultato dell'operazione.
     * */
    Boolean updateExtra(ExtraTO extra);

    /**
     * Restituisce tutte gli extra.
     * @return
     *      Lista con tutte gli extra.
     * */
    List<ExtraTO> getAll();

    /**
     * Controlla se esiste già l'extra nel sistema.
     * @param dati
     *      TO che contiene i dati dell'extra da verificare.
     * @return
     *      Il risultato della verifica.
     * */
    Boolean existCheck(ExtraTO dati);
}
