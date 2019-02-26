package com.example.spacetrader.entity;

import android.util.Log;

import java.util.List;

public class Planet implements Visitable {
    private String name;
    private List<Integer> resources;
    private int technologyLevel;
    private int position;

    public Planet(String name, List<Integer> resources, int technologyLevel, int position) {
        this.name = name;
        this.resources = resources;
        this.technologyLevel = technologyLevel;
        this.position = position;
    }

    public void onVisit() {
        Log.i("Visit","Vitisting " + this.toString());
    }

    public int getTechnologyLevel() {
        return technologyLevel;
    }

    public int getPosition() {
        return position;
    }


    public List<Integer> getResources(){
        return resources;
    }

    public String getName(){
        return name;
    }

    public String resourceString(){
        String ret = "";
        for(Integer e : resources) {
            ret += Resources.values()[e].getDescription() +", ";
        }
        return ret.substring(0,ret.length() - 2);
    }

    public String toString(){
        return position + ") Planet " + name + "is at technology Level " + technologyLevel +
                " and has resources: " + resourceString();
    }


}
