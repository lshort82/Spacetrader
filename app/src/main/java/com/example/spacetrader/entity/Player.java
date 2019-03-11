package com.example.spacetrader.entity;

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
    }

    public void removeItem(Item item, int quantity){
        if(inventory.contains(item)) {
            this.quantity.set(inventory.indexOf(item), this.quantity.get(inventory.indexOf(item)) - quantity);
        }
    }

    public int getItemQuantity(Item item){
        if(inventory.contains(item)){
            return quantity.get(inventory.indexOf(item));
        }
        return  0;
    }
    public void setShip(String ship){this.ship = ship;}

    public String toString(){
        return "name: " + name + " difficulty: " + difficulty +  " ship: " + ship +  " pilot Skill: " + pilotPoints +  " fighter Skill: " + fighterPoints
                +  " trader Skill: " + traderPoints +  " engineer Skill: " + engineerPoints +  " credits: " + credits;
    }

}
