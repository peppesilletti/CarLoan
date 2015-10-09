package commands.gestioneAutovetture;

import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneAutovetture;

import commands.Command;

/**
 * Classe che incapsula il comando di inserimento di un'autovettura.
 * */
public class InserireAutovettura implements Command {

    /**
     * Application service per la gestione delle autovetture.
     * */
    private GestioneAutovetture ga;

    private ComplexRequest<AutovetturaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *           Richiesta.
     * */
    public InserireAutovettura(final RequestInt request) {
        ga = new GestioneAutovetture();
        this.request = (ComplexRequest<AutovetturaTO>) request;
    }

    @Override
    public final ResponseInt execute() {

        AutovetturaTO to =
                request.getParameters().get(0);

        Boolean result = ga.inserireAutovettura(to);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
