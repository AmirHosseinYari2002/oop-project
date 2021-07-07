package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Grass {
    private int X;
    private int Y;
    private ImageView img;

    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    public ImageView getImg() {
        return img;
    }
    public void setX(int x) {
        X = x;
    }
    public void setY(int y) {
        Y = y;
    }

    public Grass(int x, int y) throws FileNotFoundException {
        X = x;
        Y = y;
        this.img = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\grass.png")));
        img.setFitWidth(50);
        img.setFitHeight(42);
    }

    public Grass() {
    }
}
