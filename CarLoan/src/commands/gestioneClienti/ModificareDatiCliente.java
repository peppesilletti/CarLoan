package commands.gestioneClienti;

import transferObjects.entitiesTO.ClienteTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneClienti;

import commands.Command;

/**
 * Classe che incapsula il comando di modifica di un cliente.
 * */
public class ModificareDatiCliente implements Command {

    /**
     * Application service per la gestione dei cliente.
     * */
    private GestioneClienti gc;

    private ComplexRequest<ClienteTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *           Richiesta con parametri.
     * */
    public ModificareDatiCliente(final RequestInt request) {
        gc = new GestioneClienti();
        this.request = (ComplexRequest<ClienteTO>) request;
    }

    @Override
    public final ResponseInt execute() {

        ClienteTO to =
                request.getParameters().get(0);

        Boolean result = gc.modificareDatiCliente(to);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
