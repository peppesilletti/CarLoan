package commands.gestioneAgenzia;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneAgenzie;

import commands.Command;

/**
 * Classe che incapsula il comando di ricerca di un'agenzia.
 * */
public class RicercareAgenzia implements Command {

    /**
     * Application service per la gestione delle autovetture.
     * */
    private GestioneAgenzie ga;

    private ComplexRequest<AgenziaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Request con parametri.
     * */
    public RicercareAgenzia(
            final RequestInt request) {
        ga = new GestioneAgenzie();
        this.request = (ComplexRequest<AgenziaTO>) request;
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<AgenziaTO> response =
                new ComplexResponse<AgenziaTO>();

        AgenziaTO to =
                request.getParameters().get(0);

        to = ga.ricercareDatiAgenzia(to.id);

        response.addParameter(to);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
