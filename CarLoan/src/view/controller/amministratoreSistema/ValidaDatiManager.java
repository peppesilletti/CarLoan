package view.controller.amministratoreSistema;

import javafx.scene.control.Alert.AlertType;
import view.controller.ShowAlert;
import view.model.AgenziaModel;
import view.validazioni.TipoValidazione;
import view.validazioni.Validazione;
import view.validazioni.ValidazioneFactory;

/**
 * Classe per validare i dati di un manager.
 * */
public class ValidaDatiManager {

    private ValidaDatiManager() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Valida l'input come formato.
     * */
    /**
     * @param username
     * @param password
     * @param nome
     * @param cognome
     * @return
     */
    public static final Boolean isValidInput( final String username,
            final String password,
            final String nome,
            final String cognome) {

        Validazione usernameV = ValidazioneFactory.getValidazione(
                TipoValidazione.USERNAME);
        Validazione nomeV = ValidazioneFactory.getValidazione(
                TipoValidazione.NOME);
        Validazione cognomeV = ValidazioneFactory.getValidazione(
                TipoValidazione.COGNOME);
        Validazione passwordV = ValidazioneFactory.getValidazione(
                TipoValidazione.PASSWORD);

        if (usernameV.valida(username) && nomeV.valida(nome)
               && cognomeV.valida(cognome) && passwordV.valida(password)) {
            return true;
        } else {
            ShowAlert.showMessage("Formato input non valido",
                    AlertType.WARNING);
            return false;
        }
    }

    /**
     * Controlla che i campi non siano vuoti.
     * */
    /**
     * @param username
     * @param password
     * @param nome
     * @param cognome
     * @param agenzia
     * @return
     */
    public static final Boolean isEmptyCheck(
            final String username, final String password,
            final String nome,
            final String cognome, final AgenziaModel agenzia) {
        if (username.isEmpty() || password.isEmpty() || nome.isEmpty()
                || cognome.isEmpty() || agenzia == null) {
            ShowAlert.showMessage("Riempire tutti i "
                    + "lasciati campi vuoti",
                    AlertType.ERROR);
            return true;
        } else {
            return false;
        }
    }
}
