package com.example.spaceTrader.view;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spaceTrader.R;
import com.example.spaceTrader.entity.Player;
import com.example.spaceTrader.entity.SimpleRandomEvent;
import com.example.spaceTrader.entity.Visitable;
import com.example.spaceTrader.model.MarketInteractor;
import com.example.spaceTrader.model.PlayerInteractor;
import com.example.spaceTrader.model.UniverseInteractor;
import com.example.spaceTrader.model.VisitableInteractor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class navigationActivity extends ListActivity {

    private List<Visitable> places = new ArrayList<>();
    private final List<String> listItems = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private Player player;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_navigation);
        adapter= new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);
    }

    public void onResume() {
        super.onResume();
        adapter.clear();
        player = PlayerInteractor.getPlayer();
        TextView fuel = findViewById(R.id.fuel);
        String fuelString = player.getFuel() + "";
        fuel.setText(fuelString);
        TextView loc = findViewById(R.id.locationLabel);
        String currentString = "Current: " + UniverseInteractor.getUniverse().getEntity(player.getX(),player.getY()).getName();
        loc.setText(currentString);
        places = VisitableInteractor.getVisitableList();
        for(Visitable e : places){
            listItems.add(e.getName());
        }
        adapter.notifyDataSetChanged();
    }
// --Commented out by Inspection START (4/8/2019 1:41 PM):
//    public void addItems(View v) {
//        //listItems.add("Clicked : ");
//        adapter.notifyDataSetChanged();
//    }
// --Commented out by Inspection STOP (4/8/2019 1:41 PM)

    public void onSave(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View saveView = li.inflate(R.layout.activity_save_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(saveView);

        final EditText userInput = saveView.findViewById(R.id.editText);
        alertDialogBuilder.setCancelable(false).setPositiveButton("Save",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        String fileName = userInput.getText().toString() + "_save";
                        File saveDirectory = getDir("Saves", MODE_PRIVATE);
                        //File saveFile = new File(saveDirectory, fileName);
                        try {
                            FileOutputStream saver = openFileOutput(fileName + ".txt", MODE_PRIVATE);
                            ObjectOutputStream saver2 = new ObjectOutputStream(saver);
                            saver2.writeObject(player);
                            saver2.writeObject(UniverseInteractor.getUniverse());
                            saver2.close();
                        } catch(Exception e) {
                            Toast.makeText(navigationActivity.this, "Error during save, try again", Toast.LENGTH_LONG).show();
                        }
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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (position <= places.size()) {
            if (places.get(position).hasInterior()) {
                places.get(position).onVisit();
                VisitableInteractor.setVisitableList(places.get(position).getInterior());
                TextView loc = findViewById(R.id.locationLabel);
                String planetString = "Select Planet";
                loc.setText(planetString);
                double RANDOM_CHANCE = 1;
                if (Math.random() < RANDOM_CHANCE) {
                    double threshold = 1 /(double) SimpleRandomEvent.values().length;
                    final int index = (int) (Math.random()/threshold);
                    new AlertDialog.Builder(this)
                            .setTitle(SimpleRandomEvent.values()[index].getTitle())
                            .setMessage(SimpleRandomEvent.values()[index].getDescription())
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("give " +SimpleRandomEvent.values()[index].getAmtNeeded() + " "
                                    + SimpleRandomEvent.values()[index].getGoodName(), new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    if (player.getItemQuantity(SimpleRandomEvent.values()[index].getGoodNeeded()) >= SimpleRandomEvent.values()[index].getAmtNeeded()) {
                                        player.makeStatChange(SimpleRandomEvent.values()[index].getBenefit(),SimpleRandomEvent.values()[index].getBenefitAmt());
                                        player.removeItem(SimpleRandomEvent.values()[index].getGoodNeeded(),SimpleRandomEvent.values()[index].getAmtNeeded());
                                    } else {
                                        Toast.makeText(navigationActivity.this,"not enough of good needed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("Ignore them", null).show();
                }
                onResume();
            } else if (places.get(position).hasMarket()) {
                Intent intent = new Intent(this, MarketActivity.class);
                MarketInteractor.setMarket(places.get(position).getMarket());
                startActivity(intent);
            }
        }
    }
}
