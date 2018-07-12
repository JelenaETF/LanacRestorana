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
import javafx.stage.Stage;
import model.Dobavljac;
import repository.DobavljacRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class RadSaDobavljacimaSceneController implements Initializable {

    private DobavljacRepository dobavljacRepository = new DobavljacRepository();

    @FXML
    private TableView<Dobavljac> dobavljaciTable  = new TableView<>();
    @FXML
    private TableColumn<String, Dobavljac> JIB = new TableColumn<>();
    @FXML
    private TableColumn<String, Dobavljac> telefon = new TableColumn<>();
    @FXML
    private TableColumn<String, Dobavljac> adresa = new TableColumn<>();

    public static ObservableList<Dobavljac> dobavljaciList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       generateTable();
    }

    private void generateTable(){
        JIB.setCellValueFactory(new PropertyValueFactory<>("JIB"));
        telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        adresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));

        dobavljaciList.addAll(dobavljacRepository.vratiSveDobavljace());
        dobavljaciTable.setItems(dobavljaciList);
    }

    @FXML
    public void dodajDobavljaca(){
        try {
            DodavanjeDobavljacaSceneController.setTabelaZaDodavanje(dobavljaciTable);
            Parent root = FXMLLoader.load(getClass().getResource("view/DodavanjeDobavljacaScene.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
                e.printStackTrace();
        }
    }

    @FXML
    public void izmijeniDobavljaca(){
        if(dobavljaciTable.getSelectionModel().getSelectedItem()==null){
            MessageBox.display("Morate odabrati dobavljaca za izmjenu");
        }else {
            try {
                IzmjenaDobavljacaSceneController.setTabelaZaIzmjenu(dobavljaciTable);
                IzmjenaDobavljacaSceneController.setZaIzmjenu(dobavljaciTable.getSelectionModel().getSelectedItem());
                Parent root = FXMLLoader.load(getClass().getResource("view/IzmjenaDobavljacaScene.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void ukloniDobavljaca(){
        if(dobavljaciTable.getSelectionModel().getSelectedItem()==null){
            MessageBox.display("Morate odabrati dobavljaca kojeg zelite ukloniti");
        }else {
            if(dobavljacRepository.obrisiDobavljaca(dobavljaciTable.getSelectionModel().getSelectedItem().getDobavljacId())) {
                dobavljaciList.remove(dobavljaciTable.getSelectionModel().getSelectedItem());
                dobavljaciTable.refresh();
                dobavljaciTable.setItems(dobavljaciList);
                MessageBox.display("Uspjesno uklonjen dobavljac");
            }else
                MessageBox.display("Nije moguce ukloniti dobavljaca");
        }
    }

    @FXML
    public void idiNazad(){
        Main.primaryStage.setScene(LoginSceneController.adminScene);
    }

    public static ObservableList<Dobavljac> getDobavljaciList() {
        return dobavljaciList;
    }

    public static void setDobavljaciList(ObservableList<Dobavljac> dobavljaciList) {
        RadSaDobavljacimaSceneController.dobavljaciList = dobavljaciList;
    }
}
