package datastore.factory;

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
import datastore.mySqlFactory.MySqlDAOFactory;

/**
 * Factory che restituisce i DAO per le diverse entità del sistema.
 * */
public abstract class DAOFactory {

    /**
     * Costante per selezionare il datastore MYSQL.
     * */
    public static final int MYSQL = 1;

    /**
     * Restituisce il DAO per l'entità Operatore.
     *
     * @return DAO per l'Operatore.
     * */
    public abstract OperatoreDAO getOperatoreDAO();

    /**
     * Restituisce il DAO per l'entità Anonimo.
     *
     * @return DAO per l'utente Anonimo.
     * */
    public abstract AnonimoDAO getAnonimoDAO();

    /**
     * Restituisce il DAO per l'entità Manutenzione.
     *
     * @return DAO per la Manutenzione.
     * */
    public abstract ManutenzioneDAO getManutenzioneDAO();

    /**
     * Restituisce il DAO per l'entità Agenzia.
     *
     * @return DAO per l'Agenzia.
     * */
    public abstract AgenziaDAO getAgenziaDAO();

    /**
     * Restituisce il DAO per l'entità Extra.
     *
     * @return DAO per l'Extra.
     * */
    public abstract ExtraDAO getExtraDAO();

    /**
     * Restituisce il DAO per l'entità Classe Autovettura.
     *
     * @return DAO per la Classe Autovettura.
     * */
    public abstract ClasseAutovetturaDAO getClasseAutovetturaDAO();

    /**
     * Restituisce il DAO per l'entità Contratto.
     *
     * @return DAO per il Contratto.
     * */
    public abstract ContrattoDAO getContrattoDAO();

    /**
     * Restituisce il DAO per l'entità Cliente.
     *
     * @return DAO per il Cliente.
     * */
    public abstract ClienteDAO getClienteDAO();

    /**
     * Restituisce il DAO per l'entità Tariffa.
     *
     * @return DAO per la Tariffa.
     * */
    public abstract TariffaDAO getTariffaDAO();

    /**
     * Restituisce il DAO per l'entità Manager Agenzia.
     *
     * @return DAO per il Manager Agenzia.
     * */
    public abstract ManagerAgenziaDAO getManagerAgenziaDAO();

    /**
     * Restituisce il DAO per l'entità Autovettura.
     *
     * @return DAO per l'Autovettura.
     * */
    public abstract AutovetturaDAO getAutovetturaDAO();

    /**
     * Restituisce una DAOFactory specifica.
     *
     * @param whichFactory
     *            Tipo della factory richiesta.
     * @return Factory richiesta.
     * */
    public static DAOFactory getDAOFactory(final int whichFactory) {
        switch (whichFactory) {
            case MYSQL :
                return new MySqlDAOFactory();
            default :
                return null;
        }
    }
}
