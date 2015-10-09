package view.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe model per l'entità autovettura.
 * */
public class AutovetturaModel {

    private StringProperty targa;
    private StringProperty marca;
    private StringProperty modello;
    private FloatProperty  chilometraggio;
    private StringProperty alimentazione;
    private ClasseAutovetturaModel classeAutovettura;
    private StringProperty id;

    /**
     * Costruttore con parametri.
     * */
    public AutovetturaModel(String targa, String marca, String modello,
            Float chilometraggio, ClasseAutovetturaModel classeAutovettura,
            String alimentazione, String id) {
        this.targa = new SimpleStringProperty(targa);
        this.marca = new SimpleStringProperty(marca);
        this.modello = new SimpleStringProperty(modello);
        this.chilometraggio = new SimpleFloatProperty(chilometraggio);
        this.classeAutovettura = classeAutovettura;
        this.alimentazione = new SimpleStringProperty(alimentazione);
        this.id = new SimpleStringProperty(id);
    }

    public StringProperty getTargaProperty() {
        return targa;
    }
    public void setTargaProperty(StringProperty targa) {
        this.targa = targa;
    }
    public StringProperty getMarcaProperty() {
        return marca;
    }
    public void setMarcaProperty(StringProperty marca) {
        this.marca = marca;
    }
    public StringProperty getModelloProperty() {
        return modello;
    }
    public void setModelloProperty(StringProperty modello) {
        this.modello = modello;
    }
    public FloatProperty getChilometraggioProperty() {
        return chilometraggio;
    }
    public void setChilometraggioProperty(FloatProperty chilometraggio) {
        this.chilometraggio = chilometraggio;
    }
    public StringProperty getAlimentazioneProperty() {
        return alimentazione;
    }
    public void setAlimentazioneProperty(StringProperty alimentazione) {
        this.alimentazione = alimentazione;
    }
    public ClasseAutovetturaModel getClasseAutovetturaProperty() {
        return classeAutovettura;
    }
    public void setClasseAutovetturaProperty(ClasseAutovetturaModel classeAutovettura) {
        this.classeAutovettura = classeAutovettura;
    }

    public String getTarga() {
        return targa.getValue();
    }

    public String getMarca() {
        return marca.getValue();
    }

    public String getModello() {
        return modello.getValue();
    }

    public String getAlimentazione() {
        return alimentazione.get();
    }

    public StringProperty getId() {
        return id;
    }

    public void setId(StringProperty id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return marca.get()+" "+modello.get()+"-"+targa.get();
    }

}
