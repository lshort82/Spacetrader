package com.example.spacetrader.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Item;
import com.example.spacetrader.entity.Market;
import com.example.spacetrader.entity.Player;

import java.util.ArrayList;

public class MarketActivity extends Activity {


    private MarketAdapter adapter;
    private static boolean isBuying = true;
    private ArrayList<Item> e;
    private ArrayList<Integer> e2;
    private Market market;
    private Market playerMarket;
    private Player player;
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

        //FOR M7DEMO ONLY!
        e = new ArrayList<Item>();
        e.add(Item.WATER);
        e.add(Item.ORE);
        e.add(Item.FURS);
        e2 = new ArrayList<Integer>();
        e2.add(4);
        e2.add(5);
        e2.add(2);
        market = new Market(e, e2);
        player = new Player();
        player.setCredits(1000);
        playerMarket = new Market(player.getInventory(),player.getQuantity());
        //END DEMO ONLY (KINDA)
    }


    public void onResume() {
        super.onResume();
        playerMarket = new Market(player.getInventory(),player.getQuantity(), playerMarket.getPrice());
        TextView creds = findViewById(R.id.Credits);
        creds.setText(player.getCredits()+ "");
        TextView spaceLeft = findViewById(R.id.spaceLeft);
        spaceLeft.setText(player.spaceLeft()+ "");
        if(isBuying) {
            adapter.setLists(market.getInventory(), market.getQuantity(), market.getPrice());
        }else {
            adapter.setLists(playerMarket.getInventory(), playerMarket.getQuantity(), playerMarket.getPrice());
        }
        adapter.setOnItemClickListener(new MarketAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Item item, int quant) {
                final Item item2 = item;
                if(isBuying) {
                    final AlertDialog.Builder d = new AlertDialog.Builder(MarketActivity.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.number_picker_dialog, null);
                    d.setTitle("Buying " + item.getName());
                    d.setMessage("Select Quantity");
                    d.setView(dialogView);
                    final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialog_number_picker);
                    numberPicker.setMaxValue(quant);
                    numberPicker.setMinValue(0);
                    numberPicker.setWrapSelectorWheel(false);
                    numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                        @Override
                        public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                            Log.d("NumPicker", "onValueChange: ");
                        }
                    });
                    d.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d("NumPicker", "onClick: " + numberPicker.getValue());
                            String x = market.makePurchase(player,item2,numberPicker.getValue());
                            Log.d("NumPicker",x);
                            showToast(x);
                            onResume();
                        }
                    });
                    d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    AlertDialog alertDialog = d.create();
                    alertDialog.show();
                } else {
                    final AlertDialog.Builder d = new AlertDialog.Builder(MarketActivity.this);
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.number_picker_dialog, null);
                    d.setTitle("Selling " + item.getName());
                    d.setMessage("Select Quantity");
                    d.setView(dialogView);
                    final NumberPicker numberPicker = (NumberPicker) dialogView.findViewById(R.id.dialog_number_picker);
                    numberPicker.setMaxValue(quant);
                    numberPicker.setMinValue(0);
                    numberPicker.setWrapSelectorWheel(false);
                    numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                        @Override
                        public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                            Log.d("NumPicker", "onValueChange: ");
                        }
                    });
                    d.setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d("NumPicker", "onClick: " + numberPicker.getValue());
                            Log.d("NumPicker", "onClick: " + numberPicker.getValue());
                            String x = market.makeSale(player,item2,numberPicker.getValue(),playerMarket.getPrice(item2));
                            Log.d("NumPicker",x);
                            showToast(x);
                            onResume();
                        }
                    });
                    d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    AlertDialog alertDialog = d.create();
                    alertDialog.show();
                }
            }
        });
    }
    public void onTogglePerspective(View view){
        isBuying = !isBuying;
        TextView header = findViewById(R.id.invLabel);
        if(isBuying) {
            header.setText("Store Inventory");
        } else {
            header.setText("Your Inventory");
        }
        onResume();
    }
    public void showToast(String x){
        Toast.makeText(this,x, Toast.LENGTH_SHORT).show();
    }
}
