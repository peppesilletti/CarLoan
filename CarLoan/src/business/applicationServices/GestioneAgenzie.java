package business.applicationServices;

import java.util.List;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.ExtraAgenziaTO;
import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.entitiesTO.OperatoreTO;
import business.businessObjects.Agenzia;
import business.businessObjects.AgenziaInt;

/**
 * Application Service per la gestione delle agenzie.
 * */
public class GestioneAgenzie {

    /**
     * BO Agenzia.
     * */
    private AgenziaInt agenziaBO;

    /**
     * Costruttore di default.
     * */
    public GestioneAgenzie() {
        agenziaBO = new Agenzia();
    }

    /**
     * Inserisce un'agenzia nel sistema.
     * @param agenzia
     *      Transfer Object con i dati dell'agenzia
     * @return
     *      Il risultato dell'operazione.
     *
     * */
    public final Boolean inserireAgenzia(final AgenziaTO agenzia) {

        Boolean result = agenziaBO.existCheck(agenzia);
        System.out.println(result);
        if (!result) {
            agenziaBO.addAgenzia(agenzia);
            return true;
        } else {
            return false;
        }
    }


    /**Restituisce i dati di uniagenzia cercandola tramite il suo id.
     * @param id
     *      Id dell'agenzia da ricercare.
     * @return
     *      Transfer Object con i dati dell'agenzia trovata.
     */
    public final AgenziaTO ricercareDatiAgenzia(final String id) {
        return agenziaBO.getAgenziaById(id);
    }

    /**Elimina un'agenzia dal sistema.
     * @param id
     *      L'id dell'agenzia da eliminare.
     * @return
     *      Il risultato dell'operazione.
     */
    public final Boolean eliminareAgenzia(final String id) {

        if (agenziaBO.checAutovettureAgenzia(id) ||
                agenziaBO.checkContrattiAgenzia(id)) {
            return false;
        } else {
            return agenziaBO.deleteAgenzia(id);
        }

    }

    /**Aggiorna i dati di un'agenzia presente nel sistema.
     * @param agenzia
     *      Transfer Object con i dati dell'agenzia.
     * @return
     *      Il risultato dell'operazione.
     */
    public final Boolean modificareDatiAgenzia(final AgenziaTO agenzia) {

        if (agenziaBO.checAutovettureAgenzia(agenzia.id)
                || agenziaBO.checkContrattiAgenzia(agenzia.id)) {
            return false;
        } else {
            return agenziaBO.updateAgenzia(agenzia);
        }
    }

    /**
     * Restituisce tutte le agenzie presenti nel sistema.
     * @return
     *      Lista con i Transfer Object di tutte le agenzie
     *      presenti nel sistema.
     * */
    public final List<AgenziaTO> riepilogoAgenzie() {
        return agenziaBO.getAll();
    }

    /**Restituisce il manager appartenente ad una agenzia.
     *
     * @param id
     *      id dell'agenzia.
     * @return
     *      Transfer Object con le informazioni sul mananager.
     */
    public final ManagerAgenziaTO restituisciManagerAgenzia(final String id) {
        return agenziaBO.getManagerAgenzia(id);
    }

    /**Restituisce gli operatori appartenente ad un'agenzia.
    *
    * @param id
    *      id dell'agenzia.
    * @return
    *      Lista dei Transfer Object contententi le informazioni
    *      sugli operatori appartenenti all'agenzia.
    */
   public final List<OperatoreTO> restituisciOperatoriAgenzia(final String id) {
       return agenziaBO.getOperatoriAgenzia(id);
   }

   /**Restituisce gli extra disponibili presso un'agenzia.
   *
   * @param id
   *      id dell'agenzia.
   * @return
   *      Lista dei Transfer Objectd degli extra disponibili
   *      presso un'agenzia.
   */
  public final List<ExtraAgenziaTO> restituireExtraAgenzia(final String id) {
      return agenziaBO.getExtraAgenzia(id);
  }

  /**
   * Elimina un extra da un'agenzia.
   * @param id
   *      Id dell'agenzia.
   * @return
   *      Risultato dell'operazione.
   * */
  public final Boolean eliminareExtraAgenzia(final ExtraAgenziaTO id) {
      return agenziaBO.deleteExtraAgenzia(id);
  }

  /**
   * Inserisce un nuovo extra in un'agenzia.
   * @param extra
   *      TO con i dati dell'extra
   * @return
   *      Risultato dell'operazione.
   * */
  public final Boolean inserireExtraAgenzia(final ExtraAgenziaTO extra) {
      return agenziaBO.insertExtraAgenzia(extra);
  }

  /**
   * Restituisce tutte le agenzia di una città.
   * @param città
   *      Città delle agenzie da selezionare.
   * @return
   *      Lista delle agenzie di una città.
   * */
  public final List<AgenziaTO> ricercareAgenzieCittà(final String città) {
      return agenziaBO.getAgenziaByCittà(città);
  }

}
