package transferObjects.entitiesTO;

import transferObjects.TransferObjectInt;
import business.businessObjects.Tariffa;

/**
 * Transfer Object per il Business Object {@link Tariffa}.
 * */
public class TariffaTO implements TransferObjectInt {
    public Float importoUnitario;
    public String modalità;
    public String classeAutovetturaId;
    public String chilometraggio;
    public String id;
    public Float chilometriGiorno;

    /**
     * Costruttore di default.
     * */
    public TariffaTO() { }

    /**
     * Costruttore con parametri.
     * @param importoUnitario
     *      Importo unitario della tariffa.
     * @param modalità
     *      Modalità della tariffa
     * @param classeAutovetturaId
     *      Classe Autovettura a cui si riferisce la tariffa
     * @param chilometraggio
     *      Tipo di chilometraggio
     * */
    public TariffaTO(final Float importoUnitario, final String modalità,
            final String classeAutovetturaId, final String chilometraggio,
            final String id, Float chilometriGiorno) {
        this.importoUnitario = importoUnitario;
        this.modalità = modalità;
        this.classeAutovetturaId = classeAutovetturaId;
        this.chilometraggio = chilometraggio;
        this.id = id;
        this.chilometriGiorno = chilometriGiorno;
    }

    /**
     * Costruttore che crea un nuovo TO basato su un'istanza di TO già
     * esistente.
     *
     * @param tariffa
     *            TO esterno già esistente.
     * */
    public TariffaTO(final TariffaTO tariffa) {
        this.importoUnitario = tariffa.importoUnitario;
        this.modalità = tariffa.modalità;
        this.classeAutovetturaId = tariffa.classeAutovetturaId;
        this.chilometraggio = tariffa.chilometraggio;
        this.id = tariffa.id;
    }

    /**
     * Crea un nuovo Transfer Object.
     *
     * @return Nuova istanza del TO.
     * */
    public final TariffaTO getData() {
        return new TariffaTO(this);
    }

    @Override
    public String toString() {
        return chilometraggio+"("+chilometriGiorno+")/"+modalità+"/"
                +importoUnitario;
    }

    public Boolean equals(TariffaTO tariffa) {
        if (tariffa.chilometraggio.equals(this.chilometraggio)
                && tariffa.chilometriGiorno.equals(this.chilometriGiorno)
                && tariffa.classeAutovetturaId.equals(this.classeAutovetturaId)
                && tariffa.importoUnitario.equals(this.importoUnitario)
                && tariffa.modalità.equals(this.modalità)) {
            return true;
        } else {
            return false;
        }
    }
}
