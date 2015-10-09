package view.controller.amministratoreSistema;

import javafx.scene.control.Alert.AlertType;
import view.controller.ShowAlert;
import view.validazioni.TipoValidazione;
import view.validazioni.Validazione;
import view.validazioni.ValidazioneFactory;

/**
 * Classe per la validazione dei dati di una classe autovettura.
 * */
public final class ValidaDatiClasseAutovettura {

    private ValidaDatiClasseAutovettura() {
        // TODO Auto-generated constructor stub
    }


    /**Verifica che tutti i campi siano pieni.
     * @param nome
     * @param numeroPorte
     * @param numeroPosti
     * @param ariaCondizionata
     * @param tipoCambio
     * @return
     */
    public static Boolean isEmptyCheck(String nome, String numeroPorte,
            String numeroPosti, String ariaCondizionata,
            String tipoCambio) {
        if (nome.isEmpty() || numeroPorte.isEmpty() || numeroPosti.isEmpty()
                || ariaCondizionata.isEmpty() || tipoCambio.isEmpty()) {
            ShowAlert.showMessage("Riempire i campi lasciati vuoti",
                    AlertType.ERROR);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica formato input.
     * */
    public static Boolean isValidInput(String nome, String numeroPorte,
            String numeroPosti, String ariaCond, String tipoCambio) {
        Validazione nomeV = ValidazioneFactory.
                getValidazione(TipoValidazione.NOME);
        Validazione interoV = ValidazioneFactory.getValidazione(
                TipoValidazione.INTERO);

        if (nomeV.valida(nome) && interoV.valida(numeroPosti)
                && interoV.valida(numeroPorte) && (ariaCond.equals("NO")
                || ariaCond.equals("SI")) && (tipoCambio.equals("Manuale")
                || tipoCambio.equals("Automatico"))) {
            int a = Integer.parseInt(numeroPorte);
            int b = Integer.parseInt(numeroPosti);
                if ((a <= 6 && a >= 2) && (b <= 10 && b >= 2)) {
                    return true;
                } else {
                    ShowAlert.showMessage("Formato input non valido!",
                            AlertType.WARNING);
                    return false;
                }
        } else {
            ShowAlert.showMessage("Formato input non valido!",
                    AlertType.WARNING);
            return false;
        }
    }
}
