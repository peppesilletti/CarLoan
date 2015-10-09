package commands.gestioneContratti;

import transferObjects.entitiesTO.ContrattoTO;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneContratti;

import commands.Command;

/**
 * Comando che restituisce il prossimo numero del contratto da creare.
 * */
public class NextNumeroContratto implements Command {

    private GestioneContratti gc;

    public NextNumeroContratto() {
        gc = new GestioneContratti();
    }

    @Override
    public final ResponseInt execute() {

        ContrattoTO result = gc.nextNumeroContratto();

        ComplexResponse<ContrattoTO> response =
                new ComplexResponse<ContrattoTO>();

        response.addParameter(result);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
