package business.businessObjects;

import java.util.List;

import transferObjects.entitiesTO.ClienteTO;
import datastore.entitiesDAO.ClienteDAO;
import datastore.factory.DAOFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Implementazione dell'interfaccia {@link ClienteInt}.
 * */
public class Cliente extends ClienteTO implements ClienteInt {

    private ClienteDAO dao;

    /**
     * Costruttore della classe.
     * */
    public Cliente() {
        try {
            this.dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL)
                    .getClienteDAO();
        } catch (Exception e) {
            DAOException ex = new DAOException(e.getMessage());
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
        }
    }

    @Override
    public final Boolean addCliente(final ClienteTO cliente) {
        return dao.create(cliente);
    }

    @Override
    public final ClienteTO getCliente(final String id) {
        return dao.research(id);
    }

    @Override
    public final Boolean updateCliente(final ClienteTO cliente) {
        return dao.update(cliente);
    }

    @Override
    public final List<ClienteTO> getAll() {
        return dao.getList();
    }

    @Override
    public Boolean existCheck(ClienteTO cliente) {
        return dao.existCheck(cliente);
    }

    @Override
    public List<ClienteTO> getClientiByCodFiscale(String codFiscale) {
        return dao.getClientiByCodFiscale(codFiscale);
    }



}
