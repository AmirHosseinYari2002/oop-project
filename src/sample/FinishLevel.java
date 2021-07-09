package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FinishLevel {

    public static int time;

    public static int gift;

    public static String status;

    @FXML
    private ImageView bronzeCup;

    @FXML
    private Label timeLbl;

    @FXML
    private Label giftLbl;

    @FXML
    private ImageView silverCup;

    @FXML
    private ImageView goldenCup;



    @FXML
    void initialize(){
        timeLbl.setText(timeLbl.getText()+" "+time);
        giftLbl.setText(giftLbl.getText()+" "+gift);
        switch (status){
            case "Golden" -> goldenCup.setVisible(true);
            case "Silver" -> silverCup.setVisible(true);
            case "Bronze" -> bronzeCup.setVisible(true);        }
    }

    @FXML
    void backToMenu(ActionEvent actionEvent) {
        Stage stage = (Stage) bronzeCup.getScene().getWindow();
        stage.close();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(new Scene(root, 1550, 800));
        GUI.playSound(new File("src\\sample\\pictures\\Farmville.wav")).start();
        primaryStage.show();
    }
}
