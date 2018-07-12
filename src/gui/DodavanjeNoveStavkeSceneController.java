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
            MessageBox.display("Nijedno polje ne smije ostati prazno");
        }else{
            String kategorija = kategorijaStavke.getSelectionModel().getSelectedItem();
            Integer idKategorije = kategorijaStavkeRepository.vratiIdKategorijeNaOsnovuNaziva(kategorija);
            if(regularnaCijena(cijenaStavke.getText())) {
                BigDecimal cijena = new BigDecimal(cijenaStavke.getText());
                stavkaRestoranRepositoryCustom.dodajNovuStavku(nazivStavke.getText(), kategorija, idKategorije, cijena);
                RadSaStavkamaSceneController.listaStavki.setAll(stavkaRestoranRepositoryCustom.vratiStavkeUPonudiIzRestorana(LoginSceneController.getRestoranId()));
                tabelaZaDodavanje.setItems(RadSaStavkamaSceneController.listaStavki);
            }else
                MessageBox.display("Unesena vrijednost nije regularna");
        }
    }

    private static boolean regularnaCijena(String cijena){
        try{
            BigDecimal temp = new BigDecimal(cijena);
            if(temp.doubleValue() < 0)
                return false;
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
