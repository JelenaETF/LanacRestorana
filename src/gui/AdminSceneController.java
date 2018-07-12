package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AdminSceneController {

    @FXML
    public void otvoriRadSaStavkama(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("view/RadSaStavkamaScene.fxml"));
            Scene scene = new Scene(root);
            Main.primaryStage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void otvoriRadSaRacunima(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("view/RadSaRacunimaAdminScene.fxml"));
            Scene scene = new Scene(root);
            Main.primaryStage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    public void otvoriRadSaZaposlenima(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("view/PrikazZaposlenihScene.fxml"));
            Scene scene = new Scene(root);
            Main.primaryStage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void otvoriRadSaDobavljacima(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("view/RadSaDobavljacimaScene.fxml"));
            Scene scene = new Scene(root);
            Main.primaryStage.setScene(scene);
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
