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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.ManutenzioneTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.SimpleResponse;
import view.controller.GetCommonData;
import view.controller.ShowAlert;
import view.model.AutovetturaModel;

/**
 * Controller per l'interfaccia InserireManutenzioneController.
 * */
public class InserireManutenzioneController implements Initializable {

    @FXML
    private TextField dataInizio;

    @FXML
    private ComboBox<String> tipoManutenzioneComboBox;

    @FXML
    private ComboBox<AutovetturaModel> autovetturaComboBox;

    @FXML
    private Button inserireButton;

    @FXML
    private Button indietroButton;

    private FrontControllerInt         fc;

    ObservableList<AutovetturaModel> data =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fc = new FrontController();
        getAutovetture();
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
    private void inserireButton() {
        this.inserireButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ShowAlert.showMessage("Confermare l'inserimento?",
                        AlertType.CONFIRMATION)) {
                    inserireManutenzione();
                }
            }
        });
    }

     /**
     * Gestisce l'inserimento di una manutenzione.
     * */
    private void inserireManutenzione() {

        String tipo = tipoManutenzioneComboBox.getValue();
        String data1 = dataInizio.getText();
        AutovetturaModel autovettura = autovetturaComboBox.getValue();

        if (!ValidaDatiManutenzione.isEmpty(data1, tipo, autovettura, "x")
                && ValidaDatiManutenzione.isValidInput(data1,"00/00/0000")) {

            ManutenzioneTO to = new ManutenzioneTO(
                    autovettura.getId().get(), tipo, data1);

            ComplexRequest<ManutenzioneTO> request =
                    new ComplexRequest<ManutenzioneTO>(
                    "inserireManutenzione", RequestType.SERVICE);

            request.addParameter(to);

            SimpleResponse response =
                    (SimpleResponse) fc.processRequest(request);

            if (response.getResponse()) {
                ShowAlert.showMessage("Manutenzione inserita con successo!",
                        AlertType.INFORMATION);
            } else {
                ShowAlert.showMessage("Dati inseriti non accettati, riprovare!",
                        AlertType.WARNING);
            }
        }
    }

    /**
     * Restituisce tutte le autovetture.
     * */
    private void getAutovetture() {
        autovetturaComboBox.setItems(GetCommonData.getRiepilogoAutovetture());
    }

}
