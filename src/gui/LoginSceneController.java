package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import model.Nalog;
import model.ZaposleniRestoran;
import repository.NalogRepository;
import repository.ZaposleniRestoranRepository;
import services.Util;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoginSceneController {

    private NalogRepository nalogRepository = new NalogRepository();
    private ZaposleniRestoranRepository zaposleniRestoranRepository = new ZaposleniRestoranRepository();
    private static Nalog trenutniNalog;
    private static Integer restoranId;

    public static Scene adminScene;
    public static Scene blagajnikScene;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    public void checkLoginData(){
        try {
            if (usernameField.getText() == null || passwordField.getText() == null) {
                MessageBox.display("Some fields are left empty!");
            } else {
                Nalog nalog = nalogRepository.vratiNalogZaKorisnickoIme(usernameField.getText());
                if(nalog == null)
                    MessageBox.display("Username or password are incorrect!");
                else{
                    String hashVrijednost = Util.getHashValue(passwordField.getText());
                        if (hashVrijednost.equals(nalog.getHashLozinke())) {
                            trenutniNalog = nalog;

                            String uloga = zaposleniRestoranRepository.vratiUloguNaOsnovuZaposleniId(nalog.getZaposleniId());
                            restoranId = zaposleniRestoranRepository.vratiIdRestoranaNaOsnovuZaposleniId(nalog.getZaposleniId());
                            System.out.println(restoranId);
                            if(uloga != null && "Administrator".equals(uloga)){
                                Parent root = FXMLLoader.load(getClass().getResource("view/AdminScene.fxml"));
                                Scene scene = new Scene(root);
                                adminScene = scene;
                                Main.primaryStage.setScene(scene);
                            }
                            else if(uloga != null && "Blagajnik".equals(uloga)){
                                Parent root = FXMLLoader.load(getClass().getResource("view/BlagajnikScene.fxml"));
                                Scene scene = new Scene(root);
                                blagajnikScene = scene;
                                Main.primaryStage.setScene(scene);
                            }else{
                                MessageBox.display("Unable to recognize role!");
                            }
                        }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Nalog getTrenutniNalog() {
        return trenutniNalog;
    }

    public static void setTrenutniNalogNaNull(){
        trenutniNalog = null;
    }

    public static Integer getRestoranId() {
        return restoranId;
    }

    public static void setRestoranId(Integer restoranId) {
        LoginSceneController.restoranId = restoranId;
    }
}
