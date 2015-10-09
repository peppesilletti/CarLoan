package commands.gestioneClienti;

import java.util.List;

import transferObjects.entitiesTO.ClienteTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneClienti;

import commands.Command;

/**
 * Comando per ricercare un cliente in base al codice fiscale.
 * */
public class RicercaClientiCodFiscale implements Command {

    /**
     * Application service per la gestione di un codice fiscale.
     * */
    private GestioneClienti gc;

    private ComplexRequest<ClienteTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Request con parametri.
     * */
    public RicercaClientiCodFiscale(
            final RequestInt request) {
        gc = new GestioneClienti();
        this.request = (ComplexRequest<ClienteTO>) request;
    }


    @Override
    public final ResponseInt execute() {
        ComplexResponse<ClienteTO> response =
                new ComplexResponse<ClienteTO>();

        ClienteTO to =
                request.getParameters().get(0);

        List<ClienteTO> list = gc.ricercaClientiCodFiscale(to.codiceFiscale);

        response.addParameterList(list);

        return response;
    }


    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
