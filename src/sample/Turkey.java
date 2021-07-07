package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Turkey extends DomesticAnimal{
    public Turkey(int x, int y) throws FileNotFoundException {
        this.price = 200;
        this.ManufacturedProduct = "feather";
        this.remainingLife = 100;
        this.timeRequiredToProduct = 3;
        this.name = "Turkey";
        this.OccupiedSpace = 2;
        this.X = x;
        this.Y = y;
        this.image = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\turkey1.png")));
        this.image.setFitHeight(80);
        this.image.setFitWidth(80);
    }

    @Override
    Product outProduct(TIME startDisappearTime) throws FileNotFoundException {
        return new Product(this.ManufacturedProduct,20,1,4,startDisappearTime,Manager.random.nextInt(Manager.mapWidth)+Manager.distanceMapAndPageWidth,Manager.random.nextInt(Manager.mapHeight)+Manager.distanceMapAndPageHeight);
    }
}
