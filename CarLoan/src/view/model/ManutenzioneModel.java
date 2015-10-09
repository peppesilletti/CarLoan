package view.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe model per l'entità manutenzione.
 * */
public class ManutenzioneModel {
    private StringProperty dataInizio;
    private StringProperty dataFine;
    private StringProperty tipo;
    private StringProperty difettiRiscontrati;
    private StringProperty riparazioniEseguite;
    private AutovetturaModel autovettura;
    private StringProperty numero;

    public ManutenzioneModel(String dataInizio, String dataFine,
            String tipo, String difetti, String riparazioni,
            AutovetturaModel autovettura, String numero) {
        this.dataInizio = new SimpleStringProperty(dataInizio);
        this.dataFine = new SimpleStringProperty(dataFine);
        this.tipo = new SimpleStringProperty(tipo);
        this.difettiRiscontrati = new SimpleStringProperty(difetti);
        this.riparazioniEseguite = new SimpleStringProperty(riparazioni);
        this.autovettura = autovettura;
        this.numero = new SimpleStringProperty(numero);
    }


    public String getDataInizio() {
        return dataInizio.get();
    }


    public void setDataInizio(StringProperty dataInizio) {
        this.dataInizio = dataInizio;
    }


    public String getDataFine() {
        return dataFine.get();
    }


    public void setDataFine(StringProperty dataFine) {
        this.dataFine = dataFine;
    }


    public StringProperty getTipoProperty() {
        return tipo;
    }
    public void setTipoProperty(StringProperty tipo) {
        this.tipo = tipo;
    }
    public StringProperty getDifettiRiscontratiProperty() {
        return difettiRiscontrati;
    }
    public void setDifettiRiscontratiProperty(StringProperty difettiRiscontrati) {
        this.difettiRiscontrati = difettiRiscontrati;
    }
    public StringProperty getRiparazioniEseguiteProperty() {
        return riparazioniEseguite;
    }
    public void setRiparazioniEseguiteProperty(
            StringProperty riparazioniEseguite) {
        this.riparazioniEseguite = riparazioniEseguite;
    }
    public AutovetturaModel getAutovettura() {
        return autovettura;
    }
    public void setAutovettura(AutovetturaModel autovettura) {
        this.autovettura = autovettura;
    }

    public String getTipo() {
        return tipo.getValue();
    }

    public StringProperty getNumero() {
        return numero;
    }

    public void setNumero(StringProperty numero) {
        this.numero = numero;
    }

}
