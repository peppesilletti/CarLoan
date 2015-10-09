package view.controller.operatore;

import javafx.scene.control.Alert.AlertType;
import view.controller.ShowAlert;
import view.model.AgenziaModel;
import view.model.AutovetturaModel;
import view.model.TariffaModel;
import view.validazioni.TipoValidazione;
import view.validazioni.Validazione;
import view.validazioni.ValidazioneFactory;

/**
 * Classe utility per validare i dati di un contratto.
 * */
public class ValidaDatiContratto {
    private ValidaDatiContratto() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Controlla che tutti i campi siano pieni.
     * */
    public static Boolean isEmptyCheck(String dataInizioNoleggio,
            String dataFineNoleggio, AgenziaModel agenzia,
            TariffaModel tariffa,
            AutovetturaModel auto) {
        if (dataInizioNoleggio.isEmpty() || dataFineNoleggio.isEmpty()
            || agenzia == null || tariffa == null || auto == null) {
           ShowAlert.showMessage("Inserire tutti i dati della sezione noleggio",
                   AlertType.WARNING);
           return true;
        } else {
            return false;
        }
    }

    /**
     * Controlla il formato dei dati in input.
     * */
    public static Boolean isValidInput(String dataInizioNoleggio,
            String dataFineNoleggio) {
        Validazione dataV = ValidazioneFactory.getValidazione(
                TipoValidazione.DATA);

        Validazione floatV = ValidazioneFactory.getValidazione(
                TipoValidazione.FLOAT);

        if (dataV.valida(dataFineNoleggio) && dataV.valida(dataInizioNoleggio)
                ) {
            return true;
        } else {
            ShowAlert.showMessage("Alcuni della sezione "
                    + "noleggio non hanno un formato valido",
                    AlertType.WARNING);
            return false;
        }
    }
}
