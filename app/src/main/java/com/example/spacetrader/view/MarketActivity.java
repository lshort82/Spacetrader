package com.example.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Item;
import com.example.spacetrader.entity.Market;

import java.util.ArrayList;

public class MarketActivity extends AppCompatActivity {


    private MarketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);


        RecyclerView recyclerView = findViewById(R.id.item_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Setup the adapter for this recycler view
        adapter = new MarketAdapter();
        recyclerView.setAdapter(adapter);
    }


    public void onResume() {
        super.onResume();
        //FOR M7DEMO ONLY!
        ArrayList<Item> e = new ArrayList<Item>();
        e.add(Item.WATER);
        e.add(Item.ORE);
        e.add(Item.FURS);
        ArrayList<Integer> e2 = new ArrayList<Integer>();
        e2.add(4);
        e2.add(5);
        e2.add(2);
        //END DEMO ONLY (KINDA)
        Market market = new Market(e,e2);
        adapter.setLists(market.getInventory(),market.getQuantity(),market.getPrice());//breaks everything?
        adapter.setOnItemClickListener(new MarketAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Item item) {
            }
        });
    }
}
