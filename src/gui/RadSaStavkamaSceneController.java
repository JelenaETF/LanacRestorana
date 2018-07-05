package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.modelCustom.StavkaRestoranCustom;
import repository.repositoryCustom.StavkaRestoranRepositoryCustom;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class RadSaStavkamaSceneController implements Initializable {

    @FXML
    private TableView<StavkaRestoranCustom> stavkeTable = new TableView<>();
    @FXML
    private TableColumn<String, StavkaRestoranCustom> nazivColumn = new TableColumn<>();
    @FXML
    private TableColumn<String, StavkaRestoranCustom> kategorijaColumn = new TableColumn<>();
    @FXML
    private TableColumn<BigDecimal, StavkaRestoranCustom> cijenaColumn = new TableColumn<>();

    private StavkaRestoranRepositoryCustom stavkaRestoranRepositoryCustom = new StavkaRestoranRepositoryCustom();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateTable();
    }

    public ObservableList<StavkaRestoranCustom> listaStavki = FXCollections.observableArrayList();

    private void generateTable(){
        listaStavki.setAll(stavkaRestoranRepositoryCustom.vratiStavkeUPonudiIzRestorana(LoginSceneController.getRestoranId()));
        nazivColumn.setCellValueFactory(new PropertyValueFactory<>("nazivStavke"));
        kategorijaColumn.setCellValueFactory(new PropertyValueFactory<>("nazivKategorije"));
        cijenaColumn.setCellValueFactory(new PropertyValueFactory<>("cijena"));

        stavkeTable.setItems(listaStavki);
    }

    @FXML
    public void dodajStavku(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("view/DodavanjeNoveStavkeScene.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void izmijeniStavku(){}

    @FXML
    public void izbaciIzPonude(){}

    @FXML
    public void pregledajSastav(){}

    @FXML
    public void idiNazad(){
        Main.primaryStage.setScene(LoginSceneController.adminScene);
    }




}
