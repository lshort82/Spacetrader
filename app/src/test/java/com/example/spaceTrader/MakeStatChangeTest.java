package com.example.spaceTrader;
import com.example.spaceTrader.entity.Player;
import org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MakeStatChangeTest {
    @Test
    public void testMakeStatChange() {
        Player player = new Player("player", "Easy", "gnat", 4, 4, 4, 4, 1000);
        int pp = 1;
        int fp = 2;
        int tp = -1;
        int ep = 100;
        player.makeStatChange("pilot", pp);
        player.makeStatChange("fighter", fp);
        player.makeStatChange("trader", tp);
        player.makeStatChange("engineer", ep);
        assertEquals(player.getPilotPoints(), pp + 4);
        assertEquals(player.getFighterPoints(), fp + 4);
        assertEquals(player.getTraderPoints(), tp + 4);
        assertEquals(player.getEngineerPoints(), ep + 4);
    }
}
