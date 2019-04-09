package com.example.spacetrader.entity;

import java.util.List;

public interface Visitable {
    void onVisit(); //does something when visited, this may need to be a String return type.
    boolean hasInterior();
    List<Visitable> getInterior();
    boolean hasMarket();
    Market getMarket();
    String getName();
    //public void setMarket();
}
