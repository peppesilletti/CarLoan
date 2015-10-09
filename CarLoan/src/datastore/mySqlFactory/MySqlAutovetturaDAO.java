package datastore.mySqlFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import transferObjects.entitiesTO.AutovetturaTO;
import datastore.entitiesDAO.AutovetturaDAO;
import datastore.mySqlDB.DbUtil;
import datastore.mySqlDB.MySqlConnectionFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Classe che si interfaccia con il database MySql per la memorizzazione e
 * reperimento dei autovettura di una autovettura.s Implementa
 * {@link AutovetturaDAO}.
 * */

public class MySqlAutovetturaDAO extends MySqlDao implements AutovetturaDAO {

    /**
     * Costruttore di default.
     * */
    public MySqlAutovetturaDAO() {
        super();
    }

    @Override
    public final Boolean create(final AutovetturaTO autovettura) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("CreateAutovettura"));

            int i = 1;
            statement.setString(i++, autovettura.targa);
            statement.setString(i++, autovettura.marca);
            statement.setString(i++, autovettura.modello);
            statement.setFloat(i++, autovettura.chilometraggio);
            statement.setString(i++, autovettura.alimentazione);
            statement.setInt(i++,
                    Integer.parseInt(autovettura.agenzia));
            statement.setInt(i++,
                    Integer.parseInt(autovettura.classeAutovetturaId));

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
    public final AutovetturaTO research(final String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        AutovetturaTO autovettura = new AutovetturaTO();
        ResultSet result = null;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ResearchAutovettura"));

            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeQuery();

            while (result.next()) {
                autovettura.targa = result.getString("targa");
                autovettura.marca = result.getString("marca");
                autovettura.modello = result.getString("modello");
                autovettura.chilometraggio = result.getFloat("chilometraggio");
                autovettura.alimentazione = result.getString("alimentazione");
                autovettura.agenzia =
                        Integer.toString(result.getInt("agenzia_id"));
                autovettura.classeAutovetturaId =
                        Integer.toString(result.getInt("classe_autovettura_id"));
                autovettura.id = id;
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return autovettura;
    }

    @Override
    public final Boolean delete(final String id) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;

        Boolean response = false;
        int result = 0;
        try {

            statement = con.prepareStatement(sqlFac
                    .getQuery("DeleteAutovettura"));
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
    public final Boolean update(final AutovetturaTO autovettura) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;

        int result = 0;
        Boolean response = false;
        try {

            statement = con.prepareStatement(sqlFac
                    .getQuery("UpdateAutovettura"));

            int i = 1;
            statement.setString(i++, autovettura.targa);
            statement.setString(i++, autovettura.marca);
            statement.setString(i++, autovettura.modello);
            statement.setFloat(i++, autovettura.chilometraggio);
            statement.setString(i++, autovettura.alimentazione);
            statement.setInt(i++, Integer.parseInt(
                    autovettura.classeAutovetturaId));
            statement.setInt(i++, Integer.parseInt(autovettura.id));

            result = statement.executeUpdate();

            if (result > 0) {
                response = true;
            }

        } catch (SQLException e) {
            return false;
        } finally {
            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }

        return response;

    }

    @Override
    public final List<AutovetturaTO> getList() {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<AutovetturaTO> response = new ArrayList<AutovetturaTO>();

        ResultSet result = null;
        try {

            statement = con.prepareStatement(sqlFac
                    .getQuery("GetAutovetturaList"));

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                while (result.next()) {
                    AutovetturaTO autovettura = new AutovetturaTO();
                    autovettura.targa = result.getString("targa");
                    autovettura.marca = result.getString("marca");
                    autovettura.modello = result.getString("modello");
                    autovettura.alimentazione =
                            result.getString("alimentazione");
                    autovettura.chilometraggio = result
                            .getFloat("chilometraggio");

                    autovettura.classeAutovetturaId =
                            Integer.toString(
                                    result.getInt("classe_autovettura_id"));
                    autovettura.id = Integer.toString(result.getInt("id"));
                    response.add(autovettura);
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
    public Boolean existCheck(AutovetturaTO dati) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ResultSet result = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ExistAutovettura"));

            statement.setString(1, dati.targa);

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

    @Override
    public List<AutovetturaTO> getAutovetturaParams(String targa, String marca,
            String classe) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<AutovetturaTO> response = null;

        ResultSet result = null;
        try {

            int i = 1;

            if (marca.equals("") && targa.equals("") && !classe.equals("")) {

                statement = con.prepareStatement(sqlFac
                        .getQuery("GetAutovetturaByClasse"));

                statement.setString(i++, classe);
                statement.setString(i++, targa);

            } else if (!marca.equals("") && targa.equals("")
                    && classe.equals("")) {

                statement = con.prepareStatement(sqlFac
                        .getQuery("GetAutovetturaByMarca"));

                statement.setString(i++, marca);
                statement.setString(i++, targa);
            } else {

                statement = con.prepareStatement(sqlFac
                        .getQuery("GetAutovetturaByClasseMarca"));

                statement.setString(i++, marca);
                statement.setString(i++, classe);
                statement.setString(i++, targa);
            }



            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                response = new ArrayList<AutovetturaTO>();
                while (result.next()) {
                    AutovetturaTO autovettura = new AutovetturaTO();
                    autovettura.targa = result.getString("targa");
                    autovettura.marca = result.getString("marca");
                    autovettura.modello = result.getString("modello");
                    autovettura.alimentazione =
                            result.getString("alimentazione");
                    autovettura.chilometraggio = result
                            .getFloat("chilometraggio");

                    autovettura.classeAutovetturaId =
                            Integer.toString(
                                    result.getInt("classe_autovettura_id"));
                    autovettura.id = Integer.toString(result.getInt("autoId"));
                    response.add(autovettura);
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

    @Override
    public List<AutovetturaTO> getAutovettureDisponibili(String classe,
            String agenziaId) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<AutovetturaTO> response = new ArrayList<AutovetturaTO>();

        ResultSet result = null;
        try {

            statement = con.prepareStatement(sqlFac
                    .getQuery("GetAutovettureDisponibili"));

            int i = 1;
            statement.setString(i++, classe);
            statement.setInt(i++, Integer.parseInt(agenziaId));

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                while (result.next()) {
                    AutovetturaTO autovettura = new AutovetturaTO();
                    autovettura.targa = result.getString("targa");
                    autovettura.marca = result.getString("marca");
                    autovettura.modello = result.getString("modello");
                    autovettura.alimentazione =
                            result.getString("alimentazione");
                    autovettura.chilometraggio = result
                            .getFloat("chilometraggio");

                    autovettura.classeAutovetturaId =
                            Integer.toString(
                                    result.getInt("classe_autovettura_id"));
                    autovettura.id = Integer.toString(result.getInt("autoId"));
                    response.add(autovettura);
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

    @Override
    public Boolean checkAutoDisponibile(String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ResultSet result = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("CheckAvaiabilityAuto"));

            statement.setInt(1, Integer.parseInt(id));

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
