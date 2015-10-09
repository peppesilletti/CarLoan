package view.controller.managerAgenzia;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;

/**
 * Controller per l'interfaccia PannelloManager.fxml .
 * */
public class PannelloManagerController implements Initializable {

    @FXML
    private Button             inserireAccountOperatoreButton;

    @FXML
    private Insets             x1;

    @FXML
    private Button             riepilogoAccountOperatoriButton;

    @FXML
    private Button             inserireManutenzioneButton;

    @FXML
    private Button             inserireAutovetturaButton;

    @FXML
    private Button             riepilogoAutovettureButton;

    @FXML
    private Button             riepilogoManutenzioniButton;

    @FXML
    private Pane               loginNomeManagerLabel;

    @FXML
    private Label              benvenutoLabel;

    @FXML
    private Button             logoutButton;

    private FrontControllerInt fc;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        fc = new FrontController();
    }

    @FXML
    public void inserireAccountOperatoreButton() {
        this.inserireAccountOperatoreButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "inserireAccountOperatore", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void riepilogoAccountOperatoriButton() {
        this.riepilogoAccountOperatoriButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "riepilogoAccountOperatore",
                                RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void inserireManutenzioneButton() {
        this.inserireManutenzioneButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "inserireManutenzione", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void inserireAutovetturaButton() {
        this.inserireAutovetturaButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "inserireAutovettura", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void riepilogoAutovettureButton() {
        this.riepilogoAutovettureButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "riepilogoAutovetture", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void riepilogoManutenzioniButton() {
        this.riepilogoManutenzioniButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "riepilogoManutenzioni", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

}
