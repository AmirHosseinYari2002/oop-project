package sample;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.util.Random;

public abstract class DomesticAnimal extends Animal{
    protected String ManufacturedProduct;
    protected ImageView productProduced;
    protected int timeRequiredToProduct;
    protected int remainingLife;
    protected int OccupiedSpace;
    protected TIME startProduceProduct;

    abstract Product outProduct(TIME startDisappearTime) throws FileNotFoundException;
}
