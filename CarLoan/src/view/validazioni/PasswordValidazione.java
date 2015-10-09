package view.validazioni;

import java.util.regex.Pattern;

/**
 * Valida un input di tipo password
 * */
public class PasswordValidazione implements Validazione {

    private String espressione = "[a-zA-Z0-9\\s_.-]{1,15}";

    @Override
    public Boolean valida(String string) {
        return Pattern.matches(espressione, string);
    }

}
