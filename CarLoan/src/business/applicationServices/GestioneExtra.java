package business.applicationServices;

import java.util.List;

import transferObjects.entitiesTO.ExtraTO;
import business.businessObjects.Contratto;
import business.businessObjects.Extra;
import business.businessObjects.ExtraInt;

/**
 * Application service per la gestione degli extra.
 * */
public class GestioneExtra {
    /**
     * BO extra.
     * */
    private ExtraInt extraBO;

    /**
     * Costruttore della classe.
     * */
    public GestioneExtra() {
        extraBO = new Extra();
    }

    /**
     * Invoca il metodo ononimo nel business object per inserire i dati di un
     * extra.
     *
     * @param extra
     *            Transfer Object che contiene dati dell'extra
     * @return Risultato dell'operazione.
     * */
    public final Boolean inserireExtra(final ExtraTO extra) {
        Boolean result = extraBO.existCheck(extra);

        if (!result) {
            extraBO.addExtra(extra);
            return true;
        } else {
            return false;
        }
    }


    /** Ricerca un extra per id.
     * @param id
     *      Id dell'extra.
     * @return
     *      Transfer object con i dati dell'extra.
     */
    public final ExtraTO ricercareDatiExtra(final String id) {
        return extraBO.getExtra(id);
    }

    /**
     * Elimina un extra.
     * @param id
     *      Id dell'extra.
     * @return
     *      Risultato dell'operazione.
     * */
    public final Boolean eliminareExtra(final String id) {
        Contratto contratto = new Contratto();

        for(ExtraTO c : contratto.getExtraContratti()) {
            if (c.id.equals(id)) {
                return false;
            }
        }
        return extraBO.deleteExtra(id);
    }


    /**Aggiorna i dati di un'extra.
     * @param extra
     *      Transfer object con i dati aggiornati dell'extra.
     * @return
     *      Risultato dell'operazione.
     */
    public final Boolean modificareDatiExtra(
            final ExtraTO extra) {

        Contratto contratto = new Contratto();

        for(ExtraTO c : contratto.getExtraContratti()) {
            if (c.id.equals(extra.id)) {
                return false;
            }
        }
        return extraBO.updateExtra(extra);
    }

    /**
     * Restituisce tutti gli extra.
     * @return
     *      Lista di extra.
     * */
    public final List<ExtraTO> riepilogoExtra() {
        return extraBO.getAll();
    }


}
