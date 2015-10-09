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
import transferObjects.entitiesTO.TariffaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.SimpleResponse;
import view.controller.GetCommonData;
import view.controller.ShowAlert;
import view.model.ClasseAutovetturaModel;

/**
 * Constroller per l'interfaccia InserireTariffa.fxml .
 * */
public class InserireTariffaController implements Initializable {

    @FXML
    private ResourceBundle     resources;

    @FXML
    private URL                location;

    @FXML
    private TextField          codiceTariffaText;

    @FXML
    private TextField          importoUnitarioText;

    @FXML
    private TextField          kmGiornoText;

    @FXML
    private Button             indietroButton;

    @FXML
    private Button             inserireButton;

    @FXML
    private ComboBox<String>   modalitàKmComboBox;

    @FXML
    private ComboBox<String>   modalitàNoleggioComboBox;

    @FXML
    private ComboBox<ClasseAutovetturaModel> classeAutovetturaComboBox;

    private FrontControllerInt fc;

    @FXML
    private void inserireButton() {
        this.inserireButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ShowAlert.showMessage("Confermi l'inserimento?",
                        AlertType.CONFIRMATION)) {
                    inserisciTariffa();
                }
            }
        });
    }

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
    private void showKmField() {
        if (modalitàKmComboBox.getValue().equals("Limitato")) {
            kmGiornoText.setDisable(false);
        } else if (modalitàKmComboBox.getValue().equals("Illimitato")) {
            kmGiornoText.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fc = new FrontController();
        showClassiAutovetture();
    }

    /**
     * Gestisce l'inserimento di una tariffa.
     * */
    private void inserisciTariffa() {
        String importoUnitario = importoUnitarioText.getText().trim();
        String modalitàKm = modalitàKmComboBox.getValue().trim();
        String modalitàNoleggio = modalitàNoleggioComboBox.getValue().trim();
        String kmGiorno = "0";

        if (modalitàKm.equals("Limitato")) {
             kmGiorno = kmGiornoText.getText();
        }

        ClasseAutovetturaModel classe =
                classeAutovetturaComboBox.getValue();

        if (!ValidaDatiTariffa.isEmpty(importoUnitario, modalitàKm,
                modalitàNoleggio, classe)
                && ValidaDatiTariffa.isValidInput(importoUnitario, kmGiorno)) {
                TariffaTO to = new TariffaTO();

                to.chilometraggio = modalitàKm;
                to.importoUnitario = Float.parseFloat(importoUnitario);
                to.modalità = modalitàNoleggio;
                to.classeAutovetturaId = classe.getId().getValue();
                to.chilometriGiorno = Float.parseFloat(kmGiorno);

                ComplexRequest<TariffaTO> request =
                        new ComplexRequest<TariffaTO>(
                        "inserireTariffa", RequestType.SERVICE);

                request.addParameter(to);

                SimpleResponse response =
                        (SimpleResponse) fc.processRequest(request);

                if (response.getResponse()) {
                   ShowAlert.showMessage("Tariffa inserita con successo",
                           AlertType.INFORMATION);
                } else {
                    ShowAlert.showMessage("Dati inseriti non accettati, riprovare!",
                            AlertType.WARNING);
                }
            }
    }

    /**
     * Mostra tutte le classi autovettura disponibili.
     * */
    private void showClassiAutovetture() {
        classeAutovetturaComboBox.setItems(
                GetCommonData.getRiepilogoClassiAutovettura());
    }
}
