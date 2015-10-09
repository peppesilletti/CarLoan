package view.controller.operatore;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import presentation.SessionHandler;
import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.entitiesTO.ClienteTO;
import transferObjects.entitiesTO.ContrattoTO;
import transferObjects.entitiesTO.ExtraAgenziaTO;
import transferObjects.entitiesTO.ExtraTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.ComplexResponse;
import transferObjects.response.SimpleResponse;
import utility.DateUtil;
import view.controller.GetCommonData;
import view.controller.ShowAlert;
import view.model.AgenziaModel;
import view.model.AutovetturaModel;
import view.model.ClasseAutovetturaModel;
import view.model.ClienteModel;
import view.model.ExtraAgenziaModel;
import view.model.TariffaModel;

/**
 * Controller per l'interfaccia ApriContratto.fxml .
 * */
public class ApriContrattoController implements Initializable {

    @FXML
    private Tab datiAutovetturaTab;

    @FXML
    private Tab datiClientiTab;

    @FXML
    private Tab datiExtraTab;

    @FXML
    private Tab datiNoleggioTab;

    @FXML
    private Tab riepilogoContrattoTab;

    @FXML
    private TableColumn<AutovetturaModel, String> numPorteAutoColumn;

    @FXML
    private Button selezionaAutoButton;

    @FXML
    private TextField codiceContrattoText;

    @FXML
    private Button chiudereContrattoButton;

    @FXML
    private Label autoContrLabel;

    @FXML
    private TableColumn<AutovetturaModel, String> numPostiAutoColumn;

    @FXML
    private Button ricercaTariffeButton;

    @FXML
    private ComboBox<ExtraAgenziaModel> extraComboBox;

    @FXML
    private TableView<ExtraAgenziaModel> extraTableView;

    @FXML
    private TextField idPatenteCliente;

    @FXML
    private Label tariffaContrLabel;

    @FXML
    private Label autoSelezionataLabel;

    @FXML
    private Label importoTotContrLabel;

    @FXML
    private TableColumn<ExtraAgenziaModel, String> nomeExtraColumn;

    @FXML
    private TableColumn<ExtraAgenziaModel, String> descrizioneExtraColumn;

    @FXML
    private Button rimuoviExtraButton;

    @FXML
    private TextField comuneResidenzaClienteText;

    @FXML
    private Button selezionaTariffaButton;

    @FXML
    private TableColumn<TariffaModel, String> modalitàTariffaColumn;

    @FXML
    private TableColumn<TariffaModel, String> kmGiornoColumn;

    @FXML
    private TextField codiceFiscaleText;

    @FXML
    private TextField dataInizioNoleggioText;

    @FXML
    private TextField classeAutovetturaComboBox;

    @FXML
    private TableColumn<TariffaModel, String> importoTariffaColumn;

    @FXML
    private Button stampareContrattoButton;

    @FXML
    private Label codFiscaleContrLabel;

    @FXML
    private TextField codiceFiscaleCliente;

    @FXML
    private TableColumn<AutovetturaModel, String> alimentazioneAutoColumn;

    @FXML
    private TextField indirizzoClienteText;

    @FXML
    private Button ricercaContrattoButton;

    @FXML
    private TextField nomeClienteText;

    @FXML
    private TableView<AutovetturaModel> autovetturaTableView;

    @FXML
    private TextField comuneNascitaCliente;

    @FXML
    private TableColumn<AutovetturaModel, String> marcaAutoColumn;

    @FXML
    private TextField dataNascitaCliente;

    @FXML
    private TableColumn<AutovetturaModel, String> targaAutoColumn;

    @FXML
    private TableView<TariffaModel> tariffaTableView;

    @FXML
    private Label numContrattoLabel;

    @FXML
    private TableColumn<AutovetturaModel, String> modelloAutoColumn;

    @FXML
    private Label dataAperturaLabel;

    @FXML
    private TableColumn<TariffaModel, String> codiceTariffaColumn;

    @FXML
    private TableColumn<TariffaModel, String> kmTariffaColumn;

    @FXML
    private Label statoContrLabel;

    @FXML
    private Label tariffaLabel;

    @FXML
    private Button selezionaExtraButton;

    @FXML
    private TextField cognomeClienteText;

    @FXML
    private Button ricercaClienteButton;

    @FXML
    private TextField dataFineNoleggioText;

    @FXML
    private Label saldoParzialeContrLabel;

    @FXML
    private ComboBox<AgenziaModel> agenziaRientroComboBox;

    @FXML
    private ComboBox<ClasseAutovetturaModel> classeTariffaRicerca;

    @FXML
    private Button apriContrattoButton;

    @FXML
    private Label modalitàNoleggioLabel;

    @FXML
    private Label cauzioneContrLabel;

    @FXML
    private Label dataRiconsegnaAutoLabel;

    @FXML
    private TextField kmPercorsoText;

    @FXML
    private Label dataInizioContrLabel;

    @FXML
    private Label dataFineContrLabel;

    @FXML
    private Label dataAperturaContrLabel;

    @FXML
    private Label dataChiusuraContrLabel;

    @FXML
    private Label agenziaRientroContrLabel;

    @FXML
    private Button annullaContrRicercaButton;


    //Informazioni per il contratto

    private ClienteModel cliente;
    private TariffaModel tariffa;
    private ContrattoTO contratto;
    private AutovetturaModel auto;

    private FrontControllerInt fc;
    private ObservableList<AutovetturaModel> autovetture;
    private ObservableList<ExtraAgenziaModel> extras;


    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
       fc = new FrontController();

       autovetture = FXCollections.observableArrayList();
       extras = FXCollections.observableArrayList();
       contratto = new ContrattoTO();

       //per tabella extra
       codiceTariffaColumn
       .setCellValueFactory(
               new PropertyValueFactory<TariffaModel, String>(
               "id"));

       kmTariffaColumn
       .setCellValueFactory(
               new PropertyValueFactory<TariffaModel,
               String>(
               "chilometraggio"));

       modalitàTariffaColumn
       .setCellValueFactory(
               new PropertyValueFactory<TariffaModel, String>(
               "modalità"));

       importoTariffaColumn
       .setCellValueFactory(
               new PropertyValueFactory<TariffaModel, String>(
               "importoUnitario"));

       kmGiornoColumn
       .setCellValueFactory(
               new PropertyValueFactory<TariffaModel, String>(
               "chilometriGiorno"));

       //per tabella autovetture

       marcaAutoColumn
       .setCellValueFactory(
               new PropertyValueFactory<AutovetturaModel, String>(
               "marca"));

       modelloAutoColumn
       .setCellValueFactory(
               new PropertyValueFactory<AutovetturaModel, String>(
               "modello"));

       alimentazioneAutoColumn
       .setCellValueFactory(
               new PropertyValueFactory<AutovetturaModel, String>(
               "alimentazione"));

       targaAutoColumn
       .setCellValueFactory(
               new PropertyValueFactory<AutovetturaModel, String>(
               "targa"));

       //per tabella extra

       nomeExtraColumn
       .setCellValueFactory(
               new PropertyValueFactory<ExtraAgenziaModel, String>(
               "nome"));

       descrizioneExtraColumn
       .setCellValueFactory(
               new PropertyValueFactory<ExtraAgenziaModel, String>(
               "descrizione"));

       //inizializzazioni
       showNumeroContratto();
       showDataCorrente();
       getClassiAutovettura();
       getExtra();
       getAgenzie();
    }

    //BOTTONI

    @FXML
    public final void ricercaClienteButton() {

        this.ricercaClienteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                ricercaCliente();
            }
        });

        this.codiceFiscaleText.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    ricercaCliente();
                }
            }
        });

    }

    @FXML
    public final void ricercaTariffeButton() {

        this.ricercaTariffeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                ricercaTariffe();
            }
        });
    }

    @FXML
    public final void selezionaTariffaButton() {

        this.selezionaTariffaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                setTariffa();
            }
        });
    }

    @FXML
    public final void selezionaAutoButton() {

        this.selezionaAutoButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                setAutovettura();
            }
        });
    }

    @FXML
    public final void selezionaExtraButton() {

        this.selezionaExtraButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                addExtra();
            }
        });
    }

    @FXML
    public final void rimuoviExtraButton() {

        this.rimuoviExtraButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                removeExtra();
            }
        });
    }

    @FXML
    public final void apriContrattoButton() {

        this.apriContrattoButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (ShowAlert.showMessage("Aprire il contratto?", AlertType.CONFIRMATION)) {
                    sendContratto();
                }
            }
        });
    }

    @FXML
    public final void ricercaContrattoButton() {

        this.ricercaContrattoButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                ricercaContratto();
            }
        });

        this.codiceContrattoText.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    ricercaContratto();
                }
            }
        });
    }



    @FXML
    public final void chiudereContrattoButton() {

        this.chiudereContrattoButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (ShowAlert.showMessage("Confermare la chiusura del contratto?", AlertType.CONFIRMATION)) {
                    chiudiContratto();
                }
            }
        });
    }

    @FXML
    public final void getRiepilogoContratto() {
        codFiscaleContrLabel.setText(codiceFiscaleCliente.getText());
        autoContrLabel.setText(autoSelezionataLabel.getText());

        if (tariffa != null) {
            tariffaContrLabel.setText(tariffa.toString());
        }

        dataInizioContrLabel.setText(dataInizioNoleggioText.getText());
        dataFineContrLabel.setText(dataFineNoleggioText.getText());
        dataAperturaContrLabel.setText(dataAperturaLabel.getText());

        Float[] r = calcolaSaldoParziale();

        cauzioneContrLabel.setText(Float.toString(r[1]));

        saldoParzialeContrLabel.setText(Float.toString(r[0]));

        if (agenziaRientroComboBox.getValue() != null) {
            agenziaRientroContrLabel.setText(
                agenziaRientroComboBox.getValue().toString());
        }
    }

    @FXML
    public final void annullaContrRicercaButton() {

        this.annullaContrRicercaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                annullaRicerca();
            }
        });
    }

    @FXML
    public final void stampareContratto() {

        this.stampareContrattoButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                stampaContratto();
            }
        });
    }

    //FUNZIONI

    /**
     * Mostra il numero del contratto successivo.
     * */
    private void showNumeroContratto() {
        SimpleRequest request =
                new SimpleRequest("nextNumeroContratto", RequestType.SERVICE);

        ComplexResponse<ContrattoTO> response =
                (ComplexResponse<ContrattoTO>) fc.processRequest(request);

        if (response.getResult()) {
            numContrattoLabel.setText(
                    response.getParameters().get(0).id);
        }

    }

    /**
     * Mostra la data corrente di stipulazione del contratto.
     * */
    private void showDataCorrente() {
        LocalDate dataCorrente =
                LocalDate.now();

        dataAperturaLabel.setText(
                DateUtil.toPrintDate(dataCorrente.toString()));
    }

    /**
     * Gestisce la ricerca di un cliente.
     * */
    private void ricercaCliente() {
        String codiceFiscale = codiceFiscaleText.getText().trim();
        ClienteModel clienteData = null;

        if (!codiceFiscale.isEmpty()) {
            try {
                clienteData = GetCommonData.getClienteByCodFiscale(
                        codiceFiscale)
                    .get(0);

                nomeClienteText.setText(clienteData.getNome());

                cognomeClienteText.setText(clienteData.getCognome());

                dataNascitaCliente.setText(
                        clienteData.getDataNascitaProperty().get());

                comuneNascitaCliente.setText(
                        clienteData.getComuneNascitaProperty().get());

                comuneResidenzaClienteText.setText(
                        clienteData.getComuneNascitaProperty().get());

                indirizzoClienteText.setText(
                        clienteData.getIndirizzoProperty().get());

                codiceFiscaleCliente.setText(clienteData.getCodiceFiscale());

                idPatenteCliente.setText(
                        clienteData.getNumPatenteGuidaProperty().get());

                nomeClienteText.setEditable(false);
                cognomeClienteText.setEditable(false);
                dataNascitaCliente.setEditable(false);
                comuneNascitaCliente.setEditable(false);
                comuneResidenzaClienteText.setEditable(false);
                indirizzoClienteText.setEditable(false);
                idPatenteCliente.setEditable(false);

                this.cliente = clienteData;

            } catch (Exception e) {

                codiceFiscaleText.clear();

                ShowAlert.showMessage("Non esiste un cliente con questo"
                        + " codice fiscale",
                        AlertType.WARNING);
            }

        } else {
            ShowAlert.showMessage("Inserire il parametro di ricerca",
                    AlertType.WARNING);
        }

    }

    /**
     * Gestisce i dettagli del noleggio.
     * */
    private Boolean getDatiNoleggio() {
        String dataInizio = dataInizioNoleggioText.getText().trim();
        String dataFine = dataFineNoleggioText.getText().trim();

        String agenziaPartenza = SessionHandler.currentAgenziaId;

        AgenziaModel agenziaRientro = agenziaRientroComboBox.getValue();

        if (!ValidaDatiContratto.isEmptyCheck(dataInizio, dataFine,
                agenziaRientro, this.tariffa, this.auto)
            && ValidaDatiContratto.isValidInput(dataInizio, dataFine)) {

            contratto.dataApertura = dataAperturaLabel.getText();
            contratto.dataInizioNoleggio = dataInizio;
            contratto.dataFineNoleggio = dataFine;
            contratto.agenziaAperturaId = agenziaPartenza;
            contratto.cauzione = Float.parseFloat(
                    cauzioneContrLabel.getText());
            contratto.agenziaRientroId = agenziaRientro.getIdProperty().get();
            contratto.tariffaId = tariffa.getId();
            contratto.autovetturaId = auto.getId().get();
            contratto.modalità = tariffa.getModalità();
            contratto.id = numContrattoLabel.getText();
            contratto.saldoParziale = Float.parseFloat(
                    saldoParzialeContrLabel.getText());

            for (ExtraAgenziaModel e : extras) {
                ExtraTO extra = new ExtraTO();
                extra.id = e.getIdProperty().get();
                contratto.listaExtra.add(extra);
            }

            //se l'utente è stato ricercato
            if (cliente != null) {
                contratto.clienteId = cliente.getIdProperty().get();
            }

            return true;
        } else {
            return false;
        }


    }

    /**
     * Gestisce l'acquisizione dei dati di un cliente.
     * */
    private Boolean getDataCliente() {
        String nomeCliente = nomeClienteText.getText();
        String cognomeCliente = cognomeClienteText.getText();
        String dataNascita = dataNascitaCliente.getText();
        String comuneNascita = comuneNascitaCliente.getText();
        String comuneResidenza = comuneResidenzaClienteText.getText();
        String indirizzo = indirizzoClienteText.getText();
        String codiceFiscale = codiceFiscaleCliente.getText();
        String idPatente = idPatenteCliente.getText();

        if (!ValidaDatiCliente.isEmptyCheck(nomeCliente, cognomeCliente,
                dataNascita, comuneNascita, comuneResidenza,
                indirizzo, codiceFiscale, idPatente)
            && ValidaDatiCliente.isValidInput(nomeCliente, cognomeCliente, dataNascita,
                    comuneNascita, comuneResidenza, indirizzo,
                    codiceFiscale, idPatente)) {

            ClienteTO cliente = new ClienteTO();
            cliente.nome = nomeCliente;
            cliente.cognome = cognomeCliente;
            cliente.dataDiNascita = dataNascita;
            cliente.comuneNascita = comuneNascita;
            cliente.comuneResidenza = comuneResidenza;
            cliente.indirizzo = indirizzo;
            cliente.codiceFiscale = codiceFiscale;
            cliente.idPatente = idPatente;

            contratto.infoCliente = cliente;

            return true;
        } else {
            return false;
        }
    }

    /**
     * Gestisce la ricerca di una tariffa.
     * */
    private void ricercaTariffe() {

        ClasseAutovetturaModel classe = classeTariffaRicerca.getValue();

        if (classe != null) {
            String param = classe.getNome();

            tariffaTableView.setItems(null);
            tariffaTableView.layout();

            if (GetCommonData.getTariffeClasse(param) != null) {
                tariffaTableView.setItems(FXCollections.observableArrayList(
                        GetCommonData.getTariffeClasse(param)));
            }

        } else {
            ShowAlert.showMessage("Scegliere una classe!", AlertType.WARNING);
        }

    }

    /**
     * Ottiene tutte le classi autovettura.
     * */
    private void getClassiAutovettura() {
        classeTariffaRicerca.setItems(
                GetCommonData.getRiepilogoClassiAutovettura());
    }

    /**
     * Ottiene tutte le agenzie.
     * */
    private void getAgenzie() {
        agenziaRientroComboBox.setItems(
                GetCommonData.getRiepilogoAgenzie());
    }

    /**
     * Riepilogo di tutte le agenzie.
     * */
    private void getAutovettureDisponibili() {
        ComplexRequest<AutovetturaTO> request =
                new ComplexRequest<AutovetturaTO>(
                        "restituisciAutovettureDisponibili", RequestType.SERVICE);

        AutovetturaTO autovettura = new AutovetturaTO();
        autovettura.agenzia = SessionHandler.currentAgenziaId;
        autovettura.classeAutovetturaId = tariffa.getClasse().getId().get();

        request.addParameter(autovettura);

        ComplexResponse<AutovetturaTO> response =
                (ComplexResponse<AutovetturaTO>)fc.processRequest(request);

        for (AutovetturaTO c : response.getParameters()) {
            ClasseAutovetturaModel classe =
                    GetCommonData.getClasseAutovettura(c.classeAutovetturaId);
            AutovetturaModel model = new AutovetturaModel(c.targa, c.marca,
                    c.modello, c.chilometraggio, classe, c.alimentazione,c.id);

            autovetture.add(model);
        }

        autovetturaTableView.setItems(null);
        autovetturaTableView.layout();
        autovetturaTableView.setItems(FXCollections.observableArrayList(
                autovetture));
    }

    /**
     * Riepilogo di tutti gli extra.
     * */
    private void getExtra() {

        ObservableList<ExtraAgenziaModel> list =
                FXCollections.observableArrayList();

        ComplexRequest<AgenziaTO> request =
                new ComplexRequest<AgenziaTO>("riepilogoExtraAgenzia",
                        RequestType.SERVICE);

        AgenziaTO agenzia = new AgenziaTO();
        agenzia.id = SessionHandler.currentAgenziaId;

        request.addParameter(agenzia);

        ComplexResponse<ExtraAgenziaTO> response =
                (ComplexResponse<ExtraAgenziaTO>) fc.processRequest(request);

        for (ExtraAgenziaTO c : response.getParameters()) {
            ExtraAgenziaModel extra = new ExtraAgenziaModel(
                    c.nome, c.descrizione, c.id);
            list.add(extra);
        }

        extraComboBox.setItems(list);
    }

    /**
     * Salva l'autovettura scelta.
     * */
    private void setAutovettura() {
        AutovetturaModel model =
                autovetturaTableView.getSelectionModel().getSelectedItem();
        if (model != null) {
            this.auto = model;
            autoSelezionataLabel.setText(model.toString());
        } else {
            ShowAlert.showMessage("Nessuna auto selezionata!",
                    AlertType.WARNING);
        }
    }

    /**
     * Salva tariffa selezionata.
     * */
     private void setTariffa() {
         TariffaModel model =
                 tariffaTableView.getSelectionModel().getSelectedItem();
         if (model != null) {
             this.tariffa = model;
             this.tariffaLabel.setText(model.getId());
             this.modalitàNoleggioLabel.setText(model.getModalità());
             getAutovettureDisponibili();
         } else {
             ShowAlert.showMessage("Nessuna tariffa selezionata!",
                     AlertType.WARNING);
         }
     }

     private void addExtra() {
         ExtraAgenziaModel model = extraComboBox.getValue();
         extras.add(model);

         extraTableView.setItems(extras);
     }

     private void removeExtra() {
         extras.remove(extraTableView.getSelectionModel().getSelectedItem());
         extraTableView.getSelectionModel().clearSelection();
     }

     /**
      * Ricerca le informazioni di un contratto.
      * */
     private void ricercaContratto() {
         String numContratto = codiceContrattoText.getText();

         if (!numContratto.isEmpty()) {

             ComplexRequest<ContrattoTO> request =
                     new ComplexRequest<ContrattoTO>("ricercareContratto",
                             RequestType.SERVICE);

             ContrattoTO to = new ContrattoTO();
             to.id = numContratto;

             request.addParameter(to);

             ComplexResponse<ContrattoTO> response =
                         (ComplexResponse<ContrattoTO>) fc.processRequest(request);

             to = response.getParameters().get(0);

             if (response.getResult()) {

                 annullaContrRicercaButton.setVisible(true);
                 codiceContrattoText.setDisable(true);
                 datiNoleggioTab.setDisable(true);
                 datiClientiTab.setDisable(true);
                 datiAutovetturaTab.setDisable(true);
                 datiExtraTab.setDisable(true);

                 try {

                     tariffa = new TariffaModel();
                     tariffa.setChilometraggioProperty(to.infoTariffa.chilometraggio);
                     tariffa.setChilometriGiorno(to.infoTariffa.chilometriGiorno);

                     dataInizioContrLabel.setText(
                         DateUtil.toPrintDate(to.dataInizioNoleggio));

                     dataFineContrLabel.setText(
                             DateUtil.toPrintDate(to.dataFineNoleggio));

                     dataAperturaContrLabel.setText(
                             DateUtil.toPrintDate(to.dataApertura));

                     agenziaRientroContrLabel.setText(
                             to.infoAgenziaRientro.toString());

                     autoContrLabel.setText(
                             to.infoAutovettura.toString());

                     tariffaContrLabel.setText(to.infoTariffa.toString());

                     statoContrLabel.setText(to.stato);

                     cauzioneContrLabel.setText(Float.toString(
                             to.cauzione));

                     saldoParzialeContrLabel.setText(Float.toString(
                             to.importoFinale));

                     codFiscaleContrLabel.setText(to.infoCliente.codiceFiscale);

                     apriContrattoButton.setDisable(true);

                     if (to.stato.equals("Aperto")) {
                         chiudereContrattoButton.setDisable(false);

                         if (to.infoTariffa.chilometraggio.equals("Limitato")) {
                            kmPercorsoText.setDisable(false);
                         }

                     }

                 } catch (NullPointerException e) {
                     ShowAlert.showMessage("Il contratto non esiste!",
                             AlertType.WARNING);
                 }
             }

         } else {
             ShowAlert.showMessage("Inserire il parametro",
                     AlertType.WARNING);
         }
     }

     /**
      * Salva un contratto nel sistema.
      * */
     private void sendContratto() {
         Boolean res1 = getDatiNoleggio();
         Boolean res2 = getDataCliente();

         if (res1 && res2) {

             ComplexRequest<ContrattoTO> request =
                     new ComplexRequest<ContrattoTO>("aperturaContratto",
                             RequestType.SERVICE);

             request.addParameter(this.contratto);

             SimpleResponse response =
                     (SimpleResponse) fc.processRequest(request);


             if (response.getResponse()) {
                 ShowAlert.showMessage("Contratto inserito con successo",
                         AlertType.INFORMATION);


                 statoContrLabel.setText("Aperto");

                 stampareContrattoButton.setDisable(false);
                 apriContrattoButton.setDisable(true);

                 datiAutovetturaTab.setDisable(true);
                 datiClientiTab.setDisable(true);
                 datiNoleggioTab.setDisable(true);
                 rimuoviExtraButton.setDisable(true);
                 selezionaExtraButton.setDisable(true);
                 extraComboBox.setDisable(true);
                 datiExtraTab.setDisable(false);

             } else {

                 extras.clear();
                 contratto.listaExtra.clear();

                 ShowAlert.showMessage("Non è stato possibili inserire "
                         + "il contratto. Dati non accettati.",
                         AlertType.INFORMATION);
             }
         }
     }

     /**
      * Gestisce la chiusura di un contratto.
      * */
     private void chiudiContratto() {

         String kmPercorsi = kmPercorsoText.getText();

         ComplexRequest<ContrattoTO> request =
                 new ComplexRequest<>("chiusuraContratto", RequestType.SERVICE);

         ContrattoTO to = new ContrattoTO();

         if (!kmPercorsi.isEmpty()) {
             to.chilometriPercorsi = Float.parseFloat(kmPercorsi);
         }

         to.infoTariffa.chilometraggio = tariffa.getChilometraggio();
         to.infoTariffa.chilometriGiorno = tariffa.getChilometriGiorno();

         to.dataChiusura =
                 DateUtil.toPrintDate(LocalDate.now().toString());

         to.id = codiceContrattoText.getText();

         to.saldoParziale = Float.parseFloat(
                 saldoParzialeContrLabel.getText());

         to.cauzione = Float.parseFloat(
                 cauzioneContrLabel.getText());

         to.dataInizioNoleggio = dataInizioContrLabel.getText();

         to.dataFineNoleggio = dataFineContrLabel.getText();

         request.addParameter(to);

         SimpleResponse response =
                 (SimpleResponse) fc.processRequest(request);

         if (response.getResponse()) {
             ShowAlert.showMessage("Il contratto è stato chiuso",
                     AlertType.INFORMATION);

             stampareContrattoButton.setDisable(false);
             dataChiusuraContrLabel.setText(DateUtil.toPrintDate(LocalDate.now().toString()));

             ricercaContratto();

         } else {
             ShowAlert.showMessage("Problemi con la chiusura del contratto",
                     AlertType.INFORMATION);
         }

     }

     /**
      * Annulla una ricerca.annullaContrRicercaButton
      * */
     private void annullaRicerca() {

         codiceContrattoText.setDisable(false);
         chiudereContrattoButton.setDisable(true);
         annullaContrRicercaButton.setVisible(false);

         dataInizioContrLabel.setText("");

         dataFineContrLabel.setText("");

         dataAperturaContrLabel.setText("");

         agenziaRientroContrLabel.setText("");

         autoContrLabel.setText("");

         tariffaContrLabel.setText("");

         statoContrLabel.setText("");

         cauzioneContrLabel.setText("");

         saldoParzialeContrLabel.setText("");

         codFiscaleContrLabel.setText("");

         apriContrattoButton.setDisable(false);

     }

     private Float[] calcolaSaldoParziale() {

         String dataInizio = dataInizioNoleggioText.getText();
         String dataFine = dataFineNoleggioText.getText();
         Float saldoParziale = (float) 0.0;
         Float cauzione = (float) 0.0;

         if (!dataInizio.isEmpty() && !dataFine.isEmpty() && tariffa!=null) {
             try {
                 Integer giorniNoleggio = DateUtil.getGiorniTraDate(
                         dataInizio, dataFine);

                 if (tariffa.getModalità().equals("Giornaliera")) {
                     saldoParziale =
                             ((float)giorniNoleggio * tariffa.getImportoUnitario());

                     cauzione = (saldoParziale/100)*35;

                     saldoParziale = saldoParziale + cauzione;
                 } else if (tariffa.getModalità().equals("Settimanale")){

                     Double settimane = Math.floor(giorniNoleggio / 7);
                     saldoParziale = (settimane.floatValue() * tariffa.getImportoUnitario());
                     cauzione = (saldoParziale/100)*35;

                 }
             } catch(Exception e) {
                 dataInizioContrLabel.setText("Correggere il formato della data");
                 dataFineContrLabel.setText("Correggere il formato della data");
             }
         }

         Float[] result = {saldoParziale, cauzione};
         return result;
     }

     private void stampaContratto() {
         ComplexRequest<HashMap<String,String>> request =
                 new ComplexRequest<>("stampareContratto", RequestType.SERVICE);

         HashMap<String, String> values = new HashMap<String, String>();

         values.put("codFiscale", codFiscaleContrLabel.getText());
         values.put("nome", nomeClienteText.getText());
         values.put("cognome", cognomeClienteText.getText());
         values.put("dataInizioNoleggio", dataInizioContrLabel.getText());
         values.put("dataFineNoleggio", dataFineContrLabel.getText());
         values.put("agenziaRientro", agenziaRientroContrLabel.getText());
         values.put("autovettura", autoContrLabel.getText());
         values.put("tariffa", tariffaContrLabel.getText());
         values.put("cauzione", cauzioneContrLabel.getText());
         values.put("saldoParziale", saldoParzialeContrLabel.getText());
         values.put("numContratto", numContrattoLabel.getText());
         values.put("dataCorrente", dataAperturaContrLabel.getText());

         request.addParameter(values);

         fc.processRequest(request);

     }

}
