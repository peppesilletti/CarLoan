package view.controller.managerAgenzia;

import javafx.scene.control.Alert.AlertType;
import view.controller.ShowAlert;
import view.model.AutovetturaModel;
import view.validazioni.TipoValidazione;
import view.validazioni.Validazione;
import view.validazioni.ValidazioneFactory;

/**
 * Valida i dati di una manutenzione.
 * */
public final class ValidaDatiManutenzione {
    private ValidaDatiManutenzione() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Controlla che tutti i campi siano riempiti.
     * */
    /**
     * @param data
     * @param tipo
     * @param auto
     * @return
     */
    public static Boolean isEmpty(String dataIn, String tipo,
            AutovetturaModel auto, String dataOut) {
        if (dataIn.isEmpty() || tipo.isEmpty() || auto == null
                || dataOut == null) {
            ShowAlert.showMessage("Inserire tutti i campi lasciati vuoti!",
                    AlertType.WARNING);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida i dati di una manutenzione.
     * */
    /**
     * @param data
     * @return
     */
    public static Boolean isValidInput(String dataIn, String dataOut) {
        Validazione dataValidazione = ValidazioneFactory.getValidazione(
                TipoValidazione.DATA);
        if (dataValidazione.valida(dataIn) && dataValidazione.valida(dataOut)) {
            return true;
        } else {
            ShowAlert.showMessage("Formato data non valido",
                    AlertType.WARNING);
            return false;
        }
    }

}
