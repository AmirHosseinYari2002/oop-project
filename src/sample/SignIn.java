package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Date;

public class SignIn {

    Authentication authentication = new Authentication();

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPass;

    @FXML
    void confirm(MouseEvent event) throws IOException {
        String userName = txtUser.getText();
        String password = txtPass.getText();
        authentication.initUserPass(userName, password);
        System.out.println(authentication.checkUserPass());
        if (!authentication.checkUserPass()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("");
            alert.setContentText("Username or password is incorrect!");
            alert.showAndWait();
            FileManager.addToFile(MainMenu.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"Sign in failed");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sign in");
            alert.setHeaderText("");
            alert.setContentText("Sign in successfully.");
            alert.showAndWait();
            FileManager.addToFile(MainMenu.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Signed In successfully");
            MainMenu.player = FileManager.initPlayer(userName);
            Stage stage = (Stage) txtPass.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Main Menu");
            primaryStage.setScene(new Scene(root, 1550, 800));
            primaryStage.show();
        }
    }

    private boolean isReady(){
        return !txtPass.getText().equals("") && !txtUser.getText().equals("");
    }

}