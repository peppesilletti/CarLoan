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
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.ClasseAutovetturaTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.ComplexResponse;
import transferObjects.response.SimpleResponse;
import view.controller.ShowAlert;
import view.model.ClasseAutovetturaModel;

/**
 * Controller per l'interfaccia RiepilogoClassiAutovetture.fxml .
 * */
public class RiepilogoClassiAutovettureController implements Initializable {

    @FXML
    private TableView<ClasseAutovetturaModel>  infoTableView;

    @FXML
    private TableColumn<ClasseAutovetturaModel, String> nomeClasseColonna;


    @FXML
    private Label valueNomeClasseLabel;

    @FXML
    private Label valueNumeroPorteLabel;

    @FXML
    private Label valueTipoCambioLabel;

    @FXML
    private Label valueAriaCondizLabel;

    @FXML
    private Label valueNumeroPostiLabel;

    @FXML
    private Button modificaButton;

    @FXML
    private Button eliminaButton;

    @FXML
    private Button indietroButton;

    @FXML
    private TextField numPostiModifica;

    @FXML
    private TextField numPorteModifica;

    @FXML
    private TextField nomeModifica;

    @FXML
    private ComboBox<String> ariaCondizionataModifica;

    @FXML
    private ComboBox<String> tipoCambioModifica;

    @FXML
    private Button confermaModifiche;

    private ObservableList<ClasseAutovetturaModel> list;

    private FrontControllerInt fc;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        fc = new FrontController();

        setList(FXCollections.observableArrayList());

        nomeClasseColonna
                .setCellValueFactory(
                        new PropertyValueFactory<ClasseAutovetturaModel, String>("nome"));

        riepilogo();

        // Clear classe autovettura details
        infoClasseAutovettura(null);

        infoTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) ->
                        infoClasseAutovettura(newValue));

    }

    /**
     * Metodo che permette di visualizzare le informazioni di una classe una
     * volta cliccatoci sopra.
     * @param model
     *      Dati classe autovettura.
     * */
    public final void infoClasseAutovettura(
            final ClasseAutovetturaModel model) {

        if (model != null) {
            valueNomeClasseLabel.setText(model.getNomeProperty().getValue());

            if (model.getAriaCondizionata().getValue() == 1) {
                valueAriaCondizLabel.setText("SI");
            } else {
                valueAriaCondizLabel.setText("NO");
            }
            valueNumeroPorteLabel.setText(Integer.toString(model
                    .getNumeroPorteProperty().getValue()));
            valueNumeroPostiLabel.setText(Integer.toString(model
                    .getNumeroPostiProperty().getValue()));
            valueTipoCambioLabel.setText(model.getTipoCambioProperty()
                    .getValue());
        } else {
            valueNomeClasseLabel.setText("");
            valueAriaCondizLabel.setText("");
            valueNumeroPorteLabel.setText("");
            valueNumeroPostiLabel.setText("");
            valueTipoCambioLabel.setText("");
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
     * Mostra l'elenco di classi autovetture presenti nel sistema.
     * */
    public final void riepilogo() {

        SimpleRequest request = new SimpleRequest(
                "riepilogoClasseAutovettura", RequestType.SERVICE);

        ComplexResponse<ClasseAutovetturaTO> response =
                (ComplexResponse<ClasseAutovetturaTO>) fc
                .processRequest(request);

        for (ClasseAutovetturaTO c : response.getParameters()) {
            ClasseAutovetturaModel model = new ClasseAutovetturaModel(
                    c.nome, c.tipoCambio, c.numeroPorte,
                    c.numeroPosti, c.ariaCondizionata, c.id);
            list.add(model);
        }

        infoTableView.setItems(null);
        infoTableView.layout();
        infoTableView.setItems(FXCollections.observableArrayList(list));

    }

    /**
     * Gestisce l'eliminazione classe autovettura.
     * */
    public final void eliminare() {

        ClasseAutovetturaModel selectedClasse =
                infoTableView.getSelectionModel().getSelectedItem();

        if (selectedClasse == null) {
            ShowAlert.showMessage("Nessuna classe selezionata",
                    AlertType.WARNING);
        } else {

            if (ShowAlert.showMessage("Confermi l'eliminazione?",
                    AlertType.CONFIRMATION)) {

            ClasseAutovetturaTO to = new ClasseAutovetturaTO();
            to.id = selectedClasse.getId().getValue();

            ComplexRequest<ClasseAutovetturaTO> request =
                    new ComplexRequest<ClasseAutovetturaTO>(
                    "eliminareClasseAutovettura", RequestType.SERVICE);
            request.addParameter(to);

            // risposta
            SimpleResponse response =
                    (SimpleResponse) fc.processRequest(request);

            // se la risposta va a buon fine aggiorna la tabella
            if (response.getResponse()) {

                int selectedIndex = infoTableView.getSelectionModel()
                        .getSelectedIndex();
                infoTableView.getItems().remove(selectedIndex);

                ShowAlert.showMessage("Classe eliminata con successo!",
                        AlertType.INFORMATION);
            } else {
                ShowAlert.showMessage("Impossibile eliminare la classe autovettura.",
                        AlertType.WARNING);
              }
            }
        }
    }

    /**
     * Mostra i text field per effettuare le modifiche.
     * */
    private void showModifica() {
        ClasseAutovetturaModel selectedClasse =
                infoTableView.getSelectionModel().getSelectedItem();

        if (selectedClasse == null) {
            ShowAlert.showMessage("Nessuna classe selezionato",
                    AlertType.WARNING);
        } else {
            nomeModifica.setVisible(true);
            numPorteModifica.setVisible(true);
            numPostiModifica.setVisible(true);
            tipoCambioModifica.setVisible(true);
            ariaCondizionataModifica.setVisible(true);
            confermaModifiche.setVisible(true);

            nomeModifica.setText(selectedClasse.getNome());
            numPorteModifica.setText(selectedClasse.
                    getNumeroPorteProperty().getValue().toString());
            numPostiModifica.setText(selectedClasse.
                    getNumeroPostiProperty().getValue().toString());
            tipoCambioModifica.setValue(
                    selectedClasse.getTipoCambioProperty().getValue());

            String aria = null;

            if (selectedClasse.getAriaCondizionata().getValue() == 0) {
                aria = "NO";
            } else  {
                aria = "SI";
            }

            ariaCondizionataModifica.setValue(aria);
        }
    }

    /**
     * Gestisce la modifica.
     * */
    private void modifica() {

        ClasseAutovetturaModel selectedClasse =
                infoTableView.getSelectionModel().getSelectedItem();

        String nome = nomeModifica.getText().trim();
        String numeroPorte = numPorteModifica.getText().trim();
        String numeroPosti = numPostiModifica.getText().trim();
        String ariaCondizionata = ariaCondizionataModifica.getValue().trim();
        String tipoCambio = tipoCambioModifica.getValue().trim();

        if (!ValidaDatiClasseAutovettura.isEmptyCheck(nome, numeroPorte,
                numeroPosti, ariaCondizionata, tipoCambio)
                && ValidaDatiClasseAutovettura.isValidInput(
                        nome, numeroPorte, numeroPosti, ariaCondizionata,
                        tipoCambio)) {

            ClasseAutovetturaTO to = new ClasseAutovetturaTO();
            if (ariaCondizionata.equals("SI")) {
                to.ariaCondizionata = 1;
            } else {
                to.ariaCondizionata = 0;
            }

            to.nome = nome;
            to.numeroPorte = Integer.parseInt(numeroPorte);
            to.numeroPosti = Integer.parseInt(numeroPosti);
            to.tipoCambio = tipoCambio;
            to.id = selectedClasse.getId().get();

            ComplexRequest<ClasseAutovetturaTO> request =
                    new ComplexRequest<ClasseAutovetturaTO>(
                    "modificareClasseAutovettura", RequestType.SERVICE);

            request.addParameter(to);

            SimpleResponse response =
                    (SimpleResponse) fc.processRequest(request);

            if (response.getResponse()) {
                SimpleRequest refresh =
                        new SimpleRequest("riepilogoClasseAutovettura",
                        RequestType.UI_VIEW);

                fc.processRequest(refresh);

            } else {
                ShowAlert.showMessage("Dati inseriti non accettati, riprovare!"
                        , AlertType.ERROR);
            }
        }
    }

    public final void setList(final ObservableList<ClasseAutovetturaModel> list) {
        this.list = list;
    }

}
