package transferObjects.entitiesTO;

import transferObjects.TransferObjectInt;
import business.businessObjects.Anonimo;

/**
 * Transfer Object del Business Object {@link Anonimo}.
 * */
public class AnonimoTO implements TransferObjectInt {

    // attributi pubblici
    public String username;
    public String password;
    public String tipo;
    public String agenziaId;

    /**
     * Costruttore della classe.
     * */
    public AnonimoTO() {
    }

    /**
     * Costruttore con parametri.
     *
     * @param username
     *            Username dell'anonimo.
     * @param password
     *            Password dell'anonimo.
     * */
    public AnonimoTO(final String username, final String password, String tipo) {
        this.username = username;
        this.password = password;
        this.tipo = tipo;
    }

    /**
     * Costruttore che crea un nuovo TO basato su un'istanza di TO già
     * esistente.
     *
     * @param anonimo
     *            TO esterno già esistente.
     * */
    public AnonimoTO(final AnonimoTO anonimo) {
        this.username = anonimo.username;
        this.password = anonimo.password;
        this.tipo = anonimo.tipo;
    }

    /**
     * Crea un nuovo Transfer Object.
     *
     * @return Nuova istanza del TO.
     * */
    public final AnonimoTO getData() {
        return new AnonimoTO(this);
    }

}
