package transferObjects.request;

/**
 * Interfaccia per una richiesta.
 * */
public interface RequestInt {
    String getRequest();
    void setRequest(String request);
    RequestType getType();
}
