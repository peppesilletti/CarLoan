package view.controller.managerAgenzia;

import javafx.scene.control.Alert.AlertType;
import view.controller.ShowAlert;
import view.model.ClasseAutovetturaModel;
import view.validazioni.TipoValidazione;
import view.validazioni.Validazione;
import view.validazioni.ValidazioneFactory;

/**
 * Valida i dati di un'autovettura.
 * */

public final class ValidaDatiAutovettura {
    private ValidaDatiAutovettura() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Verifica che tutti i campi siano pieni.
     * */
    /**
     * @param targa
     * @param modello
     * @param marca
     * @param km
     * @param classe
     * @param alimentazione
     * @return
     */
    public static Boolean isEmptyCheck(String targa, String modello,
            String marca, String km, ClasseAutovetturaModel classe,
            String alimentazione) {

       if (targa.isEmpty() || modello.isEmpty() || marca.isEmpty()
               || km.isEmpty() || classe == null || alimentazione.isEmpty()
               ) {
           ShowAlert.showMessage("Riempire i campi lasciati vuoti",
                   AlertType.WARNING);
           return true;
       } else {
           return false;
       }

    }

    /**
     * Valida i dati di input per un'autovettura.
     * */
    /**
     * @param targa
     * @param modello
     * @param marca
     * @param km
     * @return
     */
    public static Boolean isValidInput(String targa, String modello,
            String marca, String km) {
        Validazione nomeV = ValidazioneFactory.getValidazione(
                TipoValidazione.NOME);
        Validazione floatV = ValidazioneFactory.getValidazione(
                TipoValidazione.FLOAT);
        Validazione targaV = ValidazioneFactory.getValidazione(
                TipoValidazione.TARGA);
        if (nomeV.valida(modello) && nomeV.valida(marca)
                && floatV.valida(km) && targaV.valida(targa)) {
            return true;
        } else {
            ShowAlert.showMessage("Formato dati non valido",
                    AlertType.WARNING);
            return false;
        }
    }
}
