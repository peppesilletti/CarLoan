package commands.gestioneAgenzia;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneAgenzie;

import commands.Command;

/**
 * Classe che incapsula il comando di eliminazione di un'agenzia.
 * */
public class EliminareAgenzia implements Command {

    /**
     * Application service per la gestione delle agenzie.
     * */
    private GestioneAgenzie ga;

    private ComplexRequest<AgenziaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Richiesta.
     * */
    public EliminareAgenzia(final RequestInt request) {
        ga = new GestioneAgenzie();
        this.request = (ComplexRequest<AgenziaTO>) request;
    }

    @Override
    public final SimpleResponse execute() {

        AgenziaTO to = request.getParameters().get(0);

        Boolean result = ga.eliminareAgenzia(to.id);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // idDO Auid-generated method stub

    }

}
