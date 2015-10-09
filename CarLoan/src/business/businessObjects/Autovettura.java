package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.AutovetturaTO;
import datastore.entitiesDAO.AutovetturaDAO;
import datastore.factory.DAOFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Implementazione dell'interfaccia {@link AutovetturaInt}.
 * */
public class Autovettura extends AutovetturaTO implements AutovetturaInt {

    private AutovetturaDAO dao;

    /**
     * Costruttore della classe.
     * */
    public Autovettura() {
        try {
            this.dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
                    .getAutovetturaDAO();
        } catch (Exception e) {
            DAOException ex = new DAOException(e.getMessage());
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
        }
    }

    @Override
    public final Boolean addAutovettura(final AutovetturaTO autovettura) {
        return dao.create(autovettura);
    }

    @Override
    public final AutovetturaTO getAutovettura(final String id) {
        return dao.research(id);
    }

    @Override
    public final Boolean deleteAutovettura(final String id) {
        return dao.delete(id);
    }

    @Override
    public final Boolean updateAutovettura(final AutovetturaTO autovettura) {
        return dao.update(autovettura);
    }

    @Override
    public final List<AutovetturaTO> getAll() {
        return dao.getList();
    }

    @Override
    public Boolean existCheck(AutovetturaTO dati) {
        return dao.existCheck(dati);
    }

    @Override
    public List<AutovetturaTO> getAutovetturaParams(String targa, String marca,
            String classe) {
        return dao.getAutovetturaParams(targa, marca, classe);
    }

    @Override
    public List<AutovetturaTO> getAutovettureDisponibili(String classeId,
            String agenziaId) {
        return dao.getAutovettureDisponibili(classeId, agenziaId);
    }

    @Override
    public Boolean checkAutoDisponibile(String id) {
        return dao.checkAutoDisponibile(id);
    }


}
