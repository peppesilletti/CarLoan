package view.controller.amministratoreSistema;

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
import transferObjects.entitiesTO.TariffaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.ComplexResponse;
import transferObjects.response.SimpleResponse;
import view.controller.GetCommonData;
import view.controller.ShowAlert;
import view.model.ClasseAutovetturaModel;
import view.model.TariffaModel;

/**
 * Controller per la classe RiepilogoTariffe.fxml .
 * */
public class RiepilogoTariffeController implements Initializable {

    @FXML
    private TableView<TariffaModel> infoTableView;

    @FXML
    private TableColumn<TariffaModel, String> codiceColonna;

    @FXML
    private TableColumn<TariffaModel, String> importoColonna;

    @FXML
    private TableColumn<TariffaModel, ClasseAutovetturaModel> classeColonna;

    @FXML
    private Label valueCodiceTariffaLabel;

    @FXML
    private Label valueImportoUnitarioLabel;

    @FXML
    private Label valueModalitaKmLabel;

    @FXML
    private Label valueModalitaNoleggioLabel;

    @FXML
    private Label valueClasseAutovetturaLabel;

    @FXML
    private Label kmGiornoLabel;

    @FXML
    private Button modificaButton;

    @FXML
    private Button eliminaButton;

    @FXML
    private TextField classeRicercaText;

    @FXML
    private TextField kmGiornoText;

    @FXML
    private Button ricercaButton;

    @FXML
    private Button annullaRicercaButton;

    @FXML
    private Button indietroButton;

    @FXML
    private Button confermaModifiche;

    @FXML
    private TextField importoModifica;

    @FXML
    private ComboBox<String> kmModifica;

    @FXML
    private ComboBox<String> noleggioModifica;

    @FXML
    private ComboBox<ClasseAutovetturaModel> classeModifica;

    private ObservableList<TariffaModel>      list;

    private FrontControllerInt                fc;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        fc = new FrontController();

        setList(FXCollections.observableArrayList());

        codiceColonna
                .setCellValueFactory(
                        new PropertyValueFactory<TariffaModel, String>(
                        "id"));

        classeColonna
                .setCellValueFactory(
                        new PropertyValueFactory<TariffaModel,
                        ClasseAutovetturaModel>(
                        "classe"));

        importoColonna
                .setCellValueFactory(
                        new PropertyValueFactory<TariffaModel, String>(
                        "importoUnitario"));

        riepilogo();

        // Clear extra details
        infoTariffa(null);

        infoTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue)
                        -> infoTariffa(newValue));

    }

    /**
     * Metodo che permette di visualizzare le informazioni di un extra
     * cliccatoci sopra.
     * @param model
     *      Model con i dati della tariffa.
     * */
    public final void infoTariffa(final TariffaModel model) {
        if (model != null) {
            valueCodiceTariffaLabel.setText(model.getId());
            valueImportoUnitarioLabel.setText(Float.toString(model
                    .getImportoUnitarioProperty().getValue()));
            valueModalitaKmLabel.setText(model.getChilometraggioProperty()
                    .getValue());
            valueModalitaNoleggioLabel.setText(model.getModalitàProperty()
                    .getValue());
            valueClasseAutovetturaLabel.setText(model.getClasse().toString());
            kmGiornoLabel.setText(Float.toString(model
                    .getChilometriGiorno()));
        } else {
            valueCodiceTariffaLabel.setText("");
            valueImportoUnitarioLabel.setText("");
            valueModalitaKmLabel.setText("");
            valueModalitaNoleggioLabel.setText("");
            valueClasseAutovetturaLabel.setText("");
            kmGiornoLabel.setText("");
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

        this.classeRicercaText.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
    private void indietroButton() {
        this.indietroButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                SimpleRequest request = new SimpleRequest(
                        "PannelloAmministratore", RequestType.UI_VIEW);
                fc.processRequest(request);
            }
        });
    }

    @FXML
    private void confermaModifiche() {
        this.confermaModifiche.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (ShowAlert.showMessage("Confermi le modifiche?",
                        AlertType.CONFIRMATION)) {
                    modifica();
                }
            }
        });
    }

    @FXML
    public final void annullaRicercaButton() {

        this.annullaRicercaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                SimpleRequest refresh =
                        new SimpleRequest("riepilogoTariffe",
                        RequestType.UI_VIEW);

                fc.processRequest(refresh);
            }
        });
    }

    /**
     * Mostra l'elenco di tariffe presenti nel sistema.
     * */
    public final void riepilogo() {
        SimpleRequest request = new SimpleRequest("riepilogoTariffe",
                RequestType.SERVICE);

        ComplexResponse<TariffaTO> response =
                (ComplexResponse<TariffaTO>) fc.processRequest(request);

        for (TariffaTO c : response.getParameters()) {

            ClasseAutovetturaModel classe = getClasseAutovettura(
                    c.classeAutovetturaId);

            TariffaModel model = new TariffaModel(c.importoUnitario,
                    c.modalità, classe,
                    c.chilometraggio, c.id, c.chilometriGiorno);
            list.add(model);
        }
        infoTableView.setItems(null);
        infoTableView.layout();
        infoTableView.setItems(FXCollections.observableArrayList(
                list));

    }

    /**
     * Gestisci l'eliminazione di una tariffa.
     * */
    public final void eliminare() {

        TariffaModel selectedTariffa = infoTableView.getSelectionModel()
                .getSelectedItem();

        if (selectedTariffa == null) {
            ShowAlert.showMessage("Nessuna tariffa selezionata",
                    AlertType.WARNING);
        } else {

            if (ShowAlert.showMessage("Confermare l'eliminazione?",
                    AlertType.CONFIRMATION)) {

                // richiesta
                TariffaTO to = new TariffaTO();

                to.id = selectedTariffa.getId();

                ComplexRequest<TariffaTO> request =
                        new ComplexRequest<TariffaTO>(
                        "eliminareTariffa", RequestType.SERVICE);

                request.addParameter(to);

                // risposta
                SimpleResponse response = (SimpleResponse)
                        fc.processRequest(request);

                // se la risposta va a buon fine aggiorna la tabella
                if (response.getResponse()) {

                    int selectedIndex = infoTableView.getSelectionModel()
                            .getSelectedIndex();
                    infoTableView.getItems().remove(selectedIndex);

                    ShowAlert.showMessage("Tariffa eliminata con successo",
                            AlertType.INFORMATION);
                } else {
                    ShowAlert.showMessage("Impossibile eliminare la tariffa.",
                            AlertType.INFORMATION);
                }
            }
        }
    }

    /**
     * Gestisce la ricerca di una tariffa.
     * */
    private void ricerca() {
        String param = classeRicercaText.getText().trim();

        if (!param.isEmpty()) {

            annullaRicercaButton.setVisible(true);

            infoTableView.setItems(null);
            infoTableView.layout();

            ObservableList<TariffaModel> data = GetCommonData.getTariffeClasse(param);

            if (data != null) {
                infoTableView.setItems(FXCollections.observableArrayList(
                    data));
            }
        } else {
            ShowAlert.showMessage("Inserire il parametro di ricerca",
                    AlertType.WARNING);
        }
    }

    /**
     * Mostra i text field per la modifica.
     * */
    private void showModifica() {
        TariffaModel selectedTariffa =
                infoTableView.getSelectionModel().getSelectedItem();

        if (selectedTariffa == null) {
            ShowAlert.showMessage("Nessuna tariffa selezionato",
                    AlertType.WARNING);
        } else {
            showClassiAutovetture();
            importoModifica.setVisible(true);
            kmModifica.setVisible(true);
            noleggioModifica.setVisible(true);
            classeModifica.setVisible(true);
            confermaModifiche.setVisible(true);
            kmGiornoText.setVisible(true);

            importoModifica.setText(
                    selectedTariffa.getImportoUnitario().toString());
            kmModifica.setValue(
                    selectedTariffa.getChilometraggioProperty().getValue());
            noleggioModifica.setValue(
                    selectedTariffa.getModalitàProperty().getValue());
            classeModifica.setValue(
                    selectedTariffa.getClasseAutovetturaProperty());
            kmGiornoText.setText(Float.toString(
                    selectedTariffa.getChilometriGiorno()));
          }
    }

    /**
     * Gestisce la modifica della tariffa.
     * */
    private void modifica() {
        TariffaModel selectedTariffa =
                infoTableView.getSelectionModel().getSelectedItem();

        String importoUnitario = importoModifica.getText().trim();
        String modalitàKm = kmModifica.getValue().trim();
        String modalitàNoleggio = noleggioModifica.getValue().trim();
        String kmGiorni = kmGiornoText.getText();

        ClasseAutovetturaModel classe =
                classeModifica.getValue();

        if (!ValidaDatiTariffa.isEmpty(importoUnitario, modalitàKm,
                modalitàNoleggio, classe)
                && ValidaDatiTariffa.isValidInput(importoUnitario, kmGiorni)) {
                TariffaTO to = new TariffaTO();

                to.chilometraggio = modalitàKm;
                to.importoUnitario = Float.parseFloat(importoUnitario);
                to.modalità = modalitàNoleggio;
                to.classeAutovetturaId = classe.getId().getValue();
                to.id = selectedTariffa.getId();
                to.chilometriGiorno = Float.parseFloat(kmGiorni);

                ComplexRequest<TariffaTO> request =
                        new ComplexRequest<TariffaTO>(
                        "modificareTariffa", RequestType.SERVICE);

                request.addParameter(to);

                SimpleResponse response =
                        (SimpleResponse) fc.processRequest(request);

                if (response.getResponse()) {
                    SimpleRequest refresh =
                            new SimpleRequest("riepilogoTariffe",
                            RequestType.UI_VIEW);

                    fc.processRequest(refresh);
                } else {
                    ShowAlert.showMessage("Dati inseriti non accettati, riprovare!",
                            AlertType.WARNING);
                }
            }
    }

    /**
     * Ritorna le informazioni su una singola classe.
     * */
    private ClasseAutovetturaModel getClasseAutovettura(String id) {
        return GetCommonData.getClasseAutovettura(id);
    }

    public final void setList(final ObservableList<TariffaModel> list) {
        this.list = list;
    }

    /**
     * Mostra tutte le classi autovettura disponibili.
     * */
    private void showClassiAutovetture() {
        classeModifica.setItems(
                GetCommonData.getRiepilogoClassiAutovettura());
    }
}
