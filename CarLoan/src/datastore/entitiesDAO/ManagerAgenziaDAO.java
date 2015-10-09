package datastore.entitiesDAO;

import java.util.List;

import transferObjects.entitiesTO.ManagerAgenziaTO;

/**
 * Interfaccia che offre servizi per la memorizzazione e il recupero dei dati di
 * un manager agenzia dal datastore.
 * */
public interface ManagerAgenziaDAO extends DaoInt<ManagerAgenziaTO> {
    /**
     * Elimina tramite username.
     * @param username
     *      Username del manager da eliminare.
     * @return
     *      Risultato dell'operazione.
     * */
    Boolean deleteByUsername(String username);

    /**
     * Cerca un manager per nome e cognome.
     * @param nome
     *      Nome del manager
     * @param cognome
     *      Cognome del manager
     * @return
     *      TO con i manager con il nome e cognome indicati.
     * */
    List<ManagerAgenziaTO> getByNomeCognome(String nome, String cognome);
}
