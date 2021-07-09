package sample;

public abstract class WorkShop {
    protected int level;
    protected int cost;
    protected Product input;
    protected Product output;
    protected TIME productionTime;
    protected int upgradeCost;
    protected TIME startTime;
    protected String name;
    protected boolean isWorking;

    public int getCost() {
        return cost;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public Product getInput() {
        return input;
    }

    public void setStartTime(TIME startTime) {
        this.startTime = new TIME(startTime);
    }

    protected void upgrading(){
        if (productionTime.n % 2 == 0)
            productionTime.n /= 2;
        else
            productionTime.n = (productionTime.n+1)/2;
        this.level ++;
    }

    abstract Product producing();

    protected boolean isProductReady(TIME time){
        if (this.startTime == null)
            return false;
        return TIME.diff(time, this.startTime) == productionTime.n;
    }
}
