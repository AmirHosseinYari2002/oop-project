package sample;

import javafx.scene.image.ImageView;

import java.awt.*;

public abstract class WildAnimal extends Animal{
    protected int cageLevel;
    protected int cageLevelRequired;
    protected int OccupiedSpace;
    protected boolean decreaseCageLevel;
    protected boolean useCageOrder;
    protected ImageView cage;
    protected ImageView breakCage;
    protected int startTimeBreakCage;
}
