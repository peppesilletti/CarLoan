package presentation;

import transferObjects.ViewTO;
import transferObjects.entitiesTO.AnonimoTO;
import transferObjects.request.RequestInt;
import transferObjects.request.RequestType;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;

import commands.Command;
import commands.CommandFactory;

import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.ApplicationControllerException;

/**
 * Implementazione dell'interfaccia {@link ApplicationControllerInt}.
 * */

public class ApplicationController implements ApplicationControllerInt {


    private ViewDispatcher dispatcher;
    private CommandFactory commandFactory;

    /**
     * Costruttore di default della classe.
     * */
    public ApplicationController() {
        dispatcher = ViewDispatcher.getIstance();
        commandFactory = CommandFactory.getInstance();
    }


    @Override
    public final ResponseInt handleRequest(final RequestInt request) {

        //per richieste di servizi
        if (request.getType().equals(RequestType.SERVICE)) {

            String commandName = request.getRequest();

            if (commandName.equals("login")) {
                return login(request);
            }

            try {
                ResponseInt response = null;
                Command command = commandFactory.getCommand(
                        commandName, request);

                response = command.execute();

                return response;

            } catch (NullPointerException e) {
                e.printStackTrace();
                ApplicationControllerException ex =
                        new ApplicationControllerException(e.getMessage());

                ErrorHandler er = ErrorHandler.getIstance();

                er.processError(ex.getClass(),
                        ex, ErrorHandlerInt.FATAL);
            }

            //Per richieste di interfaccia
        } else if (request.getType().equals(RequestType.UI_VIEW)) {

            switch(request.getRequest()) {
                case "start":
                    ComplexResponse<ViewTO> response =
                    new ComplexResponse<ViewTO>();
                    ViewTO view = new ViewTO();
                    view.scene = dispatcher.start();
                    response.addParameter(view);

                    return response;
                case "logout":
                    logout();
                    break;
                default:
                    dispatcher.toView(request.getRequest());
            }
        }
        return null;
    }

    /**
     * Effettua il login.
     * @param request
     *      Richiesta con parametri per effettuare il login.
     * */
    private final ResponseInt login(final RequestInt request) {
        Command command = commandFactory.getCommand("login", request);
        SimpleResponse response = (SimpleResponse) command.execute();

        if (response.getResponse()) {

            command = commandFactory.getCommand("getTipoUtente",
                    request);

            ComplexResponse<AnonimoTO> response2 =
                     (ComplexResponse<AnonimoTO>) command.execute();

            SessionHandler.currentUser =
                    response2.getParameters().get(0).username;

            SessionHandler.currentUserType =
                    response2.getParameters().get(0).tipo;

            SessionHandler.currentAgenziaId =
                    response2.getParameters().get(0).agenziaId;

            //dispatcher.afterLogin();

            switch (response2.getParameters().get(0).tipo) {
                case "operatore":
                    SessionHandler.currentStage.close();
                    dispatcher.afterLogin("PannelloOperatore");
                    break;
                case "manager":
                    SessionHandler.currentStage.close();
                    dispatcher.afterLogin("PannelloManager");
                    break;
                case "admin":
                    SessionHandler.currentStage.close();
                    dispatcher.afterLogin("PannelloAmministratore");
                    break;
                default:

            }

        }

        return response;
    }

    /**
     * Metodo per effettuare il logout.
     * */
    private void logout() {
        SessionHandler.reset();
        dispatcher.logout();
    }


}
