package com.example.spacetrader.entity;

import java.io.Serializable;

public enum Resources implements Serializable {
    NOSPECIALRESOURCES(0, "No Special Resources"),
    MINERALRICH(1, "Mineral Rich"),
    MINERALPOOR(2, "Mineral Poor"),
    DESERT(3, "Desert"),
    LOTSOFWATER(4, "Lots of Water"),
    RICHSOIL(5, "Rich Soil"),
    POORSOIL(6, "Poor Soil"),
    RICHFAUNA(7, "Rich Fauna"),
    LIFELESS(8, "Lifeless"),
    WEIRDMUSHROOMS(9, "Weird Mushrooms"),
    LOTSOFHERBS(10, "Lots of Herbs"),
    ARTISTIC(11, "Artistic"),
    WARLIKE(12, "Warlike");

    private int index;
    private String description;

    Resources(int index, String description){
        this.index = index;
        this.description = description;
    }

    public int getIndex() {
        return  index;
    }

    public String getDescription() {
        return description;
    }
}
