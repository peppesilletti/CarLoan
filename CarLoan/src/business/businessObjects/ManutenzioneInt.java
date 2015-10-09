package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.ManutenzioneTO;

/**
 * Business Object per l'entità Manutenzione.
 * */
public interface ManutenzioneInt {

    /**
     *Inserisce una manutenzione.
     *@param manutenzione
     *      TO con i dati della manutenzione.
     *@return
     *      Risultato dellaoperazione.
     * */
     Boolean  addManutenzione(ManutenzioneTO manutenzione);

    /**
     * Restituisce una manutenzione.
     * @param id
     *      Id della manutenzione.
     * @return
     *      TO con i dati della manutenzione.
     * */
    ManutenzioneTO getManutenzione(
            String id);
    /**
     * Elimina una manutenzione dal datastore.
     * @param id
     *      Id della manutenzione.
     * @return
     *      Il risultato dellaoperazione.
     * */
    Boolean deleteManutenzione(String id);

    /**
     * Aggiorna i dati di una manutenzione.
     * @param id
     *      TO con i dati aggiornati della manutenzione.
     * @return
     *      Il risultato dellaoperazione.
     **/
    Boolean updateManutenzione(ManutenzioneTO manutenzione);

    /**
     * Restituisce tutti le autovetture.
     * @return
     *      Lista con tutte le autovetture.
     * */
    List<ManutenzioneTO> getAll();

    /**
     * Cerca una manutenzione in base alla targa dell'autovettura.
     * @param targa
     *      Targa autovettura.
     * @return
     *      Lista delle manutenzioni che rispettano
     * */
    List<ManutenzioneTO> getManutenzioniAutovettura(String targa);
}
