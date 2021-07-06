package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.Map;

public class ShowBarn {

    @FXML
    private Button back;

    @FXML
    private ListView<String> data;

    @FXML
    void returnToGame(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize(){
        GUI.addBtnGUI(back);
        Manager manager = StartLevel.manager;
        for (Map.Entry<Product,Integer> entry : manager.getProductsInBarn().entrySet()) {
            data.getItems().add("|  "+entry.getKey().getName()+manager.space(7,entry.getKey().getName()));
        }
        for (Map.Entry<Animal,Integer> entry : manager.getAnimalInBarn().entrySet()) {
            data.getItems().add("|  "+entry.getKey().name+manager.space(7,entry.getKey().name));
        }
    }

}
