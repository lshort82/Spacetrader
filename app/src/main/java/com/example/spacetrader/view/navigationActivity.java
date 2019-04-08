package com.example.spacetrader.view;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Visitable;
import com.example.spacetrader.model.MarketInteractor;
import com.example.spacetrader.model.PlayerInteractor;
import com.example.spacetrader.model.UniverseInteractor;
import com.example.spacetrader.model.VisitableInteractor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class navigationActivity extends ListActivity {

    List<Visitable> places = new ArrayList<>();
    List<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Player player;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_navigation);
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);
    }

    public void onResume() {
        super.onResume();
        adapter.clear();
        player = PlayerInteractor.getPlayer();
        TextView fuel = findViewById(R.id.fuel);
        fuel.setText(player.getFuel()+ "");
        TextView loc = findViewById(R.id.locationLabel);
        loc.setText("Current: " + UniverseInteractor.getUniverse().getEntity(player.getX(),player.getY()).getName());
        places = VisitableInteractor.getVisitables();
        for(Visitable e : places){
            listItems.add(e.getName());
        }
        adapter.notifyDataSetChanged();
    }
    public void addItems(View v) {
        //listItems.add("Clicked : ");
        adapter.notifyDataSetChanged();
    }

    public void onSave(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View saveView = li.inflate(R.layout.activity_save_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(saveView);
        final File file = new File(this.getFilesDir(), "text.bin");
        final EditText userInput = saveView.findViewById(R.id.editText);
        alertDialogBuilder.setCancelable(false).setPositiveButton("Save",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        String name = userInput.getText().toString();
                        List<Visitable> places = new ArrayList<>();
                        for (Visitable e : VisitableInteractor.getVisitables()) {
                            places.add(e);
                        }

                        ObjectOutputStream out = null;
                        try {
                            out = new ObjectOutputStream(new FileOutputStream(file));
                            out.writeObject(PlayerInteractor.getPlayer());
                            out.writeObject(UniverseInteractor.getUniverse());
                            out.writeObject(places);
                            out.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
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
            if (places.get(position).hasinterior()) {
                places.get(position).onVisit();
                VisitableInteractor.setVisitables(places.get(position).getInterior());
                TextView loc = findViewById(R.id.locationLabel);
                loc.setText("Select Planet");
                onResume();
            } else if (places.get(position).hasMarket()) {
                Intent intent = new Intent(this, MarketActivity.class);
                MarketInteractor.setMarket(places.get(position).getMarket());
                startActivity(intent);
            }
        }
    }
}
