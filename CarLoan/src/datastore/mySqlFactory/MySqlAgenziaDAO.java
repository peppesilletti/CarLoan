package datastore.mySqlFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.ExtraAgenziaTO;
import transferObjects.entitiesTO.ManagerAgenziaTO;
import transferObjects.entitiesTO.OperatoreTO;
import datastore.entitiesDAO.AgenziaDAO;
import datastore.mySqlDB.DbUtil;
import datastore.mySqlDB.MySqlConnectionFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Classe che si interfaccia con il database MySql per la memorizzazione e
 * reperimento dei dati di un'agenzia. Implementa {@link AgenziaDAO}.
 * */
public class MySqlAgenziaDAO extends MySqlDao implements AgenziaDAO {

    /**
     * Costruttore di default.
     * */
    public MySqlAgenziaDAO() {
        super();
    }

    @Override
    public final Boolean create(final AgenziaTO agenzia) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;

        int result = 0;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac.getQuery("CreateAgenzia"));
            int i = 1;
            statement.setString(i++, agenzia.città);
            statement.setString(i++, agenzia.indirizzo);
            statement.setString(i++, agenzia.telefono);

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


    /**
     * Restituisce un'agenzia tramite il suo id.
     * @param id
     *      L'id dell'agenzia.
     * @return
     *      Transfer Object con i dati dell'agenzia.
     * */
    @Override
    public final AgenziaTO research(final String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        AgenziaTO agenzia = null;
        ResultSet result = null;

        try {
            statement = con
                    .prepareStatement(sqlFac.getQuery("GetByIdAgenzia"));

            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeQuery();

            while (result.next()) {
                agenzia = new AgenziaTO();
                agenzia.città = result.getString("città");
                agenzia.indirizzo = result.getString("indirizzo");
                agenzia.telefono = result.getString("telefono");
                agenzia.id = id;
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return agenzia;
    }

    @Override
    public final Boolean delete(final String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;

        int result = 0;
        Boolean response = false;

        try {

            statement = con.prepareStatement(sqlFac.getQuery("DeleteAgenzia"));
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
    public final Boolean update(final AgenziaTO agenzia) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;

        int result = 0;
        Boolean response = false;

        try {

            statement = con.prepareStatement(sqlFac.getQuery("UpdateAgenzia"));

            int i = 1;
            statement.setString(i++, agenzia.città);
            statement.setString(i++, agenzia.indirizzo);
            statement.setString(i++, agenzia.telefono);
            statement.setInt(i++, Integer.parseInt(agenzia.id));

            result = statement.executeUpdate();

            if (result > 0) {
                response = true;
            }

        } catch (final SQLIntegrityConstraintViolationException ex) {
            return false;
        } catch (SQLException | NumberFormatException e) {
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
    public final List<AgenziaTO> getList() {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<AgenziaTO> response = new LinkedList<AgenziaTO>();
        ResultSet result = null;

        try {
            statement = con.prepareStatement(sqlFac.getQuery("GetAgenziaList"));

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                while (result.next()) {
                    AgenziaTO agenzia = new AgenziaTO();
                    agenzia.città = result.getString("città");
                    agenzia.indirizzo = result.getString("indirizzo");
                    agenzia.telefono = result.getString("telefono");
                    agenzia.id = Integer.toString(result.getInt("id"));
                    response.add(agenzia);
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
    public ManagerAgenziaTO getManagerAgenzia(String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ManagerAgenziaTO manager = new ManagerAgenziaTO();
        ResultSet result = null;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("GetManagerAgenzia"));

            statement.setString(1, id);

            result = statement.executeQuery();

            while (result.next()) {
                manager.nome = result.getString("nome");
                manager.cognome = result.getString("cognome");
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return manager;
    }

    @Override
    public List<OperatoreTO> getOperatori(String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<OperatoreTO> response = new LinkedList<OperatoreTO>();
        ResultSet result = null;

        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("GetOperatoriAgenzia"));

            statement.setString(1, id);

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                while (result.next()) {
                    OperatoreTO operatore = new OperatoreTO();
                    operatore.nome = result.getString("nome");
                    operatore.cognome = result.getString("cognome");
                    operatore.username = result.getString("username");
                    response.add(operatore);
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
    public Boolean existCheck(AgenziaTO dati) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ResultSet result = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ExistAgenzia"));

            statement.setString(1, dati.città);
            statement.setString(2, dati.indirizzo);

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
    public List<ExtraAgenziaTO> getExtraAgenzia(String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<ExtraAgenziaTO> response = new LinkedList<ExtraAgenziaTO>();
        ResultSet result = null;

        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("GetExtraAgenzia"));

            statement.setString(1, id);

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                while (result.next()) {
                    ExtraAgenziaTO extra = new ExtraAgenziaTO();
                    extra.nome = result.getString("nome");
                    extra.descrizione = result.getString("descrizione");
                    extra.id = Integer.toString(
                            result.getInt("extra_id"));
                    response.add(extra);
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
    public Boolean deleteExtraAgenzia(ExtraAgenziaTO extra) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;

        int result = 0;
        Boolean response = false;

        try {

            statement = con.prepareStatement(sqlFac.getQuery(
                    "DeleteExtraAgenzia"));

            statement.setInt(1, Integer.parseInt(extra.id));
            statement.setInt(2, Integer.parseInt(extra.agenziaId));

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
    public Boolean insertExtraAgenzia(ExtraAgenziaTO extra) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;

        int result = 0;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac.getQuery("InsertExtraAgenzia"));
            int i = 1;
            statement.setString(i++, extra.id);
            statement.setString(i++, extra.agenziaId);

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
    public List<AgenziaTO> getAgenziaByCittà(String città) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<AgenziaTO> response = null;
        ResultSet result = null;

        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("GetAgenziaByCittà"));

            statement.setString(1, città);

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                response = new LinkedList<AgenziaTO>();
                while (result.next()) {
                    AgenziaTO agenzia = new AgenziaTO();
                    agenzia.città = result.getString("città");
                    agenzia.indirizzo = result.getString("indirizzo");
                    agenzia.telefono = result.getString("telefono");
                    agenzia.id = Integer.toString(result.getInt("id"));
                    response.add(agenzia);
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
    public Boolean checkContrattiAgenzia(String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ResultSet result = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("CheckContrattiAgenzia"));

            statement.setInt(1, Integer.parseInt(id));
            statement.setInt(2, Integer.parseInt(id));

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
    public Boolean checAutovettureAgenzia(String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ResultSet result = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("CheckAutovettureAgenzia"));

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

}
