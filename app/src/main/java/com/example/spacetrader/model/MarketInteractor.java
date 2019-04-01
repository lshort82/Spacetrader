package com.example.spacetrader.model;

import com.example.spacetrader.entity.Market;

public class MarketInteractor {
    private static Market market;

    public static Market getMarket() {
        return market;
    }

    public static void setMarket(Market newMarket) {
        market =newMarket;
    }

}
