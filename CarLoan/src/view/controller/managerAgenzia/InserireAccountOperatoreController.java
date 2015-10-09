package view.controller.managerAgenzia;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import presentation.SessionHandler;
import transferObjects.entitiesTO.OperatoreTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.SimpleResponse;
import view.controller.ShowAlert;

import commands.gestioneOperatore.InserireAccountOperatore;

/**
 * Controllore per l'interfaccia grafica {@link InserireAccountOperatore.fxml}.
 * */
public class InserireAccountOperatoreController implements Initializable {

    @FXML
    private ResourceBundle     resources;

    @FXML
    private URL                location;

    @FXML
    private TextField          cognomeText;

    @FXML
    private Button             indietroButton;

    @FXML
    private Button             inserisciButton;

    @FXML
    private TextField          nomeText;

    @FXML
    private PasswordField      passwordText;

    @FXML
    private TextField          usernameText;

    /**
     * Istanza del Front Controller.
     * */
    private FrontControllerInt fc;

    @Override
    public final void initialize(URL location, ResourceBundle resources) {
        fc = new FrontController();
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
    private void inserisciButton() {
        this.inserisciButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().getName().equals("Enter")) {
                    if (ShowAlert.showMessage("Confermi l'inserimento?",
                            AlertType.CONFIRMATION)) {
                    inserireAccountOperatore();
                    }
                }
            }
        });
        this.inserisciButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ShowAlert.showMessage("Confermi l'inserimento?",
                        AlertType.CONFIRMATION)) {
                    inserireAccountOperatore();
                }
            }
        });
    }

    /**
     * Invia una richiesta per inserire un account operatore al front
     * controller.
     */
    @FXML
    private void inserireAccountOperatore() {

        String username = usernameText.getText().trim();
        String password = passwordText.getText().trim();
        String nome = nomeText.getText().trim();
        String cognome = cognomeText.getText().trim();
        String agenziaId = SessionHandler.currentAgenziaId;

        if (!ValidaDatiOperatore.isEmptyCheck(username, password,
                nome, cognome)
           && ValidaDatiOperatore.isValidInput(username, password,
                        nome, cognome)) {

            OperatoreTO operatore = new OperatoreTO(username,
                   password, nome, cognome,
                    agenziaId);

            ComplexRequest<OperatoreTO> request =
                    new ComplexRequest<OperatoreTO>(
                    "inserireAccountOperatore", RequestType.SERVICE);
            request.addParameter(operatore);

            SimpleResponse response = (SimpleResponse) fc
                    .processRequest(request);

            if (response.getResponse()) {
                ShowAlert.showMessage("Operatore inserito con successo",
                        AlertType.WARNING);
            } else {
                ShowAlert.showMessage("Dati inseriti non accettati, riprovare!",
                        AlertType.WARNING);
            }

        }
    }

}
