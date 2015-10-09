package datastore.mySqlFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import transferObjects.entitiesTO.ManutenzioneTO;
import utility.DateUtil;
import datastore.entitiesDAO.ManutenzioneDAO;
import datastore.mySqlDB.DbUtil;
import datastore.mySqlDB.MySqlConnectionFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Classe che si interfaccia con il database MySql per la memorizzazione e
 * reperimento dei dati di una manutenzione. Implementa {@link ManutenzioneDAO}.
 * */
public class MySqlManutenzioneDAO extends MySqlDao implements ManutenzioneDAO {

    /**
     * Costruttore di default.
     * */
    public MySqlManutenzioneDAO() {
       super();
    }

    @Override
    public final Boolean create(final ManutenzioneTO manutenzione) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {
            statement = con.prepareStatement(
                       sqlFac.getQuery("CreateManutenzione"));

            int i = 1;
            statement.setDate(i++, DateUtil.toSqlDate(manutenzione.dataInizio));
            statement.setString(i++, manutenzione.tipo);
            statement.setInt(i++,
                    Integer.parseInt(manutenzione.autovetturaId));

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
    public final ManutenzioneTO research(
            final String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ManutenzioneTO manutenzione = new ManutenzioneTO();
        ResultSet result = null;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ResearchManutenzione"));

            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeQuery();

            while (result.next()) {
                Date dataFine = result.getDate("data_fine");

                if (dataFine != null) {
                    manutenzione.dataFine = dataFine.toString();
                }

                manutenzione.dataInizio = result.getDate("data_inizio")
                        .toString();
                manutenzione.difettiRiscontrati = result
                        .getString("difetti_riscontrati");
                manutenzione.riparazioniEseguite = result
                        .getString("riparazioni_eseguite");
                manutenzione.tipo = result.getString("tipo_manutenzione");
                manutenzione.autovetturaId = Integer.toString(
                        result.getInt("autovettura_id"));
                manutenzione.id = id;
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return manutenzione;
    }

    @Override
    public final Boolean delete(final String id) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {

            statement = con.prepareStatement(
                    sqlFac.getQuery("DeleteManutenzione"));
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
    public final Boolean update(final ManutenzioneTO manutenzione) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;

        int result = 0;
        Boolean response = false;
        try {

            statement = con.prepareStatement(sqlFac
                    .getQuery("UpdateManutenzione"));

            int i = 1;
            statement.setDate(i++, DateUtil.toSqlDate(manutenzione.dataInizio));
            statement.setString(i++, manutenzione.tipo);
            statement.setInt(i++,
                    Integer.parseInt(manutenzione.autovetturaId));
            statement.setDate(i++, DateUtil.toSqlDate(manutenzione.dataFine));
            statement.setString(i++, manutenzione.difettiRiscontrati);
            statement.setString(i++, manutenzione.riparazioniEseguite);
            statement.setInt(i++, Integer.parseInt(manutenzione.id));

            result = statement.executeUpdate();

            if (result > 0) {
                response = true;
            }
        } catch (SQLException e) {
            if (result <= 0) {
                response = false;
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
    public final List<ManutenzioneTO> getList() {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<ManutenzioneTO> response =
                new ArrayList<ManutenzioneTO>();

        ResultSet result = null;
        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("GetManutenzioneList"));

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                while (result.next()) {
                    ManutenzioneTO manutenzione = new ManutenzioneTO();

                    Date dataFine = result.getDate("data_fine");

                    if (dataFine != null) {
                        manutenzione.dataFine = dataFine.toString();
                    }

                    manutenzione.dataInizio = result.getDate("data_inizio")
                            .toString();
                    manutenzione.difettiRiscontrati = result
                            .getString("difetti_riscontrati");
                    manutenzione.riparazioniEseguite = result
                            .getString("riparazioni_eseguite");
                    manutenzione.tipo = result.getString("tipo_manutenzione");
                    manutenzione.autovetturaId = Integer.toString(
                            result.getInt("autovettura_id"));
                    manutenzione.id = Integer.toString(result.getInt("id"));

                    response.add(manutenzione);
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
    public Boolean existCheck(ManutenzioneTO dati) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ManutenzioneTO> getManutenzioniAutovettura(String targa) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<ManutenzioneTO> response = null;

        ResultSet result = null;
        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("GetManutenzioniAutovettura"));

            statement.setString(1, targa);

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                response = new ArrayList<ManutenzioneTO>();
                while (result.next()) {
                    ManutenzioneTO manutenzione = new ManutenzioneTO();

                    Date dataFine = result.getDate("data_fine");

                    if (dataFine != null) {
                        manutenzione.dataFine = dataFine.toString();
                    }

                    manutenzione.dataInizio = result.getDate("data_inizio")
                            .toString();
                    manutenzione.difettiRiscontrati = result
                            .getString("difetti_riscontrati");
                    manutenzione.riparazioniEseguite = result
                            .getString("riparazioni_eseguite");
                    manutenzione.tipo = result.getString("tipo_manutenzione");
                    manutenzione.autovetturaId = Integer.toString(
                            result.getInt("autovettura_id"));
                    manutenzione.id = Integer.toString(result.getInt("id"));

                    response.add(manutenzione);
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
