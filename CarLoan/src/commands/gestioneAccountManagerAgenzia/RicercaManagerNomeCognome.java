package commands.gestioneAccountManagerAgenzia;

import java.util.List;

import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneManagerAgenzia;

import commands.Command;

/**
 * Classe che incapsula il comando di ricerca di un account manager agenzia
 * tramite nome e cognome.
 * */
public class RicercaManagerNomeCognome implements Command {

    /**
     * Application service per la gestione degli account manager.
     * */
    private GestioneManagerAgenzia gama;

    private ComplexRequest<ManagerAgenziaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Request con parametri.
     * */
    public RicercaManagerNomeCognome(
            final RequestInt request) {
        gama = new GestioneManagerAgenzia();
        this.request = (ComplexRequest<ManagerAgenziaTO>) request;
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<ManagerAgenziaTO> response =
                new ComplexResponse<ManagerAgenziaTO>();

        ManagerAgenziaTO to =
                request.getParameters().get(0);

        List<ManagerAgenziaTO> list =
                gama.ricercareManagerByNomeCognome(to.nome, to.cognome);

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
