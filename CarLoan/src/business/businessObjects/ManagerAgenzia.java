package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.ManagerAgenziaTO;
import datastore.entitiesDAO.ManagerAgenziaDAO;
import datastore.factory.DAOFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Implementazione dell'interfaccia {@link ManagerAgenziaInt}.
 * */
public class ManagerAgenzia extends ManagerAgenziaTO
        implements
            ManagerAgenziaInt {

    /**
     * Attributo che interfaccia il businesso object con il datastore.
     * */
    private ManagerAgenziaDAO dao;

    /**
     * Costruttore, nel quale viene istanziato il dao specifico per la classe
     * {@link Operatore}.
     * */
    public ManagerAgenzia() {
        try {
            this.dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
                    .getManagerAgenziaDAO();
        } catch (Exception e) {
            DAOException ex = new DAOException(e.getMessage());
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
        }
    }


    /**
     * Restituisce il transfer object per questa classe.
     *
     * @return Transfer Object.
     * */
    public final ManagerAgenziaTO getManagerAgenziaData() {
        ManagerAgenziaTO manager = new ManagerAgenziaTO();

        manager.agenziaId = this.agenziaId;
        manager.nome = this.nome;
        manager.cognome = this.cognome;
        manager.id = this.id;
        manager.password = this.password;
        manager.username = this.username;

        return manager;
    }



    @Override
    public final Boolean addManagerAgenzia(final ManagerAgenziaTO dati) {
        return dao.create(dati);
    }



    @Override
    public final ManagerAgenziaTO getManagerAgenzia(final String id) {
        return dao.research(id);
    }



    @Override
    public final Boolean deleteManagerAgenzia(final String username) {
      return dao.delete(username);
    }



    @Override
    public final Boolean updateManagerAgenzia(final ManagerAgenziaTO manager) {
        return dao.update(manager);
    }



    @Override
    public final List<ManagerAgenziaTO> getAll() {
        return dao.getList();
    }

    @Override
    public final Boolean existCheck(final ManagerAgenziaTO dati) {
        return dao.existCheck(dati);
    }


    @Override
    public final List<ManagerAgenziaTO> getByNomeCognome(final String nome, final String cognome) {
        return dao.getByNomeCognome(nome, cognome);
    }
}
