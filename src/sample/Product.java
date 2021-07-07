package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Product {
    private String name;
    private int sellingPrice;
    private int barnSpace;
    private int disappearTime;
    private TIME startDisappearTime;
    public ImageView image;
    private int X;
    private int Y;

    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    public int getBarnSpace() {
        return barnSpace;
    }
    public String getName() {
        return name;
    }
    public void setX(int x) {
        X = x;
    }
    public void setY(int y) {
        Y = y;
    }
    public int getSellingPrice() {
        return sellingPrice;
    }
    public int getDisappearTime() {
        return disappearTime;
    }
    public TIME getStartDisappearTime() {
        return startDisappearTime;
    }

    public void setStartDisappearTime(TIME startDisappearTime) {
        this.startDisappearTime = startDisappearTime;
    }

    public Product(String name, int sellingPrice, int barnSpace, int disappearTime, TIME startDisappearTime, int X, int Y) throws FileNotFoundException {
        this.name = name;
        this.sellingPrice = sellingPrice;
        this.barnSpace = barnSpace;
        this.disappearTime = disappearTime;
        this.X = X;
        this.Y = Y;
        this.startDisappearTime = startDisappearTime;
        this.image = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\"+name+"1.png")));
        this.image.setFitHeight(50);
        this.image.setFitWidth(50);
    }

    public Product(Product product) {
        this.name = product.name;
        this.sellingPrice = product.sellingPrice;
        this.disappearTime = product.disappearTime;
        this.barnSpace = product.barnSpace;
        this.X = product.X;
        this.Y = product.Y;
    }

    public Product(){

    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Product))
            return false;
        Product product = (Product) o;
        return product.name.equals(this.name);
    }
}
