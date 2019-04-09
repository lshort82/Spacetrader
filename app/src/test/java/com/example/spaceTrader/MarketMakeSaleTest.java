package com.example.spaceTrader;
import com.example.spaceTrader.entity.Item;
import com.example.spaceTrader.entity.Market;
import com.example.spaceTrader.entity.Player;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MarketMakeSaleTest {
    @Test
    public void testMarketMakePurchase() {
        Player player = new Player("player", "Easy", "gnat", 4, 4, 4, 4, 1000);
        //Space left will be 10
        List<Item> inventory = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();
        List<Integer> price = new ArrayList<>();
        inventory.add(Item.WATER);
        inventory.add(Item.ORE);
        inventory.add(Item.FURS);
        quantity.add(2);
        quantity.add(9);
        quantity.add(2);
        price.add(1);
        price.add(1);
        price.add(1000);

        Market market = new Market(inventory, quantity, price);

        market.makePurchase(player, Item.WATER, 1);
        assertEquals(player.getCredits(), 999);
        assertEquals(player.getItemQuantity(Item.WATER), 1);

        market.makePurchase(player, Item.FOOD, 1);
        assertEquals(player.getCredits(), 999);
        assertEquals(player.getItemQuantity(Item.FOOD), 0);

        market.makePurchase(player, Item.FURS, 1);
        assertEquals(player.getCredits(), 999);
        assertEquals(player.getItemQuantity(Item.FURS), 0);

        market.makePurchase(player, Item.ORE, 9);
        assertEquals(player.getCredits(), 990);
        assertEquals(player.getItemQuantity(Item.ORE), 9);
        assertEquals(player.spaceLeft(), 0);

        market.makePurchase(player, Item.WATER, 1);
        assertEquals(player.getCredits(), 990);
        assertEquals(player.getItemQuantity(Item.WATER), 1);
    }
}
