package datastore.entitiesDAO;

import java.util.List;

import transferObjects.entitiesTO.ManutenzioneTO;

/**
 * Interfaccia che offre servizi per la memorizzazione e il recupero dei dati di
 * una manutenzione dal datastore.
 * */
public interface ManutenzioneDAO extends DaoInt<ManutenzioneTO> {

    /**
     * Cerca una manutenzione in base alla targa dell'autovettura.
     * @param targa
     *      Targa autovettura.
     * @return
     *      Lista delle manutenzioni che rispettano
     * */
    List<ManutenzioneTO> getManutenzioniAutovettura(String targa);

}
