package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.TariffaTO;

/**
 * Business Object per l'entità Tariffa.
 * */
public interface TariffaInt {


    /**
     *Inserisce una tariffa.
     *@param tariffa
     *      TO con i dati della tariffa.
     *@return
     *      Risultato dell'operazione.
     * */
    Boolean addTariffa(TariffaTO tariffa);

    /**
     * Restituisce una tariffa.
     * @param id
     *      Id della tariffa.
     * @return
     *      TO con i dati della tariffa.
     * */
    TariffaTO getTariffa(String id);

    /**
     * Elimina una tariffa.
     * @param id
     *      Id della tariffa.
     * @return
     *      Il risultato dell'operazione.
     * */
    Boolean deleteTariffa(String id);

    /**
     * Aggiorna i dati di una tariffa.
     * @param tariffa
     *      TO con i dati aggiornati della tariffa.
     * @return
     *      Il risultato dellaoperazione.
     **/
    Boolean updateTariffa(TariffaTO tariffa);

    /**
     * Restituisce tutti le tariffe.
     * @return
     *      Lista con tutte le tariffe.
     * */
    List<TariffaTO> getAll();

    /**
     * Ricerca una tariffa in base alla classe autovettura.
     * @param classe
     *      Nome della classe.
     * @return
     *      Lista di tariffe che corrispondono alla ricerca.
     * */
    List<TariffaTO> getTariffaByClasse(String classe);
}
