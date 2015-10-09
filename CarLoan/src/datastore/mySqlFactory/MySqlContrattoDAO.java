package datastore.mySqlFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import transferObjects.entitiesTO.ClienteTO;
import transferObjects.entitiesTO.ContrattoTO;
import transferObjects.entitiesTO.ExtraTO;
import utility.DateUtil;
import datastore.entitiesDAO.ContrattoDAO;
import datastore.mySqlDB.DbUtil;
import datastore.mySqlDB.MySqlConnectionFactory;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.DAOException;

/**
 *Classe che si interfaccia con il database MySql per la memorizzazione e
 * reperimento dei dati di un contratto. Implementa {@link ContrattoDAO}.
 * */
public class MySqlContrattoDAO extends MySqlDao implements ContrattoDAO {

    /**
     * Costruttore di default.
     * */
    public MySqlContrattoDAO() {
        super();
    }

    @Override
    public final Boolean create(final ContrattoTO contratto) {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statementContratto = null;
        PreparedStatement statementExtra = null;
        PreparedStatement statementCliente = null;

        Boolean response = false;

        try {
            con.setAutoCommit(false);
            int i = 1;
            int clienteKey = 0;

            //query per inserire le info del cliente
            statementCliente = con.prepareStatement(sqlFac
                    .getQuery("CreateCliente"),
                    Statement.RETURN_GENERATED_KEYS);

            if (contratto.clienteId == null) {
                ClienteTO cliente = contratto.infoCliente;

                statementCliente.setString(i++, cliente.nome);
                statementCliente.setString(i++, cliente.cognome);
                statementCliente.setDate(i++, DateUtil.toSqlDate(
                        cliente.dataDiNascita));
                statementCliente.setString(i++, cliente.comuneNascita);
                statementCliente.setString(i++, cliente.comuneResidenza);
                statementCliente.setString(i++, cliente.indirizzo);
                statementCliente.setString(i++, cliente.codiceFiscale);
                statementCliente.setString(i++, cliente.idPatente);

                statementCliente.executeUpdate();

                ResultSet rs = statementCliente.getGeneratedKeys();
                if (rs.next()) {
                    clienteKey = rs.getInt(1);
                }

            } else {
                clienteKey = Integer.parseInt(contratto.clienteId);
            }

            //query per inserire le info del contratto
            statementContratto = con.prepareStatement(sqlFac
                    .getQuery("CreateContratto"),
                    Statement.RETURN_GENERATED_KEYS);

            int j = 1;
            statementContratto.setDate(j++, DateUtil.toSqlDate(
                    contratto.dataApertura));

            statementContratto.setString(j++, contratto.modalità);

            statementContratto.setFloat(j++, contratto.cauzione);

            statementContratto.setInt(j++, Integer.parseInt(
                    contratto.tariffaId));

            statementContratto.setInt(j++, clienteKey);

            statementContratto.setInt(j++, Integer.parseInt(
                    contratto.autovetturaId));

            statementContratto.setInt(j++, Integer.parseInt(
                    contratto.agenziaRientroId));

            statementContratto.setInt(j++, Integer.parseInt(
                    contratto.agenziaAperturaId));

            statementContratto.setDate(j++, DateUtil.toSqlDate(
                    contratto.dataInizioNoleggio));

            statementContratto.setDate(j++, DateUtil.toSqlDate(
                    contratto.dataFineNoleggio));

            statementContratto.setInt(j++,
                    Integer.parseInt(contratto.id));

            statementContratto.setString(j++, "Aperto");

            statementContratto.setFloat(j++, contratto.saldoParziale);


            statementContratto.executeUpdate();

            //query per l'inserimento degli extra contratto

            List<ExtraTO> extras = contratto.listaExtra;

            statementExtra = con.prepareStatement(sqlFac
                    .getQuery("InsertExtraContratto"));

            for (ExtraTO e : extras) {

                statementExtra.setInt(1, Integer.parseInt(
                        e.id));

                statementExtra.setInt(2, Integer.parseInt(
                        contratto.id));

                statementExtra.executeUpdate();

            }

            con.commit();
            response = true;

        } catch (SQLException e) {
            response = false;
            handleExceptions(e, ErrorHandlerInt.WARNING);
            try {
                con.rollback();
            } catch (SQLException e1) {
                handleExceptions(e1, ErrorHandlerInt.WARNING);
            }
        } finally {
            if (statementContratto != null) {
                DbUtil.close(statementContratto);
            }
            if (statementExtra != null) {
                DbUtil.close(statementExtra);
            }
            if (statementCliente != null) {
                DbUtil.close(statementCliente);
            }
            DbUtil.close(con);
        }
        return response;
    }

    @Override
    public final ContrattoTO research(final String id) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statementContratto = null;

        ContrattoTO contratto = new ContrattoTO();
        ResultSet result = null;

        try {
            statementContratto = con
                    .prepareStatement(sqlFac.getQuery("GetContratto"));

            statementContratto.setInt(1, Integer.parseInt(id));

            result = statementContratto.executeQuery();

            while (result.next()) {
                contratto.id = id;

                if (result.getDate("data_chiusura") != null) {
                    contratto.dataChiusura = result.getDate("data_chiusura")
                            .toString();
                }

                contratto.dataApertura = result.getDate("data_apertura")
                        .toString();
                contratto.modalità = result.getString("modalità");
                contratto.cauzione = result.getFloat("cauzione");
                contratto.importoFinale = result.getFloat("importo_finale");
                contratto.tariffaId = Integer.toString(
                        result.getInt("tariffa_id"));
                contratto.clienteId = Integer.toString(
                        result.getInt("cliente_id"));
                contratto.autovetturaId = Integer.toString(
                        result.getInt("autovettura_id"));
                contratto.agenziaRientroId = Integer.toString(
                        result.getInt("agenzia_rientro_id"));
                contratto.dataInizioNoleggio = result.getDate("data_inizio_noleggio")
                        .toString();
                contratto.dataFineNoleggio = result.getDate("data_fine_noleggio")
                        .toString();
                contratto.stato = result.getString("stato");
                contratto.chilometriPercorsi = result.getFloat("chilometri_percorsi");

            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statementContratto != null) {
                DbUtil.close(statementContratto);
            }
            DbUtil.close(con);
        }
        return contratto;
    }

    @Override
    public final Boolean update(final ContrattoTO contratto) {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        Boolean response = false;

        int result = 0;
        try {
            statement = con.prepareStatement(
                    sqlFac.getQuery("UpdateContratto"));

            int i = 1;

            statement.setDate(i++, DateUtil.toSqlDate(
                    contratto.dataChiusura));

            statement.setFloat(i++, contratto.importoFinale);

            statement.setString(i++, "Chiuso");

            statement.setInt(i++, Integer.parseInt(
                    contratto.id));


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
    public final List<ContrattoTO> getList() {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<ContrattoTO> response =
                new ArrayList<ContrattoTO>();

        ResultSet result = null;
        try {

            statement = con.prepareStatement(
                    sqlFac.getQuery("GetContrattoList"));

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                while (result.next()) {
                    ContrattoTO contratto = new ContrattoTO();
                    contratto.tariffaId = result.getString("tariffa_id");
                    contratto.autovetturaId = result.getString("autovettura_id");
                    contratto.dataInizioNoleggio = result.getString("data_inizio_noleggio");
                    contratto.dataFineNoleggio = result.getString("data_fine_noleggio");
                    contratto.clienteId = result.getString("cliente_id");
                    response.add(contratto);
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
    public final Boolean existCheck(final ContrattoTO dati) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final ContrattoTO getNextNumeroContratto() {

        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        ResultSet result = null;
        ContrattoTO contratto = new ContrattoTO();
        contratto.id = "0";

        try {
            statement = con.prepareStatement(sqlFac
                    .getQuery("GetLastContractNumber"));

            result = statement.executeQuery();

            if (result.next()) {
                contratto.id = Integer.toString(
                        result.getInt("maxId") + 1);
            }

        } catch (SQLException e) {
            handleExceptions(e, ErrorHandlerInt.FATAL);
        } finally {

            if (statement != null) {
                DbUtil.close(statement);
            }
            DbUtil.close(con);
        }

        return contratto;
    }

    @Override
    public List<ExtraTO> getExtraContratti() {
        Connection con = MySqlConnectionFactory.getConnection();

        PreparedStatement statement = null;
        List<ExtraTO> response = null;

        ResultSet result = null;
        try {

            statement = con.prepareStatement(
                    sqlFac.getQuery("GetExtraContratti"));

            result = statement.executeQuery();

            if (!result.isBeforeFirst()) {
                return response;
            } else {
                response = new ArrayList<ExtraTO>();
                while (result.next()) {
                    ExtraTO extra = new ExtraTO();
                    extra.id = Integer.toString(result.getInt("extra_id"));
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

}
