package commands.gestioneManutenzioni;

import java.util.List;

import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.entitiesTO.ManutenzioneTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneManutenzioni;

import commands.Command;

/**
 * Comando per ricercare una manutenzione in base alla targa di un'auto.
 * */
public class RicercareManutenzioniAutovettura implements Command {

    /**
     * Application service per la gestione delle manutenzioni.
     * */
    private GestioneManutenzioni gm;

    private ComplexRequest<AutovetturaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Request con parametri.
     * */
    public RicercareManutenzioniAutovettura(
            final RequestInt request) {
        gm = new GestioneManutenzioni();
        this.request = (ComplexRequest<AutovetturaTO>) request;
    }


    @Override
    public final ResponseInt execute() {
        ComplexResponse<ManutenzioneTO> response =
                new ComplexResponse<ManutenzioneTO>();

        AutovetturaTO to =
                request.getParameters().get(0);

        List<ManutenzioneTO> list = gm.ricercaManutenzioniAutovettura(to.targa);

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
