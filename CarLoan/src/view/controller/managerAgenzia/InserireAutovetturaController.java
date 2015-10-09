package view.controller.managerAgenzia;

import java.io.File;
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
import javafx.scene.layout.GridPane;
import presentation.FrontController;
import presentation.FrontControllerInt;
import presentation.SessionHandler;
import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.SimpleResponse;
import view.controller.GetCommonData;
import view.controller.ShowAlert;
import view.model.ClasseAutovetturaModel;

/**
 * Controller per l'interfaccia InserireAutovettura.fxml .
 * */
public class InserireAutovetturaController implements Initializable {

    @FXML
    private ResourceBundle     resources;

    @FXML
    private URL location;

    @FXML
    private TextField chilometriPercorsiText;

    @FXML
    private ComboBox<ClasseAutovetturaModel>   classeAutovetturaComboBox;

    @FXML
    private ComboBox<String>   alimentazioneComboBox;

    @FXML
    private Button indietroButton;

    @FXML
    private GridPane infoGridPane;

    @FXML
    private Button inserireButton;

    @FXML
    private TextField marcaText;

    @FXML
    private TextField modelloText;

    @FXML
    private TextField targaText;

    @FXML
    private Button scegliImmagine;

    private FrontControllerInt fc;
    private File filePath;


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
                    inserireAutovettura();
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fc = new FrontController();
        showClassiAutovetture();
    }

    /**
     * Gestisce l'inserimento di un'autovettura.
     * */
    public final void inserireAutovettura() {

        String targa = targaText.getText().trim();
        String marca = marcaText.getText().trim();
        String modello = modelloText.getText().trim();
        String chilometraggio = chilometriPercorsiText
                .getText().trim();
        String alimentazione = alimentazioneComboBox.getValue();
        String agenziaId = SessionHandler.currentAgenziaId;
        ClasseAutovetturaModel classeAutovettura =
                classeAutovetturaComboBox.getValue();

        File immagineAuto = getImmagineAuto();

        if (!ValidaDatiAutovettura.isEmptyCheck(targa, modello, marca,
                chilometraggio, classeAutovettura, alimentazione)
            && ValidaDatiAutovettura.isValidInput(targa, modello, marca,
                    chilometraggio)) {

            AutovetturaTO autovettura = new AutovetturaTO(targa, marca,
                    modello, Float.parseFloat(chilometraggio),
                    alimentazione, agenziaId,
                    classeAutovettura.getId().get());

            ComplexRequest<AutovetturaTO> request =
                    new ComplexRequest<AutovetturaTO>(
                    "inserireAutovettura", RequestType.SERVICE);

            request.addParameter(autovettura);



            SimpleResponse response =
                    (SimpleResponse) fc.processRequest(request);

            if (response.getResponse()) {
                ShowAlert.showMessage("Autovettura inserita con successo",
                        AlertType.INFORMATION);

                targaText.clear();
                marcaText.clear();
                modelloText.clear();
                chilometriPercorsiText.clear();
                alimentazioneComboBox.setValue(null);
                classeAutovetturaComboBox.setValue(null);


            } else {
                ShowAlert.showMessage("Dati inseriti non accettati, riprovare!",
                        AlertType.WARNING);
            }
        }
    }


    /**
     * Restituisce tutte le classi autovettura.
     * */
    private void showClassiAutovetture() {
        classeAutovetturaComboBox.setItems(
                GetCommonData.getRiepilogoClassiAutovettura());
    }

    private File getImmagineAuto() {
        return this.filePath;
    }
}