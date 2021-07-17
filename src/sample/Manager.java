package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Manager {
    Levels level;
    Player player;
    final static int mapWidth = 450;
    final static int mapHeight = 200;
    final static int distanceMapAndPageWidth = 250;
    final static int distanceMapAndPageHeight = 150;

    static Random random = new Random();
    private ArrayList<DomesticAnimal> domesticAnimalsList = new ArrayList<>();
    private ArrayList<WildAnimal> wildAnimalsList = new ArrayList<>();
    private ArrayList<Hound> houndsList = new ArrayList<>();
    private ArrayList<Grass> grassesList = new ArrayList<>();
    private ArrayList<Grass> removeGrassList = new ArrayList<>();
    private ArrayList<Animal> removeAnimalList = new ArrayList<>();
    private ArrayList<Product> productsList = new ArrayList<>();
    private HashMap<Product,Integer> productsInBarn = new HashMap<>();
    private HashMap<Animal,Integer> animalInBarn = new HashMap<>();
    private ArrayList<Product> removeProductList = new ArrayList<>();
    private ArrayList<Cat> catsList = new ArrayList<>();
    private ArrayList<WorkShop> workShops = new ArrayList<>();
    private HashMap<Product,Integer> loadedProducts = new HashMap<>();
    private ArrayList<WildAnimal> loadedAnimals = new ArrayList<>();
    private HashMap<Product,Integer> unLoadedProduct = new HashMap<>();
    private ArrayList<Animal> animalsCalculatedInTask = new ArrayList<>();
    private HashSet<Product> productsCalculatedInTask = new HashSet<>();

    public ArrayList<DomesticAnimal> getDomesticAnimalsList() {
        return domesticAnimalsList;
    }
    public ArrayList<WildAnimal> getWildAnimalsList() {
        return wildAnimalsList;
    }
    public ArrayList<Hound> getHoundsList() {
        return houndsList;
    }
    public ArrayList<Cat> getCatsList() {
        return catsList;
    }
    public ArrayList<Product> getProductsList() {
        return productsList;
    }
    public HashMap<Product, Integer> getProductsInBarn() {
        return productsInBarn;
    }
    public ArrayList<Grass> getGrassesList() {
        return grassesList;
    }
    public HashMap<Animal, Integer> getAnimalInBarn() {
        return animalInBarn;
    }

    public Manager(Levels level, Player player) {
        this.level = level;
        this.player = player;
    }

    private Grass shortestDistanceToEatGrass(int x, int y){
        double  shortestDistance = 1000;
        Grass nearestGrass = new Grass();
        for (Grass grass : grassesList) {
            if (Math.sqrt((grass.getX()-x)*(grass.getX()-x) + (grass.getY()-y)*(grass.getY()-y)) < shortestDistance){
                shortestDistance = Math.sqrt((grass.getX()-x)*(grass.getX()-x) + (grass.getY()-y)*(grass.getY()-y));
                nearestGrass.setX(grass.getX());
                nearestGrass.setY(grass.getY());
            }
        }
        return nearestGrass;
    }
    private Product shortestDistanceToCollectProduct(int x, int y){
        double  shortestDistance = 1000;
        Product nearestProduct = new Product();
        for (Product product : productsList) {
            if (Math.sqrt((product.getX()-x)*(product.getX()-x) + (product.getY()-y)*(product.getY()-y)) < shortestDistance){
                shortestDistance = Math.sqrt((product.getX()-x)*(product.getX()-x) + (product.getY()-y)*(product.getY()-y));
                nearestProduct.setX(product.getX());
                nearestProduct.setY(product.getY());
            }
        }
        return nearestProduct;
    }
    public void move(){
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (grassesList.size() > 0) {
                if (shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getX() > domesticAnimal.X) {
                    domesticAnimal.X++;
                    domesticAnimal.image.setX(domesticAnimal.X);
                    domesticAnimal.lifeProgBar.setLayoutX(domesticAnimal.X);
                } else if (shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getX() < domesticAnimal.X) {
                    domesticAnimal.X--;
                    domesticAnimal.image.setX(domesticAnimal.X);
                    domesticAnimal.lifeProgBar.setLayoutX(domesticAnimal.X);
                } else {
                    if (shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getY() > domesticAnimal.Y) {
                        domesticAnimal.Y++;
                        domesticAnimal.image.setY(domesticAnimal.Y);
                        domesticAnimal.lifeProgBar.setLayoutY(domesticAnimal.Y);
                    } else if (shortestDistanceToEatGrass(domesticAnimal.X, domesticAnimal.Y).getY() < domesticAnimal.Y) {
                        domesticAnimal.Y--;
                        domesticAnimal.image.setY(domesticAnimal.Y);
                        domesticAnimal.lifeProgBar.setLayoutY(domesticAnimal.Y);
                    }
                }
            }else {
                int a = 1;
                if (random.nextInt(2) == 0){
                    if (domesticAnimal.X >= mapWidth){
                        a = -a;
                    }
                    domesticAnimal.X += a;
                    domesticAnimal.image.setX(domesticAnimal.X);
                    domesticAnimal.lifeProgBar.setLayoutX(domesticAnimal.X);
                }else {
                    if (domesticAnimal.Y >= mapHeight){
                        a = -a;
                    }
                    domesticAnimal.Y += a;
                    domesticAnimal.image.setY(domesticAnimal.Y);
                    domesticAnimal.lifeProgBar.setLayoutY(domesticAnimal.Y);
                }
            }
        }
        for (Cat cat : catsList) {
            if (productsList.size() > 0) {
                if (shortestDistanceToCollectProduct(cat.X, cat.Y).getX() > cat.X) {
                    cat.X++;
                    cat.image.setX(cat.X);
                } else if (shortestDistanceToCollectProduct(cat.X, cat.Y).getX() < cat.X) {
                    cat.X--;
                    cat.image.setX(cat.X);
                } else {
                    if (shortestDistanceToCollectProduct(cat.X, cat.Y).getY() > cat.Y) {
                        cat.Y++;
                        cat.image.setY(cat.Y);
                    } else if (shortestDistanceToCollectProduct(cat.X, cat.Y).getY() < cat.Y) {
                        cat.Y--;
                        cat.image.setY(cat.Y);
                    }
                }
            }else {
                int a = 1;
                if (random.nextInt(2) == 0){
                    if (cat.X >= mapWidth){
                        a = -a;
                    }
                    cat.X += a;
                    cat.image.setX(cat.X);
                }else {
                    if (cat.Y >= mapHeight){
                        a = -a;
                    }
                    cat.Y += a;
                    cat.image.setY(cat.Y);
                }
            }
        }
        for (Hound hound : houndsList) {
            int a = 1;
            if (random.nextInt(2) == 0){
                if (hound.X >= mapWidth){
                    a = -a;
                }
                hound.X += a;
                hound.image.setX(hound.X);
            }else {
                if (hound.Y >= mapHeight){
                    a = -a;
                }
                hound.Y += a;
                hound.image.setY(hound.Y);
            }
        }
        for (WildAnimal wildAnimal : wildAnimalsList) {
            if (wildAnimal.cageLevel == 0  &&  wildAnimal.startTimeBreakCage == 0) {
                int a = 1;
                if (random.nextInt(2) == 0) {
                    if (wildAnimal.X >= mapWidth) {
                        a = -a;
                    }
                    wildAnimal.X += a;
                    wildAnimal.image.setX(wildAnimal.X);
                } else {
                    if (wildAnimal.Y >= mapHeight) {
                        a = -a;
                    }
                    wildAnimal.Y += a;
                    wildAnimal.image.setY(wildAnimal.Y);
                }
            }
        }
    }
    public void eatGrass(AnchorPane pane){
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            for (Grass grass : grassesList) {
                if (grass.getImg().getBoundsInParent().intersects(domesticAnimal.image.getBoundsInParent())  &&  domesticAnimal.remainingLife <= 50){
                    domesticAnimal.remainingLife = 100;
                    domesticAnimal.lifeProgBar.setProgress(1);
                    removeGrassList.add(grass);
                }
            }
        }
        for (Grass grass : removeGrassList) {
            if (grassesList.contains(grass)){
                grassesList.remove(grass);
                pane.getChildren().remove(grass.getImg());
            }
        }
    }
    public void reduceLife(AnchorPane pane){
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            domesticAnimal.remainingLife -= 5;
            domesticAnimal.lifeProgBar.setProgress(domesticAnimal.remainingLife/100.0);
            if (domesticAnimal.remainingLife <= 0){
                removeAnimalList.add(domesticAnimal);
                pane.getChildren().remove(domesticAnimal.image);
                pane.getChildren().remove(domesticAnimal.lifeProgBar);
            }
        }
        for (Animal animal : removeAnimalList) {
            if (domesticAnimalsList.contains(animal)){
                domesticAnimalsList.remove(animal);
            }
        }
    }
    public void destroyDomesticAnimalAndProduct(AnchorPane pane){
        for (WildAnimal wildAnimal : wildAnimalsList) {
            for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
                if (wildAnimal.image.getBoundsInParent().intersects(domesticAnimal.image.getBoundsInParent())){
                    removeAnimalList.add(domesticAnimal);
                }
            }
            for (Product product : productsList) {
                if (wildAnimal.image.getBoundsInParent().intersects(product.image.getBoundsInParent())){
                    removeProductList.add(product);
                }
            }
        }
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (domesticAnimal.remainingLife == 0){
                removeAnimalList.add(domesticAnimal);
                pane.getChildren().remove(domesticAnimal.image);
            }
        }
        for (Product product : removeProductList) {
            if (productsList.contains(product)) {
                productsList.remove(product);
                pane.getChildren().remove(product.image);
            }
        }
        for (Animal animal : removeAnimalList) {
            if (domesticAnimalsList.contains(animal)) {
                domesticAnimalsList.remove(animal);
                pane.getChildren().remove(animal.image);
                pane.getChildren().remove(animal.lifeProgBar);
            }
        }
    }
    public void destroyWildAnimal(AnchorPane pane){
        for (Hound hound : houndsList) {
            for (WildAnimal wildAnimal : wildAnimalsList) {
                if (hound.image.getBoundsInParent().intersects(wildAnimal.image.getBoundsInParent())){
                    removeAnimalList.add(hound);
                    removeAnimalList.add(wildAnimal);
                }
            }
        }
        for (Animal animal : removeAnimalList) {
            if (houndsList.contains(animal)){
                houndsList.remove(animal);
                pane.getChildren().remove(animal.image);
            }
            if (wildAnimalsList.contains(animal)){
                wildAnimalsList.remove(animal);
                pane.getChildren().remove(animal.image);
            }
        }

    }
    public void collectProducts(AnchorPane pane){
        for (Cat cat : catsList) {
            for (Product product : productsList) {
                if (product.image.getBoundsInParent().intersects(cat.image.getBoundsInParent())  &&  Barn.getInstance().getFreeSpace() >= product.getBarnSpace()){
                    removeProductList.add(product);
                    pane.getChildren().remove(product.image);
                    productsInBarn.put(product,1);
                    Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace()-product.getBarnSpace());
                }
            }
        }
        int i = 1;
        for (Product product : removeProductList) {
            if (productsList.contains(product)) {
                productsList.remove(product);
                if (i == removeProductList.size()){
                    removeProductList.clear();
                    return;
                }
                i++;
            }
        }
    }
    public Animal buyAnimal(String name) throws FileNotFoundException {
        switch (name){
            case "Buffalo" -> {
                if (player.getCoins() >= 400) {
                    Buffalo buffalo = new Buffalo(random.nextInt(mapWidth)+distanceMapAndPageWidth,random.nextInt(mapHeight)+distanceMapAndPageHeight);
                    player.setCoins(player.getCoins()-buffalo.price);
                    domesticAnimalsList.add(buffalo);
                    buffalo.startProduceProduct = new TIME(level.time.n);
                    return buffalo;
                }else return null;
            }
            case "Cat" -> {
                if (player.getCoins() >= 150) {
                    Cat cat = new Cat(random.nextInt(mapWidth)+distanceMapAndPageWidth,random.nextInt(mapHeight)+distanceMapAndPageHeight);
                    player.setCoins(player.getCoins()-cat.price);
                    catsList.add(cat);
                    return cat;
                }else return null;
            }
            case "Hen" -> {
                if (player.getCoins() >= 100) {
                    Hen hen = new Hen(random.nextInt(mapWidth)+distanceMapAndPageWidth,random.nextInt(mapHeight)+distanceMapAndPageHeight);
                    player.setCoins(player.getCoins()-hen.price);
                    domesticAnimalsList.add(hen);
                    hen.startProduceProduct = new TIME(level.time.n);
                    return hen;
                }else return null;
            }
            case "Hound" -> {
                if (player.getCoins() >= 100) {
                    Hound hound = new Hound(random.nextInt(mapWidth)+distanceMapAndPageWidth,random.nextInt(mapHeight)+distanceMapAndPageHeight);
                    player.setCoins(player.getCoins()-hound.price);
                    houndsList.add(hound);
                    return hound;
                }else return null;
            }
            case "Turkey" -> {
                if (player.getCoins() >= 200) {
                    Turkey turkey = new Turkey(random.nextInt(mapWidth)+distanceMapAndPageWidth,random.nextInt(mapHeight)+distanceMapAndPageHeight);
                    player.setCoins(player.getCoins()-turkey.price);
                    domesticAnimalsList.add(turkey);
                    turkey.startProduceProduct = new TIME(level.time.n);
                    return turkey;
                }else return null;
            }
        }
        return null;
    }
    public String buyWorkshop(String name) throws FileNotFoundException {
        for (WorkShop workShop : workShops) {
            if (workShop.name.equals(name)){
                return "have";
            }
        }
        StringBuilder stringBuilder = new StringBuilder("");
        switch (name) {
            case "bakery" -> {
                if (player.getCoins() >= Bakery.getInstance().getCost()){
                    workShops.add(Bakery.getInstance());
                    player.setCoins(player.getCoins()-Bakery.getInstance().getCost());
                    stringBuilder.append("bakery").append(" ").append(Bakery.getInstance().input.getName()).append(" ").append(Bakery.getInstance().output.getName());
                }
                else
                    return "coins";
            }
            case "iceCreamSelling" -> {
                if (player.getCoins() >= IceCreamSelling.getInstance().getCost()){
                    workShops.add(IceCreamSelling.getInstance());
                    player.setCoins(player.getCoins()-IceCreamSelling.getInstance().getCost());
                    stringBuilder.append("iceCreamSelling").append(" ").append(IceCreamSelling.getInstance().input.getName()).append(" ").append(IceCreamSelling.getInstance().output.getName());
                }
                else
                    return "coins";
            }
            case "milkPackaging" -> {
                if (player.getCoins() >= MilkPackaging.getInstance().getCost()){
                    workShops.add(MilkPackaging.getInstance());
                    player.setCoins(player.getCoins()-MilkPackaging.getInstance().getCost());
                    stringBuilder.append("milkPacking").append(" ").append(MilkPackaging.getInstance().input.getName()).append(" ").append(MilkPackaging.getInstance().output.getName());
                }
                else
                    return "coins";
            }
            case "mill" -> {
                if (player.getCoins() >= Mill.getInstance().getCost()){
                    workShops.add(Mill.getInstance());
                    player.setCoins(player.getCoins()-Mill.getInstance().getCost());
                    stringBuilder.append("mill").append(" ").append(Mill.getInstance().input.getName()).append(" ").append(Mill.getInstance().output.getName());
                }
                else
                    return "coins";
            }
            case "sewing" -> {
                if (player.getCoins() >= SewingWS.getInstance().getCost()){
                    workShops.add(SewingWS.getInstance());
                    player.setCoins(player.getCoins()-SewingWS.getInstance().getCost());
                    stringBuilder.append("sewing").append(" ").append(SewingWS.getInstance().input.getName()).append(" ").append(SewingWS.getInstance().output.getName());
                }
                else
                    return "coins";
            }
            case "weaving" -> {
                if (player.getCoins() >= WeavingWS.getInstance().getCost()){
                    workShops.add(WeavingWS.getInstance());
                    player.setCoins(player.getCoins()-WeavingWS.getInstance().getCost());
                    stringBuilder.append("weaving").append(" ").append(WeavingWS.getInstance().input.getName()).append(" ").append(WeavingWS.getInstance().output.getName());
                }
                else
                    return "coins";
            }
            case "incubator" -> {
                if (player.getCoins() >= IncubatorWS.getInstance().getCost()){
                    workShops.add(IncubatorWS.getInstance());
                    player.setCoins(player.getCoins()-IncubatorWS.getInstance().getCost());
                    stringBuilder.append("incubator").append(" ").append(IncubatorWS.getInstance().input.getName()).append(" ").append("hen");
                }
                else
                    return "coins";
            }
        }
        if (stringBuilder.length() != 0)
            return stringBuilder.toString();
        else return "Invalid";
    }
    public String upgradeWorkshop(String name){
        for (WorkShop workShop : workShops) {
            if (workShop.name.equals(name)){
                if (level.coins >= workShop.getUpgradeCost()){
                    workShop.upgrading();
                    level.coins -= workShop.getUpgradeCost();
                    return workShop.name;
                }
                return "coins";
            }
        }
        return "error";
    }
    public String pickupProduct(int x, int y, AnchorPane pane){
        for (Product product : productsList) {
            if (product.getX() == x  &&  product.getY() == y){
                if (Barn.getInstance().getFreeSpace() >= product.getBarnSpace()) {
                    pane.getChildren().remove(product.image);
                    removeProductList.add(product);
                    productsInBarn.put(product,1);
                    Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace()-product.getBarnSpace());
                }else return "barnSpace";
            }
        }
        int i = 0;
        String transfer = "";
        for (Product product : removeProductList) {
            if (productsList.contains(product)) {
                productsList.remove(product);
                transfer += product.getName()+",";
                i++;
                if (i == removeProductList.size()){
                    removeProductList.clear();
                    return transfer;
                }
            }
        }
        return "wrongLocation";
    }
    public String fillWaterBucket(){
        if (Well.getInstance().getRemainingWater() > 0)
            return "haveWater";
        else {
            Well.getInstance().setRemainingWater(5);
            return "filled";
        }
    }
    public ImageView planting(int x, int y) throws FileNotFoundException {
        if (Well.getInstance().getRemainingWater() > 0) {
            for (Grass grass : grassesList) {
                if (grass.getX() == x  &&  grass.getY() == y) {
                    ImageView img = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\errorGrass.png")));
                    img.setFitWidth(80);
                    img.setFitHeight(50);
                    return img;
                }
            }
            Grass grass = new Grass(x, y);
            grassesList.add(grass);
            Well.getInstance().setRemainingWater(Well.getInstance().getRemainingWater()-1);
            grass.getImg().setX(x);
            grass.getImg().setY(y);
            return grass.getImg();
        }
        return null;
    }
    public String
    startingWorkshop(String name){
        for (WorkShop workShop : workShops) {
            if (workShop.name.equals(name)){
                if (workShop.isWorking){
                    return "c";
                }
                Product[] product = new Product[workShop.level];
                int i = 0;
                for (Map.Entry<Product, Integer> entry : productsInBarn.entrySet()){
                    if (entry.getKey().getName().equals(workShop.input.getName()) && i < workShop.level){
                        product[i] = entry.getKey();
                        i++;
                    }
                }
                if (product[0] == null){
                    return "b";
                }
                workShop.isWorking = true;
                if (workShop.level == 1){
                    productsInBarn.remove(product[0]);
                    Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace()+product[0].getBarnSpace());
                    workShop.setStartTime(level.time);
                    return workShop.name.concat(String.valueOf(workShop.productionTime));
                }
                if (product[1] != null){
                    productsInBarn.remove(product[0]);
                    productsInBarn.remove(product[1]);
                    Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace()+product[0].getBarnSpace());
                    Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace()+product[1].getBarnSpace());
                    workShop.setStartTime(level.time);
                    return workShop.name.concat(String.valueOf(workShop.productionTime));
                }
                return "b";
            }
        }
        return "a";
    }
    public String checkWorkshops(AnchorPane pane){
        for (WorkShop workShop : workShops) {
            if (workShop.isProductReady(level.time)){
                workShop.isWorking = false;
                for (int i = 0; i < workShop.level; i++) {
                    if (workShop.name.equals("incubator")){
                        Hen hen = null;
                        try {
                            hen = new Hen(random.nextInt(mapWidth)+distanceMapAndPageWidth,random.nextInt(mapHeight)+distanceMapAndPageHeight);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        player.setCoins(player.getCoins()-hen.price);
                        domesticAnimalsList.add(hen);
                        hen.startProduceProduct = new TIME(level.time.n);
                        hen.image.setVisible(true);
                        hen.lifeProgBar.setProgress(1);
                        hen.lifeProgBar.setPrefSize(50,10);
                        hen.lifeProgBar.setLayoutX(hen.image.getX());
                        hen.lifeProgBar.setLayoutY(hen.image.getY()+hen.image.getFitHeight());
                        pane.getChildren().add(hen.lifeProgBar);
                        GUI.playSound(new File("src\\sample\\pictures\\hen.wav")).start();
                        pane.getChildren().addAll(hen.image);
                    }
                    else {
                        Product product = workShop.producing();
                        product.setStartDisappearTime(level.time);
                        productsList.add(product);
                        product.image.setX(random.nextInt(mapWidth)+distanceMapAndPageWidth);
                        product.image.setY(random.nextInt(mapHeight)+distanceMapAndPageHeight);
                        product.image.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                pickupProduct(product.getX(),product.getY(),pane);

                            }
                        });
                        product.setStartDisappearTime(level.time);
                        if (!pane.getChildren().contains(product.image))
                            pane.getChildren().addAll(product.image);
                    }
                }
                workShop.setStartTime(new TIME(0));
                return workShop.name;
            }else if (workShop.startTime == null)
                return "does not work";
        }
        return "notReady";
    }
    public int cage(int x, int y, AnchorPane pane) throws FileNotFoundException {
        for (WildAnimal wildAnimal : wildAnimalsList) {
            if (wildAnimal.X == x  &&  wildAnimal.Y == y){
                if (wildAnimal.cageLevel != wildAnimal.cageLevelRequired) {
                    pane.getChildren().remove(wildAnimal.cage);
                    wildAnimal.cageLevel++;
                    wildAnimal.cage = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\cage" + wildAnimal.cageLevel + ".png")));
                    wildAnimal.cage.setX(wildAnimal.X - 30);
                    wildAnimal.cage.setY(wildAnimal.Y - 50);
                    wildAnimal.cage.setVisible(true);
                }
                pane.getChildren().addAll(wildAnimal.cage);
                if (wildAnimal.cageLevel == wildAnimal.cageLevelRequired){
                    if (Barn.getInstance().getFreeSpace() >= wildAnimal.OccupiedSpace){
                        animalInBarn.put(wildAnimal,1);
                        Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace() - wildAnimal.OccupiedSpace);
                        pane.getChildren().remove(wildAnimal.image);
                        pane.getChildren().remove(wildAnimal.cage);
                    }
                    removeAnimalList.add(wildAnimal);
                }
                wildAnimal.decreaseCageLevel = true;
                wildAnimal.useCageOrder = true;
                return wildAnimal.cageLevel;
            }
        }
        return -1;
    }
    public String loadingProducts(String name, int amount){
        if (checkTrip() != 0)
            return "Traveling";
        else {
            int count = 0;
            for (Map.Entry<Product, Integer> entry : productsInBarn.entrySet()){
                if (entry.getKey().getName().equals(name)){
                    count++;
                }
            }
            for (Map.Entry<Animal, Integer> entry : animalInBarn.entrySet()){
                if (entry.getKey().name.equals(name)){
                    count++;
                }
            }
            if (count == 0)
                return "notInBarn";
            if (amount > count)
                return "notEnoughSpace";
            Product[] inTruck = new Product[amount];
            int i = 0;
            for (Map.Entry<Product, Integer> entry : productsInBarn.entrySet()){
                if (entry.getKey().getName().equals(name) && i < amount){
                    inTruck[i] = entry.getKey();
                    i++;
                }
            }
            for (Product product : inTruck) {
                if (product != null){
                    productsInBarn.remove(product);
                    loadedProducts.put(product, 1);
                }
            }
            if (inTruck[0] != null){
                Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace() + amount * inTruck[0].getBarnSpace());
                Car.getInstance().setEmptySpace(Car.getInstance().getEmptySpace() - amount * inTruck[0].getBarnSpace());
            }
            WildAnimal[] animalsInTruck = new WildAnimal[amount];
            for (Map.Entry<Animal, Integer> entry : animalInBarn.entrySet()){
                if (entry.getKey().name.equals(name) && i < amount && entry.getKey() instanceof WildAnimal){
                    animalsInTruck[i] =(WildAnimal) entry.getKey();
                    i++;
                }
            }
            for (WildAnimal wildAnimal : animalsInTruck) {
                if (wildAnimal != null){
                    animalInBarn.remove(wildAnimal);
                    loadedAnimals.add(wildAnimal);
                }
            }
            if (animalsInTruck[0] != null){
                Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace() + amount * animalsInTruck[0].OccupiedSpace);
                Car.getInstance().setEmptySpace(Car.getInstance().getEmptySpace() - amount * animalsInTruck[0].OccupiedSpace);
            }
        }
        return "loaded";
    }
    public String  unLoadingProducts(String name){
        if (checkTrip() != 0)
            return "Traveling";
        else {
            boolean isAnimal = false;
            String[] wildAnimalNames = {"Lion", "Tiger", "Bear"};
            for (int i = 0; i < 3; i++) {
                if (name.equals(wildAnimalNames[i]))
                    isAnimal = true;
            }
            if (isAnimal){
                int i = 0;
                WildAnimal[] inTruck = new WildAnimal[loadedAnimals.size()];
                for (WildAnimal loadedAnimal : loadedAnimals) {
                    if (loadedAnimal.name.equals(name)){
                        inTruck[i] = loadedAnimal;
                        i++;
                    }
                }
                int spaceNeeded = 0;
                for (int j = 0; j < i; j++) {
                    spaceNeeded += inTruck[j].OccupiedSpace;
                }
                if (spaceNeeded > Barn.getInstance().getFreeSpace())
                    return "notEnoughSpace";
                for (WildAnimal wildAnimal : inTruck) {
                    loadedAnimals.remove(wildAnimal);
                    animalInBarn.put(wildAnimal, 1);
                }
                Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace() - spaceNeeded);
                Car.getInstance().setEmptySpace(Car.getInstance().getEmptySpace() + spaceNeeded);
            }
            else {
                int i = 0;
                Product[] inTruck = new Product[loadedProducts.size()];
                for (Map.Entry<Product, Integer> entry : loadedProducts.entrySet()){
                    if (entry.getKey().getName().equals(name)){
                        inTruck[i] = entry.getKey();
                        i++;
                    }
                }
                int spaceNeeded = 0;
                for (int j = 0; j < i; j++) {
                    spaceNeeded += inTruck[j].getBarnSpace();
                }
                if (spaceNeeded > Barn.getInstance().getFreeSpace())
                    return "notEnoughSpace";
                for (Product product : inTruck) {
                    loadedProducts.remove(product);
                    productsInBarn.put(product, 1);
                }
                Barn.getInstance().setFreeSpace(Barn.getInstance().getFreeSpace() - spaceNeeded);
                Car.getInstance().setEmptySpace(Car.getInstance().getEmptySpace() + spaceNeeded);
            }
        }
        return "unLoaded";
    }
    public void startTrip(){
        Car.getInstance().setStartTrip(new TIME(level.time.n));
        Car.getInstance().isTraveling = true;
    }
    public int checkTrip(){
        if (Car.getInstance().getStartTrip() == null)
            return 0;
        if (!Car.getInstance().IsCarBack(level.time))
            return TIME.diff(level.time,Car.getInstance().getStartTrip());
        else return 0;
    }
    public int sellProducts(){
        if (Car.getInstance().getStartTrip() == null)
            return -1;
        if (Car.getInstance().IsCarBack(level.time)){
            Car.getInstance().setStartTrip(null);
            Car.getInstance().isTraveling = false;
            Car.getInstance().setEmptySpace(15);
            int sellPrice = 0;
            if (!loadedProducts.isEmpty()){
                for (Map.Entry<Product, Integer> entry : loadedProducts.entrySet()){
                    sellPrice += entry.getKey().getSellingPrice();
                }
                loadedProducts.clear();
            }
            if (!loadedAnimals.isEmpty()){
                for (WildAnimal loadedAnimal : loadedAnimals) {
                    sellPrice += loadedAnimal.price;
                }
                loadedAnimals.clear();
            }
            player.setCoins(player.getCoins()+sellPrice);
            return sellPrice;
        }
        return -1;
    }
    public String checkTasks(){
        if (level.tasks.containsKey("coins")){
            if (level.coins >= level.tasks.get("coins")){
                level.tasks.remove("coins");
                return "coins";
            }
        }
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (level.tasks.containsKey(domesticAnimal.name)  &&  !animalsCalculatedInTask.contains(domesticAnimal)) {
                animalsCalculatedInTask.add(domesticAnimal);
                int animalNum = level.tasks.get(domesticAnimal.name) - 1;
                if (animalNum == 0) {
                    level.tasks.remove(domesticAnimal.name);
                    return domesticAnimal.name;
                }
                level.tasks.replace(domesticAnimal.name, animalNum);
            }
        }
        for (Map.Entry<Product,Integer> entry : productsInBarn.entrySet()) {
            if (level.tasks.containsKey(entry.getKey().getName()) && !productsCalculatedInTask.contains(entry.getKey())) {
                productsCalculatedInTask.add(entry.getKey());
                int productNum = level.tasks.get(entry.getKey().getName()) - 1;
                if (productNum == 0) {
                    level.tasks.remove(entry.getKey().getName());
                    return entry.getKey().getName();
                }
                level.tasks.replace(entry.getKey().getName(), productNum);
            }
        }
        return "null";
    }
    public boolean isLevelCompleted(){
        if (level.tasks.isEmpty()){
            level.finishTime = new TIME(level.time);
            if (level.finishTime.n <= level.goldenFinishTime.n){
                level.status = "Golden";
            }
            else if (level.finishTime.n <= level.silverFinishTime.n){
                level.status = "Silver";
            }
            else{
                level.status = "Bronze";
            }
            return true;
        }
        return false;
    }
    public void isAnimalManufacturedProduct(AnchorPane ground) throws FileNotFoundException {
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (level.time.n-domesticAnimal.startProduceProduct.n == domesticAnimal.timeRequiredToProduct){
                Product product = domesticAnimal.outProduct(level.time);
                domesticAnimal.startProduceProduct = new TIME(level.time.n);
                product.setStartDisappearTime(new TIME(level.time));
                product.setX(domesticAnimal.X+10);
                product.setY(domesticAnimal.Y+10);
                productsList.add(product);
                product.image.setX(product.getX());
                product.image.setY(product.getY());
                product.image.setVisible(true);
                product.image.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        pickupProduct(product.getX(),product.getY(),ground);

                    }
                });
                ground.getChildren().addAll(product.image);
            }
        }
    }
    public void disappearProduct(AnchorPane pane){
        for (Product product : productsList) {
            if (TIME.diff(product.getStartDisappearTime(),level.time) == product.getDisappearTime()){
                removeProductList.add(product);
                pane.getChildren().remove(product.image);
            }
        }
        for (Product product : removeProductList) {
            productsList.remove(product);
        }
        removeProductList.clear();
    }
    public void appearWildAnimal(AnchorPane ground) throws FileNotFoundException {
        for (Map.Entry<String,Integer> entry : level.wildAnimals.entrySet()){
            if (entry.getValue() == level.time.n){
                switch (entry.getKey()){
                    case "Tiger" ->{
                        Tiger tiger = new Tiger(random.nextInt(mapWidth)+distanceMapAndPageWidth,random.nextInt(mapHeight)+distanceMapAndPageHeight);
                        wildAnimalsList.add(tiger);
                        tiger.image.setX(tiger.X);
                        tiger.image.setY(tiger.Y);
                        tiger.image.setVisible(true);
                        GUI.playSound(new File("src\\sample\\pictures\\Tiger.wav")).start();
                        tiger.image.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                try {
                                    cage(tiger.X,tiger.Y,ground);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        ground.getChildren().addAll(tiger.image);
                    }
                    case "Lion" ->{
                        Lion lion = new Lion(random.nextInt(mapWidth)+distanceMapAndPageWidth,random.nextInt(mapHeight)+distanceMapAndPageHeight);
                        wildAnimalsList.add(lion);
                        lion.image.setX(lion.X);
                        lion.image.setY(lion.Y);
                        lion.image.setVisible(true);
                        GUI.playSound(new File("src\\sample\\pictures\\Lion.wav")).start();
                        lion.image.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                try {
                                    cage(lion.X,lion.Y,ground);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        ground.getChildren().addAll(lion.image);
                    }
                    case "Bear" ->{
                        Bear bear = new Bear(random.nextInt(mapWidth)+distanceMapAndPageWidth,random.nextInt(mapHeight)+distanceMapAndPageHeight);
                        wildAnimalsList.add(bear);
                        bear.image.setX(bear.X);
                        bear.image.setY(bear.Y);
                        bear.image.setVisible(true);
                        GUI.playSound(new File("src\\sample\\pictures\\Tiger.wav")).start();
                        bear.image.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                try {
                                    cage(bear.X,bear.Y,ground);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        ground.getChildren().addAll(bear.image);
                    }
                }
            }
        }
    }
    public void decreaseCageLevel(AnchorPane pane) throws FileNotFoundException {
        for (WildAnimal wildAnimal : wildAnimalsList) {
            if (wildAnimal.useCageOrder){
                if (!wildAnimal.decreaseCageLevel){
                    pane.getChildren().remove(wildAnimal.cage);
                    wildAnimal.cageLevel--;
                    if (wildAnimal.cageLevel == 0  &&  wildAnimal.startTimeBreakCage == 0){
                        wildAnimal.startTimeBreakCage = level.time.n;
                        wildAnimal.breakCage.setX(wildAnimal.X-45);
                        wildAnimal.breakCage.setY(wildAnimal.Y-50);
                        wildAnimal.breakCage.setVisible(true);
                        pane.getChildren().addAll(wildAnimal.breakCage);
                    }else if (wildAnimal.cageLevel > 0){
                        wildAnimal.cage = new ImageView(new Image(new FileInputStream("src\\sample\\pictures\\cage" + wildAnimal.cageLevel + ".png")));
                        wildAnimal.cage.setX(wildAnimal.X-20);
                        wildAnimal.cage.setY(wildAnimal.Y-50);
                        wildAnimal.cage.setVisible(true);
                        pane.getChildren().addAll(wildAnimal.cage);
                    }
                }
            }
            if (level.time.n - wildAnimal.startTimeBreakCage == 3  &&  wildAnimal.startTimeBreakCage > 0){
                removeAnimalList.add(wildAnimal);
                pane.getChildren().remove(wildAnimal.breakCage);
                pane.getChildren().remove(wildAnimal.image);
            }
            wildAnimal.decreaseCageLevel = false;
        }
        for (Animal animal : removeAnimalList) {
            wildAnimalsList.remove(animal);
        }
    }
    public String space(int n,String word){
        StringBuilder stringBuilder =new StringBuilder("");
        for (int i = 0; i < n-word.length(); i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}