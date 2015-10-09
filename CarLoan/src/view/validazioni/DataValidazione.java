package view.validazioni;

import java.util.regex.Pattern;

/**
 * Validazione per una string di tipo data.
 * */
public class DataValidazione implements Validazione {

    private String espressione = "^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]";

    @Override
    public final Boolean valida(final String string) {
        return Pattern.matches(espressione, string);
    }

}
