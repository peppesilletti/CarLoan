package transferObjects.request;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe per il trasporto di richieste dal client al business
 * tier con parametri.
 * Implementa {@link RequestInt}
 * @param <T>
 *      Parametro generico che si istanzia in un transfer object.
 * */
public final class ComplexRequest<T> implements RequestInt {

    /**
     * Nome della richiesta.
     * */
    private String      request;

    /**
     * Parametri della richiesta.
     * */
    private List<T> parameters;

    /**
     * Tipo della richiesta.
     * */
    private RequestType type;

    /**
     * Costruttore della classe.
     *
     * @param request
     *            il nome della richiesta
     * @param type
     *            il tipo della richiesta
     * */
    public ComplexRequest(final String request, final RequestType type) {
        this.request = request;
        this.type = type;
        parameters = new LinkedList<>();
    }

    /**
     * Restituisce la richiesta.
     * @return
     *      Richiesta.
     * */
    @Override
    public String getRequest() {
        return request;
    }

    /**
     * Restituisce il tipo di richiesta.
     * @return
     *      Tipo di richiesta.
     * */
    @Override
    public RequestType getType() {
        return type;
    }

    /**
     * Aggiunge un parametro alla lista di parametri.
     *
     * @param p
     *            parametro da aggiungere
     * */
    public void addParameter(final T p) {
        parameters.add(p);
    }

    /**
     * Restituisce la lista dei parametri.
     *
     * @return lista dei parametri
     * */
    public List<T> getParameters() {
        return parameters;
    }

    @Override
    public void setRequest(String request) {
        this.request = request;
    }
}
