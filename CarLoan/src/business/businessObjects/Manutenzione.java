package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.ManutenzioneTO;
import datastore.entitiesDAO.ManutenzioneDAO;
import datastore.factory.DAOFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Implementazione della classe {@link Manutenzione}.
 * */
public class Manutenzione extends ManutenzioneTO implements ManutenzioneInt {

    private ManutenzioneDAO dao;


    /**
     * Costruttore della classe.
     * */
    public Manutenzione() {
        try {
            this.dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
                    .getManutenzioneDAO();
        } catch (Exception e) {
            DAOException ex = new DAOException(e.getMessage());
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
        }
    }


    @Override
    public final Boolean addManutenzione(final ManutenzioneTO manutenzione) {
        return dao.create(manutenzione);
    }


    @Override
    public final ManutenzioneTO getManutenzione(final String id) {
        return dao.research(id);
    }


    @Override
    public final Boolean deleteManutenzione(final String id) {
        return dao.delete(id);
    }


    @Override
    public final Boolean updateManutenzione(final ManutenzioneTO manutenzione) {
        return dao.update(manutenzione);
    }


    @Override
    public final List<ManutenzioneTO> getAll() {
        return dao.getList();
    }


    @Override
    public List<ManutenzioneTO> getManutenzioniAutovettura(String targa) {
        return dao.getManutenzioniAutovettura(targa);
    }


}
