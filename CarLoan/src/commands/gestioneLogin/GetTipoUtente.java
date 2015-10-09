package commands.gestioneLogin;

import transferObjects.entitiesTO.AnonimoTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneLogin;

import commands.Command;

/**
 * Ritorna il titolo dell'utente.
 * */
public class GetTipoUtente implements Command {

    private ComplexRequest<AnonimoTO> params;

    /**
     * Application service per la gestione del login.
     * */
    private GestioneLogin login;

    /**
     * Costruttore della classe.
     *
     * @param params
     *            Parametri da utilizzare per l'esecuzione del comando.
     * */
    public GetTipoUtente(final RequestInt params) {
        login = new GestioneLogin();
        this.params = (ComplexRequest<AnonimoTO>) params;
    }

    @Override
    public final ResponseInt execute() {

        ComplexResponse<AnonimoTO> response =
                new ComplexResponse<AnonimoTO>();

        AnonimoTO to = params.getParameters().get(0);
        to = login.getTipoUtente(to.username);

        response.addParameter(to);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
