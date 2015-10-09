package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.ContrattoTO;
import transferObjects.entitiesTO.ExtraTO;
import datastore.entitiesDAO.ContrattoDAO;
import datastore.factory.DAOFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Implementazione dell'interfaccia {@link ContrattoInt}.
 * */
public class Contratto implements ContrattoInt {

    /**
     * Istanza del DAO dell'entità contratto.
     * */
    private ContrattoDAO dao;

    /**
     * Costruttore, nel quale viene istanziato il dao specifico per la classe.
     * {@link Contratto}
     * */
    public Contratto() {
        try {
            this.dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
                    .getContrattoDAO();
        } catch (Exception e) {
            DAOException ex = new DAOException(e.getMessage());
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
        }
    }

    @Override
    public final Boolean addContratto(final ContrattoTO contratto) {
        return dao.create(contratto);
    }

    @Override
    public final ContrattoTO getDatiContratto(final String id) {
        return dao.research(id);
    }

    @Override
    public final Boolean chiudereContratto(final ContrattoTO id) {
        return dao.update(id);
    }

    @Override
    public final List<ContrattoTO> getAll() {
        return dao.getList();
    }

    @Override
    public ContrattoTO nextNumeroContratto() {
        return dao.getNextNumeroContratto();
    }

    @Override
    public List<ExtraTO> getExtraContratti() {
        return dao.getExtraContratti();
    }
}


