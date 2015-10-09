package view.controller.amministratoreSistema;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.SimpleResponse;
import view.controller.GetCommonData;
import view.controller.ShowAlert;
import view.model.AgenziaModel;

/**
 * Controller per l'interfacia InserireAccountManager.fxml .
 * */
public class InserireAccountManagerController implements Initializable {

    @FXML
    private ResourceBundle     resources;

    @FXML
    private URL                location;

    @FXML
    private TextField          cognomeText;

    @FXML
    private Button             indietroButton;

    @FXML
    private Button             inserireButton;

    @FXML
    private TextField          nomeText;

    @FXML
    private PasswordField      passwordText;

    @FXML
    private TextField          usernameText;

    @FXML
    private ComboBox<AgenziaModel>   agenziaComboBox;

    private FrontControllerInt fc;

    @Override
    public final void initialize(final URL arg0, final ResourceBundle arg1) {
        fc = new FrontController();
        showAgenzie();
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
    private void inserireButton() {
        this.inserireButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode().getName().equals("Enter")) {
                    if (ShowAlert.showMessage("Confermi l'inserimento?",
                            AlertType.CONFIRMATION)) {
                        inserireAccountManager();
                    }
                }
            }
        });
        this.inserireButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (ShowAlert.showMessage("Confermi l'inserimento?",
                        AlertType.CONFIRMATION)) {
                    inserireAccountManager();
                }
            }
        });
    }

    /**
     * Invia una richiesta al front controller.
     */
    @FXML
    private void inserireAccountManager() {
        String username = usernameText.getText().trim();
        String password = passwordText.getText().trim();
        String nome = nomeText.getText().trim();
        String cognome = cognomeText.getText().trim();
        AgenziaModel agenzia = agenziaComboBox.getValue();


        if (!ValidaDatiManager.isEmptyCheck(
                username, password, nome, cognome, agenzia)
                && ValidaDatiManager.isValidInput(
                username, password, nome, cognome)) {
            ManagerAgenziaTO manager = new ManagerAgenziaTO(
                    username, password, nome,
                    cognome, agenzia.getIdProperty().get());

            ComplexRequest<ManagerAgenziaTO> request =
                    new ComplexRequest<ManagerAgenziaTO>(
                    "inserireAccountManager", RequestType.SERVICE);
            request.addParameter(manager);

            SimpleResponse response = (SimpleResponse) fc
                    .processRequest(request);

            if (response.getResponse()) {
                ShowAlert.showMessage("Manager inserito con successo!",
                        AlertType.INFORMATION);
                usernameText.clear();
                passwordText.clear();
                nomeText.clear();
                cognomeText.clear();
                agenziaComboBox.setValue(null);
            } else {
                ShowAlert.showMessage("Dati inseriti non accettati, riprovare",
                        AlertType.ERROR);
            }

        }

    }


    /**
     * Seleziona tutte le agenzie dal datastore.
     * */
    private void showAgenzie() {
        ObservableList<AgenziaModel> data = GetCommonData.getRiepilogoAgenzie();
        agenziaComboBox.setItems(data);
    }



}
