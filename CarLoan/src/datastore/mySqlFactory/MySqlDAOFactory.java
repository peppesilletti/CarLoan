package datastore.mySqlFactory;

import datastore.entitiesDAO.AgenziaDAO;
import datastore.entitiesDAO.AnonimoDAO;
import datastore.entitiesDAO.AutovetturaDAO;
import datastore.entitiesDAO.ClasseAutovetturaDAO;
import datastore.entitiesDAO.ClienteDAO;
import datastore.entitiesDAO.ContrattoDAO;
import datastore.entitiesDAO.ExtraDAO;
import datastore.entitiesDAO.ManagerAgenziaDAO;
import datastore.entitiesDAO.ManutenzioneDAO;
import datastore.entitiesDAO.OperatoreDAO;
import datastore.entitiesDAO.TariffaDAO;
import datastore.factory.DAOFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Factory dell'implementazione MySql dei DAO delle entità. Implementa
 * l'interfaccia {@link DAOFactory}
 * */
public class MySqlDAOFactory extends DAOFactory {

    @Override
    public final OperatoreDAO getOperatoreDAO() {
        return (MySqlOperatoreDAO) createDAO(MySqlOperatoreDAO.class);
    }

    @Override
    public final AnonimoDAO getAnonimoDAO() {
        return (MySqlAnonimoDAO) createDAO(MySqlAnonimoDAO.class);
    }

    @Override
    public final ManutenzioneDAO getManutenzioneDAO() {
        return (MySqlManutenzioneDAO) createDAO(MySqlManutenzioneDAO.class);
    }

    @Override
    public final AgenziaDAO getAgenziaDAO() {
        return (MySqlAgenziaDAO) createDAO(MySqlAgenziaDAO.class);
    }

    @Override
    public final ExtraDAO getExtraDAO() {
        return (MySqlExtraDAO) createDAO(MySqlExtraDAO.class);
    }

    @Override
    public final ClasseAutovetturaDAO getClasseAutovetturaDAO() {
        return (MySqlClasseAutovetturaDAO)
                createDAO(MySqlClasseAutovetturaDAO.class);
    }

    @Override
    public final ContrattoDAO getContrattoDAO() {
        return (MySqlContrattoDAO) createDAO(MySqlContrattoDAO.class);
    }

    @Override
    public final ClienteDAO getClienteDAO() {
        return (MySqlClienteDAO) createDAO(MySqlClienteDAO.class);
    }

    @Override
    public final TariffaDAO getTariffaDAO() {
        return (MySqlTariffaDAO) createDAO(MySqlTariffaDAO.class);
    }

    @Override
    public final ManagerAgenziaDAO getManagerAgenziaDAO() {
        return (MySqlManagerAgenziaDAO) createDAO(MySqlManagerAgenziaDAO.class);
    }

    @Override
    public final AutovetturaDAO getAutovetturaDAO() {
        return (MySqlAutovetturaDAO) createDAO(MySqlAutovetturaDAO.class);
    }

    /**
     * Ritorna un DAO specifico per un'entit�.
     *
     * @param classObj
     *            La classe del DAO da ritornare.
     * @return Il DAO richiesto.
     * @throws DAOException
     *             Solleva un'eccezione se non � possibile istanziare il DAO.
     * */
    private Object createDAO(final Class classObj) {
        try {
            return classObj.newInstance();
        } catch (Exception e) {
            DAOException ex = new DAOException(e.getMessage());
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.FATAL);
        }
        return null;
    }
}
