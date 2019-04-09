package com.example.spacetrader.model;

import com.example.spacetrader.entity.Visitable;

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
