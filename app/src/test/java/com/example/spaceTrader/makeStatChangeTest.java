package com.example.spaceTrader;
import com.example.spaceTrader.entity.Player;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class makeStatChangeTest {
    private int pp = 1;
    private int fp = 2;
    private int tp = -1;
    private int ep = 100;
    private Player player = new Player("player", "Easy", "gnat", 4, 4, 4, 4, 1000);

    @Test
    public void testMakeStatChangePilot() {
        player.makeStatChange("pilot", pp);
        assertEquals(player.getPilotPoints(), pp + 4);
    }
    @Test
    public void testMakeStatChangeEngineer() {
        player.makeStatChange("engineer", ep);
        assertEquals(player.getEngineerPoints(), ep + 4);
    }
    @Test
    public void testMakeStatChangeFighter() {
        player.makeStatChange("fighter", fp);
        assertEquals(player.getFighterPoints(), fp + 4);
    }
    @Test
    public void testMakeStatChangeTrader() {
        player.makeStatChange("trader", tp);
        assertEquals(player.getTraderPoints(), tp + 4);
    }
}
