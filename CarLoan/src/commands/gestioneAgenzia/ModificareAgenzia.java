package commands.gestioneAgenzia;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneAgenzie;

import commands.Command;

/**
 * Classe che incapsula il comando di modifica di un'agenzia.
 * */
public class ModificareAgenzia implements Command {

    /**
     * Application service per la gestione degli account manager.
     * */
    private GestioneAgenzie ga;

    private ComplexRequest<AgenziaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *           Richiesta con parametri.
     * */
    public ModificareAgenzia(final RequestInt request) {
        ga = new GestioneAgenzie();
        this.request = (ComplexRequest<AgenziaTO>) request;
    }

    @Override
    public final ResponseInt execute() {

        AgenziaTO to =
                request.getParameters().get(0);

        Boolean result = ga.modificareDatiAgenzia(to);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
