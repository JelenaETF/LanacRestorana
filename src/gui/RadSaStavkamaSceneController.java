package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.math.BigDecimal;

public class RadSaStavkamaSceneController {
/*
    @FXML
    private TableView<Stavka> stavkeTable = new TableView<>();
    @FXML
    private TableColumn<String, Stavka> nazivColumn = new TableColumn<>();
    @FXML
    private TableColumn<String, Stavka> kategorijaColumn = new TableColumn<>();
    @FXML
    private TableColumn<BigDecimal, Stavka> cijenaColumn = new TableColumn<>();
*/

    @FXML
    public void dodajStavku(){}

    @FXML
    public void izmijeniStavku(){}

    @FXML
    public void izbaciIzPonude(){}

    @FXML
    public void pregledajSastav(){}

    @FXML
    public void idiNazad(){
        Main.primaryStage.setScene(LoginSceneController.adminScene);
    }

}
