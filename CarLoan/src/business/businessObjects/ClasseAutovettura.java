package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.ClasseAutovetturaTO;
import datastore.entitiesDAO.ClasseAutovetturaDAO;
import datastore.factory.DAOFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Implementazione dell'interfaccia {@link ClasseAutovettura}.
 * */
public class ClasseAutovettura extends ClasseAutovetturaTO
implements ClasseAutovetturaInt {

    private ClasseAutovetturaDAO dao;

    /**
     * Costruttore della classe.
     * */
    public ClasseAutovettura() {
        try {
            this.dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
                    .getClasseAutovetturaDAO();
        } catch (Exception e) {
            DAOException ex = new DAOException(e.getMessage());
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
        }
    }

    @Override
    public final Boolean addClasseAutovettura(
            final ClasseAutovetturaTO classe) {
        return dao.create(classe);
    }

    @Override
    public final ClasseAutovetturaTO getClasseAutovettura(final String id) {
        return dao.research(id);
    }

    @Override
    public final Boolean deleteClasseAutovettura(final String id) {
        return dao.delete(id);
    }

    @Override
    public final Boolean updateClasseAutovettura(
            final ClasseAutovetturaTO classe) {
        return dao.update(classe);
    }

    @Override
    public final List<ClasseAutovetturaTO> getAll() {
        return dao.getList();
    }

    @Override
    public Boolean existCheck(ClasseAutovetturaTO dati) {
        return dao.existCheck(dati);
    }


}
