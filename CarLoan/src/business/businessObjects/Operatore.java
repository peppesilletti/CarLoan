package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.OperatoreTO;
import datastore.entitiesDAO.OperatoreDAO;
import datastore.factory.DAOFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Implementazione della classe {@link OperatoreInt}.
 * */
public class Operatore extends OperatoreTO implements OperatoreInt {

    /**
     * Attributo che interfaccia il businesso object con il datastore.
     * */
    private OperatoreDAO dao;

    /**
     * Costruttore della classe.
     * */
    public Operatore() {
        try {
            this.dao = DAOFactory.getDAOFactory(
                    DAOFactory.MYSQL).getOperatoreDAO();
        } catch (Exception e) {
            DAOException ex = new DAOException(e.getMessage());
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
        }
    }

    @Override
    public final Boolean addOperatore(final OperatoreTO operatore) {
        return dao.create(operatore);
    }

    @Override
    public final OperatoreTO getOperatore(final String id) {
        return dao.research(id);
    }

    @Override
    public final Boolean deleteOperatore(final String id) {
        return dao.delete(id);
    }

    @Override
    public final Boolean updateOperatore(final OperatoreTO operatore) {
        return dao.update(operatore);
    }

    @Override
    public final List<OperatoreTO> getAll() {
        return dao.getList();
    }

    @Override
    public Boolean existCheck(OperatoreTO dati) {
        return dao.existCheck(dati);
    }

    @Override
    public List<OperatoreTO> getByNomeCognome(String nome, String cognome) {
        return dao.getByNomeCognome(nome, cognome);
    }


}
