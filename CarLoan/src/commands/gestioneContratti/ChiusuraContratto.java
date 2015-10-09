package commands.gestioneContratti;

import transferObjects.entitiesTO.ContrattoTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneContratti;

import commands.Command;

/**
 * Comando per chiudere un contratto.
 * */
public class ChiusuraContratto implements Command {

    private GestioneContratti gc;
    private ComplexRequest<ContrattoTO> request;

    public ChiusuraContratto(RequestInt request) {
        gc = new GestioneContratti();
        this.request = (ComplexRequest<ContrattoTO>) request;
    }

    @Override
    public ResponseInt execute() {
        ContrattoTO to = request.getParameters().get(0);

        Boolean response = gc.chiudereContratto(to);

        return new SimpleResponse(response);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
