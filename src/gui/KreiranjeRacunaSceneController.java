package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.modelCustom.StavkaRacunCustom;
import model.modelCustom.StavkaRestoranCustom;
import repository.RacunRepository;
import repository.TaksaRepository;
import repository.repositoryCustom.StavkaRacunRepositoryCustom;
import repository.repositoryCustom.StavkaRestoranRepositoryCustom;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class KreiranjeRacunaSceneController implements Initializable {

    private StavkaRestoranRepositoryCustom stavkaRestoranRepositoryCustom = new StavkaRestoranRepositoryCustom();
    private StavkaRacunRepositoryCustom stavkaRacunRepositoryCustom = new StavkaRacunRepositoryCustom();
    private TaksaRepository taksaRepository = new TaksaRepository();
    private RacunRepository racunRepository = new RacunRepository();

    @FXML
    private TextField kolicina = new TextField();
    @FXML
    private CheckBox placanjeKarticom = new CheckBox();

    @FXML
    private TableView<StavkaRestoranCustom> stavkeTable = new TableView<>();
    @FXML
    private TableColumn<String, StavkaRestoranCustom> nazivStavke = new TableColumn<>();
    @FXML
    private TableColumn<BigDecimal, StavkaRestoranCustom> cijenaStavke = new TableColumn<>();
    @FXML
    private TableColumn<Integer, StavkaRestoranCustom> idStavke = new TableColumn<>();

    @FXML
    private TableView<StavkaRacunCustom> racunTable = new TableView<>();
    @FXML
    private TableColumn<String, StavkaRacunCustom> nazivStavkeRacun = new TableColumn<>();
    @FXML
    private TableColumn<BigDecimal, StavkaRacunCustom> cijenaStavkeRacun = new TableColumn<>();
    @FXML
    private TableColumn<Integer, StavkaRacunCustom> kolicinaStavkeRacun = new TableColumn<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateStavkeRestoranTable();
        generateRacunTable();
        kolicina.setText("1");
    }

    public static ObservableList<StavkaRestoranCustom> stavkeRestoranList = FXCollections.observableArrayList();
    public static ObservableList<StavkaRacunCustom> stavkeRacunList = FXCollections.observableArrayList();

    @FXML
    public void dodajNaRacun(){
        if(stavkeTable.getSelectionModel().getSelectedItem() == null) {
            MessageBox.display("Morate odabrati stavku koju zelite dodati na racun");
        }
        else {
            StavkaRestoranCustom stavkaZaDodavanje = stavkeTable.getSelectionModel().getSelectedItem();
            boolean postojiNaRacunu = false;
            for(StavkaRacunCustom s : stavkeRacunList){
                if(s.getNaziv().equals(stavkaZaDodavanje.getNazivStavke())) {
                    postojiNaRacunu = true;
                }
            }
            if(postojiNaRacunu)
                MessageBox.display("Stavka vec postoji na racunu");
            else {
                stavkeRacunList.add(new StavkaRacunCustom(stavkaZaDodavanje, Integer.valueOf(kolicina.getText())));
                racunTable.setItems(stavkeRacunList);
            }
        }
    }

    @FXML
    public void ukloniSaRacuna(){
        if(racunTable.getSelectionModel().getSelectedItem() == null) {
            MessageBox.display("Morate odabrati stavku koju zelite ukloniti sa racuna");
        }else{
            stavkeRacunList.remove(racunTable.getSelectionModel().getSelectedItem());
            racunTable.setItems(stavkeRacunList);
        }
    }

    @FXML
    public void izmijeniKolicinu(){
       if(racunTable.getSelectionModel().getSelectedItem() == null)
           MessageBox.display("Morate odabrati stavku ciju kolicinu mijenjate");
       else{
            IzmjenaKolicineNaRacunuSceneController.setStavkaZaIzmjenu(racunTable.getSelectionModel().getSelectedItem());
            IzmjenaKolicineNaRacunuSceneController.setTabelaZaIzmjenu(racunTable);
           try {
               Parent root = FXMLLoader.load(getClass().getResource("view/IzmjenaKolicineNaRacunuScene.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(root);
               stage.setScene(scene);
               stage.show();
           }catch (Exception e){
               e.printStackTrace();
           }
       }
    }

    private void generateStavkeRestoranTable(){
        nazivStavke.setCellValueFactory(new PropertyValueFactory<>("nazivStavke"));
        cijenaStavke.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        idStavke.setCellValueFactory(new PropertyValueFactory<>("stavkaId"));
        stavkeRestoranList.setAll(stavkaRestoranRepositoryCustom.vratiStavkeUPonudiIzRestorana(LoginSceneController.getRestoranId()));
        stavkeTable.setItems(stavkeRestoranList);
    }

    private void generateRacunTable(){
        nazivStavkeRacun.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        cijenaStavkeRacun.setCellValueFactory(new PropertyValueFactory<>("cijenaKupljenog"));
        kolicinaStavkeRacun.setCellValueFactory(new PropertyValueFactory<>("kolicinaKupljenog"));
    }

    @FXML
    public void generisiRacun(){
        Integer taksaId = taksaRepository.vratiIdTrenutneVrijednostiTakse();
        Date datumIzdavanja = Date.valueOf(LocalDate.now());
        double tempCijena = stavkeRacunList.stream().mapToDouble(s -> s.getCijenaKupljenog().doubleValue() * s.getKolicinaKupljenog()).sum();
        BigDecimal ukupnaCijenaSaPDV =  BigDecimal.valueOf(tempCijena + (tempCijena * (taksaRepository.vratiTrenutnuVrijednostTakse(taksaId).doubleValue() / 100)));

        Integer idKreiranogRacuna = racunRepository.kreirajRacun(datumIzdavanja, ukupnaCijenaSaPDV, placanjeKarticom.isSelected(), taksaId);

        for(int i= 0; i<stavkeRacunList.size(); i++)
           generisiStavkuNaRacunu(stavkeRacunList.get(i), idKreiranogRacuna);

        MessageBox.display("Uspjesno generisan racun");
        stavkeRacunList.removeAll(stavkeRacunList);
        racunTable.refresh();
        racunTable.setItems(stavkeRacunList);

    }

    private void generisiStavkuNaRacunu(StavkaRacunCustom stavka, Integer idRacuna){
      stavkaRacunRepositoryCustom.dodajNovuStavkuNaRacun(stavka.getStavkaUPonudi().getStavkaId(),idRacuna, stavka.getKolicinaKupljenog(), stavka.getCijenaKupljenog());
    }

    @FXML
    public void idiNazad(){
        Main.primaryStage.setScene(LoginSceneController.blagajnikScene);
    }
}
