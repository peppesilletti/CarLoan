package view.controller.operatore;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.ClienteTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.ComplexResponse;
import transferObjects.response.SimpleResponse;
import utility.DateUtil;
import view.controller.GetCommonData;
import view.controller.ShowAlert;
import view.model.ClienteModel;

public class RiepilogoClientiController implements Initializable {

    @FXML
    private Label valueNomeLabel;

    @FXML
    private Label valueDataNascitaLabel;

    @FXML
    private TableColumn<ClienteModel, String> codiceFiscaleColonna;

    @FXML
    private Button modificaButton;

    @FXML
    private TableColumn<ClienteModel, String> cognomeColonna;

    @FXML
    private Label informazioniLabel;

    @FXML
    private TableColumn<ClienteModel, String> nomeColonna;

    @FXML
    private Label valueIndirizzoLabel;

    @FXML
    private Label valueCodiceFiscaleLabel;

    @FXML
    private TableView<ClienteModel> infoTableView;

    @FXML
    private Label valueNumeroPatenteLabel;

    @FXML
    private Button ricercaButton;

    @FXML
    private TextField codiceFiscaleText;

    @FXML
    private Button indietroButton;

    @FXML
    private Label valueCognomeLabel;

    @FXML
    private Label valueComuneNascitaLabel;

    @FXML
    private Label valueComuneResidenzaLabel;

    @FXML
    private TextField nomeModifica;

    @FXML
    private TextField cognomeModifica;

    @FXML
    private TextField dataNascitaModifica;

    @FXML
    private TextField comuneNascitaModifica;

    @FXML
    private TextField comuneResidenzaModifica;

    @FXML
    private TextField indirizzoModifica;

    @FXML
    private TextField codiceFiscaleModifica;

    @FXML
    private TextField numPatenteModifica;

    @FXML
    private Button confermaModifiche;

    @FXML
    private Button annullaRicercaButton;

    private FrontControllerInt fc;

    private ObservableList<ClienteModel> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fc = new FrontController();

        setList(FXCollections.observableArrayList());

        nomeColonna
        .setCellValueFactory(new PropertyValueFactory<ClienteModel, String>(
                "nome"));

        cognomeColonna
        .setCellValueFactory(new PropertyValueFactory<ClienteModel, String>(
                "cognome"));

        codiceFiscaleColonna
        .setCellValueFactory(new PropertyValueFactory<ClienteModel, String>(
                "codiceFiscale"));

        riepilogo();

        // Clear operatore details
        infoCliente(null);

        infoTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> infoCliente(newValue));

    }

    /**
     * Metodo che permette di visualizzare le informazioni di un cliente
     * cliccatoci sopra.
     * */
    public void infoCliente(ClienteModel model) {
        if (model != null) {
            valueNomeLabel.setText(model.getNome());
            valueCognomeLabel.setText(model.getCognome());
            valueDataNascitaLabel.setText(model.getDataNascitaProperty().get());
            valueComuneNascitaLabel.setText(
                    model.getComuneNascitaProperty().get());
            valueComuneResidenzaLabel.setText(
                    model.getComuneResidenzaProperty().get());
            valueIndirizzoLabel.setText(
                    model.getIndirizzoProperty().get());
            valueCodiceFiscaleLabel.setText(
                    model.getCodiceFiscaleProperty().get());
            valueNumeroPatenteLabel.setText(
                    model.getNumPatenteGuidaProperty().get());

        } else {
            valueNomeLabel.setText("");
            valueCognomeLabel.setText("");
            valueDataNascitaLabel.setText("");
            valueComuneNascitaLabel.setText("");
            valueComuneResidenzaLabel.setText("");
            valueIndirizzoLabel.setText("");
            valueCodiceFiscaleLabel.setText("");
            valueNumeroPatenteLabel.setText("");

        }
    }

    @FXML
    private void indietroButton() {
        this.indietroButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                SimpleRequest request = new SimpleRequest("PannelloOperatore",
                        RequestType.UI_VIEW);
                fc.processRequest(request);
            }
        });
    }

    @FXML
    private void modificaButton() {
        this.modificaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showModifica();
            }
        });
    }

    @FXML
    private void confermaModifiche() {
        this.confermaModifiche.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ShowAlert.showMessage("Confermare le modifiche?",
                        AlertType.CONFIRMATION)) {
                    modifica();
                }
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
                        "riepilogoClienti", RequestType.UI_VIEW);
                fc.processRequest(request);
            }
        });
    }

    @FXML
    private void ricercaButton() {
        this.ricercaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                 ricerca();
            }
        });

        this.codiceFiscaleText.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    ricerca();
                }
            }
        });

    }

    /**
     * Visualizza tutti i clienti presenti nel sistema.
     * */
    private void riepilogo() {
        SimpleRequest request =
                new SimpleRequest("riepilogoClienti",
                        RequestType.SERVICE);

        ComplexResponse<ClienteTO> response =
                (ComplexResponse<ClienteTO>) fc.processRequest(request);

        for (ClienteTO c : response.getParameters()) {
            ClienteModel model = new ClienteModel(
                    c.nome, c.cognome, DateUtil.toPrintDate(c.dataDiNascita),
                    c.comuneNascita, c.comuneResidenza,
                    c.indirizzo, c.codiceFiscale, c.idPatente);
            model.setIdProperty(c.id);
            list.add(model);
        }

        infoTableView.setItems(null);
        infoTableView.layout();
        infoTableView.setItems(FXCollections.observableArrayList(list));
    }

    /**
     * Gestisce la modifica di un cliente.
     * */
    private void modifica() {
        ClienteModel selectedCliente = infoTableView
                .getSelectionModel().getSelectedItem();

        String nome = nomeModifica.getText().trim();
        String cognome = cognomeModifica.getText().trim();
        String dataNascita = dataNascitaModifica.getText().trim();
        String comuneNascita = comuneNascitaModifica.getText().trim();
        String comuneResidenza = comuneResidenzaModifica.getText().trim();
        String indirizzo = indirizzoModifica.getText().trim();
        String codiceFiscale = codiceFiscaleModifica.getText().trim();
        String numPatente = numPatenteModifica.getText().trim();

        if (!ValidaDatiCliente.isEmptyCheck(nome, cognome, dataNascita, comuneNascita,
                comuneResidenza, indirizzo, codiceFiscale, numPatente)
            && ValidaDatiCliente.isValidInput(nome, cognome, dataNascita, comuneNascita,
                    comuneResidenza, indirizzo, codiceFiscale, numPatente)) {

            ComplexRequest<ClienteTO> request =
                    new ComplexRequest<ClienteTO>("modificareDatiCliente",
                            RequestType.SERVICE);

            ClienteTO cliente = new ClienteTO(nome, cognome, comuneNascita, comuneResidenza,
                    indirizzo, numPatente, codiceFiscale, dataNascita);

            cliente.id = selectedCliente.getIdProperty().get();

            request.addParameter(cliente);

            SimpleResponse response =
                    (SimpleResponse) fc.processRequest(request);

            if (response.getResponse()) {
                SimpleRequest refresh =
                        new SimpleRequest("riepilogoClienti",
                        RequestType.UI_VIEW);

                fc.processRequest(refresh);
            } else {
                ShowAlert.showMessage("Dati inseriti non accettati.",
                        AlertType.WARNING);
            }

        }
    }

    /**
     * Mostra le text field per effettuare le modifiche.
     * */
    private void showModifica() {
        ClienteModel selectedCliente = infoTableView
                .getSelectionModel().getSelectedItem();

        if (selectedCliente != null) {

            nomeModifica.setVisible(true);
            cognomeModifica.setVisible(true);
            dataNascitaModifica.setVisible(true);
            comuneNascitaModifica.setVisible(true);
            comuneResidenzaModifica.setVisible(true);
            indirizzoModifica.setVisible(true);
            codiceFiscaleModifica.setVisible(true);
            numPatenteModifica.setVisible(true);
            confermaModifiche.setVisible(true);

            nomeModifica.setText(selectedCliente.getNome());
            cognomeModifica.setText(selectedCliente.getCognome());
            dataNascitaModifica.setText(selectedCliente
                    .getDataNascitaProperty().get());
            comuneNascitaModifica.setText(selectedCliente
                    .getComuneNascitaProperty().get());
            comuneResidenzaModifica.setText(selectedCliente
                    .getComuneResidenzaProperty().get());
            indirizzoModifica.setText(selectedCliente
                    .getIndirizzoProperty().get());
            codiceFiscaleModifica.setText(selectedCliente
                    .getCodiceFiscale());
            numPatenteModifica.setText(selectedCliente
                    .getNumPatenteGuidaProperty().get());

        } else {
            ShowAlert.showMessage("Nessun cliente selezionato!",
                    AlertType.WARNING);
        }
    }

    /**
     * Gestisce la ricerca di un cliente.
     * */
    private void ricerca() {
        String param1 = codiceFiscaleText.getText().trim();

        if (!param1.isEmpty()) {

            annullaRicercaButton.setVisible(true);

            infoTableView.setItems(null);
            infoTableView.layout();
            infoTableView.setItems(FXCollections.observableArrayList(
                    GetCommonData.getClienteByCodFiscale(param1)));
        } else {
            ShowAlert.showMessage("Inserire il parametro di ricerca",
                    AlertType.WARNING);
        }
    }



    public void setList(ObservableList<ClienteModel> list) {
        this.list = list;
    }

}
