package commands.gestioneClassiAutovetture;

import transferObjects.entitiesTO.ClasseAutovetturaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneClassiAutovetture;

import commands.Command;

/**
 * Classe che incapsula il comando di ricerca di una classe autovettura.
 * */
public class RicercareClasseAutovettura implements Command {

    /**
     * Application service per la gestione delle classi autovettura.
     * */
    private GestioneClassiAutovetture gca;

    private ComplexRequest<ClasseAutovetturaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Request con parametri.
     * */
    public RicercareClasseAutovettura(
            final RequestInt request) {
        gca = new GestioneClassiAutovetture();
        this.request = (ComplexRequest<ClasseAutovetturaTO>) request;
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<ClasseAutovetturaTO> response =
                new ComplexResponse<ClasseAutovetturaTO>();

        ClasseAutovetturaTO to =
                request.getParameters().get(0);

        to = gca.ricercareDatiClasseAutovettura(to.id);

        response.addParameter(to);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
