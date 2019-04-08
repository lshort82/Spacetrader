package com.example.spaceTrader.model;

import com.example.spaceTrader.entity.Universe;

public class UniverseInteractor {
    private static Universe universe;

    public static Universe getUniverse() {
        if(universe == null) {
            return new Universe();
        }
        return universe;
    }

    public static void setUniverse(Universe newUniverse) {
        universe=newUniverse;
    }
}
