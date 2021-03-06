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
import model.modelCustom.NamirnicaStavkeCustom;
import model.modelCustom.StavkaRestoranCustom;
import repository.repositoryCustom.NamirnicaStavkeRepositoryCustom;
import repository.repositoryCustom.StavkaRestoranRepositoryCustom;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
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
    @FXML
    private TableColumn<Integer, StavkaRestoranCustom> idColumn = new TableColumn<>();

    private StavkaRestoranRepositoryCustom stavkaRestoranRepositoryCustom = new StavkaRestoranRepositoryCustom();
    private NamirnicaStavkeRepositoryCustom namirnicaStavkeRepositoryCustom = new NamirnicaStavkeRepositoryCustom();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateTable();
    }

    public static ObservableList<StavkaRestoranCustom> listaStavki = FXCollections.observableArrayList();

    private void generateTable(){
        listaStavki.setAll(stavkaRestoranRepositoryCustom.vratiStavkeUPonudiIzRestorana(LoginSceneController.getRestoranId()));
        nazivColumn.setCellValueFactory(new PropertyValueFactory<>("nazivStavke"));
        kategorijaColumn.setCellValueFactory(new PropertyValueFactory<>("nazivKategorije"));
        cijenaColumn.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("stavkaId"));

        stavkeTable.setItems(listaStavki);
        IzmjenaStavkeSceneController.tabelaZaIzmjenu = stavkeTable;
        DodavanjeNoveStavkeSceneController.tabelaZaDodavanje = stavkeTable;
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
    public void izmijeniStavku(){
        if(stavkeTable.getSelectionModel().getSelectedItem() == null)
            MessageBox.display("Morate odabrati stavku koju zelite izmijeniti");
        else{
            IzmjenaStavkeSceneController.setStavkaZaIzmjenu(stavkeTable.getSelectionModel().getSelectedItem());
            try {
                Parent root = FXMLLoader.load(getClass().getResource("view/IzmjenaStavkeScene.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void izbaciIzPonude() {
        if (stavkeTable.getSelectionModel().getSelectedItem() == null)
            MessageBox.display("Morate izabrati stavku koju izbacujete iz ponude");
        else {
            stavkaRestoranRepositoryCustom.izbaciStavkuIzPonude(stavkeTable.getSelectionModel().getSelectedItem().getStavkaId());
            listaStavki.remove(stavkeTable.getSelectionModel().getSelectedItem());
            stavkeTable.setItems(listaStavki);
        }
    }

    @FXML
    public void pregledajSastav() {
        if (stavkeTable.getSelectionModel().getSelectedItem() == null)
            MessageBox.display("Morate izabrati stavku ciji sastav zelite pregledati");
        else {
            NamirniceStavkeSceneController.stavkaCijeNamirniceSePrikazuju = stavkeTable.getSelectionModel().getSelectedItem();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("view/NamirniceStavkeScene.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void idiNazad(){
        Main.primaryStage.setScene(LoginSceneController.adminScene);
    }




}
