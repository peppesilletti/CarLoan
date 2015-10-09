package transferObjects.entitiesTO;

/**
 * Transfer object per un extra di un'agenzia.
 * */
public class ExtraAgenziaTO {
    public String nome;
    public String descrizione;
    public String id;
    public String agenziaId;

    /**
     * Costruttore di default.
     * */
    public ExtraAgenziaTO() { }

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
    public ExtraAgenziaTO(final String nome, final String descrizione,
            final String agenziaId, String id) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.agenziaId = agenziaId;
        this.id = id;
    }
}
