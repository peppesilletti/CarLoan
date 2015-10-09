package errorsHandling;

import java.util.Optional;
import java.util.logging.Level;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Mostra messaggi di errore graficamente.
 * */
public final class ShowErrors {

    /**
     * Unica istanza disponibile della classe.
     * */
    private ShowErrors istance = new ShowErrors();

    /**
     * Costruttore privato per realizzare il singleton.
     * */
    private ShowErrors() {
    }

    /**
     * Stampa l'errore a video.
     * @param level
     *      Livello di gravità dell'errore.
     * */
    public static void showError(final Level level, final String message) {
        if (level.equals(ErrorHandlerInt.FATAL)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Si è verificato un errore fatale");
            alert.setContentText(message);
            alert.setResizable(true);
            alert.setHeight(100);
            alert.setWidth(300);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
               Platform.exit();
            }
        } else if (level.equals(ErrorHandlerInt.WARNING)) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Si è verificato un errore");
            alert.setContentText(message);
            alert.setResizable(true);
            alert.setHeight(100);
            alert.setWidth(300);
            alert.show();
        }
    }

    /**
     * Restituisce l'istanza della classe.
     *
     * @return l'istanza della classe.
     * */
    public ShowErrors getIstance() {
        return istance;
    }
}
