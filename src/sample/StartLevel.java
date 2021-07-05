package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class StartLevel {

    public static Levels level;

    public static Manager manager;

    private static final ArrayList<ImageView> workshopBuildBtn = new ArrayList<>();

    private static final ArrayList<ImageView> workshopUpgradeBtn = new ArrayList<>();

    private static final ArrayList<ImageView> workshopBtn = new ArrayList<>();

    private static final ArrayList<Label> workshopBtnLbl = new ArrayList<>();

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
    private ImageView weavingBuildBtn;

    @FXML
    private ImageView milkBuildBtn;

    @FXML
    private ImageView iceCreamBuildBtn;

    @FXML
    private ImageView millBuildBtn;

    @FXML
    private ImageView sewingBuildBtn;

    @FXML
    private ImageView bakeryBuildBtn;

    @FXML
    private Label BuildMillLBL;

    @FXML
    private Label BuildBakeryLBL;

    @FXML
    private Label BuildWeavingLBL;

    @FXML
    private Label BuildSewingLBL;

    @FXML
    private Label BuildIceCreamLBL;

    @FXML
    private Label BuildMilkLBL;

    @FXML
    private ImageView upgradeWeaving;

    @FXML
    private ImageView upgradeSewing;

    @FXML
    private ImageView upgradeMilk;

    @FXML
    private ImageView upgradeIceCream;

    @FXML
    private ImageView upgradeMill;

    @FXML
    private ImageView upgradeBakery;

    @FXML
    private AnchorPane ground;

    @FXML
    private ImageView groundBack;

    @FXML
    void plantGrass(MouseEvent event) {
        int manageError = manager.planting((int) event.getX() ,(int) event.getY());
        if (manageError == 2) {
            GUI.createAlert(Alert.AlertType.ERROR,"ERROR","There is grass in these location !");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"There is grass in these location");
        }else if (manageError == 3) {
            try {
                ImageView img = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\grass.png")));
                img.setX(event.getX()-groundBack.getX());
                img.setY(event.getY()-groundBack.getY()-20);
                img.setFitWidth(50);
                img.setFitHeight(42);
                img.setVisible(true);
                ground.getChildren().addAll(img);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Grass was planted");
        }else if (manageError == 4){
            GUI.createAlert(Alert.AlertType.ERROR,"ERROR","Bucket does not have a  water!");
        }
    }

    @FXML
    void fillWaterBucket(MouseEvent event) {
        String manageError = manager.fillWaterBucket();
        if (manageError.equals("haveWater")) {
            GUI.createAlert(Alert.AlertType.ERROR,"ERROR","The bucket still has water. So you can not take water from the well");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"The bucket still has water");
        }
        else if (manageError.equals("filled")) {
            GUI.createAlert(Alert.AlertType.INFORMATION,"Well","The bucket of water was filled");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"The bucket of water was filled");
        }
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
        workshopBtn.add(weavingWS);
        workshopBtn.add(millWS);
        workshopBtn.add(bakeryWS);
        workshopBtn.add(iceCreamWS);
        workshopBtn.add(milkWS);
        workshopBtn.add(sewingWS);
        workshopBtnLbl.add(BuildWeavingLBL);
        workshopBtnLbl.add(BuildMillLBL);
        workshopBtnLbl.add(BuildBakeryLBL);
        workshopBtnLbl.add(BuildIceCreamLBL);
        workshopBtnLbl.add(BuildMilkLBL);
        workshopBtnLbl.add(BuildSewingLBL);
        workshopUpgradeBtn.add(upgradeWeaving);
        workshopUpgradeBtn.add(upgradeMill);
        workshopUpgradeBtn.add(upgradeBakery);
        workshopUpgradeBtn.add(upgradeIceCream);
        workshopUpgradeBtn.add(upgradeMilk);
        workshopUpgradeBtn.add(upgradeSewing);
        String[] WSBtnNames = new String[]{"weaving","mill","bakery","iceCreamSelling","milkPackaging","sewing"};
        for (ImageView imageView : workshopUpgradeBtn) {
            GUI.addLabelGUI(imageView);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String result = manager.upgradeWorkshop(WSBtnNames[workshopUpgradeBtn.indexOf(imageView)]);
                    if (result.equals("coins")){
                        GUI.createAlert(Alert.AlertType.ERROR,"ERROR","Sorry tou don't have enough coins...");
                    }
                    else {
                        GUI.playSound(new File("src\\sample\\pictures\\upgradeSound.wav")).start();
                        GUI.createAlert(Alert.AlertType.INFORMATION,"Upgrade","Great! You Upgrade this workshop to level2.");
                        imageView.setVisible(false);
                    }
                }
            });
        }
        final int[] second = {0};
        final int[] minute = {0};
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            update();
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
        workshopBuildBtn.add(weavingBuildBtn);
        workshopBuildBtn.add(millBuildBtn);
        workshopBuildBtn.add(bakeryBuildBtn);
        workshopBuildBtn.add(iceCreamBuildBtn);
        workshopBuildBtn.add(milkBuildBtn);
        workshopBuildBtn.add(sewingBuildBtn);
        for (ImageView imageView : workshopBuildBtn) {
            GUI.addLabelGUI(imageView);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Alert alert = new Alert(Alert.AlertType.WARNING,"Build workshop", ButtonType.YES,ButtonType.NO);
                    alert.setContentText("Are you sure you want to build a "+WSBtnNames[workshopBuildBtn.indexOf(imageView)]+"?");
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES){
                        String result = manager.buyWorkshop(WSBtnNames[workshopBuildBtn.indexOf(imageView)]);
                        if (result.equals("coins")){
                            GUI.createAlert(Alert.AlertType.ERROR,"ERROR","Sorry you don't have enough coin...");
                        }
                        else {
                            imageView.setVisible(false);
                            workshopUpgradeBtn.get(workshopBuildBtn.indexOf(imageView)).setVisible(true);
                            workshopBtn.get(workshopBuildBtn.indexOf(imageView)).setVisible(true);
                            workshopBtnLbl.get(workshopBuildBtn.indexOf(imageView)).setVisible(false);
                        }
                    }
                }
            });
        }
        for (ImageView imageView : workshopBtn) {
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String result = manager.startingWorkshop(WSBtnNames[workshopBtn.indexOf(imageView)]);
                    if (result.equals("b")){
                        GUI.createAlert(Alert.AlertType.ERROR,"ERROR","You do not have the raw materials to produce the product !");
                        FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"do not have the raw materials to produce the product");
                    }else {
                        GUI.createAlert(Alert.AlertType.INFORMATION,"Workshop Start",result.substring(0, result.length() - 2) + " start working, your product will be ready by" + result.substring(result.length() - 2) + " TIME.");
                        imageView.setDisable(true);
                        FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Workshop start working");
                    }
                }
            });
        }
    }

    void update(){
        coinLbl.setText("Coins: "+MainMenu.player.getCoins());
        level.time.n++;
    }
}