package com.example.spaceTrader.entity;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

public class Planet implements Visitable, Serializable {
    private final String name;
    private final List<Integer> resources;
    private final int technologyLevel;
    private final int position;
    private final Market market;
    public Planet(String name, List<Integer> resources, int technologyLevel, int position) {
        this.name = name;
        this.resources = resources;
        this.technologyLevel = technologyLevel;
        this.position = position;
        this.market = new Market(this);
    }

    public void onVisit() {
        Log.i("Visit","Visiting " + this.toString());
    }

    public int getTechnologyLevel() {
        return technologyLevel;
    }

// --Commented out by Inspection START (4/8/2019 1:41 PM):
// --Commented out by Inspection START (4/8/2019 1:41 PM):
////    public int getPosition() {
////        return position;
////    }
//// --Commented out by Inspection STOP (4/8/2019 1:41 PM)
// --Commented out by Inspection STOP (4/8/2019 1:41 PM)


    public List<Integer> getResources(){
        return resources;
    }

    public String getName(){
        return name;
    }

    public String resourceString(){
        StringBuilder ret = new StringBuilder();
        for(Integer e : resources) {
            ret.append(Resources.values()[e].getDescription()).append(", ");
        }
        return ret.substring(0,ret.length() - 2);
    }

    public Market getMarket() {
        return market;
    }

    @Override
    public String toString(){
        return position + ") Planet " + name + "is at technology Level " + technologyLevel +
                " and has resources: " + resourceString();
    }
    public boolean hasInterior() {
        return false;
    }
    public List<Visitable> getInterior() {
        return null;
    }
    public boolean hasMarket() {
        return true;
    }
}
