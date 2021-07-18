package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class SignUp {

    Authentication authentication = new Authentication();

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPass;

    @FXML
    void confirm(MouseEvent event) throws IOException {
        String userName = txtUser.getText();
        String password = txtPass.getText();
        authentication.initUserPass(userName, password);
        if (!authentication.checkNewUsername(userName)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("");
            alert.setContentText("This Username is already existed!");
            alert.showAndWait();
            FileManager.addToFile(MainMenu.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"Sign up failed");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sign up");
            alert.setHeaderText("");
            alert.setContentText("Signed up successfully.");
            alert.showAndWait();
            FileManager.addToFile(MainMenu.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Signed up successfully");
            FileManager.addToFile(authentication.getUsers(),userName+","+password+",1,200");
            Stage stage = (Stage) txtPass.getScene().getWindow();
            stage.close();
            MainMenu.player = FileManager.initPlayer(userName);
            MainMenu.database.insertData(MainMenu.player);
            Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Main Menu");
            primaryStage.setScene(new Scene(root, 1550, 800));
            GUI.playSound(new File("src\\pictures\\Farmville.wav"));
            primaryStage.show();
        }
    }

    private boolean isReady(){
        return !txtPass.getText().equals("") && !txtUser.getText().equals("");
    }

}
