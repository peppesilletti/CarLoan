package commands.gestioneManutenzioni;

import transferObjects.entitiesTO.ManutenzioneTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneManutenzioni;

import commands.Command;

/**
 * Classe che incapsula il comando di modifica di una manutenzione.
 * */
public class ModificareDatiManutenzione implements Command {

    /**
     * Application service per la gestione delle manutenzioni.
     * */
    private GestioneManutenzioni gm;

    private ComplexRequest<ManutenzioneTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *           Richiesta con parametri.
     * */
    public ModificareDatiManutenzione(final RequestInt request) {
        gm = new GestioneManutenzioni();
        this.request = (ComplexRequest<ManutenzioneTO>) request;
    }

    @Override
    public final ResponseInt execute() {

        ManutenzioneTO to =
                request.getParameters().get(0);

        Boolean result = gm.modificareDatiManutenzione(to);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
