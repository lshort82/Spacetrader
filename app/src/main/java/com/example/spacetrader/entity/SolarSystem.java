package com.example.spaceTrader.entity;

import android.support.annotation.NonNull;

import com.example.spaceTrader.model.PlayerInteractor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class SolarSystem implements Visitable, Serializable {
    private final List<Visitable> cosmicBodies;
    private final String name; // this is the name of the star;
    private final int xCoordinate;
    private final int yCoordinate;


    public SolarSystem(int x, int y){
        this.name = NameGenerator.generateName();
        this.xCoordinate = x;
        this.yCoordinate = y;
        cosmicBodies = new ArrayList<>();
        randomGen(new Random().nextInt(6) + 1);
    }

    public SolarSystem(int x, int y, String name){
        this.name = name;
        this.xCoordinate = x;
        this.yCoordinate = y;
        cosmicBodies = new ArrayList<>();
        cosmicBodies.add(new Planet("Earth", generateResources(), 7, 0));
    }

    public void onVisit(){
        Player player = PlayerInteractor.getPlayer();
        int distance = Math.abs(player.getX() - xCoordinate) < Math.abs(player.getY() - yCoordinate) ? Math.abs(player.getX() - xCoordinate) : Math.abs(player.getY() - yCoordinate);
        player.useFuel(distance);
        player.setX(xCoordinate);
        player.setY(yCoordinate);
        PlayerInteractor.setPlayer(player);
    }

    private void randomGen(int numBodies) {
        Random rand = new Random();
        for(int i = 0; i < numBodies; i++) {
            cosmicBodies.add(new Planet(NameGenerator.generateName(), generateResources(), rand.nextInt(7), i));
        }
    }

    private List<Integer> generateResources() {
        Random rand = new Random();
        List<Integer> ret = new ArrayList<>();
        RandomCollection<Integer> rc = new RandomCollection<Integer>()
                .add(40,0)
                .add(25,1)
                .add(15, 2)
                .add(10, 3)
                .add(9, 4)
                .add(.9,5)
                .add(.09,6)
                .add(.009,7)
                .add(.0009,8)
                .add(.00009,9)
                .add(.000009,10)
                .add(.0000009,11)
                .add(.0000001,12);
        for(int i = rc.next(); i > 0; i--) {
            int next = rand.nextInt(11) + 1;
            if(ret.contains(next)) {
                i++;
            } else {
                ret.add(next);
            }
        }
        if(ret.size() == 0) {
            ret.add(0);
        }
        return ret;
    }

// --Commented out by Inspection START (4/8/2019 1:41 PM):
//    public List<Visitable> getCosmicBodies() {
//        return cosmicBodies;
// --Commented out by Inspection START (4/8/2019 1:41 PM):
// --Commented out by Inspection START (4/8/2019 1:41 PM):
//////    }
////// --Commented out by Inspection STOP (4/8/2019 1:41 PM)
// --Commented out by Inspection STOP (4/8/2019 1:41 PM)
// --Commented out by Inspection STOP (4/8/2019 1:41 PM)

    public String getName() {
        return  name;
    }

    /*public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }*/

    @NonNull
    @Override
    public String toString(){
        String ret;
        ret = String.format(Locale.ENGLISH, "This is Solar System %s located at X:%d, Y:%d. It's cosmic bodies are:\n", name, xCoordinate, yCoordinate);
        for(Visitable e : cosmicBodies) {
            ret = String.format("%s\n%s\n",ret,e.toString());
        }
        return ret;
    }

    private class RandomCollection<E> {
        private final NavigableMap<Double, E> map = new TreeMap<>();
        private final Random random;
        private double total = 0;

        RandomCollection() {
            this(new Random());
        }

        RandomCollection(Random random) {
            this.random = random;
        }

        RandomCollection<E> add(double weight, E result) {
            if (weight <= 0) return this;
            total += weight;
            map.put(total, result);
            return this;
        }

        E next() {
            double value = random.nextDouble() * total;
            return map.higherEntry(value).getValue();
        }
    }

    public boolean hasInterior() {
        return true;
    }
    public List<Visitable> getInterior() {
        return cosmicBodies;
    }
    public boolean hasMarket() {
        return false;
    }
    public Market getMarket(){
        return null;
    }
}

