package application;

import javafx.application.Application;
import javafx.stage.Stage;
import presentation.FrontController;
import presentation.FrontControllerInt;
import presentation.SessionHandler;
import transferObjects.ViewTO;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.ComplexResponse;

/**
 * Classe di avvio del sistema. Configura lo Stage che conterrà le
   Scene caricate durante l’esecuzione del sistema.
 * */
public class MainApp extends Application {

    @Override
    public final void start(final Stage primaryStage) {
        FrontControllerInt fc = new FrontController();
        SimpleRequest request = new SimpleRequest("start", RequestType.UI_VIEW);

        ComplexResponse<ViewTO> scene =
        (ComplexResponse<ViewTO>) fc.processRequest(request);

        SessionHandler.currentStage = primaryStage;
        primaryStage.setScene(scene.getParameters().get(0).scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("CarLoan");
        primaryStage.show();
    }

    /**
     * Launch.
     * */
    public static void main(String[] args) {
        launch(args);
    }
}