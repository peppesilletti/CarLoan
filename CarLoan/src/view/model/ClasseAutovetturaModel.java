package view.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe model per l'entità classe autovettura.
 * */
public class ClasseAutovetturaModel {
    private IntegerProperty ariaCondizionata;
    private StringProperty  tipoCambio;
    private IntegerProperty numeroPorte;
    private IntegerProperty numeroPosti;
    private StringProperty  nome;
    private StringProperty id;

    /**
     * Default constructor.
     * */
    public ClasseAutovetturaModel() {

    }

    /**
     * Costruttore con parametri.
     *
     * @param
     * */

    public ClasseAutovetturaModel(String nome, String tipoCambio,
            Integer numeroPorte, Integer numeroPosti,
            Integer ariaCondizionata, String id) {
        this.nome = new SimpleStringProperty(nome);
        this.tipoCambio = new SimpleStringProperty(tipoCambio);
        this.numeroPorte = new SimpleIntegerProperty(numeroPorte);
        this.numeroPosti = new SimpleIntegerProperty(numeroPosti);
        this.ariaCondizionata = new SimpleIntegerProperty(ariaCondizionata);
        this.id = new SimpleStringProperty(id);
    }

    public IntegerProperty getAriaCondizionata() {
        return ariaCondizionata;
    }

    public StringProperty getTipoCambioProperty() {
        return tipoCambio;
    }

    public IntegerProperty getNumeroPorteProperty() {
        return numeroPorte;
    }

    public IntegerProperty getNumeroPostiProperty() {
        return numeroPosti;
    }

    public StringProperty getNomeProperty() {
        return nome;
    }

    public void setNomeProperty(StringProperty nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome.getValue();
    }

    public StringProperty getId() {
        return id;
    }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    @Override
    public String toString() {
        return nome.getValue();
    }

}
