package com.example.spaceTrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spaceTrader.entity.Player;
import com.example.spaceTrader.model.PlayerInteractor;

public class PlayerBuilder extends AndroidViewModel {
    private PlayerInteractor interacter;

    public PlayerBuilder(@NonNull Application application) {
        super(application);
    }

    public void newPlayer(Player player) {
        PlayerInteractor.setPlayer(player);
    }
}
