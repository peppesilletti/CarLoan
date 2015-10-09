package commands.gestioneExtra;

import transferObjects.entitiesTO.ExtraTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneExtra;

import commands.Command;
/**
 * Classe che incapsula il comando di ricerca di un'etra.
 * */
public class RicercareExtra implements Command {

    /**
     * Application service per la gestione degli extra.
     * */
    private GestioneExtra gm;

    private ComplexRequest<ExtraTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Request con parametri.
     * */
    public RicercareExtra(
            final RequestInt request) {
        gm = new GestioneExtra();
        this.request = (ComplexRequest<ExtraTO>) request;
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<ExtraTO> response =
                new ComplexResponse<ExtraTO>();

        ExtraTO to =
                request.getParameters().get(0);

        to = gm.ricercareDatiExtra(to.id);

        response.addParameter(to);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
