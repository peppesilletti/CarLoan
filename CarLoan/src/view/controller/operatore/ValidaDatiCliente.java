package view.controller.operatore;

import javafx.scene.control.Alert.AlertType;
import view.controller.ShowAlert;
import view.validazioni.TipoValidazione;
import view.validazioni.Validazione;
import view.validazioni.ValidazioneFactory;

/**
 * Classe utility per validare i dati di un cliente.
 * */
public class ValidaDatiCliente {

    private ValidaDatiCliente() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Controlla che tutti i campi siano pieni.
     * */
    public static Boolean isEmptyCheck(String nome, String cognome,
            String dataNascita, String comuneNascita,
            String comuneResidenza, String indirizzo,
            String codiceFiscale, String numPatenteGuida) {
        if (nome.isEmpty() || cognome.isEmpty() || dataNascita.isEmpty()
                || comuneNascita.isEmpty() || comuneResidenza.isEmpty()
                || indirizzo.isEmpty() || codiceFiscale.isEmpty()
                || numPatenteGuida.isEmpty()) {
            ShowAlert.showMessage("Riempire i campi lasciati vuoti"
                    + "nella sezione cliente!",
                    AlertType.WARNING);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Controlla il formato dei dati in input.
     * */
    public static Boolean isValidInput(String nome, String cognome,
            String dataNascita, String comuneNascita,
            String comuneResidenza, String indirizzo,
            String codiceFiscale, String numPatenteGuida) {

        Validazione nomeV = ValidazioneFactory.getValidazione(
                TipoValidazione.NOME);

        Validazione cognomeV = ValidazioneFactory.getValidazione(
                TipoValidazione.COGNOME);

        Validazione indirizzoV = ValidazioneFactory.getValidazione(
                TipoValidazione.INDIRIZZO);

        Validazione codiceFiscaleV = ValidazioneFactory.getValidazione(
                TipoValidazione.CODICE_FISCALE);

        Validazione numPatenteV = ValidazioneFactory.getValidazione(
                TipoValidazione.TARGA);

        Validazione dataV = ValidazioneFactory.getValidazione(
                TipoValidazione.DATA);

        Validazione city = ValidazioneFactory.getValidazione(
                TipoValidazione.CITY);

        if (nomeV.valida(nome) && cognomeV.valida(cognome)
            && indirizzoV.valida(indirizzo) && city.valida(comuneNascita)
            && city.valida(comuneResidenza) && codiceFiscaleV.valida(codiceFiscale)
            && numPatenteV.valida(numPatenteGuida)
            && dataV.valida(dataNascita)) {
            return true;
        } else {
            ShowAlert.showMessage("Le informazioni inserite nella sezione "
                    + "cliente \n non hanno un formato valido",
                    AlertType.WARNING);
            return false;
        }

    }

}
