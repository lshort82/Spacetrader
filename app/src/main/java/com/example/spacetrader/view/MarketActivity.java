package com.example.spacetrader.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Item;
import com.example.spacetrader.entity.Market;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Visitable;
import com.example.spacetrader.model.MarketInteractor;
import com.example.spacetrader.model.PlayerInteractor;
import com.example.spacetrader.model.UniverseInteractor;
import com.example.spacetrader.model.VisitableInteractor;

import java.util.ArrayList;
import java.util.List;

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

//        //FOR M7DEMO ONLY!
//        e = new ArrayList<Item>();
//        e.add(Item.WATER);
//        e.add(Item.ORE);
//        e.add(Item.FURS);
//        e2 = new ArrayList<Integer>();
//        e2.add(4);
//        e2.add(5);
//        e2.add(2);
//        market = new Market(e, e2);
//        player = new Player();
//        player.setCredits(1000);
//        playerMarket = new Market(player.getInventory(),player.getQuantity());
//        //END DEMO ONLY (KINDA)
        player = PlayerInteractor.getPlayer();
        market = MarketInteractor.getMarket();
        playerMarket = new Market(player.getInventory(),player.getQuantity());
    }


    public void onResume() {
        super.onResume();
        playerMarket = new Market(player.getInventory(),player.getQuantity(), playerMarket.getPrice());
        TextView creds = findViewById(R.id.credits);
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
                            String x = playerMarket.makeSale(player,item2,numberPicker.getValue(),playerMarket.getPrice(item2));
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
    public void onSave(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View saveView = li.inflate(R.layout.activity_save_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(saveView);

        final EditText userInput = saveView.findViewById(R.id.editText);
        alertDialogBuilder.setCancelable(false).setPositiveButton("Save",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        SavedGames.addName(userInput.getText().toString());
                        List<Visitable> places = new ArrayList<>();
                        for (Visitable e : VisitableInteractor.getVisitables()) {
                            places.add(e);
                        }
                        SavedGames.addPlaces(places);
                        SavedGames.addX(PlayerInteractor.getPlayer().getX());
                        SavedGames.addY(PlayerInteractor.getPlayer().getY());
                        SavedGames.addFuelLevel(PlayerInteractor.getPlayer().getFuel());
                        SavedGames.addUniverse(UniverseInteractor.getUniverse());
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void onTravel(View view) {
        PlayerInteractor.setPlayer(player);
        VisitableInteractor.setVisitables(UniverseInteractor.getUniverse().inRange(player.getX(),player.getY(),player.getFuel()));
        startActivity(new Intent(this, navigationActivity.class));
    }
    public void onTogglePerspective(View view){
        isBuying = !isBuying;
        TextView header = findViewById(R.id.locationLabel);
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
