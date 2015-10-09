package commands.gestioneClassiAutovetture;

import transferObjects.entitiesTO.ClasseAutovetturaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneClassiAutovetture;

import commands.Command;

/**
 * Classe che incapsula il comando di modifica di una classe autovettura.
 * */
public class ModificareClasseAutovettura implements Command {

    /**
     * Application service per la gestione delle classi autovetture.
     * */
    private GestioneClassiAutovetture gca;

    private ComplexRequest<ClasseAutovetturaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *           Richiesta con parametri.
     * */
    public ModificareClasseAutovettura(final RequestInt request) {
        gca = new GestioneClassiAutovetture();
        this.request = (ComplexRequest<ClasseAutovetturaTO>) request;
    }

    @Override
    public final ResponseInt execute() {

        ClasseAutovetturaTO to =
                request.getParameters().get(0);

        Boolean result = gca.modificareDatiClasseAutovettura(to);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
