package view.validazioni;

import java.util.regex.Pattern;

/**
 * Validazione per una stringa di tipo telefono.
 * */
public class TelefonoValidazione implements Validazione {

    private String espressione = "[0-9]{2,20}";

    @Override
    public final Boolean valida(final String string) {
        return Pattern.matches(espressione, string);
    }
}
