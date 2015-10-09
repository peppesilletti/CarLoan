package view.validazioni;

import java.util.regex.Pattern;

/**
 * Valida un input di tipo cognome.
 * */
public class CognomeValidazione implements Validazione {

    private String espressione = "[a-zA-Z'\\s]{2,15}";

    @Override
    public Boolean valida(String string) {
        return Pattern.matches(espressione, string);
    }

}
