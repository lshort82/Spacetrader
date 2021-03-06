package com.example.spaceTrader.entity;

import java.io.Serializable;

public enum Resources implements Serializable {
    NO_SPECIAL_RESOURCES(0, "No Special Resources"),
    MINERAL_RICH(1, "Mineral Rich"),
    MINERAL_POOR(2, "Mineral Poor"),
    DESERT(3, "Desert"),
    LOTS_OF_WATER(4, "Lots of Water"),
    RICH_SOIL(5, "Rich Soil"),
    POOR_SOIL(6, "Poor Soil"),
    RICH_FAUNA(7, "Rich Fauna"),
    LIFELESS(8, "Lifeless"),
    WEIRD_MUSHROOMS(9, "Weird Mushrooms"),
    LOTS_OF_HERBS(10, "Lots of Herbs"),
    ARTISTIC(11, "Artistic"),
    WARLIKE(12, "Warlike");

    private final int index;
    private final String description;
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
