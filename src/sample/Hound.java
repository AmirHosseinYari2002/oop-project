package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Hound extends Animal {
    Random random = new Random();
    public Hound(int x, int y) throws FileNotFoundException {
        this.price = 100;
        this.X = x;
        this.Y = y;
        this.name = "Hound";
        this.image = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\hound.png")));
        this.image.setFitHeight(80);
        this.image.setFitWidth(80);
    }

}
