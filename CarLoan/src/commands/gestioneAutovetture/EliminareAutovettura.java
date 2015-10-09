package commands.gestioneAutovetture;

import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneAutovetture;

import commands.Command;

/**
 * Classe che incapsula il comando di eliminazione di un'autovettura.
 * */
public class EliminareAutovettura implements Command {

    /**
     * Application service per la gestione delle agenzie.
     * */
    private GestioneAutovetture ga;

    private ComplexRequest<AutovetturaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Richiesta.
     * */
    public EliminareAutovettura(final RequestInt request) {
        ga = new GestioneAutovetture();
        this.request = (ComplexRequest<AutovetturaTO>) request;
    }

    @Override
    public final SimpleResponse execute() {

        AutovetturaTO to = request.getParameters().get(0);

        Boolean result = ga.eliminareAutovettura(to.id);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }


}
