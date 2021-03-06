package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class PauseMenu {

    public static AnchorPane pane;

    @FXML
    private ImageView resumeBtn;

    @FXML
    private ImageView restartBtn;

    @FXML
    private ImageView exitBtn;

    @FXML
    void initialize(){
        GUI.addLabelGUI(restartBtn);
        GUI.addLabelGUI(restartBtn);
        GUI.addLabelGUI(exitBtn);
    }

    @FXML
    void exit(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you want to exit?");
        alert.showAndWait();
        Stage stage =(Stage) exitBtn.getScene().getWindow();
        if (alert.getResult() == ButtonType.OK) {
            MainMenu.player.setCoins(StartLevel.level.initialMoney);
            FileManager.remove(MainMenu.authentication.getUsers(), MainMenu.player.getName());
            String input = MainMenu.player.getName()+","+MainMenu.player.getPassword()+","+MainMenu.player.getLevel()+","+MainMenu.player.getCoins();
            FileManager.addToFile(MainMenu.authentication.getUsers(),input);
            MainMenu.database.deleteData(MainMenu.player);
            MainMenu.database.insertData(MainMenu.player);
            FileManager.addToFile(MainMenu.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Save data in database");
            FileManager.addToFile(MainMenu.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Exit game");
            FileManager.replace(MainMenu.getInstance(), "Time of the last change in the file : ", ("Time of the last change in the file : " + new Date()));
            System.exit(0);
        }
    }

    @FXML
    void restart(MouseEvent event) {
        StartLevel.workshopBuildBtn.clear();
        StartLevel.workshopBtn.clear();
        StartLevel.workshopUpgradeBtn.clear();
        StartLevel.workshopBtnLbl.clear();
        StartLevel.moveThread.stop();
        Stage stage = (Stage) restartBtn.getScene().getWindow();
        stage.close();
        Stage stage1 = (Stage) pane.getScene().getWindow();
        stage1.close();
        FileManager.addToFile(MainMenu.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Restart Game");
        MainMenu.player.setCoins(StartLevel.level.initialMoney);
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
        FileManager.addToFile(MainMenu.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Resume Game");
    }


    @FXML
    void mainMenu(MouseEvent event) {
        MainMenu.player.setCoins(StartLevel.level.initialMoney);
        StartLevel.workshopBuildBtn.clear();
        StartLevel.workshopBtn.clear();
        StartLevel.workshopUpgradeBtn.clear();
        StartLevel.workshopBtnLbl.clear();
        StartLevel.moveThread.stop();
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
        Stage stage1 = (Stage) pane.getScene().getWindow();
        stage1.close();
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