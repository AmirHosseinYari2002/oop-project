package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseMenu {

//    StartLevel startLevel = new StartLevel();

    @FXML
    private ImageView resumeBtn;

    @FXML
    private ImageView restartBtn;

    @FXML
    private ImageView exitBtn;

    @FXML
    void exit(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you want to exit?");
        alert.showAndWait();
        Stage stage =(Stage) exitBtn.getScene().getWindow();
        if (alert.getResult() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    void restart(MouseEvent event) {
        Stage stage = (Stage) restartBtn.getScene().getWindow();
        stage.close();
//        Stage stage1 = (Stage) startLevel.groundBack.getScene().getWindow();
//        stage1.close();
        StartLevel.level = new Levels(StartLevel.level.levelNum);
        StartLevel.manager = new Manager(StartLevel.level,MainMenu.player);
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("startLevel.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Level");
        primaryStage.setScene(new Scene(root, 1550, 800));
        primaryStage.show();
    }

    @FXML
    void resume(MouseEvent event) {
        StartLevel.clock.play();
        Stage stage = (Stage) resumeBtn.getScene().getWindow();
        stage.close();
    }


    @FXML
    void mainMenu(MouseEvent event) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
//        Stage stage1 = (Stage) startLevel.groundBack.getScene().getWindow();
//        stage1.close();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(new Scene(root, 1550, 800));
        GUI.mainSound.start();
        primaryStage.show();
    }

}