package commands.gestioneTariffe;

import transferObjects.entitiesTO.TariffaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneTariffe;

import commands.Command;

/**
 * Classe che incapsula il comando di modifica di una tariffa.
 * */
public class ModificareTariffa implements Command {

    /**
     * Application service per la gestione delle tariffe.
     * */
    private GestioneTariffe gt;

    private ComplexRequest<TariffaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *           Richiesta con parametri.
     * */
    public ModificareTariffa(final RequestInt request) {
        gt = new GestioneTariffe();
        this.request = (ComplexRequest<TariffaTO>) request;
    }

    @Override
    public final ResponseInt execute() {

        TariffaTO to =
                request.getParameters().get(0);

        Boolean result = gt.modificareDatiTariffa(to);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
