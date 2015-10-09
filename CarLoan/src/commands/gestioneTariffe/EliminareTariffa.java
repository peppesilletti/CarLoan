package commands.gestioneTariffe;

import transferObjects.entitiesTO.TariffaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneTariffe;

import commands.Command;

/**
 * Classe che incapsula il comando di eliminazione di una tariffa.
 * */
public class EliminareTariffa implements Command {

    /**
     * Application service per la gestione delle tariffe.
     * */
    private GestioneTariffe gt;

    private ComplexRequest<TariffaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Richiesta.
     * */
    public EliminareTariffa(final RequestInt request) {
        gt = new GestioneTariffe();
        this.request = (ComplexRequest<TariffaTO>) request;
    }

    @Override
    public final SimpleResponse execute() {

        TariffaTO to = request.getParameters().get(0);

        Boolean result = gt.eliminareTariffa(to.id);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
