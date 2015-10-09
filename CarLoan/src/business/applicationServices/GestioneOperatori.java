package business.applicationServices;

import java.util.List;

import transferObjects.entitiesTO.OperatoreTO;
import utility.Security;
import business.businessObjects.Operatore;
import business.businessObjects.OperatoreInt;

/**
 * Application Service per la gestione degli account Operatore.
 * */
public class GestioneOperatori {

    /**
     * BO operatore.
     * */
    private OperatoreInt operatoreBO;

    /**
     * Costruttore della classe.
     * */
    public GestioneOperatori() {
        operatoreBO = new Operatore();
    }

    /**
     * Invoca il metodo ononimo nel business object per inserire i dati di un
     * operatore.
     *
     * @param operatore
     *            Transfer Object che contiene dati dell'operatore
     * @return Risultato dell'operazione.
     * */
    public final Boolean inserireAccountOperatore(final OperatoreTO operatore) {
        operatore.password = Security.cipher(operatore.password);

        Boolean result = operatoreBO.existCheck(operatore);

        if (!result) {
            operatoreBO.addOperatore(operatore);
            return true;
        } else {
            return false;
        }
    }


    /** Ricerca un operatore per id.
     * @param id
     *      Id dell'operatore.
     * @return
     *      Transfer object con i dati dell'operatore.
     */
    public final OperatoreTO ricercareDatiOperatore(final String id) {
        return operatoreBO.getOperatore(id);
    }

    /**
     * Elimina un operatore.
     * @param id
     *      Id dell'operatore.
     * @return
     *      Risultato dell'operazione.
     * */
    public final Boolean eliminareAccountOperatore(final String username) {
        return operatoreBO.deleteOperatore(username);
    }


    /**Aggiorna i dati di un'operatore.
     * @param operatore
     *      Transfer object con i dati aggiornati dell'operatore.
     * @return
     *      Risultato dell'operazione.
     */
    public final Boolean modificareDatiAccountOperatore(
            final OperatoreTO operatore) {
            return operatoreBO.updateOperatore(operatore);
    }

    /**
     * Restituisce tutti gli operatori.
     * @return
     *      Lista di operatori.
     * */
    public final List<OperatoreTO> riepilogoOperatori() {
        return operatoreBO.getAll();
    }

    /**
     * Cerca un operatore per nome e cognome.
     * @param nome
     *      Nome dell' operatore
     * @param cognome
     *      Cognome dell'operatore
     * @return
     *      TO con gli operatori con il nome e cognome indicati.
     * */
    public List<OperatoreTO> ricercaOperatoreNomeCognome(String nome, String cognome) {
        return operatoreBO.getByNomeCognome(nome, cognome);
    }

}
