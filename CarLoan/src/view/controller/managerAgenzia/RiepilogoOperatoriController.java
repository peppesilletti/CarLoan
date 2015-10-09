package view.controller.managerAgenzia;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import presentation.FrontController;
import presentation.FrontControllerInt;
import presentation.SessionHandler;
import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.OperatoreTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.ComplexResponse;
import transferObjects.response.SimpleResponse;
import view.controller.ShowAlert;
import view.model.OperatoreModel;

public class RiepilogoOperatoriController implements Initializable {

    @FXML
    private TableView<OperatoreModel>           infoTableView;

    @FXML
    private TableColumn<OperatoreModel, String> nomeColonna;

    @FXML
    private TableColumn<OperatoreModel, String> cognomeColonna;

    @FXML
    private Label valueNomeLabel;

    @FXML
    private Label valueCognomeLabel;

    @FXML
    private Label valueUsernameLabel;

    @FXML
    private Button modificaButton;

    @FXML
    private Button eliminaButton;

    @FXML
    private TextField nomeText;

    @FXML
    private TextField cognomeText;

    @FXML
    private Button ricercaButton;

    @FXML
    private Button indietroButton;

    @FXML
    private TextField nomeModifica;

    @FXML
    private TextField cognomeModifica;

    @FXML
    private TextField usernameModifica;

    @FXML
    private Button confermaModifiche;

    @FXML
    private Button annullaRicercaButton;

    private FrontControllerInt  fc;

    private ObservableList<OperatoreModel>      list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fc = new FrontController();

        setList(FXCollections.observableArrayList());

        nomeColonna
                .setCellValueFactory(new PropertyValueFactory<OperatoreModel, String>(
                        "nome"));

        cognomeColonna
                .setCellValueFactory(new PropertyValueFactory<OperatoreModel, String>(
                        "cognome"));

        riepilogo();

        // Clear operatore details
        infoOperatore(null);

        infoTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> infoOperatore(newValue));

    }

    /**
     * Metodo che permette di visualizzare le informazioni di un operatore
     * cliccatoci sopra.
     * */
    public void infoOperatore(OperatoreModel model) {
        if (model != null) {
            valueNomeLabel.setText(model.getNome());
            valueCognomeLabel.setText(model.getCognome());
            valueUsernameLabel.setText(model.getUsernameProperty().getValue());
        } else {
            valueNomeLabel.setText("");
            valueCognomeLabel.setText("");
            valueUsernameLabel.setText("");
        }
    }


    @FXML
    public void ricercaButton() {

        this.ricercaButton.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    ricerca();
                }
            }
        });

        this.cognomeText.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    ricerca();
                }
            }
        });

        this.ricercaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ricerca();
            }
        });

    }

    @FXML
    public void eliminaButton() {

        this.eliminaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                eliminare();
            }
        });
    }

    @FXML
    public void modificaButton() {
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
            public void handle(MouseEvent event) {
                SimpleRequest request = new SimpleRequest("PannelloManager",
                        RequestType.UI_VIEW);
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
    private void annullaRicercaButton() {
        this.annullaRicercaButton.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                SimpleRequest request = new SimpleRequest(
                        "riepilogoAccountOperatore", RequestType.UI_VIEW);
                fc.processRequest(request);
            }
        });
    }

    /**
     * Mostra l'elenco di operatori presenti nel sistema.
     * */
    public final void riepilogo() {

        ComplexRequest<AgenziaTO> request =
                new ComplexRequest<AgenziaTO>(
                "riepilogoOperatoriAgenzia", RequestType.SERVICE);

        AgenziaTO agenzia = new AgenziaTO();
        agenzia.id = SessionHandler.currentAgenziaId;

        request.addParameter(agenzia);

        ComplexResponse<OperatoreTO> response =
                (ComplexResponse<OperatoreTO>) fc.processRequest(request);

        for (OperatoreTO c : response.getParameters()) {
            OperatoreModel model = new OperatoreModel(c.username,
                    c.nome, c.cognome);
            list.add(model);
        }

        infoTableView.setItems(null);
        infoTableView.layout();
        infoTableView.setItems(FXCollections.observableArrayList(list));

    }

    /**
     * Gestisce l'eliminazione di un operatore.
     * */
    public final void eliminare() {

        OperatoreModel selectedOperatore =
                infoTableView.getSelectionModel().getSelectedItem();

        if (selectedOperatore != null) {

            if (ShowAlert.showMessage("Confermi l'eliminazione?",
                    AlertType.CONFIRMATION)) {

                // richiesta
                OperatoreTO to = new OperatoreTO();
                to.username = selectedOperatore.getUsernameProperty()
                        .getValue();

                ComplexRequest<OperatoreTO> request =
                        new ComplexRequest<OperatoreTO>(
                        "eliminareAccountOperatore", RequestType.SERVICE);
                request.addParameter(to);

                // risposta
                SimpleResponse response =
                        (SimpleResponse) fc.processRequest(request);

                // se la risposta va a buon fine aggiorna la tabella
                if (response.getResponse()) {

                    int selectedIndex = infoTableView.getSelectionModel()
                            .getSelectedIndex();

                    infoTableView.getItems().remove(selectedIndex);

                    ShowAlert.showMessage("Operatore eliminato con successo!",
                            AlertType.INFORMATION);
                }
            }
        } else {
            ShowAlert.showMessage("Nessun operatore selezionato",
                    AlertType.WARNING);
        }
    }

    /**
     * Gestisce la modifica di un extra.
     * */
    public final void modifica() {
        OperatoreModel selectedModel =
                infoTableView.getSelectionModel().getSelectedItem();

        String nome = nomeModifica.getText();
        String cognome = cognomeModifica.getText();
        String username = usernameModifica.getText();
        String id = selectedModel.getUsernameProperty().get();

        if (!ValidaDatiOperatore.isEmptyCheck(username, "xxx",
                nome, cognome)
           && ValidaDatiOperatore.isValidInput(username, "xxx",
                        nome, cognome)) {

            OperatoreTO operatore = new OperatoreTO(username, cognome,
                    nome, id);

            ComplexRequest<OperatoreTO> request =
                    new ComplexRequest<OperatoreTO>(
                    "modificareDatiAccountOperatore", RequestType.SERVICE);
            request.addParameter(operatore);

            SimpleResponse response = (SimpleResponse) fc
                    .processRequest(request);

            if (response.getResponse()) {
                SimpleRequest refresh =
                        new SimpleRequest("riepilogoAccountOperatore",
                        RequestType.UI_VIEW);

                fc.processRequest(refresh);
            } else {
                ShowAlert.showMessage("Dati inseriti non accettati, riprovare!",
                        AlertType.WARNING);
            }

        }

    }

    /**
     * Mostra i text field per effettuare le modifiche.
     * */
    private void showModifica() {
        OperatoreModel selectedOperatore =
                infoTableView.getSelectionModel().getSelectedItem();

        if (selectedOperatore == null) {
            ShowAlert.showMessage("Nessun operatore selezionato",
                    AlertType.WARNING);
        } else {
            nomeModifica.setVisible(true);
            cognomeModifica.setVisible(true);
            usernameModifica.setVisible(true);
            confermaModifiche.setVisible(true);

            nomeModifica.setText(selectedOperatore.getNome());
            cognomeModifica.setText(selectedOperatore.getCognome());
            usernameModifica.setText(selectedOperatore.getUsernameProperty()
                    .get());
        }
    }

    /**
     * Gestisce la ricerca di un operatore.
     * */
    private void ricerca() {
        String param1 = nomeText.getText().trim();
        String param2 = cognomeText.getText().trim();

        if (!param1.isEmpty() && !param2.isEmpty()) {

            annullaRicercaButton.setVisible(true);

            OperatoreTO operatore = new OperatoreTO();

            operatore.nome = param1;
            operatore.cognome = param2;

            ComplexRequest<OperatoreTO> request =
                    new ComplexRequest<OperatoreTO>(
                            "ricercareOperatoreByNomeCognome",
                            RequestType.SERVICE);

            request.addParameter(operatore);

            ComplexResponse<OperatoreTO> response =
                    (ComplexResponse<OperatoreTO>) fc.processRequest(request);

            list.removeAll(list);

            infoTableView.setItems(null);
            infoTableView.layout();

            if (response.getParameters() != null) {
                for (OperatoreTO c : response.getParameters()) {
                    OperatoreModel model = new OperatoreModel(c.username,
                            c.nome, c.cognome);
                    list.add(model);
                }

                infoTableView.setItems(FXCollections.observableArrayList(list));
            }
        } else {
            ShowAlert.showMessage("Inserire il parametro di ricerca",
                    AlertType.WARNING);
        }
    }

    public void setList(ObservableList<OperatoreModel> list) {
        this.list = list;
    }

}
