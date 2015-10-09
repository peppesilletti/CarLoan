package commands.gestioneAgenzia;

import transferObjects.entitiesTO.ExtraAgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneAgenzie;

import commands.Command;

/**
 * Classe che incapsula il comando di inserimento di un extra agenzia.
 * */
public class InserireExtraAgenzia implements Command {

    /**
     * Application service per la gestione delle agenzie.
     * */
    private GestioneAgenzie ga;

    private ComplexRequest<ExtraAgenziaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *           Richiesta.
     * */
    public InserireExtraAgenzia(final RequestInt request) {
        ga = new GestioneAgenzie();
        this.request = (ComplexRequest<ExtraAgenziaTO>) request;
    }

    @Override
    public final ResponseInt execute() {

        ExtraAgenziaTO to =
                request.getParameters().get(0);

        Boolean result = ga.inserireExtraAgenzia(to);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
