package commands.gestioneContratti;

import transferObjects.entitiesTO.ContrattoTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneContratti;

import commands.Command;

/**
 * Comando per ricercare un contratto.
 * */
public class RicercareContratto implements Command {

    private GestioneContratti gc;
    private ComplexRequest<ContrattoTO> request;

    public RicercareContratto(RequestInt request) {
        gc = new GestioneContratti();
        this.request = (ComplexRequest<ContrattoTO>) request;
    }

    @Override
    public ResponseInt execute() {
        ComplexResponse<ContrattoTO> response =
                new ComplexResponse<ContrattoTO>();

        ContrattoTO to = request.getParameters().get(0);

        to = gc.ricercareDatiContratto(to.id);

        response.addParameter(to);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
