package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.ContrattoTO;
import transferObjects.entitiesTO.ExtraTO;

/**
 * Business Object per l'entità contratto.
 * */
public interface ContrattoInt {

    /**
     * Crea un nuovo contratto.
     * @param contratto
     *      TO con i dati del contratto da inserire.
     * @return
     *      Risultato dell'operazione.
     * */
    Boolean addContratto(ContrattoTO contratto);

    /**
     * Restituisce i dati di un contratto.
     * @param id
     *      Id del contratto.
     * @return
     *      Ritorna un TO con i dati del contratto.
     * */
    ContrattoTO getDatiContratto(String id);

    /**
     * Chiude un contratto presente nel sistema.
     * @param id
     *      Id del contratto da chiudere.
     * @return
     *      Risultato dell'operazione.
     * */
    Boolean chiudereContratto(ContrattoTO id);

    /**
     * Restituisce tutti i contratti presenti nel sistema.
     * @return
     *      Lista con tutti i contratti presenti nel sistema.
     * */
   List<ContrattoTO> getAll();

   /**
    * Ritorna il successivo id del contratto da inserire.
    * */
   ContrattoTO nextNumeroContratto();

   /**
    * Restituisce una lista con tutti gli extra del contratto
    * @param id
    *      Id del contratto
    * @return
    *      Lista di extra scelti per il contratto
    * */
   List<ExtraTO> getExtraContratti();

}

