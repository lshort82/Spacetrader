package com.example.spacetrader.entity;

import java.util.List;

public interface Visitable {
    public void onVisit(); //does something when visited, this may need to be a String return type.
    public boolean hasinterior();//has sub visitables
    public List<Visitable> getInterior();
    public boolean hasMarket();
    public Market getMarket();
    public String getName();
    //public void setMarket();
}
