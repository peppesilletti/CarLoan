package transferObjects.request;

/**
 * Richiesta senza parametri.
 * Implementa l'interfaccia {@link RequestInt}
 * */
public class SimpleRequest implements RequestInt {

    private String request;
    private RequestType type;

    /**
     * Costruttore della classe.
     * */
    public SimpleRequest() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Costruttore della classe.
     * */
    public SimpleRequest(final String request, final RequestType type) {
        this.request = request;
        this.type = type;
    }

    public final void setType(final RequestType type) {
        this.type = type;
    }

    @Override
    public final String getRequest() {
        return request;
    }

    @Override
    public final void setRequest(final String request) {
       this.request = request;

    }

    @Override
    public final RequestType getType() {
        return type;
    }

}
