package presentation;

import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;


/**
 * Implementazione dell'interfaccia {@link FrontControllerInt}.
 */
public class FrontController implements FrontControllerInt {

    /**
     * Istanza dell'application controller
     * al quale delegare le richieste.
     * */
    private ApplicationControllerInt ac;

    /**
     * Costruttore di default della classe.
     * */
    public FrontController() {
        this.ac = new ApplicationController();
    }

    @Override
    public final ResponseInt processRequest(final RequestInt request) {
        return ac.handleRequest(request);
    }
}
