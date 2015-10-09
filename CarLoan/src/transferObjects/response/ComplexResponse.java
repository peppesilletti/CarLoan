package transferObjects.response;

import java.util.LinkedList;
import java.util.List;

/**
 * Trasporta la risposta di una richiesta con parametri.
 * Implementa l'interfaccia {@link ResponseInt}
 * @param <T>
 * */
public class ComplexResponse<T> implements ResponseInt {

    /**
     * Parametri della risposta.
     * */
    private List<T> parameters;

    /**
     * Risultato.
     * */
    private Boolean result;

    /**
     * Costruttore per la classe.
     * */
    public ComplexResponse() {
        parameters = new LinkedList<T>();
    }

    /**Restituisce true se l'operazione è andata a buon fine,
     * altrimenti false.
     * @return valore di successo o fallimento dell'operazione
     * */
    public final Boolean getResult() {
        return parameters.size() > 0;
    }

    /**
     * Aggiunge un elemento alla lista del risultato.
     *
     * @param parameter
     *            elemento-risultato
     * */
    public final void addParameter(final T parameter) {
        if (parameter != null) {
            this.parameters.add(parameter);
            this.result = true;
        }
    }

    /**
     * Aggiunge il risultato dell'operazione.
     * @param result
     *      Risultato dell'operazione.
     * */
    public final void addResult(final Boolean result) {
        this.result = result;
    }

    /**
     * @param list
     *      Lista di oggetti da passare nella risposta.
     * */
    public final void addParameterList(List<T> list) {
        this.parameters = list;
    }

    /**
     * Restituisce il risultato.
     *
     * @return lista-risultato
     * */
    public final List<T> getParameters() {
        return parameters;
    }
}
