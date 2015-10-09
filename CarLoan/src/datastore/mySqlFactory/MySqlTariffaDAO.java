package datastore.mySqlFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import transferObjects.entitiesTO.TariffaTO;
import datastore.entitiesDAO.TariffaDAO;
import datastore.mySqlDB.DbUtil;
import datastore.mySqlDB.MySqlConnectionFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Classe che si interfaccia con il database MySql per la memorizzazione e
 * reperimento dei dati di una tariffa. Implementa {@link TariffaDAO}.
 * */
public class MySqlTariffaDAO extends MySqlDao implements TariffaDAO {

    public MySqlTariffaDAO() {
        super();
    }

    @Override
    public final Boolean create(final TariffaTO tariffa) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("CreateTariffa"));

            int i = 1;
            statement.setFloat(i++, tariffa.importoUnitario);
            statement.setString(i++, tariffa.modalità);
            statement.setString(i++, tariffa.chilometraggio);
            statement.setInt(i++,
                    Integer.parseInt(tariffa.classeAutovetturaId));
            statement.setFloat(i++, tariffa.chilometriGiorno);

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
    public final TariffaTO research(final String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        TariffaTO tariffa = new TariffaTO();
        ResultSet result = null;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ResearchTariffa"));

            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeQuery();

            while (result.next()) {
                tariffa.importoUnitario = result.getFloat("importo_unitario");
                tariffa.modalità = result.getString("modalità");
                tariffa.classeAutovetturaId =
                        Integer.toString(
                                result.getInt("classe_autovettura_id"));
                tariffa.id = id;
                tariffa.chilometraggio = result.getString("chilometraggio");
                tariffa.chilometriGiorno  = result.getFloat("chilometri_giorno");
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return tariffa;
    }

    @Override
    public final Boolean delete(final String id) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {

            statement = con.prepareStatement(
                    sqlFac.getQuery("DeleteTariffa"));
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
    public final Boolean update(final TariffaTO tariffa) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("UpdateTariffa"));

            int i = 1;
            statement.setFloat(i++, tariffa.importoUnitario);
            statement.setString(i++, tariffa.modalità);
            statement.setString(i++, tariffa.chilometraggio);
            statement.setInt(i++,
                    Integer.parseInt(tariffa.classeAutovetturaId));
            statement.setFloat(i++, tariffa.chilometriGiorno);
            statement.setInt(i++, Integer.parseInt(tariffa.id));

            result = statement.executeUpdate();

            if (result > 0) {
                response = true;
            }
        } catch (SQLException e) {
            if (result <= 0) {
                return false;
            } else {
                handleExceptions(e, ErrorHandlerInt.WARNING);
            }
        }  finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return response;
    }

    @Override
    public final List<TariffaTO> getList() {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<TariffaTO> response = new ArrayList<TariffaTO>();

        ResultSet result = null;
        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("GetTariffaList"));

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                while (result.next()) {
                    TariffaTO tariffa = new TariffaTO();
                    tariffa.importoUnitario = result.getFloat("importo_unitario");
                    tariffa.modalità = result.getString("modalità");
                    tariffa.classeAutovetturaId =
                            Integer.toString(
                                    result.getInt("classe_autovettura_id"));
                    tariffa.id = Integer.toString(result.getInt("id"));
                    tariffa.chilometraggio = result.getString("chilometraggio");
                    tariffa.chilometriGiorno = result.getFloat(
                            "chilometri_giorno");
                    response.add(tariffa);
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
        ErrorHandler er = ErrorHandler.getIstance();
        er.processError(ex.getClass(), ex, t);
    }

    @Override
    public Boolean existCheck(TariffaTO dati) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TariffaTO> getTariffaByClasse(String classe) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<TariffaTO> response = null;

        ResultSet result = null;
        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("GetTariffeByClasse"));

            statement.setString(1, classe);

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                response = new ArrayList<TariffaTO>();
                while (result.next()) {
                    TariffaTO tariffa = new TariffaTO();
                    tariffa.importoUnitario =
                            result.getFloat("importo_unitario");
                    tariffa.modalità = result.getString("modalità");
                    tariffa.classeAutovetturaId =
                            Integer.toString(
                                    result.getInt("classe_autovettura_id"));
                    tariffa.id = Integer.toString(result.getInt("id"));
                    tariffa.chilometriGiorno = result.getFloat(
                            "chilometri_giorno");
                    tariffa.chilometraggio = result.getString("chilometraggio");

                    response.add(tariffa);
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


}
