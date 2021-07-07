package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Cat extends Animal {
    Random random = new Random();
    public Cat(int x, int y) throws FileNotFoundException {
        this.price = 150;
        this.X = x;
        this.Y = y;
        this.name = "Cat";
        this.image = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\cat.png")));
        this.image.setFitHeight(80);
        this.image.setFitWidth(80);
    }
}
