package view.validazioni;

/**
 * Valida un input di tipo output.
 * */
public class FloatValidazione implements Validazione {

    @Override
    public Boolean valida(String string) {

        boolean flag;
        try {
            Float.parseFloat(string);
            flag = true;
        } catch (NumberFormatException e) {
            flag = false;
        }
        return flag;

    }

}
