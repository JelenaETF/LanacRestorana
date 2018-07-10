package gui;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import repository.ZaposleniOdsustvoRepository;

import java.sql.Date;

public class OdsustvoSceneController {

    private ZaposleniOdsustvoRepository zaposleniOdsustvoRepository = new ZaposleniOdsustvoRepository();
    private static Integer zaposleniId;

    @FXML
    private DatePicker datumOd = new DatePicker();
    @FXML
    private DatePicker datumDo = new DatePicker();

    @FXML
    public void potvrdiOdsustvo(){
        zaposleniOdsustvoRepository.kreirajOdsustvoZaZaposlenog(zaposleniId, Date.valueOf(datumOd.getValue()), Date.valueOf(datumDo.getValue()));
    }

    public static Integer getZaposleniId() {
        return zaposleniId;
    }

    public static void setZaposleniId(Integer zaposleniId) {
        OdsustvoSceneController.zaposleniId = zaposleniId;
    }
}
