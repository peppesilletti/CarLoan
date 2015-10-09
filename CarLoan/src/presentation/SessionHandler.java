package presentation;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe che contiene tutte le informazioni sulla sessione corrente del
 * sistema.
 * */
public final class SessionHandler {

    /**
     * Scena UI corrente.
     * */
    public static Scene currentScene;

    /**
     * Stage UI aperto.
     * */
    public static Stage currentStage;

    /**
     * Stage UI precedente.
     * */

    public static Stage previousStage;
    /**
     * Utente autenticato.
     * */
     public static String currentUser;

     /**
      * Tipo dell'utente autenticato.
      * */
     public static String currentUserType;

     /**
      * Agenzia corrente.
      * */
     public static String currentAgenziaId;

    /**
     * Resetta lo stato della sessione.
     */
    public static void reset() {
        SessionHandler.currentUser = null;
        SessionHandler.currentUserType = null;
    }
}
