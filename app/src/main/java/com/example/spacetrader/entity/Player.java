package com.example.spacetrader.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private String name;
    private String difficulty;
    private String ship;
    private List<Item> inventory;
    private List<Integer> quantity;
    private int spaceLeft;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private int credits;
    private int x;
    private int y;
    private int fuel;

    public static List<String> legalDifficulties = Arrays.asList("Easy", "Normal", "Difficult", "Insane", "Impossible");

    public Player(){
        this("","","", 0,0,0,0,0);
    }

    public Player(String nam, String diff, String ship, int pPoints, int fPoints, int tPoints, int ePoints, int credits) {
        name = nam;
        difficulty = diff;
        this.ship = ship;
        pilotPoints = pPoints;
        fighterPoints = fPoints;
        traderPoints = tPoints;
        engineerPoints = ePoints;
        this.credits = credits;
        spaceLeft= 10;
        inventory = new ArrayList<Item>();
        quantity = new ArrayList<Integer>();
        x = 15;
        y = 15;
        fuel = 30;
    }

    public Player(String name, String diff, String ship, int spaceLeft, int pPoints, int fPoints, int tPoints, int ePoints, int credits, int x, int y, int fuel, List<Item> inventory, List<Integer> quantity) {
        this.name = name;
        difficulty = diff;
        this.ship = ship;
        pilotPoints = pPoints;
        fighterPoints = fPoints;
        traderPoints = tPoints;
        engineerPoints = ePoints;
        this.credits = credits;
        this.spaceLeft= spaceLeft;
        this.inventory = inventory;
        this.quantity = quantity;
        this.x = x;
        this.y = y;
        this.fuel = fuel;
    }
    public void setName(String nam) {
        name = nam;
    }

    public String getName() {
        return name;
    }

    public void setDifficulty(String diff) {
        difficulty = diff;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setPilotPoints(int points) {
        pilotPoints = points;
    }

    public int getPilotPoints() {
        return pilotPoints;
    }

    public void setFighterPoints(int points) {
        fighterPoints = points;
    }

    public int getFighterPoints() {
        return fighterPoints;
    }

    public void setTraderPoints(int points) {
        traderPoints = points;
    }

    public int getTraderPoints() {
        return traderPoints;
    }

    public void setEngineerPoints(int points) {
        engineerPoints = points;
    }

    public int getEngineerPoints() {
        return engineerPoints;
    }

    public void setCredits(int credits){this.credits = credits;}

    public int getCredits(){return credits;}

    public String getShip(){return ship;}

    public int spaceLeft(){
        return spaceLeft;
    }

    public void addItem(Item item, int quantity) {
        if(inventory.contains(item)) {
            this.quantity.set(inventory.indexOf(item),this.quantity.get(inventory.indexOf(item)) + quantity);
        }
        else{
            inventory.add(item);
            this.quantity.add(quantity);
        }
        spaceLeft -= quantity;
    }

    public int removeItem(Item item, int quantity){
        int ret = -1;
        if(inventory.contains(item)) {
            ret = inventory.indexOf(item);
            this.quantity.set(inventory.indexOf(item), this.quantity.get(inventory.indexOf(item)) - quantity);
            if(this.quantity.get(inventory.indexOf(item))==0){
                int i = inventory.indexOf(item);
                inventory.remove(item);
                this.quantity.remove(i);
            }
            spaceLeft += quantity;
        }
        return ret;
    }

    public int getItemQuantity(Item item){
        if(inventory.contains(item)){
            return quantity.get(inventory.indexOf(item));
        }
        return  0;
    }

    public List<Item> getInventory(){
        return  inventory;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setShip(String ship){this.ship = ship;}

    public String toString(){
        return "name: " + name + " difficulty: " + difficulty +  " ship: " + ship +  " pilot Skill: " + pilotPoints +  " fighter Skill: " + fighterPoints
                +  " trader Skill: " + traderPoints +  " engineer Skill: " + engineerPoints +  " credits: " + credits;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void useFuel(int x) {
        fuel -= x;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public void makeStatChange(String category, int amount) {
        if (category.equals("pilot")) {
            pilotPoints += amount;
        }
        if (category.equals("fighter")) {
            fighterPoints += amount;
        }
        if (category.equals("trader")) {
            traderPoints += amount;
        }
        if (category.equals("engineer")) {
            engineerPoints += amount;
        }
    }

    public String getSaveFormat() {
        String s = name + ";"
                + difficulty +";"
                + ship + ";"
                + spaceLeft + ";"
                + pilotPoints + ";"
                + fighterPoints + ";"
                + traderPoints  + ";"
                + engineerPoints + ";"
                + credits  + ";"
                + x + ";"
                + y + ";"
                + fuel + ";";
        for(Item e : inventory) {
            s = s + e.getIndex() + ",";
        }
        if(inventory.size() == 0) {
            s = s + "empty";
        }
        if(inventory.size() > 0) {
            s = s.substring(0, s.length() - 1);
        }
        s = s + ";";
        for(Integer e : quantity) {
            s = s + e + ",";
        }
        if(quantity.size() == 0) {
            s = s + "empty";
        }
        if(quantity.size() > 0) {
            s = s.substring(0, s.length() - 1);
        }
        s = s + ";";
        return s;
    }
}
