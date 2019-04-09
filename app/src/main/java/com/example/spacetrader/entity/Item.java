package com.example.spaceTrader.entity;

import java.io.Serializable;

public enum Item implements Serializable {
    WATER(0, "Water", 0, 0, 2, 30, 3, 4, "Drought", Resources.LOTS_OF_WATER, Resources.DESERT, 30, 50),
    FURS(1, "Furs", 0, 0, 0, 250, 10, 10, "Cold", Resources.RICH_FAUNA, Resources.LIFELESS, 230, 280),
    FOOD(2, "Food", 1, 0, 1, 100, 5, 5, "Crop fail", Resources.RICH_SOIL, Resources.POOR_SOIL, 90, 160),
    ORE(3, "Ore", 2, 2, 3, 350, 20, 10, "War", Resources.MINERAL_RICH, Resources.MINERAL_POOR, 350, 420),
    GAMES(4, "Games", 3, 1, 6, 250, -10, 5, "Boredom", Resources.ARTISTIC, null, 160, 270),
    FIREARMS(5,"Firearms",3, 1, 5, 1250, -75, 100, "WAR", Resources.WARLIKE, null, 600, 1100),
    MEDICINE(6, "Medicine", 4, 1, 6, 650, -20, 10, "PLAGUE", Resources.LOTS_OF_HERBS,	null, 400, 700),
    MACHINES(7, "Machines",4, 3, 5, 900, -30, 5, "LACK_OF_WORKERS", null, null, 600, 800),
    NARCOTICS(8, "Narcotics", 5, 0, 5, 3500, -125, 150, "BOREDOM", Resources.WEIRD_MUSHROOMS, null, 2000, 3000),
    ROBOTS(9, "Robots", 6, 4, 7, 5000, -150, 100, "LACK_OF_WORKERS", null, null, 3500, 5000);


    private final int index;
    private final String name;
    private final int minTechProd;
    private final int minTechUse;
    private final int maxTechProd;
    private final int price;
    private final int incPerTech;
    private final int variance;
    private final String event;
    private final Resources cheap;
    private final Resources expensive;
    private final int minSpacePrice;
    private final int maxSpacePrice;

    Item(int index, String name,int minTechProd, int minTechUse, int maxTechProd, int price, int incPerTech, int variance, String event, Resources cheap, Resources expensive, int minSpacePrice, int maxSpacePrice){
        this.index = index;
        this.name = name;
        this.minTechProd =minTechProd;
        this.minTechUse=minTechUse;
        this.maxTechProd=maxTechProd;
        this.price=price;
        this.incPerTech=incPerTech;
        this.variance=variance;
        this.event=event;
        this.cheap=cheap;
        this.expensive=expensive;
        this.minSpacePrice=minSpacePrice;
        this.maxSpacePrice=maxSpacePrice;
    }

    public int getIndex() {
        return  index;
    }

    public String getName() {
        return name;
    }

    /*public int getMaxSpacePrice() { return maxSpacePrice; }

    public int getMaxTechProd() {
        return maxTechProd;
    }

    public int getMinSpacePrice() {
        return minSpacePrice;
    }
*/
    public int getMinTechProd() {
        return minTechProd;
    }

  /*  public int getMinTechUse() { return minTechUse; }*/

    public int getPrice() {
        return price;
    }

    public int getVariance() {
        return variance;
    }

   /* public Resources getCheap() { return cheap; }

    public Resources getExpensive() { return expensive; }

    public String getEvent() {
        return event;
    }*/

    public int getIncPerTech() {
        return incPerTech;
    }
}
