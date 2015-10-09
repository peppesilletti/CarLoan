package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.ExtraTO;
import datastore.entitiesDAO.ExtraDAO;
import datastore.factory.DAOFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Implementazione dell'interfaccia {@link Extra}.
 * */
public class Extra extends ExtraTO implements ExtraInt {

    private ExtraDAO dao;

    /**
     * Costruttore della classe.
     * */
    public Extra() {
        try {
            this.dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getExtraDAO();
        } catch (Exception e) {
            DAOException ex = new DAOException(e.getMessage());
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
        }
    }

    @Override
    public final Boolean addExtra(final ExtraTO extra) {
        return dao.create(extra);
    }

    @Override
    public final ExtraTO getExtra(final String id) {
        return dao.research(id);
    }

    @Override
    public final Boolean deleteExtra(final String id) {
        return dao.delete(id);
    }

    @Override
    public final Boolean updateExtra(final ExtraTO extra) {
        return dao.update(extra);
    }

    @Override
    public final List<ExtraTO> getAll() {
        return dao.getList();
    }

    @Override
    public Boolean existCheck(ExtraTO dati) {
        return dao.existCheck(dati);
    }



}
