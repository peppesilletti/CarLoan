package transferObjects.entitiesTO;

import transferObjects.TransferObjectInt;
import business.businessObjects.Cliente;

/**
 * Transfer Object per il business object {@link Cliente}.
 * */
public class ClienteTO implements TransferObjectInt {

    public String nome;
    public String cognome;
    public String comuneNascita;
    public String comuneResidenza;
    public String indirizzo;
    public String idPatente;
    public String codiceFiscale;
    public String dataDiNascita;
    public String id;

    /**
     * Costruttore della classe.
     * @param nome
     *      Nome del cliente.
     *
     * @param cognome
     *      Cognome del cliente.
     *
     * @param comuneNascita
     *      Comune di nascita del cliente.
     *
     * @param comuneResidenza
     *      Comune di residenza.
     *
     * @param indirizzo
     *      Indirizzo del cliente.
     *
     * @param idPatente
     *      Id della patente del cliente.
     *
     * @param codiceFiscale
     *      Codice fiscale del cliente.
     *
     * @param dataDiNascita
     *      Data di nascita del cliente.
     * */
    public ClienteTO(final String nome, final String cognome,
            final String comuneNascita, final String comuneResidenza,
            final String indirizzo, final String idPatente,
            final String codiceFiscale, final String dataDiNascita) {

        this.nome = nome;
        this.cognome = cognome;
        this.comuneNascita = comuneNascita;
        this.comuneResidenza = comuneResidenza;
        this.indirizzo = indirizzo;
        this.idPatente = idPatente;
        this.codiceFiscale = codiceFiscale;
        this.dataDiNascita = dataDiNascita;

    }

    /**
     * Costruttore default della classe.
     * */
    public ClienteTO() { }

    /**
     * Costruttore che crea un nuovo TO basato su un'istanza di TO già
     * esistente.
     * @param cliente
     *      TO da copiare.
     */
    public ClienteTO(final ClienteTO cliente) {
        this.nome = cliente.nome;
        this.cognome = cliente.cognome;
        this.comuneNascita = cliente.comuneNascita;
        this.comuneResidenza = cliente.comuneResidenza;
        this.indirizzo = cliente.indirizzo;
        this.idPatente = cliente.idPatente;
        this.codiceFiscale = cliente.codiceFiscale;
        this.dataDiNascita = cliente.dataDiNascita;
        this.id = cliente.id;
    }


    /**
     * Crea un nuovo Transfer Object.
     *
     * @return Nuova istanza del TO.
     * */
    public final ClienteTO getData() {
        return new ClienteTO(this);
    }

    public Boolean equals(ClienteTO c) {
        if (c.nome.equals(this.nome)
                && c.cognome.equals(this.cognome)
                && c.codiceFiscale.equals(this.codiceFiscale)
                && c.comuneNascita.equals(this.comuneNascita)
                && c.comuneResidenza.equals(this.comuneResidenza)
                && c.dataDiNascita.equals(this.dataDiNascita)
                && c.idPatente.equals(this.idPatente)
                && c.indirizzo.equals(this.indirizzo)) {
            return true;
        } else {
            return false;
        }
    }

}
