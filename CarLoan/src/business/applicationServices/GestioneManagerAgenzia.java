package business.applicationServices;

import java.util.List;

import transferObjects.entitiesTO.ManagerAgenziaTO;
import utility.Security;
import business.businessObjects.ManagerAgenzia;
import business.businessObjects.ManagerAgenziaInt;

/**
 * Application service per la gestione degli account dei manager agenzia.
 * */
public class GestioneManagerAgenzia {

    /**
     * BO manager agenzia.
     * */
    private ManagerAgenziaInt managerAgenziaBO;

    /**
     * Costruttore di default.
     * */
    public GestioneManagerAgenzia() {
        managerAgenziaBO = new ManagerAgenzia();
    }

    /**
     * Inserisce un manager agenzia nel sistema.
     * @param managerAgenzia
     *      TO con i dati del manager agenzia
     * @return
     *      Il risultato dell'operazione.
     *
     * */
    public final Boolean inserireManagerAgenzia(
            final ManagerAgenziaTO managerAgenzia) {
        managerAgenzia.password = Security.cipher(managerAgenzia.password);

        Boolean result = managerAgenziaBO.existCheck(managerAgenzia);

        if (!result) {
            managerAgenziaBO.addManagerAgenzia(managerAgenzia);
            return true;
        } else {
            return false;
        }
    }

    /**Restituisce un manager agenzia tramite il suo id.
     * @param id
     *      Id del manager agenzia da ricercare.
     * @return
     *      TO con i dati del manager agenzia
     */
    public final ManagerAgenziaTO ricercareDatiManagerAgenzia(
            final String id) {
        return managerAgenziaBO.getManagerAgenzia(id);
    }

    /**Aggiorna i dati di un manager agenzia presente nel sistema.
     * @param managerAgenzia
     *      TO con i dati del manager agenzia
     * @return
     *      Il risultato del manager agenzia
     */
    public final Boolean modificareDatiManagerAgenzia(
            final ManagerAgenziaTO managerAgenzia) {
          return  managerAgenziaBO.updateManagerAgenzia(managerAgenzia);
    }

    /**
     * Elimina un manager agenzia dal sistema.
     * @param id
     *     id del manager agenzia.
     * @return
     *      Il risultato dell'operazione.
     *
     * */
    public final Boolean eliminareManagerAgenzia(final String username) {
        return managerAgenziaBO.deleteManagerAgenzia(username);
    }

    /**
     * Restituisce tutt i manager presenti nel sistema.
     * @return
     *      Lista con i manager
     * */
    public final List<ManagerAgenziaTO> riepilogoManagerAgenzia() {
        return managerAgenziaBO.getAll();
    }

    /**
     * Cerca un manager per nome e cognome.
     * @param nome
     *      Nome del manager
     * @parm cognome
     *      Cognome del manager
     * @return
     *      TO con i manager con il nome e cognome indicati.
     * */
    public final List<ManagerAgenziaTO> ricercareManagerByNomeCognome(
            String nome, String cognome) {

        return managerAgenziaBO.getByNomeCognome(nome, cognome);
    }
}
