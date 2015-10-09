package datastore.mySqlFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import transferObjects.entitiesTO.ClasseAutovetturaTO;
import datastore.entitiesDAO.ClasseAutovetturaDAO;
import datastore.mySqlDB.DbUtil;
import datastore.mySqlDB.MySqlConnectionFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Classe che si interfaccia con il database MySql per la memorizzazione e
 * reperimento dei classeAutovettura di una classe autovettura. Implementa
 * {@link ClasseAutovetturaDAO}.
 * */
public class MySqlClasseAutovetturaDAO extends MySqlDao
implements ClasseAutovetturaDAO {

    /**
     * Costruttore di default.
     */
    public MySqlClasseAutovetturaDAO() {
        super();
    }

    @Override
    public final Boolean create(final ClasseAutovetturaTO classeAutovettura) {

        Connection con =
                MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {

            int i = 1;
            statement = con.prepareStatement(
                    sqlFac.getQuery("CreateClasseAutovettura"));
            statement.setString(i++, classeAutovettura.nome);
            statement.setInt(i++, classeAutovettura.ariaCondizionata);
            statement.setString(i++, classeAutovettura.tipoCambio);
            statement.setInt(i++, classeAutovettura.numeroPorte);
            statement.setInt(i++, classeAutovettura.numeroPosti);

            result = statement.executeUpdate();

            if (result > 0) {
                response = true;
            }
        } catch (SQLException e) {
                handleExceptions(e, ErrorHandlerInt.WARNING);
        }  finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return response;
    }

    @Override
    public final ClasseAutovetturaTO research(final String id) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ClasseAutovetturaTO classeAutovettura = new ClasseAutovetturaTO();
        ResultSet result = null;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ResearchClasseAutovettura"));

            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeQuery();

            while (result.next()) {
                classeAutovettura.ariaCondizionata =
                        result.getInt("aria_condizionata");
                classeAutovettura.nome = result.getString("nome");
                classeAutovettura.numeroPorte = result.getInt("numero_porte");
                classeAutovettura.numeroPosti = result.getInt("numero_posti");
                classeAutovettura.tipoCambio = result.getString("tipo_cambio");
                classeAutovettura.id = id;
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return classeAutovettura;
    }

    @Override
    public final Boolean delete(final String id) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;

        int result = 0;
        Boolean response = false;

        try {

            statement = con.prepareStatement(
                    sqlFac.getQuery("DeleteClasseAutovettura"));
            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeUpdate();

            if (result > 0) {
                response = true;
            }
        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.WARNING);
        } finally {
            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return response;
    }

    @Override
    public final Boolean update(final ClasseAutovetturaTO classeAutovettura) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("UpdateClasseAutovettura"));

            int i = 1;
            statement.setString(i++, classeAutovettura.nome);
            statement.setInt(i++, classeAutovettura.ariaCondizionata);
            statement.setString(i++, classeAutovettura.tipoCambio);
            statement.setInt(i++, classeAutovettura.numeroPosti);
            statement.setInt(i++, classeAutovettura.numeroPorte);
            statement.setInt(i++, Integer.parseInt(classeAutovettura.id));

            result = statement.executeUpdate();

            if (result > 0) {
                response = true;
            }
        } catch (SQLException e) {
                return false;
        }  finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return response;

    }

    @Override
    public final List<ClasseAutovetturaTO> getList() {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<ClasseAutovetturaTO> response =
                new ArrayList<ClasseAutovetturaTO>();

        ResultSet result = null;
        try {

            statement = con.prepareStatement(
                    sqlFac.getQuery("GetClasseAutovetturaList"));

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                while (result.next()) {
                    ClasseAutovetturaTO classeAutovettura =
                            new ClasseAutovetturaTO();
                    classeAutovettura.nome = result.getString("nome");
                    classeAutovettura.ariaCondizionata =
                            result.getInt("aria_condizionata");
                    classeAutovettura.numeroPorte =
                            result.getInt("numero_porte");
                    classeAutovettura.numeroPosti =
                            result.getInt("numero_posti");
                    classeAutovettura.tipoCambio =
                            result.getString("tipo_cambio");
                    classeAutovettura.id =
                            Integer.toString(result.getInt("id"));
                    response.add(classeAutovettura);
                }
                return response;
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
    public Boolean existCheck(ClasseAutovetturaTO dati) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ResultSet result = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ExistClasseAutovettura"));

            statement.setString(1, dati.nome);

            result = statement.executeQuery();

            if (result.next()) {
                if (result.getInt("total") > 0) {
                    response = true;
                }
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

}
