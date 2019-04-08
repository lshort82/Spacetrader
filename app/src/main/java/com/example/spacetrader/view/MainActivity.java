package com.example.spacetrader.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Universe;
import com.example.spacetrader.entity.Visitable;
import com.example.spacetrader.model.PlayerInteractor;
import com.example.spacetrader.model.UniverseInteractor;
import com.example.spacetrader.model.VisitableInteractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    public void onStartNewGame(View view){
        startActivity(new Intent(this,Character_Creation_Activity.class));
    }

    public void onResumeGame(View view) {
//        File file = new File(this.getFilesDir(), "text.bin");
//        try {
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
//            PlayerInteractor.setPlayer((Player) in.readObject());
//            UniverseInteractor.setUniverse((Universe) in.readObject());
//            VisitableInteractor.setVisitables((List<Visitable>) in.readObject());
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        startActivity(new Intent(this, navigationActivity.class));
          startActivity(new Intent(this, SavedGames.class));
    }

    public void onExit(View view){
        this.finishAffinity();
    }
}
