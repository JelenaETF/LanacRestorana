package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.modelCustom.NamirnicaStavkeCustom;
import model.modelCustom.StavkaRestoranCustom;
import repository.repositoryCustom.NamirnicaStavkeRepositoryCustom;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;


public class NamirniceStavkeSceneController implements Initializable {

    @FXML
    private TableView<NamirnicaStavkeCustom> namirniceStavkeTable = new TableView<>();
    @FXML
    private TableColumn<String, NamirnicaStavkeCustom> nazivColumn = new TableColumn<>();
    @FXML
    private TableColumn<BigDecimal, NamirnicaStavkeCustom> kolicinaUStavkiColumn = new TableColumn<>();

    private NamirnicaStavkeRepositoryCustom namirnicaStavkeRepositoryCustom = new NamirnicaStavkeRepositoryCustom();
    private ObservableList<NamirnicaStavkeCustom> namirnice = FXCollections.observableArrayList();
    public static StavkaRestoranCustom stavkaCijeNamirniceSePrikazuju;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateTable();
    }

    private void generateTable(){
        nazivColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        kolicinaUStavkiColumn.setCellValueFactory(new PropertyValueFactory<>("kolicinaUStavki"));

        namirnice.setAll(namirnicaStavkeRepositoryCustom.vratiListuNamirnicaZaStavkuNaOsnovuIdStavke(stavkaCijeNamirniceSePrikazuju.getStavkaId()));
        namirniceStavkeTable.setItems(namirnice);
    }
}
