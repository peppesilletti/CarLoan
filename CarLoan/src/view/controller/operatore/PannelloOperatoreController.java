package view.controller.operatore;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;

/**
 * Controller per l'interfaccia PannelloOperatoreController.fxml.
 * */
public class PannelloOperatoreController implements Initializable {

    @FXML
    private Button apriContrattoButton;

    @FXML
    private Button riepilogoClientiButton;

    /**
     * Istanza del Front Controller.
     * */
    private FrontControllerInt fc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fc = new FrontController();
    }

    @FXML
    public void apriContrattoButton() {
        this.apriContrattoButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "aprireContratto", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void riepilogoClientiButton() {
        this.riepilogoClientiButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "riepilogoClienti", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

}
