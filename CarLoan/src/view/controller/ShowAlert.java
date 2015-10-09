package view.controller;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Mostra messaggi di errore sotto forma di alert.
 * */
public class ShowAlert {

    private ShowAlert instance = new ShowAlert();

    private ShowAlert() {

    }

    public static Boolean showMessage(String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }

}
