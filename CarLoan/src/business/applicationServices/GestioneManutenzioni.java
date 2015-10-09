package business.applicationServices;

import java.util.List;

import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.entitiesTO.ManutenzioneTO;
import utility.DateUtil;
import business.businessObjects.Autovettura;
import business.businessObjects.Manutenzione;
import business.businessObjects.ManutenzioneInt;

/**
 * Application service per la gestione delle manutenzioni.
 * */
public class GestioneManutenzioni {

    /**
     * BO Manutenzione.
     * */
    private ManutenzioneInt manutenzioneBO;

    /**
     * Costruttore di default.
     * */
    public GestioneManutenzioni() {
        manutenzioneBO = new Manutenzione();
    }

    /**
     * Inserisce una manutenzione nel sistema.
     * @param manutenzione
     *      TO con i dati della manutenzione
     * @return
     *      Il risultato dell'operazione.
     *
     * */
    public final Boolean inserireManutenzione(final ManutenzioneTO
            manutenzione) {

        Autovettura auto = new Autovettura();
        AutovetturaTO autoTO = auto.getAutovettura(manutenzione.autovetturaId);

        List<ManutenzioneTO> manutenzioni =
                ricercaManutenzioniAutovettura(autoTO.targa);

        if (manutenzioni != null) {
            for (ManutenzioneTO m : manutenzioni) {
                if (m.dataFine == null) {
                    return false;
                }
            }
        }

        return manutenzioneBO.addManutenzione(manutenzione);
    }


    /**Restituisce una manutenzione tramite il suo id.
     * @param id
     *      Id della manutenzione da ricercare.
     * @return
     *      TO con i dati della manutenzione
     */
    public final ManutenzioneTO ricercareDatiManutenzione(final String id) {
        return manutenzioneBO.getManutenzione(id);
    }

    /**Elimina una manutenzione dal sistema.
     * @param id
     *      L'id della manutenzione da eliminare.
     * @return
     *      Il risultato dell'operazione.
     */
    public final Boolean eliminareManutenzione(final String id) {
        return manutenzioneBO.deleteManutenzione(id);
    }

    /**Aggiorna i dati di una manutenzione presente nel sistema.
     * @param manutenzione
     *      TO con i dati della manutenzione
     * @return
     *      Il risultato dell'operazione.
     */
    public final Boolean modificareDatiManutenzione(
            final ManutenzioneTO manutenzione) {

        String dataInizio = manutenzione.dataInizio;
        String dataFine = manutenzione.dataFine;

        if (DateUtil.compareDates(dataInizio, dataFine) == 1) {
            return false;
        } else {
            return manutenzioneBO.updateManutenzione(manutenzione);
        }
    }

    /**
     * Restituisce tutte le manutenzioni presenti nel sistema.
     * @return
     *      Lista con le manutenzioni.
     * */
    public final List<ManutenzioneTO> riepilogoManutenzioni() {
        return manutenzioneBO.getAll();
    }

    /**
     * Cerca una manutenzione in base alla targa dell'autovettura.
     * @param targa
     *      Targa autovettura.
     * @return
     *      Lista delle manutenzioni che rispettano
     * */
    public List<ManutenzioneTO> ricercaManutenzioniAutovettura(String targa) {
        return manutenzioneBO.getManutenzioniAutovettura(targa);
    }

}
