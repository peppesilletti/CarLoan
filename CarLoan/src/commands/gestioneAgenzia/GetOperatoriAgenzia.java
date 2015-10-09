package commands.gestioneAgenzia;

import java.util.List;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.OperatoreTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneAgenzie;

import commands.Command;
/**
 * Comando per ottenere tutti gli operatori di un'agenzia.
 * */
public class GetOperatoriAgenzia implements Command {

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
    public GetOperatoriAgenzia(
            final RequestInt request) {
        ga = new GestioneAgenzie();
        this.request = (ComplexRequest<AgenziaTO>) request;
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<OperatoreTO> response =
                new ComplexResponse<OperatoreTO>();

        AgenziaTO to =
                request.getParameters().get(0);

        List<OperatoreTO> list = ga.restituisciOperatoriAgenzia(to.id);

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
