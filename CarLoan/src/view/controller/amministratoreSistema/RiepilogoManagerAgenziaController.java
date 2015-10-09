package view.controller.amministratoreSistema;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.ComplexResponse;
import transferObjects.response.SimpleResponse;
import view.controller.GetCommonData;
import view.controller.ShowAlert;
import view.model.AgenziaModel;
import view.model.ManagerAgenziaModel;

/**
 * Controller per l'interfaccia RiepilogoManagerAgenzia.fxml .
 * */
public class RiepilogoManagerAgenziaController implements Initializable {

    @FXML
    private TableView<ManagerAgenziaModel> infoTableView;

    @FXML
    private TableColumn<ManagerAgenziaModel, String> nomeManagerColonna;

    @FXML
    private TableColumn<ManagerAgenziaModel, String> cognomeManagerColonna;

    @FXML
    private TableColumn<ManagerAgenziaModel, AgenziaModel>
                        agenziaManagerColonna;

    @FXML
    private Label nomeLabel;

    @FXML
    private Label cognomeLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label agenziaLabel;

    @FXML
    private Button modificaButton;

    @FXML
    private Button eliminaButton;

    @FXML
    private TextField nomeRicercaText;

    @FXML
    private TextField cognomeRicercaText;

    @FXML
    private Button ricercaButton;

    @FXML
    private Button annullaRicercaButton;

    @FXML
    private Button indietroButton;

    @FXML
    private Button confermaModifiche;

    @FXML
    private TextField cognomeModifica;

    @FXML
    private TextField nomeModifica;

    @FXML
    private TextField usernameModifica;

    @FXML
    private ComboBox<AgenziaModel> agenziaModifica;

    private ObservableList<ManagerAgenziaModel>      list;

    private FrontControllerInt                       fc;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        fc = new FrontController();

        setList(FXCollections.observableArrayList());

        nomeManagerColonna
                .setCellValueFactory(
                        new PropertyValueFactory<ManagerAgenziaModel, String>(
                        "nome"));

        cognomeManagerColonna
                .setCellValueFactory(
                        new PropertyValueFactory<ManagerAgenziaModel, String>(
                        "cognome"));

        agenziaManagerColonna
                .setCellValueFactory(
                        new PropertyValueFactory<ManagerAgenziaModel,
                        AgenziaModel>("agenzia"));

        riepilogo();

        // Clear manager details
        infoManagerAgenzia(null);

        infoTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                         infoManagerAgenzia(newValue));

    }

    /**
     * Metodo che permette di visualizzare le informazioni di un manager
     * cliccatoci sopra.
     * @param model
     *      Dati del manager.
     * */
    public final void infoManagerAgenzia(final ManagerAgenziaModel model) {
        if (model != null) {
            nomeLabel.setText(model.getNomeProperty().getValue());
            cognomeLabel.setText(model.getCognomeProperty().getValue());
            usernameLabel.setText(model.getUsernameProperty().getValue());
            agenziaLabel.setText(model.getAgenzia().toString());
        } else {
            nomeLabel.setText("");
            cognomeLabel.setText("");
            usernameLabel.setText("");
            agenziaLabel.setText("");
        }
    }


    @FXML
    public final void ricercaButton() {

        this.nomeRicercaText.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    ricerca();
                }
            }
        });

        this.cognomeRicercaText.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
    private void annullaRicercaButton() {
        this.annullaRicercaButton.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                SimpleRequest request = new SimpleRequest(
                        "riepilogoAccountManager", RequestType.UI_VIEW);
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

    /**
     * Mostra l'elenco di extra presenti nel sistema.
     * */
    public final void riepilogo() {

        SimpleRequest request =
                new SimpleRequest(
                        "riepilogoManager", RequestType.SERVICE);

        ComplexResponse<ManagerAgenziaTO> response =
                (ComplexResponse<ManagerAgenziaTO>) fc
                .processRequest(request);

        for (ManagerAgenziaTO c : response.getParameters()) {
            AgenziaModel agenzia = getAgenzia(c.agenziaId);

            ManagerAgenziaModel model = new ManagerAgenziaModel(c.username,
                    null, c.nome, c.cognome, agenzia, c.id);
            list.add(model);
        }

        infoTableView.setItems(null);
        infoTableView.layout();
        infoTableView.setItems(FXCollections.observableArrayList(list));

    }

    /**
     * Ricerca informazioni manager agenzia.
     * */

    public final void ricerca() {
        String param1 = nomeRicercaText.getText().trim();
        String param2 = cognomeRicercaText.getText().trim();

        if (!param1.isEmpty() && !param2.isEmpty()) {

            annullaRicercaButton.setVisible(true);

            ManagerAgenziaTO manager = new ManagerAgenziaTO();

            manager.nome = param1;
            manager.cognome = param2;

            ComplexRequest<ManagerAgenziaTO> request =
                    new ComplexRequest<ManagerAgenziaTO>(
                            "ricercareManagerByNomeCognome",RequestType.SERVICE);

            request.addParameter(manager);

            ComplexResponse<ManagerAgenziaTO> response =
                    (ComplexResponse<ManagerAgenziaTO>) fc.processRequest(request);

            list.removeAll(list);
            infoTableView.setItems(null);
            infoTableView.layout();

            if (response.getParameters() != null) {
                for (ManagerAgenziaTO c : response.getParameters()) {
                    AgenziaModel agenzia = getAgenzia(c.agenziaId);

                    ManagerAgenziaModel model = new ManagerAgenziaModel(c.username,
                            null, c.nome, c.cognome, agenzia, c.id);
                    list.add(model);
                }

                infoTableView.setItems(FXCollections.observableArrayList(list));
            }
        } else {
            ShowAlert.showMessage("Inserire il parametro di ricerca",
                    AlertType.WARNING);
        }
    }

    /**
     * Eliminazione manager agenzia.
     * */
    public final void eliminare() {

        ManagerAgenziaModel selectedManager =
                infoTableView.getSelectionModel().getSelectedItem();
        if (selectedManager == null) {
            ShowAlert.showMessage("Nessun manager selezionato",
                    AlertType.WARNING);
        } else {
            if (ShowAlert.showMessage("Confermare l'eliminazione?",
                    AlertType.CONFIRMATION)) {
                // richiesta
                ManagerAgenziaTO to = new ManagerAgenziaTO();
                to.username = selectedManager.getUsernameProperty().getValue();

                ComplexRequest<ManagerAgenziaTO> request =
                        new ComplexRequest<ManagerAgenziaTO>(
                        "eliminareAccountManager", RequestType.SERVICE);
                request.addParameter(to);

                // risposta
                SimpleResponse response = (SimpleResponse) fc.processRequest(request);

                // se la risposta va a buon fine aggiorna la tabella
                if (response.getResponse()) {

                    int selectedIndex = infoTableView.getSelectionModel()
                            .getSelectedIndex();
                    infoTableView.getItems().remove(selectedIndex);
                    ShowAlert.showMessage("Manager eliminato con successo!",
                            AlertType.INFORMATION);
                } else {
                    ShowAlert.showMessage("Impossibile eliminare il manager!",
                            AlertType.INFORMATION);
                }
            }
        }
    }

    /**
     * Fa apparire le field per modificare.
     * */
    private void showModifica() {
        ManagerAgenziaModel selectedManager = infoTableView.getSelectionModel()
                .getSelectedItem();

        if (selectedManager == null) {
            ShowAlert.showMessage("Nessun manager selezionato",
                    AlertType.WARNING);
        } else {
            showAgenzie();
            nomeModifica.setVisible(true);
            cognomeModifica.setVisible(true);
            usernameModifica.setVisible(true);
            agenziaModifica.setVisible(true);
            confermaModifiche.setVisible(true);

            nomeModifica.setText(selectedManager.getNome());
            cognomeModifica.setText(selectedManager.getCognome());
            usernameModifica.setText(selectedManager.getUsernameProperty()
                    .getValue());
            agenziaModifica.setValue(selectedManager.getAgenzia());
        }
    }

    /**
     * Gestisce la modifica del manager.
     * */
    private void modifica() {

        String username = usernameModifica.getText().trim();
        String nome = nomeModifica.getText().trim();
        String cognome = cognomeModifica.getText().trim();
        String id = usernameLabel.getText().trim();
        AgenziaModel agenzia = agenziaModifica.getValue();


        if (!ValidaDatiManager.isEmptyCheck(
                username, "xxxx", nome, cognome, agenzia)
                && ValidaDatiManager.isValidInput(
                username, "xxxx", nome, cognome)) {

            ComplexRequest<ManagerAgenziaTO> request =
                    new ComplexRequest<>("modificareDatiAccountManager",
                            RequestType.SERVICE);
            ManagerAgenziaTO manager = new ManagerAgenziaTO();
            manager.nome = nome;
            manager.cognome = cognome;
            manager.username = username;
            manager.agenziaId = agenzia.getIdProperty().get();

            manager.id = id;
            request.addParameter(manager);

            SimpleResponse response =
                    (SimpleResponse) fc.processRequest(request);

            if (response.getResponse()) {
                SimpleRequest refresh =
                        new SimpleRequest("riepilogoAccountManager",
                        RequestType.UI_VIEW);

                fc.processRequest(refresh);
            } else {
                ShowAlert.showMessage("Dati inseriti non accettati, riprovare!",
                        AlertType.WARNING);
            }
        }

    }

    /**
     * Mostra le agenzie del sistema.
     * */
    private void showAgenzie() {
        ObservableList<AgenziaModel> data = GetCommonData.getRiepilogoAgenzie();
        agenziaModifica.setItems(data);
    }

    /**
     * Restituisce le informazioni su di un'agenzia.
     * @param agenziaId
     *      Id dell'agenzia.
     * @return
     *      Informazioni dell'agenzia.
     * */
    private AgenziaModel getAgenzia(final String agenziaId) {
        ComplexRequest<AgenziaTO> request =
                new ComplexRequest<AgenziaTO>(
                        "ricercareAgenzia", RequestType.SERVICE);

        AgenziaTO agenzia = new AgenziaTO();
        agenzia.id = agenziaId;
        request.addParameter(agenzia);

        ComplexResponse<AgenziaTO> response =
                (ComplexResponse<AgenziaTO>) fc.processRequest(request);

        agenzia = response.getParameters().get(0);

        return new AgenziaModel(agenzia.città, agenzia.indirizzo,
                agenzia.telefono, agenzia.id, null);
    }

    private final void setList(final ObservableList<ManagerAgenziaModel> list) {
        this.list = list;
    }
}
