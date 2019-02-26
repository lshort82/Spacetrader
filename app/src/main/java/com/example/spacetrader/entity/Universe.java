package com.example.spacetrader.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Universe {
    private Visitable[][] cosmicEntities;
    public Universe(){
        cosmicEntities = new Visitable[150][150];
        populate(10);
    }
    public Universe(int xdim, int ydim, int numEntities) {
        if(xdim <= 0 || ydim <= 0) {
            throw new java.lang.IllegalArgumentException("Cannot have negative or 0 size");
        }
        cosmicEntities = new Visitable[xdim][ydim];
        populate(numEntities);
    }

    private void populate(int numEntities){
        Random rand = new Random();
        while(numEntities > 0) {
            int nextX = rand.nextInt(cosmicEntities.length);
            int nextY = rand.nextInt(cosmicEntities[0].length);
            if (cosmicEntities[nextX][nextY] == null) {
                cosmicEntities[nextX][nextY] = new SolarSystem(nextX,nextY);
                numEntities--;
            }
        }
    }

    public String toString() {
        String ret = "The universe contains the following entities:";
        for(Visitable[] e : cosmicEntities) {
            for(Visitable r: e) {
                if(r != null) {
                    ret = String.format("%s\n%s", ret, r.toString());
                }
            }
        }
        return ret;
    }

    public List<String> toStringList(){
        List<String> ret = new ArrayList<String>();
        for(Visitable[] e : cosmicEntities) {
            for(Visitable r: e) {
                if(r != null) {
                    ret.add(r.toString());
                }
            }
        }
        return ret;
    }

}
