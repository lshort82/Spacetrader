package com.example.spaceTrader.model;

import com.example.spaceTrader.entity.Visitable;

import java.util.List;

public class VisitableInteractor {
    private static List<Visitable> visitableList;

    public static List<Visitable> getVisitableList() {
        return visitableList;
    }

    public static void setVisitableList(List<Visitable> newVisitableList){
        visitableList = newVisitableList;
    }
}
