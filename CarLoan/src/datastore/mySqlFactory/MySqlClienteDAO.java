package datastore.mySqlFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import transferObjects.entitiesTO.ClienteTO;
import utility.DateUtil;
import datastore.entitiesDAO.ClienteDAO;
import datastore.mySqlDB.DbUtil;
import datastore.mySqlDB.MySqlConnectionFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 * Classe che si interfaccia con il database MySql per la memorizzazione e
 * reperimento dei dati di un cliente. Implementa {@link ClienteDAO}.
 * */
public class MySqlClienteDAO extends MySqlDao implements ClienteDAO {

    /**
     * Costruttore di default.
     * */
    public MySqlClienteDAO() {
        super();
    }

    @Override
    public final Boolean create(final ClienteTO cliente) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {

            //String dataNascita = DateUtil.format(cliente.dataDiNascita);

            statement = con.prepareStatement(sqlFac
                    .getQuery("CreateCliente"));

            int i = 1;
            statement.setString(i++, cliente.nome);
            statement.setString(i++, cliente.cognome);
            statement.setDate(i++, DateUtil.toSqlDate(cliente.dataDiNascita));
            statement.setString(i++, cliente.comuneNascita);
            statement.setString(i++, cliente.comuneResidenza);
            statement.setString(i++, cliente.indirizzo);
            statement.setString(i++, cliente.codiceFiscale);
            statement.setString(i++, cliente.idPatente);

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
    public final ClienteTO research(final String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ClienteTO cliente = new ClienteTO();
        ResultSet result = null;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ResearchCliente"));

            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeQuery();

            while (result.next()) {
                cliente.nome = result.getString("nome");
                cliente.cognome = result.getString("cognome");
                cliente.comuneNascita = result.getString("comune_nascita");
                cliente.comuneResidenza = result.getString("comune_residenza");
                cliente.dataDiNascita = result.getString(
                        "data_di_nascita").toString();
                cliente.idPatente = result.getString("id_patente");
                cliente.indirizzo = result.getString("indirizzo");
                cliente.codiceFiscale = result.getString("codice_fiscale");
                cliente.id = id;
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }
        return cliente;
    }

    @Override
    public final Boolean update(final ClienteTO cliente) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("UpdateCliente"));

            int i = 1;
            statement.setString(i++, cliente.nome);
            statement.setString(i++, cliente.cognome);
            statement.setDate(i++,
                    DateUtil.toSqlDate(cliente.dataDiNascita));
            statement.setString(i++, cliente.comuneNascita);
            statement.setString(i++, cliente.comuneResidenza);
            statement.setString(i++, cliente.indirizzo);
            statement.setString(i++, cliente.codiceFiscale);
            statement.setString(i++, cliente.idPatente);
            statement.setInt(i++,
                    Integer.parseInt(cliente.id));


            result = statement.executeUpdate();

            if (result > 0) {
                response = true;
            }
        } catch (SQLException e) {
            if (result <= 0) {
                response = false;
            } else {
                handleExceptions(e, ErrorHandlerInt.FATAL);
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
    public final List<ClienteTO> getList() {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<ClienteTO> response =
                new ArrayList<ClienteTO>();

        ResultSet result = null;
        try {

            statement = con.prepareStatement(
                    sqlFac.getQuery("GetClienteList"));

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                while (result.next()) {
                    ClienteTO cliente = new ClienteTO();
                    cliente.nome = result.getString("nome");
                    cliente.cognome = result.getString("cognome");
                    cliente.comuneNascita = result.getString("comune_nascita");
                    cliente.comuneResidenza = result.getString(
                            "comune_residenza");
                    cliente.dataDiNascita = result.getString(
                            "data_di_nascita").toString();
                    cliente.idPatente = result.getString("id_patente");
                    cliente.indirizzo = result.getString("indirizzo");
                    cliente.id = Integer.toString(result.getInt("id"));
                    cliente.codiceFiscale = result.getString("codice_fiscale");
                    response.add(cliente);
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
    public final Boolean delete(final String id) {
        handleExceptions(new IllegalAccessException(
                "Metodo non utilizzabile."), ErrorHandlerInt.WARNING);
        return null;
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
    public final Boolean existCheck(final ClienteTO dati) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ResultSet result = null;
        Boolean response = false;

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("ExistCliente"));

            statement.setString(1, dati.codiceFiscale);

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
    public List<ClienteTO> getClientiByCodFiscale(String codFiscale) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<ClienteTO> response = null;


        ResultSet result = null;
        try {

            statement = con.prepareStatement(
                    sqlFac.getQuery("GetClientiByCodFiscale"));

            statement.setString(1, "%"+codFiscale+"%");

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                response = new ArrayList<ClienteTO>();
                while (result.next()) {
                    ClienteTO cliente = new ClienteTO();
                    cliente.nome = result.getString("nome");
                    cliente.cognome = result.getString("cognome");
                    cliente.comuneNascita = result.getString("comune_nascita");
                    cliente.comuneResidenza = result.getString(
                            "comune_residenza");
                    cliente.dataDiNascita = result.getString(
                            "data_di_nascita").toString();
                    cliente.idPatente = result.getString("id_patente");
                    cliente.indirizzo = result.getString("indirizzo");
                    cliente.id = Integer.toString(result.getInt("id"));
                    cliente.codiceFiscale = result.getString("codice_fiscale");
                    response.add(cliente);
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
