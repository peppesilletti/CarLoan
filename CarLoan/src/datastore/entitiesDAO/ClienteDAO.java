package datastore.entitiesDAO;

import java.util.List;

import transferObjects.entitiesTO.ClienteTO;

/**
 * Interfaccia che offre servizi per la memorizzazione e il recupero dei dati di
 * un cliente dal datastore.
 * */
public interface ClienteDAO extends DaoInt<ClienteTO> {
    /**
     * Ricerca un cliente per codice fiscale.
     * @param codFiscale
     *      codiceFiscale del cliente.
     * @result
     *      Lista con tutti i clienti.
     * */
    List<ClienteTO> getClientiByCodFiscale(String codFiscale);
}
