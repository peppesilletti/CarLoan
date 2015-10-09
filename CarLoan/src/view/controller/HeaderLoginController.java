package view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.AnonimoTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.response.SimpleResponse;
import view.validazioni.TipoValidazione;
import view.validazioni.Validazione;
import view.validazioni.ValidazioneFactory;

/**
 * Controller per l'interfaccia {@link HeaderLogin}.
 * */
public class HeaderLoginController implements Initializable {

    @FXML
    private ResourceBundle     resources;

    @FXML
    private URL                location;

    @FXML
    private Button             accediButton;

    @FXML
    private Button             amministraButton;

    @FXML
    private PasswordField      passwordText;

    @FXML
    private TextField          usernameText;

    /**
     * Istanza del front controller.
     * */
    private FrontControllerInt fc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fc = new FrontController();
    }

    @FXML
    private void loginButton() {

        this.passwordText.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    login();
                }
            }
        });

        this.accediButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                login();
            }
        });
    }


    /**
     * Metodo che controlla se tutti i campi sono stati riempiti.
     *
     * @param username
     *            Campo username da controllare
     * @param password
     *            Campo password da controllare
     * @return Vero se i campi sono vuoti
     * */
    private Boolean isValidInput(final String username, final String password) {

        if (username.isEmpty() || password.isEmpty()) {
            ShowAlert.showMessage("Riempire i campi lasciati vuoti.",
                    AlertType.ERROR);
            return false;
        } else {
            Validazione checkUsername = ValidazioneFactory
                    .getValidazione(TipoValidazione.USERNAME);
            Validazione checkPassword = ValidazioneFactory
                    .getValidazione(TipoValidazione.PASSWORD);

            if (!checkUsername.valida(username)
                    || !checkPassword.valida(password)) {

                if (ShowAlert.showMessage(
                        "Formato username o password non valido",
                        AlertType.ERROR)) {
                   usernameText.clear();
                   passwordText.clear();
                }
                return false;
            }

        }
        return true;
    }

    /**
     * Invia una richiesta di login al front controller.
     */
    private void login() {
        AnonimoTO dati = new AnonimoTO();
        String username = usernameText.getText();
        String password = passwordText.getText();

        if (isValidInput(username, password)) {
            dati.username = username;
            dati.password = password;

            ComplexRequest<AnonimoTO> request =
                    new ComplexRequest<AnonimoTO>("login", RequestType.SERVICE);
            request.addParameter(dati);

            SimpleResponse response =
                    (SimpleResponse) fc.processRequest(request);

            if (!response.getResponse()) {
                if (ShowAlert.showMessage(
                        "Username o password non corretti", AlertType.ERROR)) {
                   usernameText.clear();
                   passwordText.clear();
                }
            }
    }

    }
}
