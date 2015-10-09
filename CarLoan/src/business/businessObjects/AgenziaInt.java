package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.ExtraAgenziaTO;
import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.entitiesTO.OperatoreTO;
/**
 *Business Object per l'entità Agenzia.
 **/
public interface AgenziaInt {

    /**
     * Aggiunge un'agenzia.
     * @param agenzia
     *      TO con i dati dell'agenzia.
     * @return
     *      Risultato dell'operazione.
     * */
    Boolean addAgenzia(AgenziaTO agenzia);

    /**
     * Restituisce un'agenzia.
     * @param id
     *      Id dell'agenzia da restituire.
     * @return
     *      TO con i dati dell'agenzia.
     * */
    AgenziaTO getAgenziaById(String id);

    /**
     * Elimina un'agenzia.
     * @param id
     *      Id dell'agenzia da eliminare.
     * @return
     *      Il risultato dell'operazione.
     * */
    Boolean deleteAgenzia(String id);

    /**
     * Modifica i dati di un'agenzia.
     * @param agenzia
     *      TO con i dati modificati dell'agenzia.
     * @return
     *      Il risultato dell'operazione.
     * */
    Boolean updateAgenzia(AgenziaTO agenzia);

    /**
     * Restituisce tutte le agenzie.
     * @return
     *      Lista con tutte le agenzie nel datastore.
     * */
    List<AgenziaTO> getAll();

    /**
     * Restituisce il manager agenzia
     * appartenete all'agenzia indicata dall'id.
     * @param id
     *      Id dell'agenzia
     * @return
     *      Dati del manager di quell'agenzia.
     * */
    ManagerAgenziaTO getManagerAgenzia(String id);

    /**
     * Restituisce gli operatori dell'agenzia.
     * @param id
     *      Id dell'agenzia
     * @return
     *      Lista di operatori.
     * */
    List<OperatoreTO> getOperatoriAgenzia(String id);

    /**
     * Restituisce gli extra dell'agenzia.
     * @param id
     *      Id dell'agenzia
     * @return
     *      Lista di extra.
     * */
    List<ExtraAgenziaTO> getExtraAgenzia(String id);

    /**
     * Controlla se esiste già l'agenzia nel sistema.
     * @param dati
     *      TO che contiene i dati dell'agenzia da verificare.
     * @return
     *      Il risultato della verifica.
     * */
    Boolean existCheck(AgenziaTO dati);

    /**
     * Elimina un extra da un'agenzia.
     * @param id
     *      Id dell'agenzia.
     * @return
     *      Risultato dell'operazione.
     * */
    Boolean deleteExtraAgenzia(ExtraAgenziaTO id);

    /**
     * Inserisce un nuovo extra in un'agenzia.
     * @param extra
     *      TO con i dati dell'extra
     * @return
     *      Risultato dell'operazione.
     * */
    Boolean insertExtraAgenzia(ExtraAgenziaTO extra);

    /**
     * Restituisce tutte le agenzia di una città.
     * @param città
     *      Città delle agenzie da selezionare.
     * @return
     *      Lista delle agenzie di una città.
     * */
    List<AgenziaTO> getAgenziaByCittà(String città);

    /**
     * Verifica se ci sono contratto in corso presso l'agenzia indicata.
     * @param id
     *      Id dell'agenzia
     * @return
     *      Risultato della verifica.
     * */
    Boolean checkContrattiAgenzia(String id);

    /**
     * Verifica se ci sono autovetture assegnate all'agenzia indicata.
     * @param id
     *      Id dell'agenzia
     * @return
     *      Risultato della verifica.
     * */
    Boolean checAutovettureAgenzia(String id);
}
