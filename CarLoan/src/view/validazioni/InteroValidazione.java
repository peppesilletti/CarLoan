package view.validazioni;

/**
 * Valida un input di tipo intero.
 * */
public class InteroValidazione implements Validazione {

    @Override
    public Boolean valida(String string) {

        boolean flag;
        try {
            Integer.parseInt(string);
            flag = true;
        } catch (NumberFormatException e) {
            flag = false;
        }
        return flag;

    }

}
