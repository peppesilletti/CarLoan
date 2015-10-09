package business.applicationServices;

import java.util.List;

import transferObjects.entitiesTO.ContrattoTO;
import transferObjects.entitiesTO.TariffaTO;
import business.businessObjects.Contratto;
import business.businessObjects.Tariffa;
import business.businessObjects.TariffaInt;

/**
 * Application service per la gestione delle tariffe.
 * */
public class GestioneTariffe {

    /**
     * BO Tariffa.
     * */
    private TariffaInt tariffaBO;

    /**
     * Costruttore di default.
     * */
    public GestioneTariffe() {
        tariffaBO = new Tariffa();
    }

    /**
     * Inserisce una tariffa nel sistema.
     * @param tariffa
     *      TO con i dati della tariffa
     * @return
     *      Il risultato dell'operazione.
     *
     * */
    public final Boolean inserireTariffa(final TariffaTO
            tariffa) {
            return tariffaBO.addTariffa(tariffa);
    }


    /**Restituisce una tariffa tramite il suo id.
     * @param id
     *      Id della tariffa da ricercare.
     * @return
     *      TO con i dati della tariffa
     */
    public final TariffaTO ricercareDatiTariffa(final String id) {
        return tariffaBO.getTariffa(id);
    }

    /**Elimina una tariffa dal sistema.
     * @param id
     *      L'id della tariffa da eliminare.
     * @return
     *      Il risultato dell'operazione.
     */
    public final Boolean eliminareTariffa(final String id) {
        Contratto contratto = new Contratto();

        for(ContrattoTO c : contratto.getAll()) {
            if (c.tariffaId.equals(id)) {
                return false;
            }
        }

        return tariffaBO.deleteTariffa(id);
    }

    /**Aggiorna i dati di una tariffa presente nel sistema.
     * @param tariffa
     *      TO con i dati della tariffa
     * @return
     *      Il risultato dell'operazione.
     */
    public final Boolean modificareDatiTariffa(
            final TariffaTO tariffa) {

        Contratto contratto = new Contratto();

        for(ContrattoTO c : contratto.getAll()) {
            if (c.tariffaId.equals(tariffa.id)) {
                return false;
            }
        }

        return tariffaBO.updateTariffa(tariffa);
    }

    /**
     * Restituisce tutte le tariffe presenti nel sistema.
     * @return
     *      Lista con le tariffe.
     * */
    public final List<TariffaTO> riepilogoTariffe() {
        return tariffaBO.getAll();
    }

    /**
     * Ricerca una tariffa in base alla classe autovettura.
     * @param classe
     *      Nome della classe.
     * @return
     *      Lista di tariffe che corrispondono alla ricerca.
     * */
    public final List<TariffaTO> ricercareTariffaByClasse(String classe) {
        return tariffaBO.getTariffaByClasse(classe);
    }

}
