package commands.gestioneExtra;

import transferObjects.entitiesTO.ExtraTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneExtra;

import commands.Command;

/**
 * Classe che incapsula il comando di modifica di un'extra.
 * */
public class ModificareExtra implements Command {

    /**
     * Application service per la gestione degli extra.
     * */
    private GestioneExtra gm;

    private ComplexRequest<ExtraTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *           Richiesta con parametri.
     * */
    public ModificareExtra(final RequestInt request) {
        gm = new GestioneExtra();
        this.request = (ComplexRequest<ExtraTO>) request;
    }

    @Override
    public final ResponseInt execute() {

        ExtraTO to =
                request.getParameters().get(0);

        Boolean result = gm.modificareDatiExtra(to);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
