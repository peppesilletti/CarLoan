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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import presentation.FrontController;
import presentation.FrontControllerInt;
import transferObjects.entitiesTO.AgenziaTO;
import transferObjects.entitiesTO.ExtraAgenziaTO;
import transferObjects.entitiesTO.ExtraTO;
import transferObjects.request.ComplexRequest;
import transferObjects.request.RequestType;
import transferObjects.request.SimpleRequest;
import transferObjects.response.ComplexResponse;
import transferObjects.response.SimpleResponse;
import view.controller.ShowAlert;
import view.model.ExtraAgenziaModel;

/**
 * Controller per l'interfaccia RiepilogoExtraAgenzia.fxml .
 * */
public class RiepilogoExtraAgenziaController implements Initializable {

    @FXML
    private Button inserireButton;

    @FXML
    private Button rimuoviButton;

    @FXML
    private ComboBox<ExtraAgenziaModel> extraComboBox;

    @FXML
    private TableColumn<ExtraAgenziaModel, String> extraColonna;

    @FXML
    private TableColumn<ExtraAgenziaModel, String> descrizioneColonna;

    @FXML
    private TableView<ExtraAgenziaModel> infoTableView;

    @FXML
    private Label hiddenAgenzia;

    FrontControllerInt fc;

    private ObservableList<ExtraAgenziaModel> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fc = new FrontController();

        setList(FXCollections.observableArrayList());

        extraColonna
        .setCellValueFactory(new PropertyValueFactory<ExtraAgenziaModel, String>(
                "nome"));

        descrizioneColonna
        .setCellValueFactory(new PropertyValueFactory<ExtraAgenziaModel, String>(
                "descrizione"));

        showExtra();
    }

    public void setData(String id) {
        hiddenAgenzia.setText(id);
        riepilogo();
    }

    @FXML
    public final void rimuoviButton() {

        this.rimuoviButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                rimuovi();
            }
        });
    }

    @FXML
    public final void inserireButton() {

        this.inserireButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                inserire();
            }
        });
    }

    /**
     * Mostra tutti gli extra di un'agenzia.
     * */
    private void riepilogo() {
        ComplexRequest<AgenziaTO> request =
                new ComplexRequest<AgenziaTO>("riepilogoExtraAgenzia",
                        RequestType.SERVICE);

        AgenziaTO agenzia = new AgenziaTO();
        agenzia.id = hiddenAgenzia.getText();

        request.addParameter(agenzia);

        ComplexResponse<ExtraAgenziaTO> response = (ComplexResponse<ExtraAgenziaTO>) fc
                .processRequest(request);

        for (ExtraAgenziaTO c : response.getParameters()) {
            ExtraAgenziaModel extra = new ExtraAgenziaModel(
                    c.nome, c.descrizione, c.id);
            list.add(extra);
        }

        infoTableView.setItems(null);
        infoTableView.layout();
        infoTableView.setItems(FXCollections.observableArrayList(list));
    }

    /**
     * Gestisce l'eliminazione di un extra agenzia.
     * */
    private void rimuovi() {
        ExtraAgenziaModel selectedExtra =
                infoTableView.getSelectionModel().getSelectedItem();

        if (selectedExtra != null) {
            if (ShowAlert.showMessage("Confermare l'eliminazione?",
                    AlertType.CONFIRMATION)) {

                ComplexRequest<ExtraAgenziaTO> request =
                       new  ComplexRequest<ExtraAgenziaTO>(
                               "eliminareExtraAgenzia", RequestType.SERVICE);

                ExtraAgenziaTO extra = new ExtraAgenziaTO();
                extra.id = selectedExtra.getIdProperty().get();
                extra.agenziaId = hiddenAgenzia.getText();

                request.addParameter(extra);

                SimpleResponse response =
                        (SimpleResponse) fc.processRequest(request);

                if (response.getResponse()) {

                    ShowAlert.showMessage("Extra eliminato con successo",
                            AlertType.INFORMATION);

                    int selectedIndex = infoTableView.getSelectionModel()
                            .getSelectedIndex();
                    infoTableView.getItems().remove(selectedIndex);
                }
            }
        } else {
            ShowAlert.showMessage("Nessun extra selezionato",
                    AlertType.WARNING);
        }
    }

    /**
     * Gestisce l'inserimento di un extra agenzia.
     * */
    private void inserire() {

        ExtraAgenziaModel extraModel = extraComboBox.getValue();

        if (extraModel != null) {

            if (ShowAlert.showMessage("Confermare l'inserimento?",
                    AlertType.CONFIRMATION)) {
                ComplexRequest<ExtraAgenziaTO> request =
                        new  ComplexRequest<ExtraAgenziaTO>(
                                "inserireExtraAgenzia", RequestType.SERVICE);

                 ExtraAgenziaTO extra = new ExtraAgenziaTO();
                 extra.id = extraModel.getIdProperty().get();
                 extra.agenziaId = hiddenAgenzia.getText();

                 request.addParameter(extra);

                 SimpleResponse response =
                         (SimpleResponse) fc.processRequest(request);

                 if (response.getResponse()) {
                     list.removeAll(list);
                     riepilogo();
                 } else {
                     ShowAlert.showMessage("Extra già inserito!",
                             AlertType.WARNING);
                 }
            }
        } else {
            ShowAlert.showMessage("Selezionare un extra!", AlertType.WARNING);
        }

    }

    /**
     * Mostra tutti gli extra nel sistema.
     * */
    private void showExtra() {

        ObservableList<ExtraAgenziaModel> data =
                FXCollections.observableArrayList();

        SimpleRequest request = new SimpleRequest(
                "riepilogoExtra", RequestType.SERVICE);

        ComplexResponse<ExtraTO> response =
                (ComplexResponse<ExtraTO>) fc.processRequest(request);

        for (ExtraTO c : response.getParameters()) {
            ExtraAgenziaModel model = new ExtraAgenziaModel(c.nome,
                    c.descrizione, c.id);
            data.add(model);
        }

        extraComboBox.setItems(data);
    }

    public final void setList(final ObservableList<ExtraAgenziaModel> list) {
        this.list = list;
    }

}
