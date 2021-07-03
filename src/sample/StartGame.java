package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class StartGame {

    @FXML
    private Rectangle backGround;

    @FXML
    private ImageView userImage;

    @FXML
    private Button signInBtn;

    @FXML
    private Button signUpBtn;

    @FXML
    public void initialize(){
        GUI.addBtnGUI(signUpBtn);
        GUI.addBtnGUI(signInBtn);
    }

    @FXML
    void signInProcess(ActionEvent event) throws Exception {
        Stage stage = (Stage) signInBtn.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Sign in Panel");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @FXML
    void signupProcess(ActionEvent event) throws Exception {
        Stage stage = (Stage) signUpBtn.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Sign up Panel");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
