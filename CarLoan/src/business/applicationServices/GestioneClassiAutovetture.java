package business.applicationServices;

import java.util.List;

import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.entitiesTO.ClasseAutovetturaTO;
import transferObjects.entitiesTO.TariffaTO;
import business.businessObjects.Autovettura;
import business.businessObjects.ClasseAutovettura;
import business.businessObjects.ClasseAutovetturaInt;
import business.businessObjects.Tariffa;

/**
 * Application Service per la gestione delle classi autovetture.
 * */
public class GestioneClassiAutovetture {

    /**
     * BO Classe Autovettura.
     * */
    private ClasseAutovetturaInt classeAutovetturaBO;

    /**
     * Costruttore di default.
     * */
    public GestioneClassiAutovetture() {
        classeAutovetturaBO = new ClasseAutovettura();
    }

    /**
     * Inserisce una classeAutovettura nel sistema.
     * @param classeAutovettura
     *      TO con i dati della classeAutovettura
     * @return
     *      Il risultato dell'operazione.
     *
     * */
    public final Boolean inserireClasseAutovettura(final ClasseAutovetturaTO
            classeAutovettura) {

        Boolean result = classeAutovetturaBO.existCheck(classeAutovettura);

        if (!result) {
            classeAutovetturaBO.addClasseAutovettura(classeAutovettura);
            return true;
        } else {
            return false;
        }
    }


    /**Restituisce una classeAutovettura tramite il suo id.
     * @param id
     *      Id della classeAutovettura da ricercare.
     * @return
     *      TO con i dati della classeAutovettura
     */
    public final ClasseAutovetturaTO ricercareDatiClasseAutovettura(
            final String id) {
        return classeAutovetturaBO.getClasseAutovettura(id);
    }

    /**Elimina una classeAutovettura dal sistema.
     * @param id
     *      L'id della classeAutovettura da eliminare.
     * @return
     *      Il risultato dell'operazione.
     */
    public final Boolean eliminareClasseAutovettura(final String id) {

        Boolean result = true;

        Tariffa tariffa = new Tariffa();
        Autovettura autovettura = new Autovettura();

        List<AutovetturaTO> autovetture = autovettura.getAll();
        List<TariffaTO> tariffe = tariffa.getAll();

        for (TariffaTO t : tariffe) {
            if (t.classeAutovetturaId.equals(id)) {
                result = false;
            }
        }

        for (AutovetturaTO t : autovetture) {
            if (t.classeAutovetturaId.equals(id)) {
                result = false;
            }
        }

        if (result) {
            return classeAutovetturaBO.deleteClasseAutovettura(id);
        } else {
            return false;
        }
    }

    /**Aggiorna i dati di una classeAutovettura presente nel sistema.
     * @param classeAutovettura
     *      TO con i dati della classeAutovettura
     * @return
     *      Il risultato dell'operazione.
     */
    public final Boolean modificareDatiClasseAutovettura(
            final ClasseAutovetturaTO classeAutovettura) {
        Boolean result = true;

        Tariffa tariffa = new Tariffa();
        Autovettura autovettura = new Autovettura();

        List<AutovetturaTO> autovetture = autovettura.getAll();
        List<TariffaTO> tariffe = tariffa.getAll();

        for (TariffaTO t : tariffe) {
            if (t.classeAutovetturaId.equals(classeAutovettura.id)) {
                result = false;
            }
        }

        for (AutovetturaTO t : autovetture) {
            if (t.classeAutovetturaId.equals(classeAutovettura.id)) {
                result = false;
            }
        }

        if (result) {
            return classeAutovetturaBO.updateClasseAutovettura(classeAutovettura);
        } else {
            return false;
        }
    }

    /**
     * Restituisce tutte le classi autovettura presenti nel sistema.
     * @return
     *      Lista con le classi autovettura.
     * */
    public final List<ClasseAutovetturaTO> riepilogoClassiAutovettura() {
        return classeAutovetturaBO.getAll();
    }
}
