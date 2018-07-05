package gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.modelCustom.StavkaRestoranCustom;
import repository.KategorijaStavkeRepository;
import repository.repositoryCustom.StavkaRestoranRepositoryCustom;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class DodavanjeNoveStavkeSceneController implements Initializable {

    private KategorijaStavkeRepository kategorijaStavkeRepository = new KategorijaStavkeRepository();
    private StavkaRestoranRepositoryCustom stavkaRestoranRepositoryCustom = new StavkaRestoranRepositoryCustom();

    public static TableView<StavkaRestoranCustom> tabelaZaDodavanje;

    @FXML
    public TextField nazivStavke = new TextField();
    @FXML
    public TextField cijenaStavke = new TextField();
    @FXML
    public ComboBox<String> kategorijaStavke = new ComboBox<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      kategorijaStavke.setItems(FXCollections.observableArrayList(kategorijaStavkeRepository.vratiSveKategorije()));
    }

    @FXML
    public void potvrdiDodavanjeStavke(){
        if(kategorijaStavke.getSelectionModel().getSelectedItem() == null || cijenaStavke.getText() == null || nazivStavke.getText() == null){
            MessageBox.display("Some fields are left empty");
        }else{
            String kategorija = kategorijaStavke.getSelectionModel().getSelectedItem();
            Integer idKategorije = kategorijaStavkeRepository.vratiIdKategorijeNaOsnovuNaziva(kategorija);
            BigDecimal cijena = new BigDecimal(cijenaStavke.getText());
            stavkaRestoranRepositoryCustom.dodajNovuStavku(nazivStavke.getText(), kategorija, idKategorije,cijena);
            RadSaStavkamaSceneController.listaStavki.setAll(stavkaRestoranRepositoryCustom.vratiStavkeUPonudiIzRestorana(LoginSceneController.getRestoranId()));
            tabelaZaDodavanje.setItems(RadSaStavkamaSceneController.listaStavki);
        }
    }
}
