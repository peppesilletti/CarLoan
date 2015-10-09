package datastore.mySqlFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import transferObjects.entitiesTO.AnonimoTO;
import datastore.entitiesDAO.AnonimoDAO;
import datastore.mySqlDB.DbUtil;
import datastore.mySqlDB.MySqlConnectionFactory;
import datastore.mySqlDB.SqlQueryFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Classe che preleva informazioni dal database SQL per l'attore Anonimo.
 * Implementa l'interfaccia {@link AnonimoDAO}.
 * */
public class MySqlAnonimoDAO implements AnonimoDAO {

    /**
     * Query Factory.
     * */
    private SqlQueryFactory sqlFac;

    /**
     * Costruttore.
     * */
    public MySqlAnonimoDAO() {
        sqlFac = SqlQueryFactory.getInstance();
    }

    @Override
    public final Boolean accountExsist(final AnonimoTO dati) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        try {

            statement = con.prepareStatement(sqlFac.getQuery("Autenticazione"));
            statement.setString(1, dati.username);
            statement.setString(2, dati.password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                response = true;
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {
            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return response;
    }

    /**
     * Gestisce le eccezioni DAO che si verificano.
     *
     * @param e
     *            eccezione verificatasi
     * @param t
     *            tipo di errore da sollevare
     * */
    private void handleExceptions(final Exception e, final Level t) {
        DAOException ex = new DAOException(e.getMessage());
        ErrorHandlerInt er = ErrorHandler.getIstance();
        er.processError(ex.getClass(), ex, t);
    }

    @Override
    public final AnonimoTO searchByUsername(final String username) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        AnonimoTO anonimo = new AnonimoTO();
        ResultSet result = null;

        try {
            statement = con
                    .prepareStatement(sqlFac.getQuery("ResearchUserByName"));

            statement.setString(1, username);

            result = statement.executeQuery();

            while (result.next()) {
                anonimo.username = result.getString("username");
                anonimo.tipo = result.getString("tipo");
                anonimo.agenziaId = result.getString("agenzia_id");
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return anonimo;
    }

}
