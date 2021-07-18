package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoadApp {

    Boolean finish = false;

    @FXML
    private Rectangle backGround;

    @FXML
    private ImageView titleImg;

    @FXML
    private Text titileTxt;

    @FXML
    private ImageView loadImg;

    @FXML
    private ProgressBar PrgBar;

    @FXML
    private Label info1;

    @FXML
    private Label info2;

    @FXML
    public void initialize() {
        Thread t1 = new Thread() {
            public void run() {
                try {
                    for(;;) {
                        if (PrgBar.getProgress() > 1){
                            strtBt.setDisable(false);
                        }
                        PrgBar.setProgress(PrgBar.getProgress()+0.01);
                        PrgBar.setVisible(true);
                        sleep(50);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };t1.start();
    }

    @FXML
    private Button strtBt;

    @FXML
    void finish(ActionEvent event) throws Exception {
        Stage stage = (Stage) strtBt.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("StartGame.fxml"));
        primaryStage.setTitle("SUT Farm Frenzy");
        primaryStage.setScene(new Scene(root, 552, 479));
        primaryStage.show();
    }
}
