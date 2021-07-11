package sample;

import java.io.FileNotFoundException;

public class IncubatorWS extends WorkShop{

    public IncubatorWS() throws FileNotFoundException {
        this.level = 1;
        this.cost = 600;
        this.productionTime = new TIME(10);
        this.input = new Product("egg",15,1,4,this.startTime,Manager.random.nextInt(6)+1,Manager.random.nextInt(6)+1);
        this.name = "incubator";
    }

    //Singleton Design
    private static IncubatorWS incubatorWSInstance;
    public static IncubatorWS getInstance() throws FileNotFoundException {
        if (incubatorWSInstance==null){
            incubatorWSInstance = new IncubatorWS();
        }
        return incubatorWSInstance;
    }

    @Override
    Product producing() {
        return null;
    }
}
