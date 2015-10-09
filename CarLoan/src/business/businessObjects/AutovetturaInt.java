package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.AutovetturaTO;

/**
 * Business Object per l'entità Autovettura.
 * */
public interface AutovetturaInt {
    /**
     *Inserisce un'autovettura.
     *@param autovettura
     *      TO con i dati dell'autovettura.
     *@return
     *      Risultato dell'operazione.
     * */
    Boolean addAutovettura(AutovetturaTO autovettura);
    /**
     * Restituisce un'autovettura.
     * @param id
     *      Id dell'autovettura.
     * @return
     *      TO con i dati dell'autovettura.
     * */
    AutovetturaTO getAutovettura(
            String id);

    /**
     * Elimina un'autovettura dal datastore.
     * @param id
     *      Id dell'autovettura.
     * @return
     *      Il risultato dell'operazione.
     * */
    Boolean deleteAutovettura(String id);

    /**
     * Aggiorna i dati di un'autovettura.
     * @param autovettura
     *      TO con i dati aggiornati dell'autovettura.
     * @return
     *      Il risultato dell'operazione.
     **/
    Boolean updateAutovettura(AutovetturaTO autovettura);

    /**
     * Restituisce tutti le autovetture.
     * @return
     *      Lista con tutte le autovetture.
     * */
    List<AutovetturaTO> getAll();

    /**
     * Controlla se esiste già l'autovettura nel sistema.
     * @param dati
     *      TO che contiene i dati delle autovetture da verificare.
     * @return
     *      Il risultato della verifica.
     * */
    Boolean existCheck(AutovetturaTO dati);

    /**
     * Ricerca un'autovettura secondo targa, marca o classe autovettura.
     * @param targa
     *      Targa dell'autovettura da cercare
     * @param marca
     *      Marca dell'autovettura da cercare
     * @param classe
     *      Classe dell'autovettura da cercare
     * @return
     *      List con tutte le autovettura che corrispondono ai parametri.
     */
    List<AutovetturaTO> getAutovetturaParams(String targa,
            String marca, String classe);

    /**
     * Restituisce un elenco di autovetture disponibili nell'agenzia.
     * @param classe
     *      Classe dell'autovettura.
     * @param agenziaID
     *      Id dell'agenzia dove sono le autovetture.
     *
     * @return
     *      Lista delle agenzie.
     * */
    List<AutovetturaTO> getAutovettureDisponibili(String classeId,
            String agenziaId);

    /**
     * Verifica che un'autovettura non sia in manutenzione o noleggiata.
     * @param id
     *      Id dell'autovettura.
     * @result
     *      Risultato dell'operazione.
     * */
    Boolean checkAutoDisponibile(String id);
}


