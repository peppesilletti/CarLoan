package transferObjects.entitiesTO;

import transferObjects.TransferObjectInt;
import business.businessObjects.Extra;

/**
 *Transfer Object per il Business Object {@link Extra}.
 * */
public class ExtraTO implements TransferObjectInt {
    public String nome;
    public String descrizione;
    public Float  prezzo;
    public String id;

    /**
     * Costruttore di default.
     * */
    public ExtraTO() { }

    /**
     * Costruttore con parametri.
     * @param nome
     *      Nome dell'extra
     * @param descrizione
     *      Descrizione dell'extra.
     * @param prezzo
     *      Prezzo unitario per l'extra.
     *
     * */
    public ExtraTO(final String nome, final String descrizione,
            final Float prezzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }

    /**
     * Costruttore che crea un nuovo TO basato su un'istanza di TO già
     * esistente.
     * @param extra
     *      TO da copiare.
     */
    public ExtraTO(final ExtraTO extra) {
        this.nome = extra.nome;
        this.descrizione = extra.descrizione;
        this.prezzo = extra.prezzo;
        this.id = extra.id;
    }

    /**
     * Crea un nuovo Transfer Object.
     *
     * @return Nuova istanza del TO.
     * */
    public final ExtraTO getData() {
        return new ExtraTO(this);
    }

    public Boolean equals(ExtraTO e) {
        if (e.nome.equals(this.nome)
                && e.descrizione.equals(this.descrizione)
                && e.prezzo.equals(this.prezzo)) {
            return true;
        } else {
            return false;
        }
    }

}
