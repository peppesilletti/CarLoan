package commands.gestioneAccountManagerAgenzia;

import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneManagerAgenzia;

import commands.Command;

/**
 * Classe che incapsula il comando di inserimento di un account manager agenzia.
 * */
public class InserireAccountManager implements Command {

    /**
     * Application service per la gestione degli account manager.
     * */
    private GestioneManagerAgenzia gama;

    private ComplexRequest<ManagerAgenziaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *           Richiesta.
     * */
    public InserireAccountManager(final RequestInt request) {
        gama = new GestioneManagerAgenzia();
        this.request = (ComplexRequest<ManagerAgenziaTO>) request;
    }

    @Override
    public final ResponseInt execute() {

        ManagerAgenziaTO to =
                request.getParameters().get(0);

        Boolean result = gama.inserireManagerAgenzia(to);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
