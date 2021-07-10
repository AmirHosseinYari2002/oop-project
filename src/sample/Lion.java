package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Lion extends WildAnimal{
    public Lion(int x, int y) throws FileNotFoundException {
        this.cageLevel = 0;
        this.price = 300;
        this.X = x;
        this.Y = y;
        this.cageLevelRequired = 3;
        this.OccupiedSpace = 15;
        this.name = "Lion";
        this.decreaseCageLevel = false;
        this.useCageOrder = false;
        this.image = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\lion.png")));
        this.image.setFitHeight(80);
        this.image.setFitWidth(80);
        this.cage = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\cage1.png")));
        this.cage.setX(100);
        this.cage.setY(100);
    }
}
