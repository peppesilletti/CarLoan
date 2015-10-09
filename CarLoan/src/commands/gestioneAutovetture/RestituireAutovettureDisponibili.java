package commands.gestioneAutovetture;

import java.util.List;

import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneAutovetture;

import commands.Command;

/**
 * Comando che restituisce le autovettura disponibili in un'agenzia.
 * */
public class RestituireAutovettureDisponibili implements Command {

    /**
     * Application service per la gestione delle autovettura.
     * */
    private GestioneAutovetture ga;
    private ComplexRequest<AutovetturaTO> request;

    /**
     * Costruttore della classe.
     * */
    public RestituireAutovettureDisponibili(RequestInt request) {
        ga = new GestioneAutovetture();
        this.request = (ComplexRequest<AutovetturaTO>) request;
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<AutovetturaTO> response =
                new ComplexResponse<AutovetturaTO>();

        AutovetturaTO to = request.getParameters().get(0);

        List<AutovetturaTO> list = ga.restituireAutovettureDisponibili(
                to.classeAutovetturaId, to.agenzia);

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
