package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.Map;

public class StartLevel {

    public static Levels level;

    @FXML
    private ImageView barn;

    @FXML
    private ImageView well;

    @FXML
    private ImageView truck;

    @FXML
    private ImageView millWS;

    @FXML
    private ImageView bakeryWS;

    @FXML
    private ImageView iceCreamWS;

    @FXML
    private ImageView sewingWS;

    @FXML
    private ImageView weavingWS;

    @FXML
    private ImageView milkWS;

    @FXML
    private Label tasksLbl;

    @FXML
    private Label timeLbl;

    @FXML
    private ImageView henIcon;

    @FXML
    private ImageView catIcon;

    @FXML
    private ImageView dogIcon;

    @FXML
    private ImageView turkeyIcon;

    @FXML
    private ImageView buffaloIcon;

    @FXML
    private Label coinLbl;

    @FXML
    void fillWaterBucket(MouseEvent event) {

    }

    @FXML
    void showBarn(MouseEvent event) {

    }

    @FXML
    void travel(MouseEvent event) {

    }

    @FXML
    public void initialize(){
        GUI.addLabelGUI(barn);
        GUI.addLabelGUI(well);
        GUI.addLabelGUI(truck);
        GUI.addLabelGUI(henIcon);
        GUI.addLabelGUI(catIcon);
        GUI.addLabelGUI(dogIcon);
        GUI.addLabelGUI(turkeyIcon);
        GUI.addLabelGUI(buffaloIcon);
        GUI.addLabelGUI(weavingWS);
        GUI.addLabelGUI(millWS);
        GUI.addLabelGUI(bakeryWS);
        GUI.addLabelGUI(iceCreamWS);
        GUI.addLabelGUI(milkWS);
        GUI.addLabelGUI(sewingWS);
        weavingWS.setVisible(false);
        milkWS.setVisible(false);
        millWS.setVisible(false);
        iceCreamWS.setVisible(false);
        bakeryWS.setVisible(false);
        sewingWS.setVisible(false);
        final int[] second = {0};
        final int[] minute = {0};
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            timeLbl.setText("Time: "+ minute[0] + ":" + second[0]);
            second[0] = second[0] +1 ;
            if (second[0] == 60){
                second[0] = 0;
                minute[0]++;
            }
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        coinLbl.setText(String.valueOf("Coins: "+MainMenu.player.getCoins()));
        String tasks = "";
        for (Map.Entry<String, Integer> entry : level.tasks.entrySet()) {
            tasks += entry.getKey() + ":" + entry.getValue().toString()+"\n";
        }
        tasksLbl.setText(tasks);
    }
}