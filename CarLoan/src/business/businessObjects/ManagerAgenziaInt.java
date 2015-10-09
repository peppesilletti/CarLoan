package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.ManagerAgenziaTO;

/**
 * Business Object per l'entit� manager Agenzia.
 * */
public interface ManagerAgenziaInt {
    /**
     * Inserisce un manager.
     * @param dati
     *      Dati del manager da inserire.
     * @return
     *      Risultato dell'operazione.
     * */
    Boolean addManagerAgenzia(ManagerAgenziaTO dati);

    /**
     * Ritorna i dati di un manager.
     * @param id
     *      Id del manager da ricercare.
     * @return
     *      TO con i dati del manager.
     * */
    ManagerAgenziaTO getManagerAgenzia(
            String id);

    /**
     * Elimina un manager.
     * @param id
     *      Id del manager da eliminare.
     * @return
     *      Il risultato dell'operazione.
     * */
    Boolean deleteManagerAgenzia(
            String username);

    /**
     * Modifica i dati di un manager.
     * @param manager
     *      TO con i dati modificati del manager.
     * @return
     *      Il risultato dell'operazione.
     * */
    Boolean updateManagerAgenzia(
            ManagerAgenziaTO manager);

    /**
     * Restituisce tutti i manager.
     * @return
     *      Lista con tutti i manager.
     * */
    List<ManagerAgenziaTO> getAll();

    /**
     * Controlla se esiste già il manager nel sistema.
     * @param dati
     *      TO che contiene i dati del manager da verificare.
     * @return
     *      Il risultato della verifica.
     * */
    Boolean existCheck(ManagerAgenziaTO dati);

    /**
     * Cerca un manager per nome e cognome.
     * @param nome
     *      Nome del manager
     * @parm cognome
     *      Cognome del manager
     * @return
     *      TO con i manager con il nome e cognome indicati.
     * */
    List<ManagerAgenziaTO> getByNomeCognome(String nome, String cognome);
}
