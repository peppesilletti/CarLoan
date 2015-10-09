package view.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe model per l'entità extra agenzia.
 * */
public class ExtraAgenziaModel {

    private StringProperty nome;
    private StringProperty descrizione;
    private StringProperty id;

    /**
     * @param nome
     * @param descrizione
     * @param id
     * @param quantità
     */
    public ExtraAgenziaModel(String nome, String descrizione,
            String id) {
        this.nome = new SimpleStringProperty(nome);
        this.descrizione = new SimpleStringProperty(descrizione);
        this.id = new SimpleStringProperty(id);
    }
    public StringProperty getNomeProperty() {
        return nome;
    }
    public void setNomeProperty(StringProperty nome) {
        this.nome = nome;
    }
    public StringProperty getDescrizioneProperty() {
        return descrizione;
    }
    public void setDescrizioneProperty(StringProperty descrizione) {
        this.descrizione = descrizione;
    }
    public StringProperty getIdProperty() {
        return id;
    }
    public void setIdProperty(StringProperty id) {
        this.id = id;
    }

    public String getNome() {
        return nome.get();
    }

    public String getDescrizione() {
        return descrizione.get();
    }

    @Override
    public String toString() {
        return nome.get()+", "+descrizione.get();
    }

}
