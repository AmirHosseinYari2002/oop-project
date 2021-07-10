package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class StartLevel {

    public static Levels level;

    public static Manager manager;

    public static Timeline clock;

    public static Thread moveThread;

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
    public ImageView groundBack;

    @FXML
    private ImageView pauseBtn;

    @FXML
    void plantGrass(MouseEvent event) throws FileNotFoundException {
        ImageView imageView = manager.planting((int) event.getX() ,(int) event.getY());
        if (imageView != null){
            if (imageView.getFitHeight() == 50  &&  imageView.getFitWidth() == 80){
                GUI.createAlert(Alert.AlertType.ERROR,"ERROR","There is grass in this location");
                FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"There is grass in this location");
            }else {
                imageView.setVisible(true);
                ground.getChildren().addAll(imageView);
                FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"Grass planted");
            }
        }else {
            GUI.createAlert(Alert.AlertType.ERROR,"ERROR","Bucket is empty!");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"Bucket is empty!");
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
        try {
            Parent root = FXMLLoader.load(getClass().getResource("showBarn.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Barn");
            primaryStage.setScene(new Scene(root, 850, 465));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void travel(MouseEvent event) {
        try {
            if (Car.getInstance().isTraveling){
                GUI.createAlert(Alert.AlertType.ERROR,"ERROR","Truck is traveling!...");
                return;
            }
            Parent root = FXMLLoader.load(getClass().getResource("showTruck.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Truck");
            primaryStage.setScene(new Scene(root, 750, 465));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(){
        GUI.addLabelGUI(pauseBtn);
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
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            try {
                update();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
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
                        String result = null;
                        try {
                            result = manager.buyWorkshop(WSBtnNames[workshopBuildBtn.indexOf(imageView)]);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        if (result != null)
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
                    }
                    else if (result.equals("c"))
                        GUI.createAlert(Alert.AlertType.ERROR,"ERROR","Workshop is busy!...");
                    else {
                        GUI.createAlert(Alert.AlertType.INFORMATION,"Workshop Start",result.substring(0, result.length() - 2) + " start working, your product will be ready by" + result.substring(result.length() - 2) + " TIME.");
                        FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"Workshop start working");
                    }
                }
            });
        }
        moveThread = new Thread() {
            public void run() {
                try {
                    for(;;) {
                        manager.move();
                        sleep(50);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };moveThread.start();
    }

    void update() throws FileNotFoundException {
        coinLbl.setText("Coins: "+MainMenu.player.getCoins());
        level.time.n++;
        manager.eatGrass(ground);
        manager.reduceLife(ground);
        manager.isAnimalManufacturedProduct(ground);
        manager.collectProducts(ground);
        manager.appearWildAnimal(ground);
        manager.disappearProduct(ground);
        manager.destroyWildAnimal(ground);
        if (level.time.n % 3 == 0)
        manager.decreaseCageLevel(ground);
        manager.destroyDomesticAnimalAndProduct(ground);
        int sellProducts = manager.sellProducts();
        if (sellProducts != -1 && sellProducts != 0)
            GUI.playSound(new File("src\\sample\\pictures\\car.wav")).start();
        manager.checkWorkshops(ground);
        String task = manager.checkTasks();
        if (task.equals("coins")){
            GUI.playSound(new File("src\\sample\\pictures\\finishChime.wav")).start();
            GUI.createAlert(Alert.AlertType.INFORMATION,"Tasks","Good! You complete a task. Your coins reach the desired amount.");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+"coins reach the desired amount");
            String tasks = "";
            for (Map.Entry<String, Integer> entry : level.tasks.entrySet()) {
                tasks += entry.getKey() + ":" + entry.getValue().toString()+"\n";
            }
            tasksLbl.setText(tasks);
        }else if (!task.equals("null")){
            GUI.playSound(new File("src\\sample\\pictures\\finishChime.wav")).start();
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.INFO+"] "+task+" reach the desired amount.");
            String tasks = "";
            for (Map.Entry<String, Integer> entry : level.tasks.entrySet()) {
                tasks += entry.getKey() + ":" + entry.getValue().toString()+"\n";
            }
            tasksLbl.setText(tasks);
        }
        if (manager.isLevelCompleted()){
            GUI.playSound(new File("src\\sample\\pictures\\finish.wav")).start();
            MainMenu.player = FileManager.initPlayer(MainMenu.player.getName());
            Stage stage = (Stage) barn.getScene().getWindow();
            clock.stop();
            stage.close();
            int levelPrize = switch (level.status) {
                case "Golden" -> level.goldenGiftCoin;
                case "Silver" -> level.silverGiftCoin;
                case "Bronze" -> level.bronzeGiftCoin;
                default -> 0;
            };
            MainMenu.player.setCoins(MainMenu.player.getCoins()+levelPrize);
            if (level.levelNum == MainMenu.player.getLevel())
                MainMenu.player.setLevel(MainMenu.player.getLevel()+1);
            FinishLevel.time = level.time.n;
            FinishLevel.gift = levelPrize;
            FinishLevel.status = level.status;
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("finishLevel.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Finish");
            primaryStage.setScene(new Scene(root, 600, 370));
            primaryStage.show();
        }
    }

    @FXML
    void buyBuffalo(MouseEvent event) throws FileNotFoundException {
        Animal manageError = manager.buyAnimal("Buffalo");
        if (manageError != null){
            manageError.image.setVisible(true);
            manageError.lifeProgBar.setProgress(1);
            manageError.lifeProgBar.setPrefSize(50,10);
            manageError.lifeProgBar.setLayoutX(manageError.image.getX());
            manageError.lifeProgBar.setLayoutY(manageError.image.getY()+50
            );
            ground.getChildren().add(manageError.lifeProgBar);
            GUI.playSound(new File("src\\sample\\pictures\\buffalo.wav")).start();
            ground.getChildren().addAll(manageError.image);
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"buy buffalo");

        }else{
            GUI.createAlert(Alert.AlertType.ERROR,"ERROR","Sorry! You don't have enough coins");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"don't have enough coins to buy buffalo");
        }
    }

    @FXML
    void buyCat(MouseEvent event) throws FileNotFoundException {
        Animal manageError = manager.buyAnimal("Cat");
        if (manageError != null){
            manageError.image.setVisible(true);
            GUI.playSound(new File("src\\sample\\pictures\\Cat.wav")).start();
            ground.getChildren().addAll(manageError.image);
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"buy cat");

        }else{
            GUI.createAlert(Alert.AlertType.ERROR,"ERROR","Sorry! You don't have enough coins");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"don't have enough coins to buy cat");
        }
    }

    @FXML
    void buyHen(MouseEvent event) throws FileNotFoundException {
        Animal manageError = manager.buyAnimal("Hen");
        if (manageError != null){
            manageError.image.setVisible(true);
            manageError.lifeProgBar.setProgress(1);
            manageError.lifeProgBar.setPrefSize(50,10);
            manageError.lifeProgBar.setLayoutX(manageError.image.getX());
            manageError.lifeProgBar.setLayoutY(manageError.image.getY()+manageError.image.getFitHeight());
            ground.getChildren().add(manageError.lifeProgBar);
            GUI.playSound(new File("src\\sample\\pictures\\hen.wav")).start();
            ground.getChildren().addAll(manageError.image);
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"buy hen");

        }else {
            GUI.createAlert(Alert.AlertType.ERROR,"ERROR","Sorry! You don't have enough coins");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"don't have enough coins to buy hen");
        }
    }

    @FXML
    void buyHound(MouseEvent event) throws FileNotFoundException {
        Animal manageError = manager.buyAnimal("Hound");
        if (manageError != null){
            manageError.image.setVisible(true);
            GUI.playSound(new File("src\\sample\\pictures\\Dog.wav")).start();
            ground.getChildren().addAll(manageError.image);
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"buy hound");

        }else{
            GUI.createAlert(Alert.AlertType.ERROR,"ERROR","Sorry! You don't have enough coins");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"don't have enough coins to buy hound");
        }
    }

    @FXML
    void buyTurkey(MouseEvent event) throws FileNotFoundException {
        Animal manageError = manager.buyAnimal("Turkey");
        if (manageError != null){
            manageError.image.setVisible(true);
            manageError.lifeProgBar.setProgress(1);
            manageError.lifeProgBar.setPrefSize(50,10);
            manageError.lifeProgBar.setLayoutX(manageError.image.getX());
            manageError.lifeProgBar.setLayoutY(manageError.image.getY()+manageError.image.getFitHeight());
            ground.getChildren().add(manageError.lifeProgBar);
            GUI.playSound(new File("src\\sample\\pictures\\Turkey.wav")).start();
            ground.getChildren().addAll(manageError.image);
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"buy turkey");

        }else{
            GUI.createAlert(Alert.AlertType.ERROR,"ERROR","Sorry! You don't have enough coins");
            FileManager.addToFile(GameHandler.getInstance(),new Date().toString()+" ["+Log.ERROR+"] "+"don't have enough coins to buy turkey");
        }
    }

    @FXML
    void pause(MouseEvent event) {
        try {
            PauseMenu.pane = ground;
            clock.pause();
            Parent root = FXMLLoader.load(getClass().getResource("pauseMenu.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Pause");
            primaryStage.setScene(new Scene(root, 415, 556));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}