package business.applicationServices;

import java.util.List;

import transferObjects.entitiesTO.ClienteTO;
import business.businessObjects.Cliente;
import business.businessObjects.ClienteInt;

/**
 * Application service per la gestione dei clienti.
 * */
public class GestioneClienti {

    /**
     * BO cliente.
     * */
    private ClienteInt clienteBO;

    /**
     * Costruttore di default.
     * */
    public GestioneClienti() {
        clienteBO = new Cliente();
    }

    /**
     * Inserisce un cliente nel sistema.
     * @param cliente
     *      TO con i dati del cliente
     * @return
     *      Il risultato dell'operazione.
     *
     * */
    public final Boolean inserireCliente(final ClienteTO cliente) {

        Boolean result = clienteBO.existCheck(cliente);

        if (!result) {
            clienteBO.addCliente(cliente);
            return true;
        } else {
            return false;
        }
    }

    /**Restituisce un cliente tramite il suo id.
     * @param id
     *      Id del cliente da ricercare.
     * @return
     *      TO con i dati del cliente
     */
    public final ClienteTO ricercareDatiCliente(final String id) {
        return clienteBO.getCliente(id);
    }

    /**Aggiorna i dati di un cliente presente nel sistema.
     * @param cliente
     *      TO con i dati del cliente
     * @return
     *      Il risultato del cliente
     */
    public final Boolean modificareDatiCliente(final ClienteTO cliente) {
        return clienteBO.updateCliente(cliente);
    }

    /**
     * Restituisce tutte i clienti presenti nel sistema.
     * @return
     *      Lista con i clienti.
     * */
    public final List<ClienteTO> riepilogoClienti() {
        return clienteBO.getAll();
    }

    /**
     * Ricerca un cliente per codice fiscale.
     * @param codFiscale
     *      codiceFiscale del cliente.
     * @result
     *      Lista con tutti i clienti.
     * */
    public List<ClienteTO> ricercaClientiCodFiscale(String codFiscale) {
        return clienteBO.getClientiByCodFiscale(codFiscale);
    }
}
