package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Racun;
import repository.RacunRepository;
import repository.TaksaRepository;
import repository.ZaposleniRestoranRepository;


import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class RadSaRacunimaAdminSceneController implements Initializable {

    private RacunRepository racunRepository = new RacunRepository();
    private ZaposleniRestoranRepository zaposleniRestoranRepository = new ZaposleniRestoranRepository();
    private TaksaRepository taksaRepository = new TaksaRepository();

    @FXML
    public TableView<Racun> racuniTable = new TableView<>();
    @FXML
    public TableColumn<Date, Racun> datumIzdavanja = new TableColumn<>();
    @FXML
    public TableColumn<String, Racun> racunIzdao = new TableColumn<>();
    @FXML
    public TableColumn<BigDecimal, Racun> vrijednostTakse = new TableColumn<>();
    @FXML
    public TableColumn<String, Racun> placenoKarticom = new TableColumn<>();
    @FXML
    public TableColumn<BigDecimal, Racun> cijena = new TableColumn<>();
    @FXML
    public TableColumn<String, Racun> ponisten = new TableColumn<>();

    private ObservableList<Racun> racuniList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      generateTable();
    }

    private void generateTable(){
        datumIzdavanja.setCellValueFactory(new PropertyValueFactory<>("datumIzdavanja"));
        racunIzdao.setCellValueFactory(new PropertyValueFactory<>("racunIzdao"));
        vrijednostTakse.setCellValueFactory(new PropertyValueFactory<>("vrijednostTakse"));
        placenoKarticom.setCellValueFactory(new PropertyValueFactory<>("placanjeKarticom"));
        cijena.setCellValueFactory(new PropertyValueFactory<>("ukupnaCijena"));
        ponisten.setCellValueFactory(new PropertyValueFactory<>("ponisten"));

        racuniList.setAll(racunRepository.vratiSveRacuneKojeSuIzdaliZaposleniIzOvogRestorana());
        for(Racun r : racuniList) {
           r.setRacunIzdao(zaposleniRestoranRepository.vratiImeIPrezimeZapolenogUResotoranuNaOsnovuZaposleniId(r.getZaposleniId()));
           r.setVrijednostTakse(taksaRepository.vratiTrenutnuVrijednostTakse(r.getTaksaId()));
        }
        racuniTable.setItems(racuniList);
    }

    @FXML
    public void ponistiRacun(){
        if(racuniTable.getSelectionModel().getSelectedItem() == null){
            MessageBox.display("Odaberite racun koji zelite ponistiti");
        }else if("da".equals(racuniTable.getSelectionModel().getSelectedItem().getPonisten())){
            MessageBox.display("Racun vec ponisten");
        }else{
           racunRepository.ponistiRacun(racuniTable.getSelectionModel().getSelectedItem().getRacunId());
           racuniTable.getSelectionModel().getSelectedItem().setPonisten("da");
           racuniTable.refresh();
           MessageBox.display("Racun uspjesno ponisten");
        }
    }

    @FXML
    public void pregledajStavke(){
        if(racuniTable.getSelectionModel().getSelectedItem() == null){
            MessageBox.display("Morate odabrati racun");
        }else{
            try {
                PregledStavkiRacunaSceneController.setRacunId(racuniTable.getSelectionModel().getSelectedItem().getRacunId());
                Parent root = FXMLLoader.load(getClass().getResource("view/PregledStavkiRacunaScene.fxml"));
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

    public ObservableList<Racun> getRacuniList() {
        return racuniList;
    }

    public void setRacuniList(ObservableList<Racun> racuniList) {
        this.racuniList = racuniList;
    }
}
