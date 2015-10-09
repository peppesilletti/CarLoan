package commands;

import transferObjects.response.ResponseInt;

/**
 * Interfaccia di un comando.
 * */
public interface Command {
    /**
     * Esegue un comando.
     * @return
     *      Risultato dell'esecuzione.
     * */
    ResponseInt execute();
    /**
     * Annulla un comando eseguito.
     * */
    void undo();
}
