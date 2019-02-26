package com.example.spacetrader.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Universe;

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
    }


}
