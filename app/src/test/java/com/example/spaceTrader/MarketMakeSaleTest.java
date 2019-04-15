package com.example.spaceTrader;
import com.example.spaceTrader.entity.Item;
import com.example.spaceTrader.entity.Market;
import com.example.spaceTrader.entity.Player;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/*
* Logan Short Unit Test
* */
public class MarketMakeSaleTest {

    @Test
    public void testMarketMakeSale() {



        List<Item> inventory = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();
        inventory.add(Item.FOOD);
        inventory.add(Item.ORE);
        inventory.add(Item.GAMES);
        inventory.add(Item.MEDICINE);
        quantity.add(6);
        quantity.add(7);
        quantity.add(8);
        quantity.add(9);

        Player player = new Player("player", "Easy", "Gnat", 0, 16, 0, 0, 0, 500, 0, 0, 20, inventory, quantity);

        List<Integer> mQuantities = new ArrayList<>();
        List<Integer> mPrices = new ArrayList<>();
        Market market = new Market(inventory, mQuantities, mPrices);

        assertEquals("Not enough of Food to sell!", market.makeSale(player, Item.FOOD, 7, 0));
        assertEquals(500, player.getCredits());
        assertEquals("Sale made!", market.makeSale(player, Item.FOOD, 6, 2));
        assertEquals(512, player.getCredits());
        assertEquals(0, player.getItemQuantity(Item.FOOD));
        assertNotEquals(Item.FOOD, player.getInventory().get(0));
        assertEquals(99999, market.getPrice(Item.FOOD));
    }
}