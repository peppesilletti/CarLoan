package view.controller.amministratoreSistema;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;

/**
 * Controller per l'interfaccia PannelloAmministratore.fxml .
 * */
public class PannelloAmministratoreController implements Initializable {

    private FrontControllerInt fc;

    @FXML
    private GridPane           buttonGridPane;

    @FXML
    private Button             inserireAccountManagerButton;

    @FXML
    private Button             riepilogoTariffeButton;

    @FXML
    private Button             inserireTariffaButton;

    @FXML
    private Button             inserireClasseAutovetturaButton;

    @FXML
    private Button             inserireExtraButton;

    @FXML
    private Button             riepilogoAccountManagerButton;

    @FXML
    private Button             riepilogoClasseAutovetturaButton;

    @FXML
    private Button             riepilogoExtraButton;

    @FXML
    private Button             inserireAgenziaButton;

    @FXML
    private Button             riepilogoAgenzieButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        fc = new FrontController();
    }

    @FXML
    public void inserireAccountManagerButton() {
        this.inserireAccountManagerButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "inserireAccountManager", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void inserireTariffaButton() {
        this.inserireTariffaButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "inserireTariffa", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void inserireClasseAutovetturaButton() {
        this.inserireClasseAutovetturaButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "inserireClasseAutovettura",
                                RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void inserireExtraButton() {
        this.inserireExtraButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "inserireExtra", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void inserireAgenziaButton() {
        this.inserireAgenziaButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "inserireAgenzia", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void riepilogoAgenzieButton() {
        this.riepilogoAgenzieButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "riepilogoAgenzie", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void riepilogoTariffeButton() {
        this.riepilogoTariffeButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "riepilogoTariffe", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void riepilogoAccountManagerButton() {
        this.riepilogoAccountManagerButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "riepilogoAccountManager", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void riepilogoClasseAutovetturaButton() {
        this.riepilogoClasseAutovetturaButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "riepilogoClasseAutovettura",
                                RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

    @FXML
    public void riepilogoExtraButton() {
        this.riepilogoExtraButton
                .setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        SimpleRequest request = new SimpleRequest(
                                "riepilogoExtra", RequestType.UI_VIEW);
                        fc.processRequest(request);
                    }
                });
    }

}
