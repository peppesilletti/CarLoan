package commands.gestioneOperatore;

import transferObjects.entitiesTO.OperatoreTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneOperatori;

import commands.Command;

/**
 * Classe che incapsula il comando di eliminazione di un account operatore.
 * */
public class EliminareAccountOperatore implements Command {

    /**
     * Application service per la gestione degli account operatore.
     * */
    private GestioneOperatori gama;

    private ComplexRequest<OperatoreTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Richiesta.
     * */
    public EliminareAccountOperatore(final RequestInt request) {
        gama = new GestioneOperatori();
        this.request = (ComplexRequest<OperatoreTO>) request;
    }

    @Override
    public final SimpleResponse execute() {

        OperatoreTO to = request.getParameters().get(0);

        Boolean result = gama.eliminareAccountOperatore(to.username);

        return new SimpleResponse(result);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
