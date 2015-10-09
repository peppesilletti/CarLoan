package transferObjects.entitiesTO;

import transferObjects.TransferObjectInt;
import business.businessObjects.Agenzia;

/**
 * Transfer Object per il Business Object {@link Agenzia}.
 * */
public class AgenziaTO implements TransferObjectInt {
    public String  città;
    public String  indirizzo;
    public String  telefono;
    public String id;
    public ManagerAgenziaTO infoManager;

    /**
     * Costruttore di default.
     * */
    public AgenziaTO() {
        infoManager = new ManagerAgenziaTO();
    }

    /**
     * @param città
     * @param indirizzo
     * @param telefono
     * @param id
     */
    public AgenziaTO(final String città, final String indirizzo,
            final String telefono) {
        this.città = città;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
    }

    /**
     * Costruttore che crea un nuovo TO basato su un'istanza di TO già
     * esistente.
     * @param agenzia
     *      TO da copiare.
     */
    public AgenziaTO(final AgenziaTO agenzia) {
        this.città = agenzia.città;
        this.indirizzo = agenzia.indirizzo;
        this.telefono = agenzia.telefono;
        this.id = agenzia.id;
    }

    /**
     * Crea un nuovo Transfer Object.
     *
     * @return Nuova istanza del TO.
     * */
    public final AgenziaTO getData() {
        return new AgenziaTO(this);
    }

    @Override
    public String toString() {
        return città+", "+indirizzo;
    }

    public Boolean equals(AgenziaTO agenzia) {

        if (agenzia == null) {
            return false;
        }

        if (this.città.equals(agenzia.città)
                && this.indirizzo.equals(agenzia.indirizzo)
                && this.telefono.equals(agenzia.telefono)) {
            return true;
        } else {
            return false;
        }
    }

}
