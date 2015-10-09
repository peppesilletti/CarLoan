package commands.gestioneManutenzioni;

import java.util.List;

import transferObjects.entitiesTO.ManutenzioneTO;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneManutenzioni;

import commands.Command;

/**
 * Classe che incapsula il comando di riepilogo delle manutenzioni.
 * */
public class RiepilogoManutenzioni implements Command {

    /**
     **
     * Application service per la gestione delle manutenzioni.
     * */
    private GestioneManutenzioni gm;

    /**
     * Costruttore della classe.
     * */
    public RiepilogoManutenzioni() {
        gm = new GestioneManutenzioni();
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<ManutenzioneTO> response =
                new ComplexResponse<ManutenzioneTO>();

        List<ManutenzioneTO> list = gm.riepilogoManutenzioni();

        response.addParameterList(list);

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
