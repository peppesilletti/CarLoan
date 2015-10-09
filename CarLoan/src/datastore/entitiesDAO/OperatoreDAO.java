package datastore.entitiesDAO;

import java.util.List;

import transferObjects.entitiesTO.OperatoreTO;

/**
 * Interfaccia che offre servizi per la memorizzazione e il recupero dei dati di
 * un operatore dal datastore.
 * */
public interface OperatoreDAO extends DaoInt<OperatoreTO> {
    /**
     * Cerca un operatore per nome e cognome.
     * @param nome
     *      Nome dell' operatore
     * @param cognome
     *      Cognome dell'operatore
     * @return
     *      TO con gli operatori con il nome e cognome indicati.
     * */
    List<OperatoreTO> getByNomeCognome(String nome, String cognome);
}
