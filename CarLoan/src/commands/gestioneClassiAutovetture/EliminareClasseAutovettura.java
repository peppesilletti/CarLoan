package commands.gestioneClassiAutovetture;

import transferObjects.entitiesTO.ClasseAutovetturaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneClassiAutovetture;

import commands.Command;

/**
 * Classe che incapsula il comando di eliminazione di una classe autovettura.
 * */
public class EliminareClasseAutovettura implements Command {

    /**
     * Application service per la gestione delle classi autovetture.
     * */
    private GestioneClassiAutovetture gca;

    private ComplexRequest<ClasseAutovetturaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Richiesta.
     * */
    public EliminareClasseAutovettura(final RequestInt request) {
        gca = new GestioneClassiAutovetture();
        this.request = (ComplexRequest<ClasseAutovetturaTO>) request;
    }

    @Override
    public final SimpleResponse execute() {

        ClasseAutovetturaTO to = request.getParameters().get(0);

        Boolean result = gca.eliminareClasseAutovettura(to.id);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
