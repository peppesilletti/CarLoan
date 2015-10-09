package view.controller.amministratoreSistema;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.ExtraTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.SimpleResponse;
import view.controller.ShowAlert;

/**
 * Controller per l'interfaciia InserireExtra.fxml .
 * */
public class InserireExtraController implements Initializable {

    @FXML
    private ResourceBundle     resources;

    @FXML
    private URL                location;

    @FXML
    private Button             indietroButton;

    @FXML
    private Button             inserireButton;

    @FXML
    private TextField          nomeText;

    @FXML
    private TextArea           descrizioneText;

    @FXML
    private TextField          prezzoText;

    private FrontControllerInt fc;

    @FXML
    private void indietroButton() {
        this.indietroButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SimpleRequest request = new SimpleRequest(
                        "PannelloAmministratore", RequestType.UI_VIEW);
                fc.processRequest(request);
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fc = new FrontController();
    }

    @FXML
    private void inserireButton() {
        this.inserireButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ShowAlert.showMessage("Confermi l'inserimento?",
                        AlertType.CONFIRMATION)) {
                inserireExtra();
                }
            }
        });
    }

    /**
     * Gestisce l'inserimento dell'extra.
     * */
    public final void inserireExtra() {

        String nome = nomeText.getText().trim();
        String prezzo = prezzoText.getText().trim();
        String descrizione = descrizioneText.getText().trim();

        if (!ValidaDatiExtra.isEmpty(nome, prezzo, descrizione)
                && ValidaDatiExtra.isValidInput(nome, prezzo, descrizione)) {
            ExtraTO to = new ExtraTO();

            to.nome = nome;
            to.prezzo = Float.parseFloat(prezzo);
            to.descrizione = descrizione;

            ComplexRequest<ExtraTO> request = new ComplexRequest<ExtraTO>(
                    "inserireExtra", RequestType.SERVICE);

            request.addParameter(to);

            SimpleResponse response =
                    (SimpleResponse) fc.processRequest(request);

            if (response.getResponse()) {
                ShowAlert.showMessage("Extra inserito con successo",
                        AlertType.INFORMATION);
            } else {
                ShowAlert.showMessage("Dati inseriti non accettati, riprovare!",
                        AlertType.WARNING);
            }
        }
    }

}
