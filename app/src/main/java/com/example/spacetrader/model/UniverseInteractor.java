package com.example.spacetrader.model;

import com.example.spacetrader.entity.Universe;

public class UniverseInteractor {
    private static Universe universe;

    public static Universe getUniverse() {
        return universe;
    }

    public static void setUniverse(Universe newUniverse) {
        universe=newUniverse;
    }
}
