package gui;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import model.modelCustom.ZaposleniRestoranCustom;
import repository.ZaposleniOdsustvoRepository;

import java.sql.Date;

public class OdsustvoSceneController {

    private ZaposleniOdsustvoRepository zaposleniOdsustvoRepository = new ZaposleniOdsustvoRepository();
    private static Integer zaposleniId;
    private static TableView<ZaposleniRestoranCustom> zaposleniTable;

    @FXML
    private DatePicker datumOd = new DatePicker();
    @FXML
    private DatePicker datumDo = new DatePicker();

    @FXML
    public void potvrdiOdsustvo(){
        if(zaposleniOdsustvoRepository.kreirajOdsustvoZaZaposlenog(zaposleniId, Date.valueOf(datumOd.getValue()), Date.valueOf(datumDo.getValue()))) {
            MessageBox.display("Odsustvo uspjesno kreirano");
            PrikazZaposlenihSceneController.getZaposleniRestoranBezTrenutnoPrijavljenogList().remove(zaposleniTable.getSelectionModel().getSelectedItem());
            zaposleniTable.refresh();
            zaposleniTable.setItems(PrikazZaposlenihSceneController.getZaposleniRestoranBezTrenutnoPrijavljenogList());
        }

    }

    public static Integer getZaposleniId() {
        return zaposleniId;
    }

    public static void setZaposleniId(Integer zaposleniId) {
        OdsustvoSceneController.zaposleniId = zaposleniId;
    }

    public static TableView<ZaposleniRestoranCustom> getZaposleniTable() {
        return zaposleniTable;
    }

    public static void setZaposleniTable(TableView<ZaposleniRestoranCustom> zaposleniTable) {
        OdsustvoSceneController.zaposleniTable = zaposleniTable;
    }
}
