package presentation;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utility.XMLReader;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.ViewDispatcherException;

/**
 * Supporta l'ApplicaionController nel dispatching delle view.
 * Classe Singleton.
 * @author Gianluca
 *
 */
public final class ViewDispatcher {

	/**
	 * Larghezza di default della finestra di sistema.
	 */
	private final int larghezza = 1000;

	/**
	 * Lunghezza di default della finestra di sistema.
	 */
	private final int lunghezza = 700;

	private XMLReader viewReader;

	private static ViewDispatcher vd  = new ViewDispatcher();;

	/**
	 * Fornisce l'unica istanza Singleton dell'oggetto {@link ViewDispatcher}.
	 * @return l'unica istanza della classe.
	 */
	public static ViewDispatcher getIstance() {
		return vd;
	}

	/**
	 * Costruttore di default.
	 */
	private ViewDispatcher() {
		this.viewReader = new XMLReader("/view/ViewsMap.xml");
	}

	/**
	 * Carica l'interfaccia di avvio.
	 * @return la scena con l'interfaccia.
	 */
	public Scene start() {
			try {
				/*BorderPane root = FXMLLoader.load(getClass().getResource(
				        "/view/RootLayout.fxml"));*/
				AnchorPane pane = FXMLLoader.load(getClass().getResource(
				        "/view/HeaderLogin.fxml"));

				//root.setTop(pane);
				Scene scena = new Scene(pane);
				SessionHandler.currentScene = scena;
			} catch (IOException | NullPointerException e) {
				handleException(e);
			}
			return SessionHandler.currentScene;
	}

	/**
	 * Aggiorna interfaccia dopo logout.
	 * */
	public void logout() {
	    try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(
                    "/view/HeaderLogin.fxml"));

            BorderPane root = (BorderPane) SessionHandler.
                    currentScene.getRoot();
            root.setTop(pane);
            root.setCenter(null);
        } catch (IOException | NullPointerException e) {
            handleException(e);
        }
	}

	/**
	 * Interfaccia da mostrare dopo l'autenticazione.
	 * */
	public void afterLogin(String view) {

            String path = viewReader.readPath("interface", view);
            try {
                BorderPane root = FXMLLoader.load(getClass().getResource(
                "/view/RootLayout.fxml"));
                Stage stage = new Stage();
                AnchorPane pane = FXMLLoader.load(getClass().getResource(path));
                root.setCenter(pane);

                Scene scene = new Scene(root, larghezza, lunghezza);

                SessionHandler.currentScene = scene;
                SessionHandler.currentStage = stage;

                stage.setTitle(view);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();

            } catch (IOException | NullPointerException e) {
                handleException(e);
            }
	}

	/**
     * Imposta una view.
     * @param view Interfaccia da settare.
     */
    public void toView(final String view) {

        String path = viewReader.readPath("interface", view);
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(path));
            BorderPane root = (BorderPane) SessionHandler.
                    currentScene.getRoot();
            root.setCenter(pane);
        } catch (IOException | NullPointerException e) {
            handleException(e);
        }

    }

	/**
	 * Inoltra l'eccezione sollevata al gestore delle eccezioni.
	 * @param e l'eccezione sollevata.
	 */
	private void handleException(Exception e) {
	    e.printStackTrace();
		ViewDispatcherException ex = new ViewDispatcherException(
		        e.getMessage());
		ErrorHandlerInt ge = ErrorHandler.getIstance();
		ge.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
	}

}
