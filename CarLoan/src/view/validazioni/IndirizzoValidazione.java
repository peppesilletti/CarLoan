package view.validazioni;

import java.util.regex.Pattern;

/**
 * Validazione per una stringa di tipo indirizzo.
 * */
public class IndirizzoValidazione implements Validazione {

    private String espressione = "[a-zA-Z'\\s]{2,40},"
            + "[0-9]{1,3}";

    @Override
    public final Boolean valida(final String string) {
        return Pattern.matches(espressione, string);
    }

}
