package transferObjects.entitiesTO;

import transferObjects.TransferObjectInt;
import business.businessObjects.Autovettura;

/**
 * Transfer Object per il business object {@link Autovettura}.
 * */
public class AutovetturaTO implements TransferObjectInt {

    public String targa;
    public String marca;
    public String modello;
    public Float  chilometraggio;
    public String alimentazione;
    public String agenzia;
    public String id;
    public String classeAutovetturaId;


    /**
     * Costruttore di default.
     * */
    public AutovetturaTO() {
        // TODO Auto-generated constructor stub
    }


    /**Costruttore con parametri.
     * @param targa
     * @param marca
     * @param modello
     * @param chilometraggio
     * @param alimentazione
     * @param agenzia
     * @param id
     * @param classeAutovetturaId
     */
    public AutovetturaTO(final String targa, final String marca, final String modello,
            final Float chilometraggio, final String alimentazione, final String agenzia,
            final String classeAutovetturaId) {
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
        this.chilometraggio = chilometraggio;
        this.alimentazione = alimentazione;
        this.agenzia = agenzia;
        this.classeAutovetturaId = classeAutovetturaId;
    }

    /**
     * Costruttore che crea un nuovo TO basato su un'istanza di TO già
     * esistente.
     *
     * @param autovettura
     *            TO esterno già esistente.
     * */
    public AutovetturaTO(final AutovetturaTO autovettura) {
        this.targa = autovettura.targa;
        this.marca = autovettura.marca;
        this.modello = autovettura.modello;
        this.chilometraggio = autovettura.chilometraggio;
        this.alimentazione = autovettura.alimentazione;
        this.agenzia = autovettura.agenzia;
        this.classeAutovetturaId = autovettura.classeAutovetturaId;
        this.id = autovettura.id;
    }

    /**
     * Crea un nuovo Transfer Object.
     *
     * @return Nuova istanza del TO.
     * */
    public final AutovetturaTO getData() {
        return new AutovetturaTO(this);
    }

    @Override
    public String toString() {
        return marca+" "+modello+" - "+targa;
    }

    public Boolean equals(AutovetturaTO auto) {
        if (auto.alimentazione.equals(this.alimentazione)
                && auto.chilometraggio.equals(this.chilometraggio)
                && auto.classeAutovetturaId.equals(this.classeAutovetturaId)
                && auto.marca.equals(this.marca)
                && auto.modello.equals(this.modello)
                && auto.targa.equals(this.targa)) {
            return true;
        } else {
            return false;
        }
    }

}
