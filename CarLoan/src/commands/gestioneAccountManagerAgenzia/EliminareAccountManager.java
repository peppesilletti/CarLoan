package commands.gestioneAccountManagerAgenzia;

import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneManagerAgenzia;

import commands.Command;

/**
 * Classe che incapsula il comando di eliminazione di un account manager
 * agenzia.
 * */
public class EliminareAccountManager implements Command {

    /**
     * Application service per la gestione degli account manager.
     * */
    private GestioneManagerAgenzia gama;

    private ComplexRequest<ManagerAgenziaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Richiesta.
     * */
    public EliminareAccountManager(final RequestInt request) {
        gama = new GestioneManagerAgenzia();
        this.request = (ComplexRequest<ManagerAgenziaTO>) request;
    }

    @Override
    public final SimpleResponse execute() {

        ManagerAgenziaTO to = request.getParameters().get(0);

        Boolean result = gama.eliminareManagerAgenzia(to.username);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {

    }

}
