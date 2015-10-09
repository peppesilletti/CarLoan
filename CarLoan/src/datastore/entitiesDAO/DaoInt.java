package datastore.entitiesDAO;

import java.util.List;

/**
 * Interfaccia che mette a disposizione i servizi CRUD per la persistenza dei
 * dati.
 *
 * @param <T>
 *            Tipo di entità per la quale persistere i dati.
 * */
public interface DaoInt<T> {
    /**
     * Crea una nuova istanza dell'entit� nel datastore.
     *
     * @param e
     *            Transfer Object con i dati dell'entit� da creare.
     * @return Esito dell'operazione.
     * */
    Boolean create(T e);

    /**
     * Preleva informazioni di un'entit� dal datastore.
     *
     * @param id
     *            Id dell'entit� da ricercare.
     * @return Transfer Object con i dati dell'entit� ricercata.
     * */
    T research(String id);

    /**
     * Aggiorna le informazioni dell'entità nel datastore.
     *
     * @param e
     *            Transfer Object con i dati dell'entit� da modificare.
     * @return Esito dell'operazione.
     * */
    Boolean update(T e);

    /**
     * Elimina l'entit� dal datastore.
     *
     * @param id
     *            id dell'entit� da eliminare
     * @return Esito dell'operazione.
     * */
    Boolean delete(String id);

    /**
     * Restituisce un insieme di entit� dello stesso tipo dal datastore.
     *
     * @return Lista dei transfer object.
     *
     * */
    List<T> getList();

    /**
     * Controlla se esiste già l'entità nel sistema.
     * @param dati
     *      TO che contiene i dati dell'entità da verificare.
     * @return
     *      Il risultato della verifica.
     * */
    Boolean existCheck(T dati);
}
