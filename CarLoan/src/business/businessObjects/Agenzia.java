package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.ExtraAgenziaTO;
import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.entitiesTO.OperatoreTO;
import datastore.entitiesDAO.AgenziaDAO;
import datastore.factory.DAOFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Implementazione dell'interfaccia {@link AgenziaInt}.
 * */
public class Agenzia extends AgenziaTO implements AgenziaInt {
    /**
     * Istanza del DAO dell'entità agenzia.
     * */
    private AgenziaDAO dao;

    /**
     * Costruttore, nel quale viene istanziato il dao specifico per la classe.
     * {@link Agenzia}
     * */
    public Agenzia() {
        try {
            this.dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
                    .getAgenziaDAO();
        } catch (Exception e) {
            DAOException ex = new DAOException(e.getMessage());
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
        }
    }

    @Override
    public final Boolean addAgenzia(final AgenziaTO agenzia) {
        return dao.create(agenzia);
    }

    @Override
    public final AgenziaTO getAgenziaById(
            final String id) {
        return dao.research(id);
    }

    @Override
    public final Boolean deleteAgenzia(final String id) {
        return dao.delete(id);
    }

    @Override
    public final Boolean updateAgenzia(final AgenziaTO agenzia) {
        return dao.update(agenzia);
    }

    @Override
    public final List<AgenziaTO> getAll() {
        return dao.getList();
    }

    @Override
    public final ManagerAgenziaTO getManagerAgenzia(final String id) {
        return dao.getManagerAgenzia(id);
    }

    @Override
    public final List<OperatoreTO> getOperatoriAgenzia(final String id) {
        return dao.getOperatori(id);
    }

    @Override
    public Boolean existCheck(AgenziaTO dati) {
        return dao.existCheck(dati);
    }

    @Override
    public List<ExtraAgenziaTO> getExtraAgenzia(String id) {
        return dao.getExtraAgenzia(id);
    }

    @Override
    public Boolean deleteExtraAgenzia(ExtraAgenziaTO id) {
        return dao.deleteExtraAgenzia(id);
    }

    @Override
    public Boolean insertExtraAgenzia(ExtraAgenziaTO extra) {
        return dao.insertExtraAgenzia(extra);
    }

    @Override
    public List<AgenziaTO> getAgenziaByCittà(String città) {
        return dao.getAgenziaByCittà(città);
    }

    @Override
    public Boolean checkContrattiAgenzia(String id) {
        return dao.checkContrattiAgenzia(id);
    }

    @Override
    public Boolean checAutovettureAgenzia(String id) {
        return dao.checAutovettureAgenzia(id);
    }

}
