package view.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe model per l'entità manager agenzia.
 * */
public class ManagerAgenziaModel {
    private StringProperty username;
    private StringProperty password;
    private StringProperty nome;
    private StringProperty cognome;
    private AgenziaModel agenzia;
    private StringProperty id;

    public ManagerAgenziaModel() {
        // TODO Auto-generated constructor stub
    }

    public ManagerAgenziaModel(String username, String password, String nome,
            String cognome, AgenziaModel agenzia, String id) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.nome = new SimpleStringProperty(nome);
        this.cognome = new SimpleStringProperty(cognome);
        this.agenzia = agenzia;
        this.id = new SimpleStringProperty(id);
    }

    public ManagerAgenziaModel(String username, String nome,
            String cognome, String id) {
        this.username = new SimpleStringProperty(username);
        this.nome = new SimpleStringProperty(nome);
        this.cognome = new SimpleStringProperty(cognome);
        this.id = new SimpleStringProperty(id);
    }

    public StringProperty getUsernameProperty() {
        return username;
    }
    public void setUsernameProperty(String username) {
        this.username = new SimpleStringProperty(username);
    }
    public StringProperty getPasswordProperty() {
        return password;
    }
    public void setPasswordProperty(String password) {
        this.password = new SimpleStringProperty(password);
    }
    public StringProperty getNomeProperty() {
        return nome;
    }
    public void setNomeProperty(String nome) {
        this.nome = new SimpleStringProperty(nome);
    }
    public StringProperty getCognomeProperty() {
        return cognome;
    }
    public void setCognomeProperty(String cognome) {
        this.cognome = new SimpleStringProperty(cognome);
    }

    public String getNome() {
        return this.nome.getValue();
    }

    public String getCognome() {
        return this.cognome.getValue();
    }

    public AgenziaModel getAgenzia() {
        return agenzia;
    }

    public void setIdProperty(String id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return nome.getValue()+" "+cognome.getValue();
    }

}
