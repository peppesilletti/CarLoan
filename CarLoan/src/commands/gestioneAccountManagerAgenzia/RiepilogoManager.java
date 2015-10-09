package commands.gestioneAccountManagerAgenzia;

import java.util.List;

import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneManagerAgenzia;

import commands.Command;

/**
 * Classe che incapsula il comando di riepilogo degli account manager agenzia.
 * */
public class RiepilogoManager implements Command {
    /**
     * Application service per la gestione degli account manager.
     * */
    private GestioneManagerAgenzia gama;

    /**
     * Costruttore della classe.
     * */
    public RiepilogoManager() {
        gama = new GestioneManagerAgenzia();
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<ManagerAgenziaTO> response =
                new ComplexResponse<ManagerAgenziaTO>();

        List<ManagerAgenziaTO> list = gama.riepilogoManagerAgenzia();

        response.addParameterList(list);

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
