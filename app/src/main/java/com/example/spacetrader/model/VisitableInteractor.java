package com.example.spacetrader.model;

import com.example.spacetrader.entity.Visitable;

import java.util.List;

public class VisitableInteractor {
    private static List<Visitable> visitables;

    public static List<Visitable> getVisitables() {
        return visitables;
    }

    public static void setVisitables(List<Visitable> newVisitables){
        visitables = newVisitables;
    }
}
