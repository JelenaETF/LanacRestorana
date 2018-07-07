package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.modelCustom.NamirnicaStavkeCustom;
import model.modelCustom.ZaposleniRestoranCustom;
import repository.repositoryCustom.ZaposleniRestoranRepositoryCustom;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrikazZaposlenihSceneController implements Initializable {

    private ZaposleniRestoranRepositoryCustom zaposleniRestoranRepositoryCustom = new ZaposleniRestoranRepositoryCustom();

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

    private ObservableList<ZaposleniRestoranCustom> zaposleniRestoranBezTrenutnoPrijavljenogList = FXCollections.observableArrayList();

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
}
