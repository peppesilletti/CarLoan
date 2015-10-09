package view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.entitiesTO.ClasseAutovetturaTO;
import transferObjects.entitiesTO.ClienteTO;
import transferObjects.entitiesTO.TariffaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.ComplexResponse;
import utility.DateUtil;
import view.model.AgenziaModel;
import view.model.AutovetturaModel;
import view.model.ClasseAutovetturaModel;
import view.model.ClienteModel;
import view.model.TariffaModel;

/**
 * Raccoglie metodi per restituire dati comuni
 * tra i vari controller.
 * */
public final class GetCommonData {

    /**
     * Costruttore privato.
     * */
    private GetCommonData() {

    }

    /**
     * Restituisce tutte le agenzie.
     * @return
     *      Lista con tutte le agenzie.
     * */
    public static ObservableList<AgenziaModel> getRiepilogoAgenzie() {
        FrontControllerInt fc = new FrontController();
        ObservableList<AgenziaModel> data = FXCollections.observableArrayList();

        SimpleRequest request = new SimpleRequest(
                "riepilogoAgenzie", RequestType.SERVICE);

        ComplexResponse<AgenziaTO> response =
                (ComplexResponse<AgenziaTO>) fc.processRequest(request);

        for (AgenziaTO agenzia : response.getParameters()) {
            AgenziaModel model = new AgenziaModel();
            model.setCittàProperty(agenzia.città);
            model.setIdProperty(agenzia.id);
            model.setIndirizzoProperty(agenzia.indirizzo);
            model.setTelefonoProperty(agenzia.telefono);
            data.add(model);
        }
        return data;
    }

    /**
     * Restituisce tutte le classi autovettura.
     * @return
     *      Lista con tutte le classi autovettura.
     * */
    public static ObservableList<ClasseAutovetturaModel>
    getRiepilogoClassiAutovettura() {

        FrontControllerInt fc = new FrontController();

        ObservableList<ClasseAutovetturaModel> data =
                FXCollections.observableArrayList();

        SimpleRequest request =
                new SimpleRequest("riepilogoClasseAutovettura",
                        RequestType.SERVICE);

        ComplexResponse<ClasseAutovetturaTO> response =
                (ComplexResponse<ClasseAutovetturaTO>) fc
        .processRequest(request);

        for (ClasseAutovetturaTO c : response.getParameters()) {
            ClasseAutovetturaModel model =
                    new ClasseAutovetturaModel(c.nome, c.tipoCambio,
                            c.numeroPorte, c.numeroPosti,
                            c.ariaCondizionata, c.id);
            data.add(model);
        }

        return data;
    }

    /**
     * Ritorna le informazioni su una singola classe.
     *
     * @param id
     *      Id della classe autovettura.
     * @return
     *      Model con le info della classe.
     */
    public static ClasseAutovetturaModel getClasseAutovettura(String id) {
        FrontControllerInt fc = new FrontController();

        ComplexRequest<ClasseAutovetturaTO> request =
                new ComplexRequest<ClasseAutovetturaTO>(
                        "ricercareClasseAutovettura", RequestType.SERVICE);

        ClasseAutovetturaTO to = new ClasseAutovetturaTO();
        to.id = id;

        request.addParameter(to);

        ComplexResponse<ClasseAutovetturaTO> response =
                (ComplexResponse<ClasseAutovetturaTO>)
                fc.processRequest(request);

        to = response.getParameters().get(0);

        return new ClasseAutovetturaModel(to.nome, to.tipoCambio,
                to.numeroPorte, to.numeroPosti, to.ariaCondizionata,
                to.id);

    }

    /**
     * Ritorna tutte le informazioni.
     *
     *@return
     *  Ritorna tutte le autovetture.
     */
    public static ObservableList<AutovetturaModel>
    getRiepilogoAutovetture() {
        FrontControllerInt fc = new FrontController();

        ObservableList<AutovetturaModel> data =
                FXCollections.observableArrayList();

        SimpleRequest request =
                new SimpleRequest("riepilogoAutovetture",
                        RequestType.SERVICE);

        ComplexResponse<AutovetturaTO> response =
                (ComplexResponse<AutovetturaTO>) fc
        .processRequest(request);

        for (AutovetturaTO c : response.getParameters()) {
            ClasseAutovetturaModel classe =
                    getClasseAutovettura(c.classeAutovetturaId);
            AutovetturaModel model =
                    new AutovetturaModel(c.targa, c.marca, c.modello,
                            c.chilometraggio, classe,
                            c.alimentazione, c.id);
            data.add(model);
        }

        return data;
    }

    /**
     * Ritorna tutte le informazioni di un cliente dato il codice fiscale.
     *
     *@param codice
     *      Codice fiscale del cliente.
     *
     *@return
     *  Ritorna le info del cliente.
     */
    public static ObservableList<ClienteModel> getClienteByCodFiscale(
            String codice) {

        FrontControllerInt fc = new FrontController();

        ObservableList<ClienteModel> data =
                FXCollections.observableArrayList();

        ClienteTO cliente = new ClienteTO();

        cliente.codiceFiscale = codice;

        ComplexRequest<ClienteTO> request =
                new ComplexRequest<ClienteTO>(
                        "ricercareClientiCodFiscale",
                        RequestType.SERVICE);

        request.addParameter(cliente);

        ComplexResponse<ClienteTO> response =
                (ComplexResponse<ClienteTO>) fc.processRequest(request);

        data.removeAll(data);

        for (ClienteTO c : response.getParameters()) {
            ClienteModel model = new ClienteModel(
                    c.nome, c.cognome, DateUtil.toPrintDate(c.dataDiNascita),
                    c.comuneNascita, c.comuneResidenza,
                    c.indirizzo, c.codiceFiscale, c.idPatente);
            model.setIdProperty(c.id);
            data.add(model);
        }

        return data;

    }

    /**
     * Ritorna tutte le tariffe.
     * */
    public static ObservableList<TariffaModel> getTariffeClasse(String param) {

        FrontControllerInt fc = new FrontController();

        ObservableList<TariffaModel> data =
                FXCollections.observableArrayList();

        ClasseAutovetturaTO classe = new ClasseAutovetturaTO();

        classe.nome = param;

        ComplexRequest<ClasseAutovetturaTO> request =
                new ComplexRequest<ClasseAutovetturaTO>(
                        "ricercareTariffeClasse", RequestType.SERVICE);

        request.addParameter(classe);

        ComplexResponse<TariffaTO> response =
                (ComplexResponse<TariffaTO>) fc.processRequest(request);

        if (response.getParameters() != null ) {

            for (TariffaTO c : response.getParameters()) {
                ClasseAutovetturaModel classe1 = getClasseAutovettura(
                        c.classeAutovetturaId);

                TariffaModel model = new TariffaModel(c.importoUnitario,
                        c.modalità, classe1,
                        c.chilometraggio, c.id, c.chilometriGiorno);

                data.add(model);
            }
            return data;
        } else {
            return null;
        }


    }

}
