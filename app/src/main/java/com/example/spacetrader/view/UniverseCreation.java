package com.example.spacetrader.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Item;
import com.example.spacetrader.entity.Market;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Universe;
import com.example.spacetrader.model.PlayerInteractor;
import com.example.spacetrader.model.UniverseInteractor;
import com.example.spacetrader.model.VisitableInteractor;

import java.util.ArrayList;

public class UniverseCreation extends Activity {

    ListView universeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe_creation);
        universeView = findViewById(R.id.universeListView);
        Universe universe = new Universe();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,universe.toStringList());
        universeView.setAdapter(adapter);
        Player player = PlayerInteractor.getPlayer();
        VisitableInteractor.setVisitables(universe.inRange(player.getX(),player.getY(),player.getFuel()));
//        //FOR M7 DEMO ONLY
//        Intent intent = new Intent(this,MarketActivity.class);
//        ArrayList<Item> e = new ArrayList<Item>();
//        e.add(Item.WATER);
//        e.add(Item.ORE);
//        e.add(Item.FURS);
//        ArrayList<Integer> e2 = new ArrayList<Integer>();
//        e2.add(4);
//        e2.add(5);
//        e2.add(2);
//        intent.putExtra("inventory",e);
//        intent.putExtra("quantity",e2);
//        startActivity(intent);
//        //end demo only
        UniverseInteractor.setUniverse(universe);
        Intent intent = new Intent(this, navigationActivity.class);
        startActivity(intent);
    }


}
