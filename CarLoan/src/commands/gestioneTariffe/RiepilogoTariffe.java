package commands.gestioneTariffe;

import java.util.List;

import transferObjects.entitiesTO.TariffaTO;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneTariffe;

import commands.Command;

/**
 * Classe che incapsula il comando di riepilogo delle tariffe.
 * */
public class RiepilogoTariffe implements Command {

    /**
     **
     * Application service per la gestione delle tariffe.
     * */
    private GestioneTariffe gm;

    /**
     * Costruttore della classe.
     * */
    public RiepilogoTariffe() {
        gm = new GestioneTariffe();
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<TariffaTO> response =
                new ComplexResponse<TariffaTO>();

        List<TariffaTO> list = gm.riepilogoTariffe();

        response.addParameterList(list);

        response.addParameterList(list);

        return response;
    }


    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
