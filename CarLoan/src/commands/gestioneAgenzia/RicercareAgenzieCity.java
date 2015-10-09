package commands.gestioneAgenzia;

import java.util.List;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneAgenzie;

import commands.Command;

/**
 * Comando per la ricerca di agenzia di una città.
 * */
public class RicercareAgenzieCity implements Command {

    /**
     * Application service per la gestione delle agenzie.
     * */
    private GestioneAgenzie ga;

    private ComplexRequest<AgenziaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Request con parametri.
     * */
    public RicercareAgenzieCity(
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

        List<AgenziaTO> list = ga.ricercareAgenzieCittà(to.città);

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
