package view.validazioni;

import java.util.regex.Pattern;

/**
 * Validazione per una stringa di tipo targa.
 * */
public class TargaValidazione implements Validazione {

    private String espressione = "[a-zA-Z0-9\\s_.-]{2,15}";

    @Override
    public Boolean valida(String string) {
        return Pattern.matches(espressione, string);
    }

}
