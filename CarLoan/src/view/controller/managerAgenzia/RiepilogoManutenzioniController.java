package view.controller.managerAgenzia;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.entitiesTO.ManutenzioneTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.ComplexResponse;
import transferObjects.response.SimpleResponse;
import utility.DateUtil;
import view.controller.GetCommonData;
import view.controller.ShowAlert;
import view.model.AutovetturaModel;
import view.model.ClasseAutovetturaModel;
import view.model.ManutenzioneModel;

/**
 * Controller per l'interfaccia RiepilogoManutenzioni.fxml.
 * */
public class RiepilogoManutenzioniController implements Initializable {

    @FXML
    private TableColumn<ManutenzioneModel, String> dataInizioColumn;


    @FXML
    private Label valueTipoManutenzioneLabel;

    @FXML
    private Button modificaButton;

    @FXML
    private ComboBox<AutovetturaModel> autovetturaRicerca;

    @FXML
    private Button eliminaButton;

    @FXML
    private Button confermaModifiche;

    @FXML
    private Button annullaRicercaButton;

    @FXML
    private TextArea difettiModifica;

    @FXML
    private TextArea riparazioniModifica;

    @FXML
    private TableColumn<ManutenzioneModel, String> autovetturaColumn;

    @FXML
    private Label difettiLabel;

    @FXML
    private Label numeroManutenzioneLabel;

    @FXML
    private Button ricercaButton;

    @FXML
    private TextField dataInizioModifica;

    @FXML
    private TextField dataInizioText;

    @FXML
    private Label valueDataInizioLabel;

    @FXML
    private Label valueAutovetturaLabel;

    @FXML
    private Label valueIdLabel;

    @FXML
    private ComboBox<String> tipoManutenzioneModifica;

    @FXML
    private ComboBox<AutovetturaModel> autovetturaModifica;

    @FXML
    private Label valueDataFineLabel;

    @FXML
    private Button infoAuto;

    @FXML
    private TextField dataFineModifica;

    @FXML
    private TableView<ManutenzioneModel> infoTableView;

    @FXML
    private TableColumn<ManutenzioneModel, String> tipoColumn;

    @FXML
    private Button indietroButton;

    @FXML
    private Label riparazioniLabel;

    private ObservableList<ManutenzioneModel> list;

    private FrontControllerInt fc;

    @Override
    public final void initialize(final URL arg0, final ResourceBundle arg1) {
        fc = new FrontController();

        setList(FXCollections.observableArrayList());

        autovetturaColumn
                .setCellValueFactory(new PropertyValueFactory<ManutenzioneModel, String>(
                        "autovettura"));

        dataInizioColumn
                .setCellValueFactory(new PropertyValueFactory<ManutenzioneModel, String>(
                        "dataInizio"));

        tipoColumn
                .setCellValueFactory(new PropertyValueFactory<ManutenzioneModel, String>(
                        "tipo"));

        riepilogo();
        getAutovetture();

        // Clear manutenzione details
        infoManutenzione(null);

        infoTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> infoManutenzione(newValue));

        difettiModifica.setEditable(false);
        riparazioniModifica.setEditable(false);
    }

    /**
     * Metodo che permette di visualizzare le informazioni di un manutenzione
     * cliccatoci sopra.
     * */
    public final void infoManutenzione(final ManutenzioneModel model) {
        if (model != null) {

            infoAuto.setVisible(true);

            valueDataInizioLabel.setText(model.getDataInizio());
            valueDataFineLabel.setText(model.getDataFine());

            valueIdLabel.setText(model.getNumero().get());

            valueTipoManutenzioneLabel.setText(model.getTipo());
            valueAutovetturaLabel.setText(model.getAutovettura().toString());
            numeroManutenzioneLabel.setText(model.getNumero()
                    .getValue());
            difettiModifica.setText(model.getDifettiRiscontratiProperty().
                    get());
            riparazioniModifica.setText(model.getRiparazioniEseguiteProperty().
                    get());
        } else {
            valueDataInizioLabel.setText("");
            valueDataFineLabel.setText("");
            valueTipoManutenzioneLabel.setText("");
            valueAutovetturaLabel.setText("");
            valueIdLabel.setText("");
            difettiModifica.setText("");
            riparazioniModifica.setText("");
        }
    }

    /**
     * Evento di ricerca delle informazioni di un manutenzione.
     * */
    @FXML
    public final void ricercaButton() {

        this.ricercaButton.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    ricerca();
                }
            }
        });

        this.ricercaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                ricerca();
            }
        });

    }

    /**
     * Evento di eliminazione di un manutenzione.
     * */

    @FXML
    public final void eliminaButton() {

        this.eliminaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                eliminare();
            }
        });
    }

    @FXML
    public final void confermaModifiche() {

        this.confermaModifiche.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (ShowAlert.showMessage("Confermare le modifiche?",
                        AlertType.CONFIRMATION)) {
                    modifica();
                }
            }
        });
    }

    @FXML
    public void modificaButton() {
        this.modificaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                showModifica();
            }
        });
    }

    @FXML
    public void infoAuto() {
        this.infoAuto.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                showInfoAuto();
            }
        });
    }

    /**
     * Gestisce la pressione del tasto indietro.
     * */
    @FXML
    private void indietroButton() {
        this.indietroButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                SimpleRequest request = new SimpleRequest("PannelloManager",
                        RequestType.UI_VIEW);
                fc.processRequest(request);
            }
        });
    }

    @FXML
    private void annullaRicercaButton() {
        this.annullaRicercaButton.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                SimpleRequest request = new SimpleRequest(
                        "riepilogoManutenzioni", RequestType.UI_VIEW);
                fc.processRequest(request);
            }
        });
    }

    /**
     * Mostra l'elenco di tariffe presenti nel sistema.
     * */
    public final void riepilogo() {

        SimpleRequest request = new SimpleRequest("riepilogoManutenzioni",
                RequestType.SERVICE);

        ComplexResponse<ManutenzioneTO> response =
                (ComplexResponse<ManutenzioneTO>) fc.processRequest(request);

        for (ManutenzioneTO c : response.getParameters()) {
            AutovetturaModel auto = getAutovettura(c.autovetturaId);

            //Converto formati stringa
            String dataIn = DateUtil.toPrintDate(c.dataInizio);
            String dataOut = null;

            if (c.dataFine != null) {
                 dataOut = DateUtil.toPrintDate(c.dataFine);
            }

            ManutenzioneModel model = new ManutenzioneModel(
                    dataIn, dataOut, c.tipo, c.difettiRiscontrati,
                    c.riparazioniEseguite, auto, c.id);
            list.add(model);
        }

        infoTableView.setItems(null);
        infoTableView.layout();
        infoTableView.setItems(FXCollections.observableArrayList(list));

    }

    /**
     * Gestione dell'eliminazione Manutenzione.
     * */
    public final void eliminare() {

        ManutenzioneModel selectedManutenzione = infoTableView
                .getSelectionModel().getSelectedItem();

        if (selectedManutenzione != null) {

            if (ShowAlert.showMessage("Confermare l'eliminazione?",
                    AlertType.CONFIRMATION)) {
                // richiesta
                ManutenzioneTO to = new ManutenzioneTO();

                to.id = selectedManutenzione.getNumero().getValue();
                ComplexRequest<ManutenzioneTO> request =
                        new ComplexRequest<ManutenzioneTO>(
                        "eliminareManutenzione", RequestType.SERVICE);
                request.addParameter(to);

                // risposta
                SimpleResponse response =
                        (SimpleResponse) fc.processRequest(request);

                // se la risposta va a buon fine aggiorna la tabella
                if (response.getResponse()) {

                    int selectedIndex = infoTableView.getSelectionModel()
                            .getSelectedIndex();
                    infoTableView.getItems().remove(selectedIndex);

                    ShowAlert.showMessage("Manutenzione eliminata con successo.",
                            AlertType.INFORMATION);
                } else {
                    ShowAlert.showMessage("Impossibile eliminare la manutenzione",
                            AlertType.INFORMATION);
                }
            }
        }
    }

    /**
     * Gestisce la modifica di una manutenzione.
     * */
    private void modifica() {
        ManutenzioneModel selectedManutenzione =
                infoTableView.getSelectionModel().getSelectedItem();

        String tipo = tipoManutenzioneModifica.getValue();
        String dataIn = dataInizioModifica.getText();
        String dataOut = dataFineModifica.getText();
        AutovetturaModel autovettura = autovetturaModifica.getValue();
        String difetti = difettiModifica.getText();
        String riparazioni = riparazioniModifica.getText();


        if (!ValidaDatiManutenzione.isEmpty(dataIn, tipo, autovettura, dataOut)
                && ValidaDatiManutenzione.isValidInput(dataIn, dataOut)) {

            ManutenzioneTO to = new ManutenzioneTO(
                    autovettura.getId().get(), tipo, dataIn,
                    dataOut, difetti, riparazioni);
            to.id = selectedManutenzione.getNumero().getValue();

            ComplexRequest<ManutenzioneTO> request =
                    new ComplexRequest<ManutenzioneTO>(
                    "modificareDatiManutenzione", RequestType.SERVICE);

            request.addParameter(to);

            SimpleResponse response =
                    (SimpleResponse) fc.processRequest(request);

            if (response.getResponse()) {
                SimpleRequest refresh =
                        new SimpleRequest("riepilogoManutenzioni",
                        RequestType.UI_VIEW);

                fc.processRequest(refresh);
            } else {
                ShowAlert.showMessage("Dati inseriti non accettati, riprovare!",
                        AlertType.WARNING);
            }
        }
    }

    /**
     * Show text fields per modifica.
     * */
    private void showModifica() {
        ManutenzioneModel selectedManutenzione =
                infoTableView.getSelectionModel().getSelectedItem();

        if (selectedManutenzione == null) {
            ShowAlert.showMessage("Nessuna manutenzione selezionata!",
                    AlertType.WARNING);
        } else {

            autovetturaModifica.setItems(
                            GetCommonData.getRiepilogoAutovetture());

            dataInizioModifica.setVisible(true);
            dataFineModifica.setVisible(true);
            tipoManutenzioneModifica.setVisible(true);
            autovetturaModifica.setVisible(true);
            confermaModifiche.setVisible(true);

            String dataIn = selectedManutenzione.getDataInizio();
            String dataOut = selectedManutenzione.getDataFine();

            dataInizioModifica.setText(dataIn);
            dataFineModifica.setText(dataOut);
            tipoManutenzioneModifica.setValue(selectedManutenzione.getTipo());
            autovetturaModifica.setValue(selectedManutenzione.getAutovettura());

            difettiModifica.setEditable(true);
            riparazioniModifica.setEditable(true);
        }
    }

    /**
     * Restituisce le informazioni di un'autovettura.
     * */
    private AutovetturaModel getAutovettura(String id) {

        FrontControllerInt fc = new FrontController();

        ComplexRequest<AutovetturaTO> request =
                new ComplexRequest<AutovetturaTO>(
                        "ricercareDatiAutovettura", RequestType.SERVICE);

        AutovetturaTO to = new AutovetturaTO();
        to.id = id;

        request.addParameter(to);

        ComplexResponse<AutovetturaTO> response =
                (ComplexResponse<AutovetturaTO>)
                fc.processRequest(request);

        to = response.getParameters().get(0);

        ClasseAutovetturaModel classe =
                GetCommonData.getClasseAutovettura(to.classeAutovetturaId);

        return new AutovetturaModel(
                to.targa, to.marca, to.modello, to.chilometraggio,
                classe, to.alimentazione, to.id);
    }

    /**
     * Mostra un pane con le info dell'auto.
     * */
    private void showInfoAuto() {
        ManutenzioneModel selectedManutenzione =
                infoTableView.getSelectionModel().getSelectedItem();

        AutovetturaModel auto = selectedManutenzione.getAutovettura();

        ShowAlert.showMessage("Targa:\t"+auto.getTarga()+"\n"+
                "Marca:\t"+auto.getMarca()+"\n"+
                "Modello:\t"+auto.getModello()+"\n"+
                "Chilometraggio:\t"+auto.getChilometraggioProperty().get()+"\n"+
                "Alimentazione:\t"+auto.getAlimentazioneProperty().get(),
                AlertType.INFORMATION);
    }

    /**
     * Gestisce la ricerca di una manutenzione.
     * */
    private void ricerca() {
        AutovetturaModel param1 = autovetturaRicerca.getValue();

        if (param1 == null) {
            ShowAlert.showMessage("Inserire il parametro di ricerca",
                    AlertType.WARNING);
        } else {

            annullaRicercaButton.setVisible(true);

            AutovetturaTO autovettura = new AutovetturaTO();

            autovettura.targa = param1.getTarga();

            ComplexRequest<AutovetturaTO> request =
                    new ComplexRequest<AutovetturaTO>(
                            "ricercareManutenzioniAutovettura",
                            RequestType.SERVICE);

            request.addParameter(autovettura);

            ComplexResponse<ManutenzioneTO> response =
                    (ComplexResponse<ManutenzioneTO>) fc.processRequest(request);

            list.removeAll(list);

            infoTableView.setItems(null);
            infoTableView.layout();

            if (response.getParameters() != null) {

                for (ManutenzioneTO c : response.getParameters()) {
                    AutovetturaModel auto = getAutovettura(c.autovetturaId);

                    //Converto formati stringa
                    String dataIn = DateUtil.toPrintDate(c.dataInizio);
                    String dataOut = null;

                    if (c.dataFine != null) {
                         dataOut = DateUtil.toPrintDate(c.dataFine);
                    }

                    ManutenzioneModel model = new ManutenzioneModel(
                            dataIn, dataOut, c.tipo, c.difettiRiscontrati,
                            c.riparazioniEseguite, auto, c.id);
                    list.add(model);
                }

                infoTableView.setItems(FXCollections.observableArrayList(list));
            }
        }
    }

    private final void setList(final ObservableList<ManutenzioneModel> list) {
        this.list = list;
    }

    private void getAutovetture() {
        autovetturaRicerca.setItems(
                GetCommonData.getRiepilogoAutovetture());
    }

}
