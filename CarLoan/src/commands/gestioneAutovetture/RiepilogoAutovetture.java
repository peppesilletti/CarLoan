package commands.gestioneAutovetture;

import java.util.List;

import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneAutovetture;

import commands.Command;
/**
 * Classe che incapsula il comando di riepilogo delle autovetture.
 * */
public class RiepilogoAutovetture implements Command {

    /**
     * Application service per la gestione delle autovettura.
     * */
    private GestioneAutovetture gama;

    /**
     * Costruttore della classe.
     * */
    public RiepilogoAutovetture() {
        gama = new GestioneAutovetture();
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<AutovetturaTO> response =
                new ComplexResponse<AutovetturaTO>();

        List<AutovetturaTO> list = gama.riepilogoAutovetture();

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
