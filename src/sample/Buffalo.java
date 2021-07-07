package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Buffalo extends DomesticAnimal{
    public Buffalo(int x, int y) throws FileNotFoundException {
        this.price = 400;
        this.ManufacturedProduct = "milk";
        this.remainingLife = 100;
        this.timeRequiredToProduct = 5;
        this.name = "Buffalo";
        this.OccupiedSpace = 4;
        this.X = x;
        this.Y = y;
        this.image = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\buffaloIcon.png")));
        this.image.setFitHeight(80);
        this.image.setFitWidth(80);
    }

    @Override
    Product outProduct(TIME startDisappearTime) {
        return new Product(this.ManufacturedProduct,25,1,4,startDisappearTime,Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
    }
}
