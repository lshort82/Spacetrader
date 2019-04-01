package com.example.spacetrader.model;

import android.util.Log;

import com.example.spacetrader.entity.Player;

import java.util.List;

public class PlayerInteractor {

    private static Player player;

    public static Player getPlayer() {
        if(player == null) {
            return new Player();
        }
        return player;
    }

    public static void setPlayer(Player newplayer) {
        player = newplayer;
    }
}
