package com.example.spacetrader.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.spacetrader.R;

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
        startActivity(new Intent(this, SavedGames.class));
    }

    public void onExit(View view){
        this.finishAffinity();
    }
}
