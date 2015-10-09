package commands.gestioneClienti;

import java.util.List;

import transferObjects.entitiesTO.ClienteTO;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneClienti;

import commands.Command;

/**
 * Classe che incapsula il comando di riepilogo dei clienti.
 * */
public class RiepilogoClienti implements Command {
    /**
     * Application service per la gestione dei clienti.
     * */
    private GestioneClienti gc;

    /**
     * Costruttore della classe.
     * */
    public RiepilogoClienti() {
        gc = new GestioneClienti();
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<ClienteTO> response =
                new ComplexResponse<ClienteTO>();

        List<ClienteTO> list = gc.riepilogoClienti();

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
