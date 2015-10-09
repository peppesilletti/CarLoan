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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.ExtraTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.ComplexResponse;
import transferObjects.response.SimpleResponse;
import view.controller.ShowAlert;
import view.model.ExtraModel;

/**
 * Controller per l'interfaccia RiepilogoExtra.fxml .
 * */
public class RiepilogoExtraController implements Initializable {

    @FXML
    private TableView<ExtraModel>           infoTableView;

    @FXML
    private TableColumn<ExtraModel, String> nomeExtraColonna;

    @FXML
    private Label valueNomeExtraLabel;

    @FXML
    private Label valuePrezzoLabel;

    @FXML
    private Button modificaButton;

    @FXML
    private Button eliminaButton;

    @FXML
    private Button indietroButton;

    @FXML
    private TextField nomeModifica;

    @FXML
    private TextField prezzoModifica;

    @FXML
    private TextField classeRicercaText;

    @FXML
    private TextArea valueDescrizioneLabel;

    @FXML
    private Button confermaModifiche;


    private ObservableList<ExtraModel>      list;

    private FrontControllerInt              fc;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        fc = new FrontController();

        setList(FXCollections.observableArrayList());

        nomeExtraColonna
                .setCellValueFactory(new PropertyValueFactory<ExtraModel, String>(
                        "nome"));

        riepilogo();

        // Clear extra details
        infoClasseAutovettura(null);

        infoTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> infoClasseAutovettura(newValue));

    }

    /**
     * Metodo che permette di visualizzare le informazioni di un extra
     * cliccatoci sopra.
     * */
    public final void infoClasseAutovettura(final ExtraModel model) {
        if (model != null) {
            valueNomeExtraLabel.setText(model.getNomeProperty().getValue());
            valuePrezzoLabel.setText(Float.toString(model.getPrezzoProperty()
                    .getValue()));
            valueDescrizioneLabel.setText(model.getDescrizioneProperty()
                    .getValue());
            valueDescrizioneLabel.setEditable(false);
        } else {
            valueNomeExtraLabel.setText("");
            valuePrezzoLabel.setText("");
            valueDescrizioneLabel.setText("");
            valueDescrizioneLabel.setEditable(false);
        }
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

    /**
     * Mostra l'elenco di extra presenti nel sistema.
     * */
    public final void riepilogo() {

        SimpleRequest request = new SimpleRequest(
                "riepilogoExtra", RequestType.SERVICE);

        ComplexResponse<ExtraTO> response = (ComplexResponse<ExtraTO>) fc
                .processRequest(request);

        for (ExtraTO c : response.getParameters()) {
            ExtraModel model = new ExtraModel(c.nome, c.prezzo,
                    c.descrizione, c.id);
            list.add(model);
        }

        infoTableView.setItems(null);
        infoTableView.layout();
        infoTableView.setItems(FXCollections.observableArrayList(list));

    }


    /**
     * Mostra i text field per effettuare le modifiche.
     * */
    private void showModifica() {
        ExtraModel selectedExtra =
                infoTableView.getSelectionModel().getSelectedItem();

        if (selectedExtra == null) {
            ShowAlert.showMessage("Nessuna classe selezionato",
                    AlertType.WARNING);
        } else {
            nomeModifica.setVisible(true);
            prezzoModifica.setVisible(true);
            valueDescrizioneLabel.setEditable(true);
            confermaModifiche.setVisible(true);
            nomeModifica.setText(selectedExtra.getNome());
            prezzoModifica.setText(selectedExtra.getPrezzoProperty().
                    getValue().toString());
        }
    }

    /**
     * Gestisce la modifica di un extra.
     * */
    public final void modifica() {
        ExtraModel selectedModel =
                infoTableView.getSelectionModel().getSelectedItem();

        String nome = nomeModifica.getText();
        String prezzo = prezzoModifica.getText();
        String descrizione = valueDescrizioneLabel.getText();
        String id = selectedModel.getIdProperty().getValue();

        if (!ValidaDatiExtra.isEmpty(nome, prezzo, descrizione)
                && ValidaDatiExtra.isValidInput(nome, prezzo, descrizione)) {
            ExtraTO to = new ExtraTO();

            to.nome = nome;
            to.prezzo = Float.parseFloat(prezzo);
            to.descrizione = descrizione;
            to.id = id;

            ComplexRequest<ExtraTO> request = new ComplexRequest<ExtraTO>(
                    "modificareExtra", RequestType.SERVICE);

            request.addParameter(to);

            SimpleResponse response =
                    (SimpleResponse) fc.processRequest(request);

            if (response.getResponse()) {
                SimpleRequest refresh =
                        new SimpleRequest("riepilogoExtra",
                        RequestType.UI_VIEW);

                fc.processRequest(refresh);
            } else {
                ShowAlert.showMessage("Dati inseriti non accettati, riprovare!",
                        AlertType.WARNING);
            }
        }
    }

    /**
     * Eliminazione extra.
     * */
    public final void eliminare() {

        ExtraModel selectedExtra =
                infoTableView.getSelectionModel().getSelectedItem();

        if (selectedExtra != null) {
            if (ShowAlert.showMessage("Confermi l'eliminazione?",
                    AlertType.CONFIRMATION)) {

                // richiesta
                ExtraTO to = new ExtraTO();
                to.id = selectedExtra.getIdProperty().getValue();
                ComplexRequest<ExtraTO> request = new ComplexRequest<ExtraTO>(
                        "eliminareExtra", RequestType.SERVICE);
                request.addParameter(to);

                // risposta
                SimpleResponse response = (SimpleResponse)
                        fc.processRequest(request);

                // se la risposta va a buon fine aggiorna la tabella
                if (response.getResponse()) {

                    ShowAlert.showMessage("Extra eliminato con successo",
                            AlertType.INFORMATION);

                    int selectedIndex = infoTableView.getSelectionModel()
                            .getSelectedIndex();
                    infoTableView.getItems().remove(selectedIndex);
                } else {
                    ShowAlert.showMessage("Impossibile eliminare l'extra!",
                            AlertType.INFORMATION);
                }
            }
        } else {
            ShowAlert.showMessage("Nessun extra selezionato",
                    AlertType.WARNING);
        }
    }

    public final void setList(final ObservableList<ExtraModel> list) {
        this.list = list;
    }

}
