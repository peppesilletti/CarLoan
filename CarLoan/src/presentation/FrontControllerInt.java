package presentation;

import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;


/**
 * Interfaccia per il Front Controller, la componente che gestisce le richieste
 * provenienti dall'applicazione.
 * */
public interface FrontControllerInt {

    /**
     * Metodo per richiedere un servizio all'Application Controller.
     *
     * @param request
     *            Richiesta da processare
     * @return
     *            Risposta della richiesta.
     */
    ResponseInt processRequest(RequestInt request);
}
