package transferObjects.response;

/**
 * Classe per una risposta senza parametri.
 * Implementa l'interfaccia {@link ResponseInt}
 * */
public class SimpleResponse implements ResponseInt {

    Boolean response;

    /**
     * Costruttore di default.
     * */
    public SimpleResponse() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Costruttore con parametri.
     * */
    public SimpleResponse(Boolean response) {
        this.response = response;
    }

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

}
