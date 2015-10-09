package view.controller.amministratoreSistema;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.ClasseAutovetturaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.SimpleResponse;
import view.controller.ShowAlert;

/**
 * Controller per la classe InserireClasseAutovettura.fxml .
 * */
public class InserireClasseAutovettureController implements Initializable {

    @FXML
    private ResourceBundle     resources;

    @FXML
    private URL                location;

    @FXML
    private TextField          numeroPostiText;

    @FXML
    private ComboBox<String>   ariaCondizionataComboBox;

    @FXML
    private Button             indietroButton;

    @FXML
    private Button             inserireButton;

    @FXML
    private TextField          nomeClasseText;

    @FXML
    private TextField          numeroPorteText;

    @FXML
    private ComboBox<String>   tipoCambioComboBox;

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

    @FXML
    private void inserireButton() {
        this.inserireButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ShowAlert.showMessage("Confermare l'inserimento?",
                        AlertType.CONFIRMATION)) {
                inserireClasseAutovettura();
                }
            }
        });
    }

    /**
     * Gestisce l'inserimento di una classe autovettura.
     * */
    public final void inserireClasseAutovettura() {

        String nome = nomeClasseText.getText().trim();
        String numeroPorte = numeroPorteText.getText().trim();
        String numeroPosti = numeroPostiText.getText().trim();
        String ariaCondizionata = ariaCondizionataComboBox.getValue().trim();
        String tipoCambio = tipoCambioComboBox.getValue().trim();

        if (!ValidaDatiClasseAutovettura.isEmptyCheck(nome, numeroPorte,
                numeroPosti, ariaCondizionata, tipoCambio)
                && ValidaDatiClasseAutovettura.isValidInput(
                        nome, numeroPorte, numeroPosti, ariaCondizionata,
                        tipoCambio)) {

            ClasseAutovetturaTO to = new ClasseAutovetturaTO();
            if (ariaCondizionata.equals("SI")) {
                to.ariaCondizionata = 1;
            } else {
                to.ariaCondizionata = 0;
            }

            to.nome = nome;
            to.numeroPorte = Integer.parseInt(numeroPorte);
            to.numeroPosti = Integer.parseInt(numeroPosti);
            to.tipoCambio = tipoCambio;

            ComplexRequest<ClasseAutovetturaTO> request =
                    new ComplexRequest<ClasseAutovetturaTO>(
                    "inserireClasseAutovettura", RequestType.SERVICE);

            request.addParameter(to);

            SimpleResponse response =
                    (SimpleResponse) fc.processRequest(request);

            if (response.getResponse()) {
                ShowAlert.showMessage("Classe autovettura inserita "
                        + "con successo", AlertType.INFORMATION);
                nomeClasseText.clear();
                numeroPorteText.clear();
                numeroPostiText.clear();
                ariaCondizionataComboBox.setValue(null);
                tipoCambioComboBox.setValue(null);

            } else {
                ShowAlert.showMessage("Dati inseriti non accettati, riprovare!"
                        , AlertType.ERROR);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fc = new FrontController();
    }
}
