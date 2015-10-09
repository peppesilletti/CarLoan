package view.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe model per l'entità operatore.
 * */
public class OperatoreModel {

    private StringProperty username;
    private StringProperty password;
    private StringProperty nome;
    private StringProperty cognome;
    private StringProperty agenzia;

    public OperatoreModel(String username, String nome, String cognome) {
        this.username = new SimpleStringProperty(username);
        this.nome = new SimpleStringProperty(nome);
        this.cognome = new SimpleStringProperty(cognome);
    }

    public StringProperty getUsernameProperty() {
        return username;
    }
    public void setUsernameProperty(String username) {
        this.username.set(username);
    }
    public StringProperty getPasswordProperty() {
        return password;
    }
    public void setPasswordProperty(String password) {
        this.password.set(password);
    }
    public StringProperty getNomeProperty() {
        return nome;
    }
    public void setNomeProperty(String nome) {
        this.nome.set(nome);;
    }
    public StringProperty getCognomeProperty() {
        return cognome;
    }
    public void setCognomeProperty(String cognome) {
        this.cognome.set(cognome);;
    }
    public StringProperty getAgenziaProperty() {
        return agenzia;
    }
    public void setAgenziaProperty(StringProperty agenzia) {
        this.agenzia = agenzia;
    }
    public String getNome() {
        return nome.getValue();
    }
    public String getCognome() {
        return cognome.getValue();
    }
}
