package commands.gestioneAccountManagerAgenzia;

import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneManagerAgenzia;

import commands.Command;

/**
 * Classe che incapsula il comando di ricerca di un account manager agenzia.
 * */
public class RicercareDatiAccountManager implements Command {

    /**
     * Application service per la gestione degli account manager.
     * */
    private GestioneManagerAgenzia gama;

    private ComplexRequest<ManagerAgenziaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Request con parametri.
     * */
    public RicercareDatiAccountManager(
            final RequestInt request) {
        gama = new GestioneManagerAgenzia();
        this.request = (ComplexRequest<ManagerAgenziaTO>) request;
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<ManagerAgenziaTO> response =
                new ComplexResponse<ManagerAgenziaTO>();

        ManagerAgenziaTO to =
                request.getParameters().get(0);

        to = gama.ricercareDatiManagerAgenzia(to.id);

        response.addParameter(to);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
