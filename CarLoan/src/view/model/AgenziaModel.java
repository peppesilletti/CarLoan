package view.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe model per l'entità agenzia.
 * */
public class AgenziaModel {
    private StringProperty  città;
    private StringProperty  indirizzo;
    private StringProperty  telefono;
    private StringProperty id;
    private ManagerAgenziaModel manager;
    private Boolean hasManager;

    public Boolean getHasManager() {
        return hasManager;
    }

    public AgenziaModel(){}

    public AgenziaModel(String città, String indirizzo, String telefono,
            String id, ManagerAgenziaModel manager) {
        this.città = new SimpleStringProperty(città);
        this.indirizzo = new SimpleStringProperty(indirizzo);
        this.telefono = new SimpleStringProperty(telefono);
        this.id = new SimpleStringProperty(id);
        this.manager = manager;
    }

    public ManagerAgenziaModel getManagerProperty() {
        return manager;
    }

    public void setManagerProperty(ManagerAgenziaModel manager) {
        this.manager = manager;
    }

    public StringProperty getCittàProperty() {
        return città;
    }
    public void setCittàProperty(String città) {
        this.città = new SimpleStringProperty(città);
    }
    public StringProperty getIndirizzoProperty() {
        return indirizzo;
    }
    public void setIndirizzoProperty(String indirizzo) {
        this.indirizzo = new SimpleStringProperty(indirizzo);
    }
    public StringProperty getTelefonoProperty() {
        return telefono;
    }
    public void setTelefonoProperty(String telefono) {
        this.telefono = new SimpleStringProperty(telefono);
    }
    public StringProperty getIdProperty() {
        return id;
    }
    public void setIdProperty(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public String getCittà() {
        return città.getValue();
    }

    public String getIndirizzo() {
        return indirizzo.getValue();
    }

    public String getTelefono() {
        return telefono.getValue();
    }

    public String getManager() {
        return manager.toString();
    }

    @Override
    public String toString() {
        return città.getValue()+", "+indirizzo.getValue();
    }


}
