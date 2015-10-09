package view.controller.amministratoreSistema;

import javafx.scene.control.Alert.AlertType;
import view.controller.ShowAlert;
import view.validazioni.TipoValidazione;
import view.validazioni.Validazione;
import view.validazioni.ValidazioneFactory;

/**
 * Classe utility per validare i dati degli extra.
 * */
public final class ValidaDatiExtra {

    private ValidaDatiExtra() {

    }

    /**
     * Controlla che i campi siano tutti riempiti.
     * */
    /**
     * @param nome
     * @param prezzo
     * @param descrizione
     * @return
     */
    public static Boolean isEmpty(String nome, String prezzo,
            String descrizione) {
        if (nome.isEmpty() || prezzo.isEmpty() || descrizione.isEmpty()) {
            ShowAlert.showMessage("Riempire i campi lasciati vuoti",
                    AlertType.WARNING);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validazione del formato.
     * */
    public static Boolean isValidInput(String nome, String prezzo,
            String descrizione) {
            Validazione nomeV = ValidazioneFactory.getValidazione(
                    TipoValidazione.NOME);
            Validazione prezzoV = ValidazioneFactory.getValidazione(
                    TipoValidazione.FLOAT);

            if (nomeV.valida(nome) && prezzoV.valida(prezzo)
                    && (descrizione.length() <= 200)) {
                return true;
            } else {
                ShowAlert.showMessage("Formato dati non valido", AlertType.WARNING);
                return false;
            }
    }

}
