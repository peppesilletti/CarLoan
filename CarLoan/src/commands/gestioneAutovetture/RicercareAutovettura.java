package commands.gestioneAutovetture;

import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneAutovetture;

import commands.Command;

/**
 * Classe che incapsula il comando di ricerca di un'autovettura.
 * */
public class RicercareAutovettura implements Command {

    /**
     * Application service per la gestione delle autovetture.
     * */
    private GestioneAutovetture ga;

    private ComplexRequest<AutovetturaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Request con parametri.
     * */
    public RicercareAutovettura(
            final RequestInt request) {
        ga = new GestioneAutovetture();
        this.request = (ComplexRequest<AutovetturaTO>) request;
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<AutovetturaTO> response =
                new ComplexResponse<AutovetturaTO>();

        AutovetturaTO to =
                request.getParameters().get(0);

        to = ga.ricercareAutovettura(to.id);

        response.addParameter(to);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
