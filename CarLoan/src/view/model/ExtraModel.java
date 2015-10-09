package view.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe model per l'entità extra.
 * */
public class ExtraModel {

    private StringProperty nome;
    private FloatProperty  prezzo;
    private StringProperty descrizione;
    private StringProperty id;

    /**
     * Default constructor.
     * */
    public ExtraModel() {
        // TODO Auto-generated constructor stub
    }

    public ExtraModel(String nome, Float prezzo, String descrizione,
            String id) {
        this.nome = new SimpleStringProperty(nome);
        this.prezzo = new SimpleFloatProperty(prezzo);
        this.descrizione = new SimpleStringProperty(descrizione);
        this.id = new SimpleStringProperty(id);
    }

    public StringProperty getNomeProperty() {
        return nome;
    }

    public void setNomeProperty(StringProperty nome) {
        this.nome = nome;
    }

    public FloatProperty getPrezzoProperty() {
        return prezzo;
    }

    public void setPrezzoProperty(FloatProperty prezzo) {
        this.prezzo = prezzo;
    }

    public StringProperty getDescrizioneProperty() {
        return descrizione;
    }

    public void setDescrizioneProperty(StringProperty descrizione) {
        this.descrizione = descrizione;
    }

    public String getNome() {
        return this.nome.getValue();
    }

    public StringProperty getIdProperty() {
        return id;
    }

    public void setSProperty(StringProperty id) {
        this.id = id;
    }

}
