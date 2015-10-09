package view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import presentation.SessionHandler;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;

/**
 * Controller per l'interfaccia {@link HeaderInfo.fxml}.
 * */
public class HeaderInfoController implements Initializable {

    @FXML
    private Label              usernameLabel;

    @FXML
    private Label              tipoLabel;

    @FXML
    private Button             logoutButton;

    /**
     * Istanza del Front Controller.
     * */
    private FrontControllerInt fc;

    @Override
    public  void initialize(URL arg0, ResourceBundle arg1) {
        fc = new FrontController();
        usernameLabel.setText(SessionHandler.currentUser);
        tipoLabel.setText(SessionHandler.currentUserType.toUpperCase());
    }

    @FXML
    public final void logoutButton() {
        this.logoutButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                logout();
            }
        });
    }

    /**
     * Metodo che permette il logout dell'utente corrente.
     * */
    public final void logout() {
        SessionHandler.reset();

        SimpleRequest request =
                new SimpleRequest("logout", RequestType.UI_VIEW);

        fc.processRequest(request);
    }
}
