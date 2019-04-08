package com.example.spaceTrader.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Universe implements Serializable {
    private final Visitable[][] cosmicEntities;
    public Universe(){
        cosmicEntities = new Visitable[30][30];
        cosmicEntities[15][15] = new SolarSystem(15,15,"Sol");
        populate(10);
    }
    public Universe(int xDimension, int yDimension, int numEntities) {
        if(xDimension <= 0 || yDimension <= 0) {
            throw new java.lang.IllegalArgumentException("Cannot have negative or 0 size");
        }
        cosmicEntities = new Visitable[xDimension][yDimension];
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

    public Visitable getEntity(int x, int y){
        return cosmicEntities[x][y];
    }
    public List<Visitable> inRange(int x, int y, int fuel) {
        List<Visitable> ret = new ArrayList<>();
        for(int i =0; i<cosmicEntities.length; i++) {
            for(int j=0; j<cosmicEntities[0].length;j++) {
                if(cosmicEntities[i][j] != null && (Math.abs(i - x) < fuel || Math.abs(j-y)< fuel)){
                    ret.add(cosmicEntities[i][j]);
                }
            }
        }
        return ret;
    }

    @Override
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
        List<String> ret = new ArrayList<>();
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
