package view.controller.amministratoreSistema;

import javafx.scene.control.Alert.AlertType;
import view.controller.ShowAlert;
import view.model.ClasseAutovetturaModel;
import view.validazioni.TipoValidazione;
import view.validazioni.Validazione;
import view.validazioni.ValidazioneFactory;

/**
 * Classe utility per validare dati tariffa.
 * */
public final class ValidaDatiTariffa {
    private ValidaDatiTariffa() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constrolla che tutti i campi siano pieni.
     * */
    /**
     * @param importo
     * @param chilometraggio
     * @param modNoleggio
     * @param classe
     * @return
     */
    public static Boolean isEmpty(String importo, String chilometraggio,
            String modNoleggio, ClasseAutovetturaModel classe) {
        if (importo.isEmpty() || chilometraggio.isEmpty()
                || modNoleggio.isEmpty() || classe == null) {
            ShowAlert.showMessage("Riempire i campi lasciati vuoti",
                    AlertType.WARNING);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Controlla che il formato dell'input sia valido.s
     * */
    /**
     * @param importo
     * @return
     */
    public static Boolean isValidInput(String importo, String km) {
           Validazione importoV = ValidazioneFactory.getValidazione(
                   TipoValidazione.FLOAT);
           if (importoV.valida(importo) && importoV.valida(km)) {
               return true;
           } else  {
               ShowAlert.showMessage("Formato dati non valido",
                       AlertType.WARNING);
               return false;
           }

    }


}
