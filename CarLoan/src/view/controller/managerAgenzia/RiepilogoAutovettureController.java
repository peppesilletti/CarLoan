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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import presentation.SessionHandler;
import transferObjects.entitiesTO.AutovetturaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.ComplexResponse;
import transferObjects.response.SimpleResponse;
import view.controller.GetCommonData;
import view.controller.ShowAlert;
import view.model.AutovetturaModel;
import view.model.ClasseAutovetturaModel;

/**
 * Controller per l'interfaccia RiepilogoAutovetture.fxml.
 * */
public class RiepilogoAutovettureController implements Initializable {

    @FXML
    private Label valueAlimentazioneLabel;

    @FXML
    private TextField marcaRicercaText;

    @FXML
    private Label valueClasseAutovetturaLabel;

    @FXML
    private Label valueKmPercorsiLabel;

    @FXML
    private Label valueModelloLabel;

    @FXML
    private Button modificaButton;

    @FXML
    private ComboBox<String> alimentazioneModifica;

    @FXML
    private Button confermaModifiche;

    @FXML
    private Button annullaRicercaButton;

    @FXML
    private Label valueTargaLabel;

    @FXML
    private TableColumn<AutovetturaModel, String> modelloColonna;

    @FXML
    private ComboBox<ClasseAutovetturaModel> classeModifica;

    @FXML
    private ComboBox<ClasseAutovetturaModel> classeRicercaComboBox;

    @FXML
    private TextField targaModifica;

    @FXML
    private TableColumn<AutovetturaModel, String> marcaColonna;

    @FXML
    private Button eliminaButton;

    @FXML
    private TextField targaRicercaText;

    @FXML
    private TableView<AutovetturaModel> infoTableView;

    @FXML
    private TableColumn<AutovetturaModel, String> targaColonna;

    @FXML
    private TextField marcaModifica;

    @FXML
    private TextField kmModifica;

    @FXML
    private Button ricercaButton;

    @FXML
    private Button indietroButton;

    @FXML
    private Label valueMarcaLabel;

    @FXML
    private TextField modelloModifica;

    private ObservableList<AutovetturaModel> list;

    private FrontControllerInt fc;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        fc = new FrontController();

        setList(FXCollections.observableArrayList());

        targaColonna
                .setCellValueFactory(new PropertyValueFactory<AutovetturaModel, String>(
                        "targa"));

        marcaColonna
                .setCellValueFactory(new PropertyValueFactory<AutovetturaModel, String>(
                        "marca"));

        modelloColonna
                .setCellValueFactory(new PropertyValueFactory<AutovetturaModel, String>(
                        "modello"));

        riepilogo();
        getClassiAutovetture();

        // Clear extra details
        infoAutovettura(null);

        infoTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> infoAutovettura(newValue));

    }

    /**
     * Metodo che permette di visualizzare le informazioni di un extra
     * cliccatoci sopra.
     * */
    public final void infoAutovettura(final AutovetturaModel model) {
        if (model != null) {
            valueTargaLabel.setText(model.getTarga());
            valueModelloLabel.setText(model.getModello());
            valueMarcaLabel.setText(model.getMarca());
            valueKmPercorsiLabel.setText(Float.toString(model
                    .getChilometraggioProperty().getValue()));
            valueClasseAutovetturaLabel.setText(model
                    .getClasseAutovetturaProperty().getNome());
            valueAlimentazioneLabel.setText(model.getAlimentazioneProperty()
                    .getValue());
        } else {
            valueTargaLabel.setText("");
            valueModelloLabel.setText("");
            valueMarcaLabel.setText("");
            valueKmPercorsiLabel.setText("");
            valueClasseAutovetturaLabel.setText("");
            valueAlimentazioneLabel.setText("");
        }
    }

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

        this.targaRicercaText.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    ricerca();
                }
            }
        });

        this.marcaRicercaText.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
    public final void modificaButton() {
        this.modificaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                showModifica();
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
                        "riepilogoAutovetture", RequestType.UI_VIEW);
                fc.processRequest(request);
            }
        });
    }

    /**
     * Mostra l'elenco di tariffe presenti nel sistema.
     * */
    public final void riepilogo() {

        SimpleRequest request = new SimpleRequest(
                "riepilogoAutovetture", RequestType.SERVICE);

        ComplexResponse<AutovetturaTO> response =
                (ComplexResponse<AutovetturaTO>) fc.processRequest(request);

        for (AutovetturaTO c : response.getParameters()) {
            ClasseAutovetturaModel classe = getClasse(c.classeAutovetturaId);
            AutovetturaModel model = new AutovetturaModel(c.targa, c.marca,
                    c.modello, c.chilometraggio, classe, c.alimentazione,c.id);

            list.add(model);
        }

        infoTableView.setItems(null);
        infoTableView.layout();
        infoTableView.setItems(FXCollections.observableArrayList(list));

    }

    /**
     * Gestisce l'eliminazione dell'autovettura.
     * */
    public final void eliminare() {

        AutovetturaModel selectedAutovettura = infoTableView
                .getSelectionModel().getSelectedItem();

        if (selectedAutovettura != null) {

            if (ShowAlert.showMessage("Confermi l'eliminazione?",
                    AlertType.CONFIRMATION)) {
                // richiesta
                AutovetturaTO to = new AutovetturaTO();

                to.id = selectedAutovettura.getId().get();
                ComplexRequest<AutovetturaTO> request =
                        new ComplexRequest<AutovetturaTO>(
                        "eliminareAutovettura", RequestType.SERVICE);
                request.addParameter(to);

                // risposta
                SimpleResponse response =
                        (SimpleResponse) fc.processRequest(request);

                // se la risposta va a buon fine aggiorna la tabella
                if (response.getResponse()) {

                    int selectedIndex = infoTableView.getSelectionModel()
                            .getSelectedIndex();
                    infoTableView.getItems().remove(selectedIndex);

                    ShowAlert.showMessage("Autovettura eliminata con successo",
                            AlertType.INFORMATION);
                } else {
                    ShowAlert.showMessage("Impossibile eliminare l'autovettura!",
                            AlertType.WARNING);
                }
            }
        }
    }


    /**
     * Gestisce la modifica di un'autovettura.
     * */
    private void modifica() {
        AutovetturaModel selectedAutovettura =
                infoTableView.getSelectionModel().getSelectedItem();

        String targa = targaModifica.getText().trim();
        String marca = marcaModifica.getText().trim();
        String modello = modelloModifica.getText().trim();
        String chilometraggio = kmModifica.getText().trim();
        String alimentazione = alimentazioneModifica.getValue();
        String agenziaId = SessionHandler.currentAgenziaId;
        ClasseAutovetturaModel classeAutovettura =
                classeModifica.getValue();

        if (!ValidaDatiAutovettura.isEmptyCheck(targa, modello, marca,
                chilometraggio, classeAutovettura, alimentazione)
            && ValidaDatiAutovettura.isValidInput(targa, modello, marca,
                    chilometraggio)) {

            AutovetturaTO autovettura = new AutovetturaTO(targa, marca,
                    modello, Float.parseFloat(chilometraggio),
                    alimentazione, agenziaId,
                    classeAutovettura.getId().get());

            autovettura.id = selectedAutovettura.getId().get();

            ComplexRequest<AutovetturaTO> request =
                    new ComplexRequest<AutovetturaTO>(
                    "modificareAutovettura", RequestType.SERVICE);

            request.addParameter(autovettura);

            SimpleResponse response =
                    (SimpleResponse) fc.processRequest(request);

            if (response.getResponse()) {
                SimpleRequest refresh =
                        new SimpleRequest("riepilogoAutovetture",
                        RequestType.UI_VIEW);

                fc.processRequest(refresh);
            } else {
                ShowAlert.showMessage("Dati inseriti non accettati.",
                        AlertType.WARNING);
            }
        }
    }

    /**
     * Mostra i text field per le modifiche.
     * */
    private void showModifica() {
        AutovetturaModel selectedAutovettura =
                infoTableView.getSelectionModel().getSelectedItem();

        if (selectedAutovettura == null) {
            ShowAlert.showMessage("Nessuna autovettura selezionato",
                    AlertType.WARNING);
        } else {
            targaModifica.setVisible(true);
            marcaModifica.setVisible(true);
            modelloModifica.setVisible(true);
            kmModifica.setVisible(true);
            alimentazioneModifica.setVisible(true);
            classeModifica.setVisible(true);
            confermaModifiche.setVisible(true);

            targaModifica.setText(selectedAutovettura.getTarga());
            marcaModifica.setText(selectedAutovettura.getMarca());
            modelloModifica.setText(selectedAutovettura.getModello());
            kmModifica.setText(selectedAutovettura.getChilometraggioProperty()
                    .getValue().toString());
            alimentazioneModifica.setValue(selectedAutovettura.getAlimentazioneProperty()
                    .get());
            classeModifica.setValue(selectedAutovettura.getClasseAutovetturaProperty());

        }
    }

    /**
     * Gestisce la ricerca di un'autovettura.
     * */
    private void ricerca() {

        String param1 = "";
        String param2 = "";
        String param3 = "";

        if (!marcaRicercaText.getText().isEmpty()) {
            param1 = marcaRicercaText.getText().trim();
        }

        if (!targaRicercaText.getText().isEmpty()) {
            param2 = targaRicercaText.getText().trim();
        }

        if (classeRicercaComboBox.getValue() != null) {
            param3 = classeRicercaComboBox.getValue().getNome();
        }

        if (param1.isEmpty() && param2.isEmpty() && param3 == null) {
            ShowAlert.showMessage("Inserire almeno un parametro di ricerca",
                    AlertType.WARNING);
        } else {

            annullaRicercaButton.setVisible(true);

            AutovetturaTO autovettura = new AutovetturaTO();

            autovettura.targa = param2;
            autovettura.marca = param1;
            autovettura.classeAutovetturaId = param3;

            ComplexRequest<AutovetturaTO> request =
                    new ComplexRequest<AutovetturaTO>(
                            "ricercareAutovetturaParams",
                            RequestType.SERVICE);

            request.addParameter(autovettura);

            ComplexResponse<AutovetturaTO> response =
                    (ComplexResponse<AutovetturaTO>) fc.processRequest(request);

            list.removeAll(list);

            infoTableView.setItems(null);
            infoTableView.layout();

            if (response.getParameters() != null) {

                for (AutovetturaTO c : response.getParameters()) {
                    ClasseAutovetturaModel classe = getClasse(c.classeAutovetturaId);
                    AutovetturaModel model = new AutovetturaModel(c.targa, c.marca,
                            c.modello, c.chilometraggio, classe, c.alimentazione,c.id);
                    list.add(model);
                }

                infoTableView.setItems(FXCollections.observableArrayList(list));
            }
        }
    }

    private final void setList(final ObservableList<AutovetturaModel> list) {
        this.list = list;
    }

    private ClasseAutovetturaModel getClasse(String id) {
        return GetCommonData.getClasseAutovettura(id);
    }

    private void getClassiAutovetture() {
        classeRicercaComboBox.setItems(
                GetCommonData.getRiepilogoClassiAutovettura());
    }

}
