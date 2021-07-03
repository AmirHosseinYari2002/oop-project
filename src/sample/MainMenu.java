package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

public class MainMenu {

    public static Player player;
    Authentication authentication = new Authentication();
    ArrayList<Button> levelBtn = new ArrayList<>();

    @FXML
    private ImageView farmer;
    @FXML
    private Label playerName;
    @FXML
    private Label playerCoins;
    @FXML
    private ImageView Levels;
    @FXML
    private ImageView Help;
    @FXML
    private ImageView profile;
    @FXML
    private ImageView signOut;
    @FXML
    private ImageView exit;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private AnchorPane dataPanel;
    @FXML
    private Rectangle dataPnlBackG;
    @FXML
    private Button backBtn;
    @FXML
    private Label dataPanelTitle;
    @FXML
    private TextArea helpInfo;
    @FXML
    private Label UserLbl;
    @FXML
    private Label passLbl;
    @FXML
    private ImageView userImg;
    @FXML
    private ImageView passImg;
    @FXML
    private Button confirm;
    @FXML
    private TextField getUsername;
    @FXML
    private TextField getPass;
    @FXML
    private Button level2Btn;
    @FXML
    private Button level3Btn;
    @FXML
    private Button level5Btn;
    @FXML
    private Button level8Btn;
    @FXML
    private Button level7Btn;
    @FXML
    private Button level4Btn;
    @FXML
    private Button level1Btn;
    @FXML
    private ImageView exitImg;
    @FXML
    private ImageView signOutImg;
    @FXML
    private ImageView editPrfImg;
    @FXML
    private ImageView helpImg;
    @FXML
    private ImageView levelsImg;
    @FXML
    private Button level6Btn;
    @FXML
    private Button level9Btn;
    @FXML
    private ProgressIndicator gameCopml;
    @FXML
    private Label gameCmpltLbl;
    @FXML
    void editUserInfo(ActionEvent event) {
        String input = getUsername.getText()+","+getPass.getText()+","+player.getLevel()+","+player.getCoins();
        FileManager.remove(authentication.getUsers(),player.getName());
        FileManager.addToFile(authentication.getUsers(),input);
        playerName.setText("Player: "+getUsername.getText());
        player.setName(getUsername.getText());
    }
    @FXML
    void back(ActionEvent event) {
        dataPanel.setVisible(false);
        helpInfo.setVisible(false);
        UserLbl.setVisible(false);
        userImg.setVisible(false);
        passImg.setVisible(false);
        passLbl.setVisible(false);
        getPass.setVisible(false);
        gameCmpltLbl.setVisible(false);
        gameCopml.setVisible(false);
        getUsername.setVisible(false);
        confirm.setVisible(false);
        for (Button button : levelBtn) {
            button.setVisible(false);
        }
    }
    @FXML
    void showExit(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING,"Exit",ButtonType.YES,ButtonType.NO);
        alert.setTitle("Exit");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you want to exit?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES){
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
            System.exit(0);
        }
    }
    @FXML
    void showHelp(MouseEvent event) {
        dataPanel.setVisible(true);
        dataPanelTitle.setText("Introduction of SUT Farm Frenzy");
        helpInfo.setVisible(true);
    }
    @FXML
    void showLevels(MouseEvent event) {
        dataPanel.setVisible(true);
        dataPanelTitle.setText("Choose a level to start game");
        gameCopml.setVisible(true);
        gameCmpltLbl.setVisible(true);
        gameCopml.setProgress((double) player.getLevel()*0.111);
        for (int i = 0; i < player.getLevel(); i++) {
            levelBtn.get(i).setVisible(true);

            int finalI = i;
            int finalI1 = i;
            levelBtn.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    GUI.mainSound.stop();
                    System.out.println(finalI1);
                    StartLevel.level = new Levels(finalI1 +1);
                    Stage stage = (Stage) level1Btn.getScene().getWindow();
                    stage.close();
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
            });
        }
    }
    @FXML
    void showProfile(MouseEvent event) {
        dataPanel.setVisible(true);
        dataPanelTitle.setText("You can edit your profile here.");
        UserLbl.setVisible(true);
        userImg.setVisible(true);
        passImg.setVisible(true);
        passLbl.setVisible(true);
        confirm.setVisible(true);
        getPass.setVisible(true);
        getUsername.setVisible(true);
    }
    @FXML
    public void initialize() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            time.setText("Time: "+currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        levelBtn.add(level1Btn);
        levelBtn.add(level2Btn);
        levelBtn.add(level3Btn);
        levelBtn.add(level4Btn);
        levelBtn.add(level5Btn);
        levelBtn.add(level6Btn);
        levelBtn.add(level7Btn);
        levelBtn.add(level8Btn);
        levelBtn.add(level9Btn);
        for (Button button : levelBtn) {
            GUI.addBtnGUI(button);
        }
        GUI.addLabelGUI(Levels);
        GUI.addLabelGUI(Help);
        GUI.addLabelGUI(profile);
        GUI.addLabelGUI(signOut);
        GUI.addLabelGUI(exit);
        GUI.addBtnGUI(backBtn);
        GUI.addBtnGUI(confirm);
        GUI.addLabelGUI(levelsImg);
        GUI.addLabelGUI(helpImg);
        GUI.addLabelGUI(editPrfImg);
        GUI.addLabelGUI(signOutImg);
        GUI.addLabelGUI(exitImg);
        GUI.addLabelGUI(farmer);
        playerName.setText("Player: "+player.getName());
        playerCoins.setText("Coins: "+player.getCoins());
        Calendar calendar = Calendar.getInstance();
        date.setText("Date: "+calendar.get(Calendar.YEAR)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.DAY_OF_MONTH));
    }
    @FXML
    void showSignOut(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING,"Sign out",ButtonType.YES,ButtonType.NO);
        System.out.println(alert.getButtonTypes());
        alert.setTitle("Sign out");
        alert.setHeaderText("");
        alert.setContentText("Are you sure you want to sign out?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES){
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("StartGame.fxml"));
            primaryStage.setTitle("SUT Farm Frenzy");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }
    }
}