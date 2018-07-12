package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;
    public static Scene primaryScene;

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/LoginScene.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login forma");
        Image image = new Image(LoginSceneController.class.getResourceAsStream("view/icon.png"));
        stage.getIcons().add(image);
        primaryStage = stage;
        primaryScene = scene;
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
