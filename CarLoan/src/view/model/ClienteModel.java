package view.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Classe model per l'entità cliente.
 * */
public class ClienteModel {
    private SimpleStringProperty nome;
    private SimpleStringProperty cognome;
    private SimpleStringProperty dataNascita;
    private SimpleStringProperty comuneNascita;
    private SimpleStringProperty comuneResidenza;
    private SimpleStringProperty indirizzo;
    private SimpleStringProperty codiceFiscale;
    private SimpleStringProperty numPatenteGuida;
    private SimpleStringProperty id;

    public ClienteModel() {
        // TODO Auto-generated constructor stub
    }

    public ClienteModel(String nome, String cognome, String dataNascita,
            String comuneNascita, String comuneResidenza, String indirizzo,
            String codiceFiscale, String numPatente) {
        this.nome = new SimpleStringProperty(nome);
        this.cognome = new SimpleStringProperty(cognome);
        this.dataNascita = new SimpleStringProperty(dataNascita);
        this.comuneNascita = new SimpleStringProperty(comuneNascita);
        this.comuneResidenza = new SimpleStringProperty(comuneResidenza);
        this.indirizzo = new SimpleStringProperty(indirizzo);
        this.codiceFiscale = new SimpleStringProperty(codiceFiscale);
        this.numPatenteGuida = new SimpleStringProperty(numPatente);
    }

    public SimpleStringProperty getNomeProperty() {
        return nome;
    }
    public void setNomeProperty(SimpleStringProperty nome) {
        this.nome = nome;
    }
    public SimpleStringProperty getCognomeProperty() {
        return cognome;
    }
    public void setCognomeProperty(SimpleStringProperty cognome) {
        this.cognome = cognome;
    }
    public SimpleStringProperty getDataNascitaProperty() {
        return dataNascita;
    }
    public void setDataNascitaProperty(SimpleStringProperty dataNascita) {
        this.dataNascita = dataNascita;
    }
    public SimpleStringProperty getComuneNascitaProperty() {
        return comuneNascita;
    }
    public void setComuneNascitaProperty(SimpleStringProperty comuneNascita) {
        this.comuneNascita = comuneNascita;
    }
    public SimpleStringProperty getComuneResidenzaProperty() {
        return comuneResidenza;
    }
    public void setComuneResidenzaProperty(SimpleStringProperty comuneResidenza) {
        this.comuneResidenza = comuneResidenza;
    }
    public SimpleStringProperty getIndirizzoProperty() {
        return indirizzo;
    }
    public void setIndirizzoProperty(SimpleStringProperty indirizzo) {
        this.indirizzo = indirizzo;
    }
    public SimpleStringProperty getCodiceFiscaleProperty() {
        return codiceFiscale;
    }
    public void setCodiceFiscaleProperty(SimpleStringProperty codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    public SimpleStringProperty getNumPatenteGuidaProperty() {
        return numPatenteGuida;
    }
    public void setNumPatenteGuidaProperty(SimpleStringProperty numPatenteGuida) {
        this.numPatenteGuida = numPatenteGuida;
    }

    public String getNome() {
        return nome.get();
    }

    public String getCognome() {
        return cognome.get();
    }

    public String getCodiceFiscale() {
        return codiceFiscale.get();
    }

    public SimpleStringProperty getIdProperty() {
        return id;
    }

    public void setIdProperty(String id) {
        this.id = new SimpleStringProperty(id);
    }


}
