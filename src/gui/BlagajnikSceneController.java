package gui;

import javafx.fxml.FXML;

public class BlagajnikSceneController {

    @FXML
    public void otvoriDostavu(){}

    @FXML
    public void otvoriIzdavanjeRacuna(){}

    @FXML
    public void odjaviSe(){
        Main.primaryStage.setScene(Main.primaryScene);
        LoginSceneController.setTrenutniNalogNaNull();
    }
}
