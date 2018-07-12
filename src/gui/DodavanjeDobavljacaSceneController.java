package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Dobavljac;
import repository.DobavljacRepository;


public class DodavanjeDobavljacaSceneController {

    private DobavljacRepository dobavljacRepository = new DobavljacRepository();
    private static TableView<Dobavljac> tabelaZaDodavanje;
    @FXML
    private TextField JIB = new TextField();
    @FXML
    private TextField telefon = new TextField();
    @FXML
    private TextField adresa = new TextField();

    @FXML
    public void potvrdiDodavanje(){
        Dobavljac dobavljac = new Dobavljac(0, JIB.getText(), telefon.getText(), adresa.getText());
        if(dobavljacRepository.dodajDobavljaca(dobavljac)){
            MessageBox.display("Uspjesno dodan dobavljac");
            RadSaDobavljacimaSceneController.dobavljaciList.add(dobavljac);
            tabelaZaDodavanje.refresh();
            tabelaZaDodavanje.setItems(RadSaDobavljacimaSceneController.dobavljaciList);
        }
    }

    public static TableView<Dobavljac> getTabelaZaDodavanje() {
        return tabelaZaDodavanje;
    }

    public static void setTabelaZaDodavanje(TableView<Dobavljac> tabelaZaDodavanje) {
        DodavanjeDobavljacaSceneController.tabelaZaDodavanje = tabelaZaDodavanje;
    }
}
