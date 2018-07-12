package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Dobavljac;
import repository.DobavljacRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class IzmjenaDobavljacaSceneController implements Initializable {

    private DobavljacRepository dobavljacRepository = new DobavljacRepository();
    private static TableView<Dobavljac> tabelaZaIzmjenu;
    private static Dobavljac zaIzmjenu;

    @FXML
    private TextField JIB = new TextField();
    @FXML
    private TextField telefon = new TextField();
    @FXML
    private TextField adresa = new TextField();

    @FXML
    public void potvrdiIzmjenu(){
        if(JIB.getText()==null || telefon.getText()==null || adresa.getText()==null){
            MessageBox.display("Nijedno polje ne smije ostati prazno");
        }else{
           if(dobavljacRepository.izmijeniDobavljaca(zaIzmjenu.getDobavljacId(), JIB.getText(), telefon.getText(), adresa.getText())){
               zaIzmjenu.setAdresa(adresa.getText());
               zaIzmjenu.setJIB(JIB.getText());
               zaIzmjenu.setTelefon(telefon.getText());
               tabelaZaIzmjenu.refresh();
               MessageBox.display("Uspjesna izmjena");
           }
           else
               MessageBox.display("Neuspjesna izmjena");
        }
    }

    public static TableView<Dobavljac> getTabelaZaIzmjenu() {
        return tabelaZaIzmjenu;
    }

    public static void setTabelaZaIzmjenu(TableView<Dobavljac> tabelaZaIzmjenu) {
        IzmjenaDobavljacaSceneController.tabelaZaIzmjenu = tabelaZaIzmjenu;
    }

    public static Dobavljac getZaIzmjenu() {
        return zaIzmjenu;
    }

    public static void setZaIzmjenu(Dobavljac zaIzmjenu) {
        IzmjenaDobavljacaSceneController.zaIzmjenu = zaIzmjenu;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JIB.setText(zaIzmjenu.getJIB());
        telefon.setText(zaIzmjenu.getTelefon());
        adresa.setText(zaIzmjenu.getAdresa());
    }
}
