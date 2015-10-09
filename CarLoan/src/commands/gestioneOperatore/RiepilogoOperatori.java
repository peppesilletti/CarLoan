package commands.gestioneOperatore;

import java.util.List;

import transferObjects.entitiesTO.OperatoreTO;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneOperatori;

import commands.Command;

/**
 * Classe che incapsula il comando di riepilogo degli account operatore.
 * */
public class RiepilogoOperatori implements Command {

    /**
     * Application service per la gestione degli account operatore.
     * */
    private GestioneOperatori go;

    /**
     * Costruttore della classe.
     * */
    public RiepilogoOperatori() {
        go = new GestioneOperatori();
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<OperatoreTO> response =
                new ComplexResponse<OperatoreTO>();

        List<OperatoreTO> list = go.riepilogoOperatori();

        response.addParameterList(list);

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
