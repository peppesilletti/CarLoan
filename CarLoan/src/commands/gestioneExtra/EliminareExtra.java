package commands.gestioneExtra;

import transferObjects.entitiesTO.ExtraTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneExtra;

import commands.Command;

/**
 * Classe che incapsula il comando di eliminazione di un'extra.
 * */
public class EliminareExtra implements Command {

    /**
     * Application service per la gestione degli extra.
     * */
    private GestioneExtra ge;

    private ComplexRequest<ExtraTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Richiesta.
     * */
    public EliminareExtra(final RequestInt request) {
        ge = new GestioneExtra();
        this.request = (ComplexRequest<ExtraTO>) request;
    }

    @Override
    public final SimpleResponse execute() {

        ExtraTO to = request.getParameters().get(0);

        Boolean result = ge.eliminareExtra(to.id);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
