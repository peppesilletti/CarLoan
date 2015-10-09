package commands.gestioneAgenzia;

import java.util.List;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneAgenzie;

import commands.Command;

/**
 * Classe che incapsula il comando di riepilogo delle agenzie.
 * */
public class RiepilogoAgenzie implements Command {

    /**
     * Application service per la gestione delle agenzie.
     * */
    private GestioneAgenzie ga;

    /**
     * Costruttore della classe.
     * */
    public RiepilogoAgenzie() {
        ga = new GestioneAgenzie();
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<AgenziaTO> response =
                new ComplexResponse<AgenziaTO>();

        List<AgenziaTO> list = ga.riepilogoAgenzie();

        response.addParameterList(list);

        response.addParameterList(list);

        return response;
    }


    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
