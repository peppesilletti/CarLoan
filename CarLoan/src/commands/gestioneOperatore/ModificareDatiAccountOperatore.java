package commands.gestioneOperatore;

import transferObjects.entitiesTO.OperatoreTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneOperatori;

import commands.Command;

/**
 * Classe che incapsula il comando di modifica di un account operatore.
 * */
public class ModificareDatiAccountOperatore implements Command {

    /**
     * Application service per la gestione degli account operatore.
     * */
    private GestioneOperatori go;

    private ComplexRequest<OperatoreTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *           Richiesta con parametri.
     * */
    public ModificareDatiAccountOperatore(final RequestInt request) {
        go = new GestioneOperatori();
        this.request = (ComplexRequest<OperatoreTO>) request;
    }

    @Override
    public final ResponseInt execute() {

        OperatoreTO to =
                request.getParameters().get(0);

        Boolean result = go.modificareDatiAccountOperatore(to);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
