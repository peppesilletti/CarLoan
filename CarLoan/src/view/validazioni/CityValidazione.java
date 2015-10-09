package view.validazioni;

import java.util.regex.Pattern;

public class CityValidazione implements Validazione {

    private String espressione = "[a-zA-Z'\\s]{2,20},"
            + "[A-Z]{1,3}";

    @Override
    public Boolean valida(String string) {
        return Pattern.matches(espressione, string);
    }

}
