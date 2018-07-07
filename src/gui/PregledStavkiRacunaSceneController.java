package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.modelCustom.StavkaRacunCustom;
import repository.repositoryCustom.StavkaRacunRepositoryCustom;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;


public class PregledStavkiRacunaSceneController implements Initializable {

    private StavkaRacunRepositoryCustom stavkaRacunRepositoryCustom = new StavkaRacunRepositoryCustom();
    private static Integer racunId;

    @FXML
    private TableView<StavkaRacunCustom> stavkeRacunaTable = new TableView<>();
    @FXML
    private TableColumn<String, StavkaRacunCustom> naziv = new TableColumn<>();
    @FXML
    private TableColumn<BigDecimal, StavkaRacunCustom> cijena = new TableColumn<>();
    @FXML
    private TableColumn<Integer, StavkaRacunCustom> kolicina = new TableColumn<>();

    private ObservableList<StavkaRacunCustom> stavkeRacunaList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateTable();
    }

    private void generateTable(){
        naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        cijena.setCellValueFactory(new PropertyValueFactory<>("cijenaKupljenog"));
        kolicina.setCellValueFactory(new PropertyValueFactory<>("kolicinaKupljenog"));

        stavkeRacunaList.addAll(stavkaRacunRepositoryCustom.vratiStavkeSaRacuna(racunId));
        stavkeRacunaTable.setItems(stavkeRacunaList);
    }

    public static Integer getRacunId() {
        return racunId;
    }

    public static void setRacunId(Integer racunId) {
        PregledStavkiRacunaSceneController.racunId = racunId;
    }
}
