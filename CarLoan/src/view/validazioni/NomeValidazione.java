package view.validazioni;

import java.util.regex.Pattern;

/**
 * Valida un input di tipo nome.
 * */
public class NomeValidazione implements Validazione {

    private String espressione = "[a-zA-Z'\\s]{2,20}";

    @Override
    public Boolean valida(String string) {
        return Pattern.matches(espressione, string);
    }

}
