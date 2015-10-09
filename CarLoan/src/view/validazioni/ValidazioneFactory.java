package view.validazioni;

/**
 * Classe singleton che implementa la Factory dei tipi di validazione per gli
 * input.
 * */
public final class ValidazioneFactory {

    /**
     * Unica istanza della classe.
     * */
    public ValidazioneFactory istance = new ValidazioneFactory();

    /**
     * Costruttore privato per il singleton.
     * */
    private ValidazioneFactory() {
    }

    /**
     * Restituisce un tipo di validazione.
     *
     * @param tipo
     *            Tipo della validazione da restituire
     * @return tipo della validazione
     * */
    public static Validazione getValidazione(final TipoValidazione tipo) {

        if (tipo == TipoValidazione.USERNAME) {
            return new UsernameValidazione();
        } else if (tipo == TipoValidazione.PASSWORD) {
            return new PasswordValidazione();
        } else if (tipo == TipoValidazione.NOME) {
            return new NomeValidazione();
        } else if (tipo == TipoValidazione.COGNOME) {
            return new CognomeValidazione();
        } else if (tipo == TipoValidazione.INTERO) {
            return new InteroValidazione();
        } else if (tipo == TipoValidazione.FLOAT) {
            return new FloatValidazione();
        } else if (tipo == TipoValidazione.TELEFONO) {
            return new TelefonoValidazione();
        } else if (tipo == TipoValidazione.INDIRIZZO) {
            return new IndirizzoValidazione();
        } else if (tipo == TipoValidazione.TARGA) {
            return new TargaValidazione();
        } else if (tipo == TipoValidazione.DATA) {
            return new DataValidazione();
        } else if (tipo == TipoValidazione.CODICE_FISCALE) {
            return new CodiceFiscaleValidazione();
        } else if (tipo == TipoValidazione.CITY) {
            return new CityValidazione();
        }

        return null;
    }
}
