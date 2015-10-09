package commands.gestioneExtra;

import java.util.List;

import transferObjects.entitiesTO.ExtraTO;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneExtra;

import commands.Command;

/**
 * Classe che incapsula il comando di riepilogo degli extra.
 * */
public class RiepilogoExtra implements Command {

    /**
     * Application service per la gestione degli extra.
     * */
    private GestioneExtra gama;

    /**
     * Costruttore della classe.
     * */
    public RiepilogoExtra() {
        gama = new GestioneExtra();
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<ExtraTO> response =
                new ComplexResponse<ExtraTO>();

        List<ExtraTO> list = gama.riepilogoExtra();

        response.addParameterList(list);

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
