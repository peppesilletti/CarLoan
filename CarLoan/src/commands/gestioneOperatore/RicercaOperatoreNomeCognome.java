package commands.gestioneOperatore;

import java.util.List;

import transferObjects.entitiesTO.OperatoreTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneOperatori;

import commands.Command;

/**
 * Classe che incapsula il comando di ricerca di un account operatore
 * tramite nome e cognome.
 * */
public class RicercaOperatoreNomeCognome implements Command {

    /**
     * Application service per la gestione degli operatori.
     * */
    private GestioneOperatori go;

    private ComplexRequest<OperatoreTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Request con parametri.
     * */
    public RicercaOperatoreNomeCognome(
            final RequestInt request) {
        go = new GestioneOperatori();
        this.request = (ComplexRequest<OperatoreTO>) request;
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<OperatoreTO> response =
                new ComplexResponse<OperatoreTO>();

        OperatoreTO to =
                request.getParameters().get(0);

        List<OperatoreTO> list =
                go.ricercaOperatoreNomeCognome(to.nome, to.cognome);

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
