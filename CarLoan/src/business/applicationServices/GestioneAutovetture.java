package business.applicationServices;

import java.util.List;

import transferObjects.entitiesTO.AutovetturaTO;
import business.businessObjects.Autovettura;
import business.businessObjects.AutovetturaInt;
/**
 * Application Service per la gestione delle autovetture.
 * */
public class GestioneAutovetture {

    /**
     * BO Autovettura.
     * */
    private AutovetturaInt autovetturaBO;

    /**
     * Costruttore di default.
     * */
    public GestioneAutovetture() {
        autovetturaBO = new Autovettura();
    }

    /**
     * Inserisce un'autovettura nel sistema.
     * @param autovettura
     *      Transfer Object con i dati dell'autovettura
     * @return
     *      Il risultato dell'operazione.
     *
     * */
    public final Boolean inserireAutovettura(final AutovetturaTO autovettura) {
        Boolean result = autovetturaBO.existCheck(autovettura);

        if (!result) {
            autovetturaBO.addAutovettura(autovettura);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Restituisce tutte le autovetture presenti nel sistema.
     * @return
     *      Lista con i Transfer Object di tutte le agenzie.
     * */
    public final List<AutovetturaTO> riepilogoAutovetture() {
        return autovetturaBO.getAll();
    }

    /**Restituisce un autovettura tramite il suo id.
     * @param id
     *      Id dell'autovettura da ricercare.
     * @return
     *      TO con i dati dell'autovettura.
     */
    public final AutovetturaTO ricercareAutovettura(
            final String id) {
        return autovetturaBO.getAutovettura(id);
    }

    /**Elimina un'autovettura dal sistema.
     * @param id
     *      L'id dell'autovettura da eliminare.
     * @return
     *      Il risultato dell'operazione.
     */
    public final Boolean eliminareAutovettura(final String id) {
        if (autovetturaBO.checkAutoDisponibile(id)) {
            return false;
        } else {
            return autovetturaBO.deleteAutovettura(id);
        }

    }

    /**Aggiorna i dati di un'autovettura presente nel sistema.
     * @param autovettura
     *      TO con i dati dell'autovettura.
     * @return
     *      Il risultato dell'operazione.
     */
    public final Boolean modificareDatiAutovettura(
            final AutovetturaTO autovettura) {

        if (autovetturaBO.checkAutoDisponibile(autovettura.id)) {
            return false;
        } else {
            return autovetturaBO.updateAutovettura(autovettura);
        }
    }

    /**
     * Ricerca un'autovettura secondo targa, marca o classe autovettura.
     * @param targa
     *      Targa dell'autovettura da cercare
     * @param marca
     *      Marca dell'autovettura da cercare
     * @param classe
     *      Classe dell'autovettura da cercare
     * @return
     *      List con tutte le autovettura che corrispondono ai parametri.
     */
    public final List<AutovetturaTO> ricercareAutovetturaParams(
            final String targa, final String marca, final String classe) {

        return autovetturaBO.getAutovetturaParams(targa, marca, classe);
    }

    /**
     * Restituisce un elenco di autovetture disponibili nell'agenzia.
     * @param classe
     *      Classe dell'autovettura.
     * @param agenziaID
     *      Id dell'agenzia dove sono le autovetture.
     *
     * @return
     *      Lista delle agenzie.
     * */
    public List<AutovetturaTO> restituireAutovettureDisponibili(String classeId,
            String agenziaId) {
        return autovetturaBO.getAutovettureDisponibili(classeId, agenziaId);
    }

}
