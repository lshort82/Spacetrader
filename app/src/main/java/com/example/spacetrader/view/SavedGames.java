package com.example.spacetrader.view;

import android.app.ListActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Universe;
import com.example.spacetrader.entity.Visitable;
import com.example.spacetrader.model.MarketInteractor;
import com.example.spacetrader.model.PlayerInteractor;
import com.example.spacetrader.model.UniverseInteractor;
import com.example.spacetrader.model.VisitableInteractor;

import java.util.ArrayList;
import java.util.List;

public class SavedGames extends ListActivity {

    private static ArrayList<Universe> universes = new ArrayList<>();
    private static ArrayList<Integer> xs = new ArrayList<>();
    private static ArrayList<Integer> ys = new ArrayList<>();
    private static ArrayList<Integer> fuelLevels = new ArrayList<>();
    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<List<Visitable>> places = new ArrayList<>();
    ArrayAdapter<String> adapter;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_resume_game);
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                names);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (position <= names.size()) {
            Player player = PlayerInteractor.getPlayer();
            player.setX(xs.get(position));
            player.setY(ys.get(position));
            player.setFuel(fuelLevels.get(position));
            PlayerInteractor.setPlayer(player);
            UniverseInteractor.setUniverse(universes.get(position));
            VisitableInteractor.setVisitables(places.get(position));
            startActivity(new Intent(this, navigationActivity.class));
        }
    }

    public static void addPlaces(List<Visitable> plcs) {
        places.add(plcs);
    }

    public static void addX(int x) {
        xs.add(x);
    }

    public static void addY(int y) {
        ys.add(y);
    }

    public static void addFuelLevel(int fuelLevel) {
        fuelLevels.add(fuelLevel);
    }

    public static void addUniverse(Universe unvrs) {
        universes.add(unvrs);
    }

    public static void addName(String name) {
        names.add(name);
    }
}

