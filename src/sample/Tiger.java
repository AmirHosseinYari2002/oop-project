package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Tiger extends WildAnimal{
    public Tiger(int x, int y) throws FileNotFoundException {
        this.cageLevel = 0;
        this.price = 500;
        this.X = x;
        this.Y = y;
        this.cageLevelRequired = 4;
        this.OccupiedSpace = 15;
        this.name = "Tiger";
        this.decreaseCageLevel = false;
        this.useCageOrder = false;
        this.image = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\tiger.png")));
        this.image.setFitHeight(80);
        this.image.setFitWidth(80);
        this.cage = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\cage1.png")));
        this.cage.setX(100);
        this.cage.setY(100);
    }
}
