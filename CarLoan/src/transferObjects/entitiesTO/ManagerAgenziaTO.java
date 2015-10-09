package transferObjects.entitiesTO;

import transferObjects.TransferObjectInt;
import business.businessObjects.ManagerAgenzia;

/**
 * Data Transfer Object per la classe {@link ManagerAgenzia}.
 * */
public class ManagerAgenziaTO implements TransferObjectInt {

    public String  username;
    public String  password;
    public String  nome;
    public String  cognome;
    public String  agenziaId;
    public String id;

    /**
     * Costruttore di default.
     * */
    public ManagerAgenziaTO() {
    }

    /**
     * Costruttore con parametri.
     * @param username
     *      Username del manager
     * @param password
     *      Password del manager
     * @param nome
     *      Nome del manager
     * @param cognome
     *      Cognome del manager
     * @param agenziaId
     *      Agenzia del manager
     */
    public ManagerAgenziaTO(final String username, final String password,
            final String nome,
            final String cognome, final String agenziaId) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.agenziaId = agenziaId;
    }


    /**
     * Costruttore che crea un nuovo TO basato su un'istanza di TO già
     * esistente.
     *
     * @param manager
     *            TO esterno già esistente.
     * */
    public ManagerAgenziaTO(final ManagerAgenziaTO manager) {
        this.username = manager.username;
        this.password = manager.password;
        this.nome = manager.nome;
        this.cognome = manager.nome;
        this.agenziaId = manager.agenziaId;
        this.id = manager.id;
    }

    /**
     * Crea un nuovo Transfer Object.
     *
     * @return Nuova istanza del TO.
     * */
    public final ManagerAgenziaTO getData() {
        return new ManagerAgenziaTO(this);
    }

    public Boolean equals(ManagerAgenziaTO m) {
        if (m.username.equals(this.username)
                && m.agenziaId.equals(this.agenziaId)
                && m.nome.equals(this.nome)
                && m.cognome.equals(this.cognome)) {

            return true;

        } else {
            return false;
        }
    }

}
