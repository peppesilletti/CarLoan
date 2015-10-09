package view.controller.amministratoreSistema;

import javafx.scene.control.Alert.AlertType;
import view.controller.ShowAlert;
import view.validazioni.TipoValidazione;
import view.validazioni.Validazione;
import view.validazioni.ValidazioneFactory;

/**
 * Classe utility per validare dati agenzia.
 * */
public final class ValidareDatiAgenzia {
    private ValidareDatiAgenzia() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Controlla che tutti i campi siano pieni.
     * */
    /**
     * @param città
     * @param indirizzo
     * @param telefono
     * @return
     */
    public static Boolean isEmptyCheck(String città, String indirizzo,
            String telefono) {
        if (città.isEmpty() || indirizzo.isEmpty() || telefono.isEmpty()) {
            ShowAlert.showMessage("Riempire i campi lasciati vuoti",
                    AlertType.WARNING);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Controlla che tutti gli input siano validi.
     * */
    /**
     * @param città
     * @param indirizzo
     * @param telefono
     * @return
     */
    public static Boolean isValidInput(String città, String indirizzo,
            String telefono) {
        Validazione nomeV = ValidazioneFactory.getValidazione(
                TipoValidazione.NOME);
        Validazione telV = ValidazioneFactory.getValidazione(
                TipoValidazione.TELEFONO);
        Validazione indirizzoV = ValidazioneFactory.getValidazione(
                TipoValidazione.INDIRIZZO);

        if (nomeV.valida(città) && telV.valida(telefono)
                && indirizzoV.valida(indirizzo)) {
            return true;
        } else {
            ShowAlert.showMessage("Formato input non valido",
                    AlertType.WARNING);
            return false;
        }
    }
}
