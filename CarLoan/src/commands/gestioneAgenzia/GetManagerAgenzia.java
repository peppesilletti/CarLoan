package commands.gestioneAgenzia;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneAgenzie;

import commands.Command;

/**
 * Comando che restituisce il manager di un'agenzia.
 * */
public class GetManagerAgenzia implements Command {

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
    public GetManagerAgenzia(
            final RequestInt request) {
        ga = new GestioneAgenzie();
        this.request = (ComplexRequest<AgenziaTO>) request;
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<ManagerAgenziaTO> response =
                new ComplexResponse<ManagerAgenziaTO>();

        AgenziaTO to =
                request.getParameters().get(0);

        ManagerAgenziaTO man = ga.restituisciManagerAgenzia(to.id);

        response.addParameter(man);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
