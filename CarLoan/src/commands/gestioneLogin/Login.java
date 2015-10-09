package commands.gestioneLogin;

import transferObjects.entitiesTO.AnonimoTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneLogin;

import commands.Command;

/**
 * Command per l'operazione di Login.
 * */
public class Login implements Command {

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
    public Login(final RequestInt params) {
        login = new GestioneLogin();
        this.params = (ComplexRequest<AnonimoTO>) params;
    }

    @Override
    public final ResponseInt execute() {

        AnonimoTO to = params.getParameters().get(0);
        Boolean result = login.autenticazione(to);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
