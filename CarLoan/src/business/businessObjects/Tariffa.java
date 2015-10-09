package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.TariffaTO;
import datastore.entitiesDAO.TariffaDAO;
import datastore.factory.DAOFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Implementazione dell'interfaccia {@link TariffaInt}.
 * */
public class Tariffa extends TariffaTO implements TariffaInt {

    private TariffaDAO dao;

    /**
     * Costruttore della classe.
     * */
    public Tariffa() {
        try {
            this.dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
                    .getTariffaDAO();
        } catch (Exception e) {
            DAOException ex = new DAOException(e.getMessage());
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
        }
    }

    @Override
    public final Boolean addTariffa(final TariffaTO tariffa) {
        return dao.create(tariffa);
    }

    @Override
    public final TariffaTO getTariffa(final String id) {
        return dao.research(id);
    }

    @Override
    public final Boolean deleteTariffa(final String id) {
        return dao.delete(id);
    }

    @Override
    public final Boolean updateTariffa(final TariffaTO tariffa) {
        return dao.update(tariffa);
    }

    @Override
    public final List<TariffaTO> getAll() {
        return dao.getList();
    }

    @Override
    public List<TariffaTO> getTariffaByClasse(String classe) {
        return dao.getTariffaByClasse(classe);
    }


}
