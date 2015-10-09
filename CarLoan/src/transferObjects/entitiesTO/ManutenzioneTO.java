package transferObjects.entitiesTO;

import transferObjects.TransferObjectInt;
import business.businessObjects.Manutenzione;

/**
 * Transfer Object per il Business Object {@link Manutenzione}.
 * */
public class ManutenzioneTO implements TransferObjectInt {

    public String autovetturaId;
    public String difettiRiscontrati;
    public String riparazioniEseguite;
    public String tipo;
    public String dataInizio;
    public String dataFine;
    public String id;

    /**
     * Costruttore di default.
     * */
    public ManutenzioneTO() {
        // TODO Auto-generated constructor stub
    }
    /**
     * Costruttore con parametri.
     * @param autovetturaId
     *      Id dell'autovettura manutenuta
     * @param tipo
     *      Tipo della manutenzione
     * @param dataInizio
     *      Data di inizio manutenzione
     * */
    public ManutenzioneTO(final String autovetturaId, final String tipo,
            final String dataInizio) {
       this.autovetturaId = autovetturaId;
       this.tipo = tipo;
       this.dataInizio = dataInizio;
    }

    /**
     * Costruttore con parametri.
     * @param autovetturaId
     *      Id dell'autovettura manutenuta
     * @param tipo
     *      Tipo della manutenzione
     * @param dataInizio
     *      Data di inizio manutenzione
     * */
    public ManutenzioneTO(final String autovetturaId, final String tipo,
            final String dataInizio, final String dataFine,
            final String difetti, final String riparazioni) {
       this.autovetturaId = autovetturaId;
       this.tipo = tipo;
       this.dataInizio = dataInizio;
       this.dataFine = dataFine;
       this.difettiRiscontrati = difetti;
       this.riparazioniEseguite = riparazioni;
    }

    public Boolean equals(ManutenzioneTO m) {
        if (m.dataInizio.equals(this.dataInizio)
                && m.autovetturaId.equals(this.autovetturaId)
                && m.tipo.equals(this.tipo)
                ) {
            return true;
        } else {
            return false;
        }
    }


}
