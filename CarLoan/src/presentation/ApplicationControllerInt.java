package presentation;

import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;

/**
 * Riceve richieste dal {@link FrontController} e delega l'esecuzione dei
 * servizi alla componente {@link CommandInvoker}. Utilizza la classe
 * {@link ViewDispatcher}, per aprire una determinata interfaccia grafica
 */
public interface ApplicationControllerInt {

    /**
     * Esegue una specifica richiesta.
     *
     * @param request
     *            Transfer Object con la richiesta da gestire
     * @return
     *      Risultato della richiesta
     */
    ResponseInt handleRequest(RequestInt request);
}