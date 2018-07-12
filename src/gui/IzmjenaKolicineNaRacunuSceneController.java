package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.modelCustom.StavkaRacunCustom;

import java.net.URL;
import java.util.ResourceBundle;


public class IzmjenaKolicineNaRacunuSceneController implements Initializable {

    private static StavkaRacunCustom stavkaZaIzmjenu;
    private static TableView<StavkaRacunCustom> tabelaZaIzmjenu;

    @FXML
    private TextField novaKolicina = new TextField();

    public void potvrdiIzmjenuKolicine(){
        if(novaKolicina.getText() == null){
            MessageBox.display("Morate unijeti novu vrijednost");
        }else if(!regularnaNovaKolicina(novaKolicina.getText())) {
             MessageBox.display("Neregularna vrijednost za novu kolicinu");
        } else{
            KreiranjeRacunaSceneController.stavkeRacunList.remove(stavkaZaIzmjenu);
            stavkaZaIzmjenu.setKolicinaKupljenog(Integer.valueOf(novaKolicina.getText()));
            KreiranjeRacunaSceneController.stavkeRacunList.add(stavkaZaIzmjenu);
            tabelaZaIzmjenu.refresh();
            tabelaZaIzmjenu.setItems(KreiranjeRacunaSceneController.stavkeRacunList);
        }
    }

    private static boolean regularnaNovaKolicina(String kolicina){
        try{
            Integer temp = Integer.valueOf(kolicina);
            if(temp < 0)
                return false;
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static StavkaRacunCustom getStavkaZaIzmjenu() {
        return stavkaZaIzmjenu;
    }

    public static void setStavkaZaIzmjenu(StavkaRacunCustom stavkaZaIzmjenu) {
        IzmjenaKolicineNaRacunuSceneController.stavkaZaIzmjenu = stavkaZaIzmjenu;
    }

    public static TableView<StavkaRacunCustom> getTabelaZaIzmjenu() {
        return tabelaZaIzmjenu;
    }

    public static void setTabelaZaIzmjenu(TableView<StavkaRacunCustom> tabelaZaIzmjenu) {
        IzmjenaKolicineNaRacunuSceneController.tabelaZaIzmjenu = tabelaZaIzmjenu;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        novaKolicina.setText(stavkaZaIzmjenu.getKolicinaKupljenog().toString());
    }
}
