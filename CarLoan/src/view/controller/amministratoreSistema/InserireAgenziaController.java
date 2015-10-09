package view.controller.amministratoreSistema;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.SimpleResponse;
import view.controller.ShowAlert;

/**
 * Controller per l'interfaccia InserireAgenzia.fxml .
 * */
public class InserireAgenziaController implements Initializable {

    @FXML
    private TextField indirizzoText;

    @FXML
    private Button inserireButton;

    @FXML
    private TextField telefonoText;

    @FXML
    private Button indietroButton;

    @FXML
    private TextField cittaText;

    private FrontControllerInt fc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fc = new FrontController();
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
                        inserireAgenzia();
                    }
                }
            }
        });
        this.inserireButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (ShowAlert.showMessage("Confermi l'inserimento?",
                        AlertType.CONFIRMATION)) {
                        inserireAgenzia();
                }
            }
        });
    }

    /**
     * Gestisce l'inserimento di un'agenzia.
     * */
    private void inserireAgenzia() {
        String città = cittaText.getText().trim();
        String indirizzo = indirizzoText.getText().trim();
        String telefono = telefonoText.getText().trim();

        if (!ValidareDatiAgenzia.isEmptyCheck(
                città, indirizzo, telefono)
            && ValidareDatiAgenzia.isValidInput(
                città, indirizzo, telefono)) {

            AgenziaTO agenzia = new AgenziaTO(
                    città, indirizzo, telefono);

            ComplexRequest<AgenziaTO> request =
                    new ComplexRequest<AgenziaTO>(
                    "inserireAgenzia", RequestType.SERVICE);
            request.addParameter(agenzia);

            SimpleResponse response = (SimpleResponse) fc
                    .processRequest(request);

            if (response.getResponse()) {
                ShowAlert.showMessage("Agenzia inserita con successo!",
                        AlertType.INFORMATION);
                cittaText.clear();
                indirizzoText.clear();
                telefonoText.clear();
            } else {
                ShowAlert.showMessage("Dati inseriti non accettati, riprovare",
                        AlertType.ERROR);
            }

        }
      }
}
