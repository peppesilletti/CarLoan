package commands.gestioneContratti;

import java.util.HashMap;

import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ResponseInt;
import transferObjects.response.SimpleResponse;
import business.applicationServices.GestioneContratti;

import commands.Command;

public class StampaContratto implements Command {

    private GestioneContratti gc;

    private ComplexRequest<HashMap<String,String>> request;

    public StampaContratto(RequestInt request) {
        gc = new GestioneContratti();
        this.request = (ComplexRequest<HashMap<String,String>>) request;
    }

    @Override
    public ResponseInt execute() {
        gc.stampaContratto(request.getParameters().get(0));
        return new SimpleResponse(true);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
