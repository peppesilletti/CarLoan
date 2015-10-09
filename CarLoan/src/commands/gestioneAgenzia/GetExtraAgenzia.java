package commands.gestioneAgenzia;

import java.util.List;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.ExtraAgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneAgenzie;

import commands.Command;

/**
 * Comando per ottenere tutti gli extra di un'agenzia.
 * */
public class GetExtraAgenzia implements Command {

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
    public GetExtraAgenzia(
            final RequestInt request) {
        ga = new GestioneAgenzie();
        this.request = (ComplexRequest<AgenziaTO>) request;
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<ExtraAgenziaTO> response =
                new ComplexResponse<ExtraAgenziaTO>();

        AgenziaTO to =
                request.getParameters().get(0);

        List<ExtraAgenziaTO> list = ga.restituireExtraAgenzia(to.id);

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
