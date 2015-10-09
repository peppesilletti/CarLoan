package transferObjects.entitiesTO;

import transferObjects.TransferObjectInt;

/**
 * Transfer object per l'entità operatore.
 * */
public class OperatoreTO implements TransferObjectInt {
    public String  username;
    public String  password;
    public String  nome;
    public String  cognome;
    public String  agenziaId;
    public String id;


    /**
     * Costruttore di default.
     * */
    public OperatoreTO() {
    }

    /**
     * Costruttore con parametri.
     * @param username
     *      Username dell'operatore.
     * @param password
     *      Password dell'operatore.
     * @param nome
     *      Nome dell'operatore.
     * @param cognome
     *      Cognome dell'operatore
     * @param agenzia
     *      Agenzia dell'operatore.
     * */
    public OperatoreTO(final String username, final String password,
            final String nome,
            final String cognome, final String agenzia) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.agenziaId = agenzia;
    }

    /**
     * Costruttore con parametri.
     * @param username
     *      Username dell'operatore.
     * @param nome
     *      Nome dell'operatore.
     * @param cognome
     *      Cognome dell'operatore
     * @param agenzia
     *      Agenzia dell'operatore.
     * */
    public OperatoreTO(final String username, final String nome,
            final String cognome, final String id) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.id = id;
    }

    /**
     * Costruttore che crea un nuovo TO basato su un'istanza di TO già
     * esistente.
     *
     * @param operatore
     *            TO esterno già esistente.
     * */
    public OperatoreTO(final OperatoreTO operatore) {
        this.username = operatore.username;
        this.password = operatore.password;
        this.nome = operatore.nome;
        this.cognome = operatore.nome;
        this.agenziaId = operatore.agenziaId;
        this.id = operatore.id;
    }

    /**
     * Crea un nuovo Transfer Object.
     *
     * @return Nuova istanza del TO.
     * */
    public final OperatoreTO getData() {
        return new OperatoreTO(this);
    }

    public Boolean equals(OperatoreTO m) {
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
