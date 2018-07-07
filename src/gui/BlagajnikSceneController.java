package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class BlagajnikSceneController {


    @FXML
    public void otvoriIzdavanjeRacuna(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("view/KreiranjeRacunaScene.fxml"));
            Scene scene = new Scene(root);
            Main.primaryStage.setScene(scene);
            Main.primaryStage.setTitle("Accounts creation");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void odjaviSe(){
        Main.primaryStage.setScene(Main.primaryScene);
        LoginSceneController.setTrenutniNalogNaNull();
    }
}
