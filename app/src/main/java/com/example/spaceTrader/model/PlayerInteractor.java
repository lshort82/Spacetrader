package com.example.spaceTrader.model;

import com.example.spaceTrader.entity.Player;

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
