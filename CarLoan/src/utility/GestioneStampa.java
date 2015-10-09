package utility;


import java.awt.Desktop;
import java.io.File;

import javafx.scene.control.Alert.AlertType;
import view.controller.ShowAlert;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.PrintException;

/**
 * Classe adibita alla stampa di un file.
 *
 */
public class GestioneStampa {

	/**
	 * Richiama il gestore predefinito associato ad un file per effettuare la
	 * stampa. Se non è impostato alcun gestore predefinito per l'estensione del
	 * file, la stampa non è eseguita.
	 *
	 * @param f
	 *            il file da stampare. Può essere null.
	 * @return true sse la richiesta di stampa è stata inoltrata correttamente,
	 *         altrimenti false.
	 */
	public static boolean stampa(File f) {
		boolean controll = false;
		String message;
		if (Desktop.isDesktopSupported()) {
			Desktop stampante = Desktop.getDesktop();
			if (f != null) {
				try {
					stampante.print(f);
					controll = true;
				} catch (Exception e) {
					PrintException ex = new PrintException(e.getMessage());
					ErrorHandlerInt ge = ErrorHandler.getIstance();
					ge.processError(ex.getClass(), ex, ErrorHandlerInt.WARNING);
				}
			} else {
				//errore file non valido
				message = "Impossibile stampare il file selezionato.\nRiprovare";
				ShowAlert.showMessage(message, AlertType.CONFIRMATION);
			}
		} else {
			//errore funzionalità non supportata dal sistema
			message = "Funzionalità non supportata da questo sistema operativo.";
			ShowAlert.showMessage(message, AlertType.CONFIRMATION);
		}
		return controll;
	}

	/**
	 * @see #stampa(File).
	 * @param path
	 *            il percorso del file da stampare.
	 * @return true sse la richiesta di stampa � stata inoltrata correttamente,
	 *         altrimenti false.
	 */
	public static boolean stampa(String path) {
		File f = new File(path);
		return stampa(f);
	}

}
