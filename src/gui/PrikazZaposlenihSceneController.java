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
import model.modelCustom.ZaposleniRestoranCustom;
import repository.ZaposleniRestoranRepository;
import repository.repositoryCustom.ZaposleniRestoranRepositoryCustom;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrikazZaposlenihSceneController implements Initializable {

    private ZaposleniRestoranRepositoryCustom zaposleniRestoranRepositoryCustom = new ZaposleniRestoranRepositoryCustom();
    private ZaposleniRestoranRepository zaposleniRestoranRepository = new ZaposleniRestoranRepository();

    @FXML
    private TableView<ZaposleniRestoranCustom> zaposleniTable = new TableView<>();
    @FXML
    private TableColumn<String, ZaposleniRestoranCustom> jmb = new TableColumn<>();
    @FXML
    private TableColumn<String, ZaposleniRestoranCustom> ime = new TableColumn<>();
    @FXML
    private TableColumn<String, ZaposleniRestoranCustom> prezime = new TableColumn<>();
    @FXML
    private TableColumn<String, ZaposleniRestoranCustom> adresa = new TableColumn<>();
    @FXML
    private TableColumn<String, ZaposleniRestoranCustom> stepenStrucneSpreme = new TableColumn<>();
    @FXML
    private TableColumn<String, ZaposleniRestoranCustom> uloga = new TableColumn<>();

    private static ObservableList<ZaposleniRestoranCustom> zaposleniRestoranBezTrenutnoPrijavljenogList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateTable();
    }

    private void generateTable(){
        jmb.setCellValueFactory(new PropertyValueFactory<>("JMB"));
        ime.setCellValueFactory(new PropertyValueFactory<>("ime"));
        prezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        adresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        stepenStrucneSpreme.setCellValueFactory(new PropertyValueFactory<>("stepenStrucneSpreme"));
        uloga.setCellValueFactory(new PropertyValueFactory<>("uloga"));

        ArrayList<ZaposleniRestoranCustom> zaposleni = zaposleniRestoranRepositoryCustom.vratiSveZaposleneINjihovuUloguURestoranu();
        for(ZaposleniRestoranCustom z : zaposleni){
            if(z.getZaposleniId() != LoginSceneController.getTrenutniNalog().getZaposleniId())
                zaposleniRestoranBezTrenutnoPrijavljenogList.add(z);
        }

        zaposleniTable.setItems(zaposleniRestoranBezTrenutnoPrijavljenogList);
    }

    public ObservableList<ZaposleniRestoranCustom> getZaposleniRestoranList() {
        return zaposleniRestoranBezTrenutnoPrijavljenogList;
    }

    public void setZaposleniRestoranList(ObservableList<ZaposleniRestoranCustom> zaposleniRestoranList) {
        this.zaposleniRestoranBezTrenutnoPrijavljenogList = zaposleniRestoranList;
    }

    @FXML
    public void posaljiNaOdsustvo(){
      try{
          if(zaposleniTable.getSelectionModel().getSelectedItem() == null){
              MessageBox.display("Morate odabrati zaposlenog kojeg saljete na odsustvo");
          }else{
              OdsustvoSceneController.setZaposleniId(zaposleniTable.getSelectionModel().getSelectedItem().getZaposleniId());
              OdsustvoSceneController.setZaposleniTable(zaposleniTable);
              Parent root = FXMLLoader.load(getClass().getResource("view/OdsustvoScene.fxml"));
              Stage stage = new Stage();
              Scene scene = new Scene(root);
              stage.setScene(scene);
              stage.show();
          }
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    @FXML
    public void ukloniIzPoslovnice(){
        if(zaposleniTable.getSelectionModel().getSelectedItem() == null){
            MessageBox.display("Morate odabrati zaposlenog");
        }else {
            zaposleniRestoranRepository.postaviDatumPrekidaRadaUPoslovnici(zaposleniTable.getSelectionModel().getSelectedItem().getZaposleniId());
            MessageBox.display("Uspjesno uklonjeno");
        }
    }

    @FXML
    public void idiNazad(){
        Main.primaryStage.setScene(LoginSceneController.adminScene);
    }

    public static ObservableList<ZaposleniRestoranCustom> getZaposleniRestoranBezTrenutnoPrijavljenogList() {
        return zaposleniRestoranBezTrenutnoPrijavljenogList;
    }

    public static void setZaposleniRestoranBezTrenutnoPrijavljenogList(ObservableList<ZaposleniRestoranCustom> zaposleniRestoranBezTrenutnoPrijavljenogList) {
        PrikazZaposlenihSceneController.zaposleniRestoranBezTrenutnoPrijavljenogList = zaposleniRestoranBezTrenutnoPrijavljenogList;
    }
}
