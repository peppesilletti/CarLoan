package view.controller.amministratoreSistema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.ComplexResponse;
import transferObjects.response.SimpleResponse;
import view.controller.ShowAlert;
import view.model.AgenziaModel;
import view.model.ManagerAgenziaModel;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.ViewDispatcherException;

/**
 * Controller per l'interfaccia RiepilogoAgenzie.fxml.
 * */
public class RiepilogoAgenzieController implements Initializable {

    @FXML
    private TableColumn<AgenziaModel, String> indirizzoColonna;

    @FXML
    private Button modificaButton;

    @FXML
    private Button annullaRicercaButton;

    @FXML
    private TextField cittàText;

    @FXML
    private Label valueCittaLabel;

    @FXML
    private Label valueManagerLabel;

    @FXML
    private Button eliminaButton;

    @FXML
    private TableColumn<AgenziaModel, ManagerAgenziaModel> managerColonna;

    @FXML
    private Label valueIndirizzoLabel;

    @FXML
    private TableView<AgenziaModel> infoTableView;

    @FXML
    private Button ricercaButton;

    @FXML
    private TableColumn<AgenziaModel, String> cittàColonna;

    @FXML
    private Button indietroButton;

    @FXML
    private Label valueTelefonoLabel;

    @FXML
    private TextField cognomeText;

    @FXML
    private Button confermaModifiche;

    @FXML
    private TextField cittàModifica;

    @FXML
    private TextField indirizzoModifica;

    @FXML
    private TextField telefonoModifica;

    @FXML
    private Button riepilogoExtraButton;


    private ObservableList<AgenziaModel> list;

    FrontControllerInt fc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fc = new FrontController();

        setList(FXCollections.observableArrayList());

        cittàColonna
        .setCellValueFactory(new PropertyValueFactory<AgenziaModel, String>(
                "città"));

        indirizzoColonna
        .setCellValueFactory(new PropertyValueFactory<AgenziaModel, String>(
                "indirizzo"));

       managerColonna
        .setCellValueFactory(new PropertyValueFactory<AgenziaModel, ManagerAgenziaModel>(
                "telefono"));

        riepilogo();

        // Clear extra details
        infoAgenzia(null);

        infoTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> infoAgenzia(newValue));

    }

    @FXML
    public final void ricercaButton() {

        this.cittàText.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    ricerca();
                }
            }
        });

        this.ricercaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                ricerca();
            }
        });

    }

    @FXML
    public final void eliminaButton() {

        this.eliminaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                eliminare();
            }
        });
    }

    @FXML
    public final void annullaRicercaButton() {

        this.annullaRicercaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                SimpleRequest refresh =
                        new SimpleRequest("riepilogoAgenzie",
                        RequestType.UI_VIEW);

                fc.processRequest(refresh);
            }
        });
    }

    @FXML
    public final void modificaButton() {
        this.modificaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                showModifica();
            }
        });
    }

    @FXML
    private void indietroButton() {
        this.indietroButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                SimpleRequest request = new SimpleRequest(
                        "PannelloAmministratore", RequestType.UI_VIEW);
                fc.processRequest(request);
            }
        });
    }

    @FXML
    private void confermaModifiche() {
        this.confermaModifiche.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (ShowAlert.showMessage("Confermi le modifiche?",
                        AlertType.CONFIRMATION)) {
                   modifica();
                }
            }
        });
    }

    @FXML
    public final void riepilogoExtraButton() {

        this.riepilogoExtraButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                toExtraAgenzia();
            }
        });
    }

    /**
     * Metodo che permette di visualizzare le informazioni di un'agenzia
     * cliccatoci sopra.
     * */
    public final void infoAgenzia(final AgenziaModel model) {
        if (model != null) {
            valueCittaLabel.setText(model.getCittàProperty().getValue());
            valueIndirizzoLabel.setText(model.getIndirizzoProperty().getValue());
            valueTelefonoLabel.setText(model.getTelefonoProperty().getValue());

            if (model.getManager().toString().equals("null null")) {
                valueManagerLabel.setText("Nessun manager assegnato");
            } else {
                valueManagerLabel.setText(model.getManager().toString());
            }
        } else {
            valueCittaLabel.setText("");
            valueIndirizzoLabel.setText("");
            valueTelefonoLabel.setText("");
            valueManagerLabel.setText("");
        }
    }

    /**
     * Mostra l'elenco di extra presenti nel sistema.
     * */
    public final void riepilogo() {

        SimpleRequest request = new SimpleRequest(
                "riepilogoAgenzie", RequestType.SERVICE);

        ComplexResponse<AgenziaTO> response = (ComplexResponse<AgenziaTO>) fc
                .processRequest(request);

        for (AgenziaTO c : response.getParameters()) {
            ManagerAgenziaModel manager = getManager(c.id);
            AgenziaModel model = new AgenziaModel(c.città, c.indirizzo,
                    c.telefono, c.id, manager);
            list.add(model);
        }

        infoTableView.setItems(null);
        infoTableView.layout();
        infoTableView.setItems(FXCollections.observableArrayList(list));

    }

    /**
     * Restituisce le informazioni su di un manager agenzia.
     * @param agenziaId
     *      Id del manager
     * @return
     *      Informazioni del manager.
     * */
    private ManagerAgenziaModel getManager(final String agenziaId) {
        ComplexRequest<AgenziaTO> request =
                new ComplexRequest<AgenziaTO>(
                        "GetManagerAgenzia", RequestType.SERVICE);

        AgenziaTO agenzia = new AgenziaTO();
        agenzia.id = agenziaId;
        request.addParameter(agenzia);

        ComplexResponse<ManagerAgenziaTO> response =
                (ComplexResponse<ManagerAgenziaTO>) fc.processRequest(request);

        ManagerAgenziaTO manager = response.getParameters().get(0);

        ManagerAgenziaModel model = new ManagerAgenziaModel();

        model.setNomeProperty(manager.nome);
        model.setCognomeProperty(manager.cognome);

        return model;
    }

    /**
     * Eliminazione agenzia.
     * */
    public final void eliminare() {

        AgenziaModel selectedAgenzia =
                infoTableView.getSelectionModel().getSelectedItem();

        if (selectedAgenzia != null) {
            if (ShowAlert.showMessage("Confermi l'eliminazione?",
                    AlertType.CONFIRMATION)) {

                // richiesta
                AgenziaTO to = new AgenziaTO();
                to.id = selectedAgenzia.getIdProperty().getValue();

                ComplexRequest<AgenziaTO> request =
                        new ComplexRequest<AgenziaTO>(
                        "eliminareAgenzia", RequestType.SERVICE);

                request.addParameter(to);

                // risposta
                SimpleResponse response = (SimpleResponse)
                        fc.processRequest(request);

                // se la risposta va a buon fine aggiorna la tabella
                if (response.getResponse()) {

                    int selectedIndex = infoTableView.getSelectionModel()
                            .getSelectedIndex();
                    infoTableView.getItems().remove(selectedIndex);
                } else {
                    ShowAlert.showMessage("Impossibile eliminare l'agenzia.",
                            AlertType.WARNING);
                }
            }
        } else {
            ShowAlert.showMessage("Nessuna agenzia selezionata",
                    AlertType.WARNING);
        }
    }

    /**
     * Mostra i text field per effettuare le modifiche.
     * */
    private void showModifica() {
        AgenziaModel selectedAgenzia =
                infoTableView.getSelectionModel().getSelectedItem();

        if (selectedAgenzia == null) {
            ShowAlert.showMessage("Nessuna agenzia selezionato",
                    AlertType.WARNING);
        } else {
            cittàModifica.setVisible(true);
            indirizzoModifica.setVisible(true);
            telefonoModifica.setVisible(true);
            confermaModifiche.setVisible(true);

            cittàModifica.setText(selectedAgenzia.getCittà());
            indirizzoModifica.setText(selectedAgenzia.getIndirizzo());
            telefonoModifica.setText(selectedAgenzia.getTelefono());
        }
    }

    /**
     * Gestisce la modifica di un'agenzia.
     * */
    public final void modifica() {

        AgenziaModel selectedModel =
                infoTableView.getSelectionModel().getSelectedItem();

        String città = cittàModifica.getText().trim();
        String indirizzo = indirizzoModifica.getText().trim();
        String telefono = telefonoModifica.getText().trim();
        String id = selectedModel.getIdProperty().getValue();

        if (!ValidareDatiAgenzia.isEmptyCheck(
                città, indirizzo, telefono)
            && ValidareDatiAgenzia.isValidInput(
                città, indirizzo, telefono)) {

            AgenziaTO agenzia = new AgenziaTO(
                    città, indirizzo, telefono);
            agenzia.id = id;

            ComplexRequest<AgenziaTO> request =
                    new ComplexRequest<AgenziaTO>(
                    "modificareAgenzia", RequestType.SERVICE);
            request.addParameter(agenzia);

            SimpleResponse response = (SimpleResponse) fc
                    .processRequest(request);

            if (response.getResponse()) {
                SimpleRequest refresh =
                        new SimpleRequest("riepilogoAgenzie",
                        RequestType.UI_VIEW);

                fc.processRequest(refresh);
            } else {
                ShowAlert.showMessage("Dati inseriti non accettati, riprovare",
                        AlertType.ERROR);
            }

        }

    }

    /**
     * Apre una nuova finestra per visualizzare gli extra
     * dell'agenzia.
     * */
    private void toExtraAgenzia() {
        AgenziaModel selectedModel =
                infoTableView.getSelectionModel().getSelectedItem();

        if (selectedModel != null) {

            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(
                        "/view/amministratoreSistema/RiepilogoExtraAgenzia.fxml"));

                AnchorPane pane = loader.load();

                RiepilogoExtraAgenziaController controller =
                        loader.getController();

                controller.setData(selectedModel.getIdProperty().get());

                Stage stage = new Stage();
                Scene scene = new Scene(pane);

                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
                ViewDispatcherException ex = new ViewDispatcherException(
                        e.getMessage());
                ErrorHandlerInt ge = ErrorHandler.getIstance();
                ge.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
            }
        } else {
            ShowAlert.showMessage("Nessuna agenzia selezionata.", AlertType.WARNING);
        }
    }

    /**
     * Gestisce la ricerca di un'agenzia.
     * */
    private void ricerca() {
        String param = cittàText.getText().trim();


        if (!param.isEmpty()) {

            annullaRicercaButton.setVisible(true);

            AgenziaTO agenzia = new AgenziaTO();

            agenzia.città = param;

            ComplexRequest<AgenziaTO> request =
                    new ComplexRequest<>("ricercareAgenzieCittà",
                            RequestType.SERVICE);

            request.addParameter(agenzia);

            ComplexResponse<AgenziaTO> response =
                    (ComplexResponse<AgenziaTO>) fc.processRequest(request);

            list.removeAll(list);
            infoTableView.setItems(null);
            infoTableView.layout();

            if (response.getParameters() != null) {

                for (AgenziaTO c : response.getParameters()) {
                    ManagerAgenziaModel manager = getManager(c.id);
                    AgenziaModel model = new AgenziaModel(c.città, c.indirizzo,
                            c.telefono, c.id, manager);
                    list.add(model);
                }

            infoTableView.setItems(FXCollections.observableArrayList(list));
            }
        } else {
            ShowAlert.showMessage("Inserire il parametro di ricerca",
                    AlertType.WARNING);
        }
    }

    public final void setList(final ObservableList<AgenziaModel> list) {
        this.list = list;
    }

}
