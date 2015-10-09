package datastore.entitiesDAO;

import java.util.List;

import transferObjects.entitiesTO.ContrattoTO;
import transferObjects.entitiesTO.ExtraTO;

/**
 * Interfaccia che offre servizi per la memorizzazione e il recupero dei dati di
 * un manager agenzia dal datastore.
 * */
public interface ContrattoDAO extends DaoInt<ContrattoTO> {

    /**
     * Restituisce il numero di contratto successivo.
     * @return
     *      Numero contratto successivo.
     * */
    ContrattoTO getNextNumeroContratto();

    /**
     * Restituisce una lista con tutti gli extra del contratto
     * @param id
     *      Id del contratto
     * @return
     *      Lista di extra scelti per il contratto
     * */
    List<ExtraTO> getExtraContratti();

}
