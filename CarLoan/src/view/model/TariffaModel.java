package view.model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe model per una tariffa.
 * */
public class TariffaModel {

    private FloatProperty   importoUnitario;
    private StringProperty  modalità;
    private ClasseAutovetturaModel  classeAutovettura;
    private StringProperty  chilometraggio;
    private StringProperty id;
    private FloatProperty chilometriGiorno;

    public Float getChilometriGiorno() {
        return chilometriGiorno.get();
    }

    public void setChilometriGiorno(Float chilometriGiorno) {
        this.chilometriGiorno = new SimpleFloatProperty(chilometriGiorno);
    }

    public TariffaModel() {}

    public TariffaModel(Float importoUnitario, String modalità,
            ClasseAutovetturaModel classeAutovettura,
            String chilometraggio, String id, Float kmGiorno) {

        this.importoUnitario = new SimpleFloatProperty(importoUnitario);
        this.modalità = new SimpleStringProperty(modalità);
        this.classeAutovettura = classeAutovettura;
        this.chilometraggio = new SimpleStringProperty(chilometraggio);
        this.id = new SimpleStringProperty(id);
        this.chilometriGiorno = new SimpleFloatProperty(kmGiorno);
    }

    public FloatProperty getImportoUnitarioProperty() {
        return importoUnitario;
    }
    public void setImportoUnitarioProperty(FloatProperty importoUnitario) {
        this.importoUnitario = importoUnitario;
    }
    public StringProperty getModalitàProperty() {
        return modalità;
    }
    public void setModalitàProperty(StringProperty modalità) {
        this.modalità = modalità;
    }
    public ClasseAutovetturaModel getClasseAutovetturaProperty() {
        return classeAutovettura;
    }
    public void setClasseAutovetturaProperty(ClasseAutovetturaModel classeAutovettura) {
        this.classeAutovettura = classeAutovettura;
    }
    public StringProperty getChilometraggioProperty() {
        return chilometraggio;
    }
    public void setChilometraggioProperty(String chilometraggio) {
        this.chilometraggio = new SimpleStringProperty(chilometraggio);
    }
    public StringProperty getIdProperty() {
        return id;
    }
    public void setIdProperty(StringProperty id) {
        this.id = id;
    }

    public ClasseAutovetturaModel getClasse() {
        return classeAutovettura;
    }

    public String getId() {
        return id.getValue();
    }

    public Float getImportoUnitario() {
        return importoUnitario.getValue();
    }

    public String getChilometraggio() {
        return chilometraggio.get();
    }

    public String getModalità() {
        return modalità.get();
    }

    @Override
    public String toString() {
        return "Codice: "+id.get()+", Chilometraggio: "+getChilometraggio()
                +", Modalità: "+getModalità();
    }



}
