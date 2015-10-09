package datastore.entitiesDAO;

import java.util.List;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.ExtraAgenziaTO;
import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.entitiesTO.OperatoreTO;

/**
 * Interfaccia che offre servizi per la memorizzazione e il recupero dei dati di
 * una agenzia dal datastore.
 * */
public interface AgenziaDAO extends DaoInt<AgenziaTO> {
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
    * Restituisce tutti gli operatori di un'agenzia.
    * @param id
    *       Id dell'agenzia.
    * @return
    *       Lista con gli operatori.
    * */
    List<OperatoreTO> getOperatori(String id);

    /**
     * Restituisce tutti gli extra disponibili in un'agenzia.
     * @param id
     *      Id dell'agenzia.
     * @return
     *      Lista degli extra.
     * */
    List<ExtraAgenziaTO> getExtraAgenzia(String id);

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
