package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.ClienteTO;

/**
 * Business Object per l'entità Cliente.
 * */
public interface ClienteInt {
    /**
     * Inserisce un cliente.
     * @param cliente
     *      TO con i dati del cliente.
     * @return
     *      Risultato dell'operazione.
     * */
    Boolean addCliente(ClienteTO cliente);

    /**
     * Restituisce un cliente.
     * @param id
     *      Id del cliente.
     * @return
     *      TO con i dati del cliente.
     * */
    ClienteTO getCliente(String id);

    /**
     * Aggiorna i dati di un cliente.
     * @param cliente
     *      TO con i dati aggiornati del cliente.
     * @return
     *      Risultato dell'operazione.
     * */
    Boolean updateCliente(ClienteTO cliente);

    /**
     * Restituisce tutti i clienti.
     * @return
     *      Lista con tutti i clienti
     * */
    List<ClienteTO> getAll();

    /**
     * Controlla se esiste già il cliente nel sistema.
     * @param dati
     *      TO che contiene i dati del cliente da verificare.
     * @return
     *      Il risultato della verifica.
     * */
    Boolean existCheck(ClienteTO cliente);

    /**
     * Ricerca un cliente per codice fiscale.
     * @param codFiscale
     *      codiceFiscale del cliente.
     * @result
     *      Lista con tutti i clienti.
     * */
    List<ClienteTO> getClientiByCodFiscale(String codFiscale);
}
