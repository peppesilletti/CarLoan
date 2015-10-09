package business.applicationServices;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.HashMap;

import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.entitiesTO.ClienteTO;
import transferObjects.entitiesTO.ContrattoTO;
import transferObjects.entitiesTO.TariffaTO;
import utility.DateUtil;
import utility.GeneratePdf;
import utility.GestioneStampa;
import business.businessObjects.Agenzia;
import business.businessObjects.Autovettura;
import business.businessObjects.Cliente;
import business.businessObjects.ClienteInt;
import business.businessObjects.Contratto;
import business.businessObjects.ContrattoInt;
import business.businessObjects.Tariffa;
import config.ConfigReaderPenali;
import errorsHandling.ErrorHandler;
import errorsHandling.ErrorHandlerInt;
import errorsHandling.exceptions.ViewDispatcherException;

/**
 * Application service per la gestione dei contratti.
 * */
public final class GestioneContratti {

    private ContrattoInt contrattoBO;
    private ClienteInt clienteBO;
    private ConfigReaderPenali readerPenali;

    /**
     * Costruttore principale.
     * */
    public GestioneContratti() {
        contrattoBO = new Contratto();
        clienteBO = new Cliente();
        readerPenali = ConfigReaderPenali.getInstance();
    }

    /**
     * Crea un nuovo contratto.
     * @param contratto
     *      TO con i dati del contratto da inserire.
     * @return
     *      Risultato dell'operazione.
     * */
    public Boolean aperturaContratto(
            final ContrattoTO contratto) {

        String dataInizioNoleggio = contratto.dataInizioNoleggio;
        String dataFineNoleggio = contratto.dataFineNoleggio;
        String dataApertura = contratto.dataApertura;
        String tariffa = contratto.modalità;
        String autovetturaId = contratto.autovetturaId;
        String clienteId = contratto.clienteId;

        Boolean check = true;

        /*controllo correttezza date*/

        if (!controlloDate(dataInizioNoleggio, dataFineNoleggio,
                dataApertura, tariffa)) {
            check = false;
        }

        /*controllo autovettura*/
        if (!controlloAutovettura(autovetturaId, dataInizioNoleggio,
                dataFineNoleggio)) {
            check = false;
        }

        /* Controllo che il cliente non abbia già affittato un'autovettura*/
        if (!controlloCliente(clienteId)) {
            check = false;
        }

        /* Controllo non esistenza cliente nel caso venga
           inserito uno nuovo */

        if (contratto.clienteId == null) {

            if (clienteBO.existCheck(contratto.infoCliente)) {
                check = false;
            }
        }

        /* Se tutti i controlli risultano positivi, invia.*/
        if (check) { //effettuare controllo correttezza codice fiscale.
            return contrattoBO.addContratto(contratto);
        } else {
            return false;
        }
    }

    /**
     * Restituisce i dati di un contratto.
     * @param id
     *      Id del contratto.
     * @return
     *      Ritorna un TO con i dati del contratto.
     * */
    public ContrattoTO ricercareDatiContratto(final String id) {

        Autovettura autovettura = new Autovettura();
        Tariffa tariffa = new Tariffa();
        Cliente cliente = new Cliente();
        Agenzia agenzia = new Agenzia();

        ContrattoTO contratto = contrattoBO.getDatiContratto(id);

        AutovetturaTO autoTO = autovettura.getAutovettura(
                contratto.autovetturaId);

        TariffaTO tariffaTO = tariffa.getTariffa(contratto.tariffaId);

        ClienteTO clienteTO = cliente.getCliente(contratto.clienteId);

        AgenziaTO agenziaTO = agenzia.getAgenziaById(
                contratto.agenziaRientroId);

        contratto.infoAutovettura = autoTO;
        contratto.infoCliente = clienteTO;
        contratto.infoTariffa = tariffaTO;
        contratto.infoAgenziaRientro = agenziaTO;

        return contratto;

    }

    /**
     * Chiude un contratto presente nel sistema.
     * @param id
     *      Id del contratto da chiudere.
     * @return
     *      Risultato dell'operazione.
     * */
    public Boolean chiudereContratto(final ContrattoTO contratto) {

        TariffaTO tariffa = contratto.infoTariffa;
        Float importoFinale = (float) 0.0;
        String dataFineNoleggio = contratto.dataFineNoleggio;
        String dataInizioNoleggio = contratto.dataInizioNoleggio;
        String dataCorrente = DateUtil.toPrintDate(LocalDate.now().toString());
        Integer numeroGiorniNoleggio = DateUtil.getGiorniTraDate(
                dataInizioNoleggio, dataFineNoleggio);

        /* Se il cliente chiude il contratto prima di iniziare il noleggio*/

        if (DateUtil.compareDates(dataInizioNoleggio,
                dataCorrente) == 1) {

            importoFinale = contratto.cauzione;

            contratto.importoFinale = importoFinale;

            return contrattoBO.chiudereContratto(contratto);
        }

        /* Se il cliente chiude il contratto durante il noleggio*/

        if (DateUtil.compareDates(dataFineNoleggio,
                dataCorrente) == 1
                && DateUtil.compareDates(dataInizioNoleggio,
                        dataCorrente) < 0) {

            Float penaleConsegnaAnticipata = Float.parseFloat(
                    readerPenali.getProperty("penale_consegna_anticipata"));

            Float penale = (contratto.saldoParziale / 100) * penaleConsegnaAnticipata;

            importoFinale += penale + contratto.saldoParziale + contratto.cauzione;

            contratto.importoFinale = importoFinale;

            return contrattoBO.chiudereContratto(contratto);

        }

        /*Se il cliente consegna l'autovettura in ritardo*/

        if (DateUtil.compareDates(dataCorrente, dataFineNoleggio) == 1) {

            Float penaleConsegnaRitardata = Float.parseFloat(
                    readerPenali.getProperty("penale_data_ritorno_superata"));

            importoFinale += contratto.saldoParziale + penaleConsegnaRitardata;

            contratto.importoFinale = importoFinale;

            return contrattoBO.chiudereContratto(contratto);
        }

        /*Conteggio costo chilometri effettuati*/
        if (tariffa.chilometraggio.equals("Limitato")) {

            Float chilometriPercorsi = contratto.chilometriPercorsi;
            Float chilometriEffettuabili = tariffa.chilometriGiorno * numeroGiorniNoleggio;

            Float differenzaKm = chilometriPercorsi - chilometriEffettuabili;

            if (differenzaKm > 0) {
                Float penale = Float.parseFloat(readerPenali.getProperty(
                        "penale_chilometri_superati"));

                importoFinale += penale * differenzaKm;

                contratto.importoFinale = importoFinale - contratto.cauzione;
            } else {
                contratto.importoFinale = contratto.saldoParziale - contratto.cauzione;
            }
        }

        return contrattoBO.chiudereContratto(contratto);
    }

    /**
     * Restituisce il successivo numero di contratto da inserire.
     * @return
     *      TO con il risultato.
     * */
    public ContrattoTO nextNumeroContratto() {
        return contrattoBO.nextNumeroContratto();
    }

    private Boolean controlloDate(String dataInizioNoleggio, String dataFineNoleggio,
            String dataApertura, String tariffa) {

        //controllo correttezza date
        if (DateUtil.compareDates(dataInizioNoleggio,
                dataFineNoleggio) != -1) {
            return false;
        }

        if (DateUtil.compareDates(dataApertura, dataInizioNoleggio) == 1) {
            return false;
        }

        if (tariffa.equals("Settimanale")) {
            if (DateUtil.getGiorniTraDate(dataInizioNoleggio, dataFineNoleggio) % 7 != 0) {
                return false;
            }
        }

        return true;

    }

    private Boolean controlloAutovettura(String autovetturaId,
            String dataInizioNoleggio, String dataFineNoleggio) {

        for (ContrattoTO c : contrattoBO.getAll()) {

            String dataInizio = DateUtil.toPrintDate(c.dataInizioNoleggio);
            String dataFine = DateUtil.toPrintDate(c.dataFineNoleggio);
            String autoId = c.autovetturaId;


            if (autoId.equals(autovetturaId)
                    && DateUtil.compareDates(dataInizio, dataInizioNoleggio) <= 0
                    && DateUtil.compareDates(dataFine, dataFineNoleggio) >=0) {
                return false;
            }

        }

        return true;
    }

    private Boolean controlloCliente(String clienteId) {

        for (ContrattoTO c : contrattoBO.getAll()) {
            String id = c.clienteId;

            if (id.equals(clienteId)) {
                return false;
            }
        }

        return true;
    }

    public void stampaContratto(HashMap<String,String> values) {
        GeneratePdf gen = new GeneratePdf();
        try {
            String path = gen.genTable(values);
            GestioneStampa.stampa(path);
        } catch (MalformedURLException e) {
            ViewDispatcherException ex = new ViewDispatcherException();
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.WARNING);
        } catch (IOException e) {
            ViewDispatcherException ex = new ViewDispatcherException();
            ErrorHandlerInt er = ErrorHandler.getIstance();
            er.processError(ex.getClass(), ex, ErrorHandlerInt.WARNING);
        }
    }

}
