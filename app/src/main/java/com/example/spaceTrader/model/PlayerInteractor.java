package com.example.spacetrader.model;

import com.example.spacetrader.entity.Player;

public class PlayerInteractor {

    private static Player player;

    public static Player getPlayer() {
        if(player == null) {
            return new Player();
        }
        return player;
    }

    public static void setPlayer(Player newPlayer) {
        player = newPlayer;
    }
}
