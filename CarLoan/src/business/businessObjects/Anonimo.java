package business.businessObjects;

import transferObjects.entitiesTO.AnonimoTO;
import datastore.entitiesDAO.AnonimoDAO;
import datastore.factory.DAOFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Implementazione dell'interfaccia {@link AnonimoInt}.
 * */
public class Anonimo extends AnonimoTO implements AnonimoInt {

    /**
     * Data access object dell'entit� anonimo.
     * */
    private AnonimoDAO dao;

    /**
     * Costruttore, nel quale viene istanziato il dao specifico. per la classe
     * {@link Anonimo}
     * */
    public Anonimo() {
        try {
            this.dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
                    .getAnonimoDAO();
        } catch (Exception e) {
            DAOException ex = new DAOException(e.getMessage());
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
        }
    }

    /**
     * Metodo che si interfaccia con {@link AnonimoDAO} per effettuare
     * l'autenticazione.
     *
     * @param dati
     *            transfer object contentente i dati dell'utente che si sta
     *            autenticando
     * @return il risultato dell'autenticazione
     * */

    @Override
    public final Boolean autenticazione(final AnonimoTO dati) {
        return dao.accountExsist(dati);
    }

    @Override
    public final AnonimoTO getUserType(final String username) {
        return dao.searchByUsername(username);
    }

}
