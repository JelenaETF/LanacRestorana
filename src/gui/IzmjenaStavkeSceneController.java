package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.modelCustom.StavkaRestoranCustom;
import repository.KategorijaStavkeRepository;
import repository.repositoryCustom.StavkaRestoranRepositoryCustom;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class IzmjenaStavkeSceneController implements Initializable {

    private static StavkaRestoranCustom stavkaZaIzmjenu;
    public static TableView<StavkaRestoranCustom> tabelaZaIzmjenu;
    private KategorijaStavkeRepository kategorijaStavkeRepository = new KategorijaStavkeRepository();
    private StavkaRestoranRepositoryCustom stavkaRestoranRepositoryCustom = new StavkaRestoranRepositoryCustom();

    @FXML
    private TextField nazivStavke = new TextField();
    @FXML
    private TextField cijenaStavke = new TextField();
    @FXML
    private ComboBox<String> kategorijaStavke = new ComboBox<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       nazivStavke.setText(stavkaZaIzmjenu.getNazivStavke());
       cijenaStavke.setText(stavkaZaIzmjenu.getCijena().toString());
       kategorijaStavke.getItems().setAll(kategorijaStavkeRepository.vratiSveKategorije());
    }


    public static StavkaRestoranCustom getStavkaZaIzmjenu() {
        return stavkaZaIzmjenu;
    }

    public static void setStavkaZaIzmjenu(StavkaRestoranCustom stavkaZaIzmjenu) {
        IzmjenaStavkeSceneController.stavkaZaIzmjenu = stavkaZaIzmjenu;
    }

    @FXML
    public void potvrdiIzmjenuStavke(){
        String kategorija = kategorijaStavke.getSelectionModel().getSelectedItem();
        if(kategorija != null) {
            Integer idKategorije = kategorijaStavkeRepository.vratiIdKategorijeNaOsnovuNaziva(kategorija);
            BigDecimal cijena = new BigDecimal(cijenaStavke.getText());
            stavkaRestoranRepositoryCustom.izmijeniStavku(stavkaZaIzmjenu.getStavkaId(), nazivStavke.getText(),idKategorije, cijena);

            RadSaStavkamaSceneController.listaStavki.setAll(stavkaRestoranRepositoryCustom.vratiStavkeUPonudiIzRestorana(LoginSceneController.getRestoranId()));
            tabelaZaIzmjenu.setItems(RadSaStavkamaSceneController.listaStavki);
            MessageBox.display("Stavka uspjesno izmijenjena");
        }
    }


}
