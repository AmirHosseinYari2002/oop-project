package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class ShowTruck {

    @FXML
    private Button back;

    @FXML
    private Button Go;

    @FXML
    private ProgressBar truckCapacity;

    @FXML
    private ComboBox<String> productComboBox;

    @FXML
    private TextField numTextField;

    @FXML
    private ListView<String> showInTtruck;

    @FXML
    private ImageView addBtn;

    @FXML
    private ImageView removeBtn;

    @FXML
    void addToTruck(MouseEvent event) {
        String result = StartLevel.manager.loadingProducts(productComboBox.getValue(), Integer.parseInt(numTextField.getText()));
        if (result.equals("loaded")){
            showInTtruck.getItems().add(productComboBox.getValue()+"\tnumber: "+numTextField.getText());
        }
        else if (result.equals("notEnoughSpace"))
            GUI.createAlert(Alert.AlertType.ERROR,"ERROR","Sorry you don't have enough space...");
        else if (result.equals("Traveling")){
            GUI.createAlert(Alert.AlertType.ERROR,"ERROR","The truck is traveling, please wait...");
        }
    }

    @FXML
    void backToGame(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    @FXML
    void removeFromTruck(MouseEvent event) {
        String result = StartLevel.manager.unLoadingProducts(productComboBox.getValue());
        if (result.equals("unLoaded")){
            ObservableList<String> texts = showInTtruck.getItems();
            for (String text : texts) {
                if (text.contains(productComboBox.getValue()))
                    showInTtruck.getItems().remove(text);
            }
        }
        else if (result.equals("notEnoughSpace"))
            GUI.createAlert(Alert.AlertType.ERROR,"ERROR","Sorry! you don't have enough space in your barn");
    }

    @FXML
    void startTravel(ActionEvent event) {
        StartLevel.manager.startTrip();
        GUI.createAlert(Alert.AlertType.INFORMATION,"Travel","The car started transporting products to the city.");
    }

    @FXML
    void initialize(){
        GUI.addBtnGUI(back);
        GUI.addBtnGUI(Go);
        GUI.addLabelGUI(addBtn);
        GUI.addLabelGUI(removeBtn);
        HashSet<String> products = new HashSet<>();
        for (Map.Entry<Product, Integer> entry : StartLevel.manager.getProductsInBarn().entrySet()){
            products.add(entry.getKey().getName());
        }
        for (Map.Entry<Animal, Integer> entry : StartLevel.manager.getAnimalInBarn().entrySet()){
            products.add(entry.getKey().name);
        }
        ObservableList<String> names = FXCollections.observableArrayList(products);
        productComboBox.getItems().addAll(names);
    }

    @FXML
    void update(MouseEvent event) {
        if (!showInTtruck.getItems().isEmpty() && StartLevel.manager.checkTrip() == 0)
            Go.setDisable(false);
        truckCapacity.setProgress((15.0-Car.getInstance().getEmptySpace())/15.0);
        if (!numTextField.getText().matches("\\d+")){
            addBtn.setDisable(true);
            removeBtn.setDisable(true);
        }
        else{
            addBtn.setDisable(false);
            removeBtn.setDisable(false);
        }
    }

}
