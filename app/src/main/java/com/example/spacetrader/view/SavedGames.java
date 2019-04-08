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
import com.example.spacetrader.entity.Item;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Universe;
import com.example.spacetrader.entity.Visitable;
import com.example.spacetrader.model.MarketInteractor;
import com.example.spacetrader.model.PlayerInteractor;
import com.example.spacetrader.model.UniverseInteractor;
import com.example.spacetrader.model.VisitableInteractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SavedGames extends ListActivity {

    private static ArrayList<Universe> universes = new ArrayList<>();
    private static ArrayList<Integer> xs = new ArrayList<>();
    private static ArrayList<Integer> ys = new ArrayList<>();
    private static ArrayList<Integer> fuelLevels = new ArrayList<>();
    private static ArrayList<String> name = new ArrayList<>();
    private static ArrayList<List<Visitable>> places = new ArrayList<>();
    ArrayAdapter<String> adapter;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_resume_game);
        name.clear();
        File saveDirectory = getFilesDir();
        File[] saves = saveDirectory.listFiles();
        for( File e: saves) {
            if (e.getName().contains("_save")) {
                name.add(e.getName().substring(0, e.getName().length() - 9));
            }
        }
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                name);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (position <= name.size()) {
            String fileName = name.get(position)+ "_save.txt";
            try {
                BufferedReader reader = new BufferedReader(new FileReader(new File(getFilesDir(),fileName)));
                String[] line = reader.readLine().split(";");
                String name = line[0];
                String difficulty = line[1];
                String ship = line[2];
                int spaceLeft = Integer.parseInt(line[3]);
                int pilotPoints = Integer.parseInt(line[4]);
                int fighterPoints = Integer.parseInt(line[5]);
                int traderPoints = Integer.parseInt(line[6]);
                int engineerPoints = Integer.parseInt(line[7]);
                int credits = Integer.parseInt(line[8]);
                //int x = Integer.parseInt(line[9]);
                //int y = Integer.parseInt(line[10]);
                int x = 15;
                int y = 15;
                int fuel = Integer.parseInt(line[11]);
                String[] items = line[12].split(",");
                List<Item> inventory = new ArrayList<>();
                if(!items[0].equals("empty")) {
                    for (String e : items) {
                        inventory.add(Item.values()[Integer.parseInt(e)]);
                    }
                }
                List<Integer> quantity = new ArrayList<>();
                String[] quantities = line[13].split(",");
                if(!quantities[0].equals("empty")) {
                    for (String e: quantities) {
                        quantity.add(Integer.parseInt(e));
                    }
                }
                Player player = new Player(name,difficulty,ship,spaceLeft,pilotPoints,fighterPoints,traderPoints,engineerPoints,credits,x,y,fuel,inventory,quantity);
                PlayerInteractor.setPlayer(player);
                Universe universe = UniverseInteractor.getUniverse();
                UniverseInteractor.setUniverse(universe);
                VisitableInteractor.setVisitables(universe.inRange(player.getX(),player.getY(),player.getFuel()));
                startActivity(new Intent(this, navigationActivity.class));
                Toast.makeText(this,"Game Loaded!",Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this,"Error during load",Toast.LENGTH_LONG).show();
            }
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

    }
}

