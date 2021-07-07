package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Hen extends DomesticAnimal{
    public Hen(int x, int y) throws FileNotFoundException {
        this.price = 100;
        this.ManufacturedProduct = "egg";
        this.remainingLife = 100;
        this.timeRequiredToProduct = 2;
        this.name = "Hen";
        this.OccupiedSpace = 1;
        this.X = x;
        this.Y = y;
        this.image = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\hen.png")));
        this.image.setFitHeight(80);
        this.image.setFitWidth(80);
    }

    @Override
    Product outProduct(TIME startDisappearTime) {
        return new Product(this.ManufacturedProduct,15,1,4,startDisappearTime,Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
    }
}
