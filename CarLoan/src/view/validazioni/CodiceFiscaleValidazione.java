package view.validazioni;

import java.util.regex.Pattern;

/**
 * Permette di validare il formato di un codice fiscale.
 * */
public class CodiceFiscaleValidazione implements Validazione {

    private String espressione =
            "[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]";

    @Override
    public Boolean valida(String string) {
        return Pattern.matches(espressione, string);
    }

    public static void main(String[] args) {
        CodiceFiscaleValidazione c = new CodiceFiscaleValidazione();
        System.out.println(c.valida("RSSMRA85T10A562S"));
    }

}


