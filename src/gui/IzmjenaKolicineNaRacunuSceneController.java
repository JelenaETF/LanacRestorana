package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.modelCustom.StavkaRacunCustom;


public class IzmjenaKolicineNaRacunuSceneController {

    private static StavkaRacunCustom stavkaZaIzmjenu;
    private static TableView<StavkaRacunCustom> tabelaZaIzmjenu;

    @FXML
    private TextField novaKolicina = new TextField();

    public void potvrdiIzmjenuKolicine(){
        if(novaKolicina.getText() == null){
            MessageBox.display("You must enter a new value");
        }else{
            KreiranjeRacunaSceneController.stavkeRacunList.remove(stavkaZaIzmjenu);
            stavkaZaIzmjenu.setKolicinaKupljenog(Integer.valueOf(novaKolicina.getText()));
            KreiranjeRacunaSceneController.stavkeRacunList.add(stavkaZaIzmjenu);
            tabelaZaIzmjenu.refresh();
            tabelaZaIzmjenu.setItems(KreiranjeRacunaSceneController.stavkeRacunList);
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
}
