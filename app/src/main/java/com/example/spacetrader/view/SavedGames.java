package com.example.spaceTrader.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.spaceTrader.R;
import com.example.spaceTrader.entity.Player;
import com.example.spaceTrader.entity.Universe;
import com.example.spaceTrader.model.PlayerInteractor;
import com.example.spaceTrader.model.UniverseInteractor;
import com.example.spaceTrader.model.VisitableInteractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class SavedGames extends ListActivity {

   /* private static final ArrayList<Universe> universes = new ArrayList<>();
    private static final ArrayList<Integer> xs = new ArrayList<>();
    private static final ArrayList<Integer> ys = new ArrayList<>();
    private static final ArrayList<Integer> fuelLevels = new ArrayList<>();*/
    private static final ArrayList<String> name = new ArrayList<>();
    //private static final ArrayList<List<Visitable>> places = new ArrayList<>();


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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                name);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (position <= name.size()) {
            String fileName = name.get(position)+ "_save.txt";
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(getFilesDir(),fileName)));
                PlayerInteractor.setPlayer((Player) in.readObject());
                UniverseInteractor.setUniverse((Universe) in.readObject());
                VisitableInteractor.setVisitableList(UniverseInteractor.getUniverse().inRange(PlayerInteractor.getPlayer().getX(), PlayerInteractor.getPlayer().getY(), PlayerInteractor.getPlayer().getFuel()));
                in.close();
//                String[] line = reader.readLine().split(";");
//                String name = line[0];
//                String difficulty = line[1];
//                String ship = line[2];
//                int spaceLeft = Integer.parseInt(line[3]);
//                int pilotPoints = Integer.parseInt(line[4]);
//                int fighterPoints = Integer.parseInt(line[5]);
//                int traderPoints = Integer.parseInt(line[6]);
//                int engineerPoints = Integer.parseInt(line[7]);
//                int credits = Integer.parseInt(line[8]);
//                //int x = Integer.parseInt(line[9]);
//                //int y = Integer.parseInt(line[10]);
//                int x = 15;
//                int y = 15;
//                int fuel = Integer.parseInt(line[11]);
//                String[] items = line[12].split(",");
//                List<Item> inventory = new ArrayList<>();
//                if(!items[0].equals("empty")) {
//                    for (String e : items) {
//                        inventory.add(Item.values()[Integer.parseInt(e)]);
//                    }
//                }
//                List<Integer> quantity = new ArrayList<>();
//                String[] quantities = line[13].split(",");
//                if(!quantities[0].equals("empty")) {
//                    for (String e: quantities) {
//                        quantity.add(Integer.parseInt(e));
//                    }
//                }
//
                startActivity(new Intent(this, navigationActivity.class));
                Toast.makeText(this,"Game Loaded!",Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this,"Error during load",Toast.LENGTH_LONG).show();
            }
        }
    }
}

