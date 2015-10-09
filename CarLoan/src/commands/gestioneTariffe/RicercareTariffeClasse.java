package commands.gestioneTariffe;

import java.util.List;

import transferObjects.entitiesTO.ClasseAutovetturaTO;
import transferObjects.entitiesTO.TariffaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestInt;
import transferObjects.response.ComplexResponse;
import transferObjects.response.ResponseInt;
import business.applicationServices.GestioneTariffe;

import commands.Command;

/**
 * Comando per ricercare le tariffe in base alle classi autovetture.
 * */
public class RicercareTariffeClasse implements Command {

    /**
     * Application service per la gestione delle tariffe.
     * */
    private GestioneTariffe gt;

    private ComplexRequest<ClasseAutovetturaTO> request;

    /**
     * Costruttore della classe.
     *
     * @param request
     *      Request con parametri.
     * */
    public RicercareTariffeClasse(
            final RequestInt request) {
        gt = new GestioneTariffe();
        this.request = (ComplexRequest<ClasseAutovetturaTO>) request;
    }

    @Override
    public final ResponseInt execute() {
        ComplexResponse<TariffaTO> response =
                new ComplexResponse<TariffaTO>();

        ClasseAutovetturaTO to = request.getParameters().get(0);

        List<TariffaTO> list = gt.ricercareTariffaByClasse(to.nome);

        response.addParameterList(list);

        return response;
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
