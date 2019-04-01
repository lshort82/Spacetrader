package com.example.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.spacetrader.entity.Player;
import com.example.spacetrader.model.PlayerInteractor;

public class PlayerBuilder extends AndroidViewModel {
    private PlayerInteractor interactor;

    public PlayerBuilder(@NonNull Application application) {
        super(application);
    }

    public void newPlayer(Player player) {
        //interactor = new PlayerInteractor(player);
    }
}
