package com.example.spacetrader.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Universe;
import com.example.spacetrader.model.PlayerInteractor;
import com.example.spacetrader.viewmodel.PlayerBuilder;

public class Character_Creation_Activity extends Activity {

    private PlayerBuilder viewModel;

    private EditText name;
    private TextView pointsLeft;
    private TextView pilot;
    private TextView fighter;
    private TextView trader;
    private TextView engineer;
    private Spinner diff;
    private Player player;
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character__creation_);

        name = findViewById(R.id.nameText);
        pointsLeft = findViewById(R.id.pointsLeftText);
        pilot = findViewById(R.id.pilotSkill);
        fighter = findViewById(R.id.fighterSkill);
        trader = findViewById(R.id.traderSkill);
        engineer = findViewById(R.id.engineerSkill);
        diff = findViewById(R.id.difficultySpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Player.legalDifficulties);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diff.setAdapter(adapter);

        player = new Player();
        points = 16;
        viewModel = new PlayerBuilder(this.getApplication());
    }

    public void onPPlus(View view) {
        if(points > 0) {
            player.setPilotPoints(player.getPilotPoints() + 1);
            points--;
            pilot.setText(String.valueOf(player.getPilotPoints()));
            pointsLeft.setText(String.valueOf(points));
        } else {
            Toast.makeText(this,"You have no more skill points to allocate!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void onFPlus(View view) {
        if(points > 0) {
            player.setFighterPoints(player.getFighterPoints() + 1);
            points--;
            fighter.setText(String.valueOf(player.getFighterPoints()));
            pointsLeft.setText(String.valueOf(points));
        } else {
            Toast.makeText(this,"You have no more skill points to allocate!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void onTPlus(View view) {
        if(points > 0) {
            player.setTraderPoints(player.getTraderPoints() + 1);
            points--;
            trader.setText(String.valueOf(player.getTraderPoints()));
            pointsLeft.setText(String.valueOf(points));
        } else {
            Toast.makeText(this,"You have no more skill points to allocate!",
                    Toast.LENGTH_LONG).show();
        }
    }
    public void onEPlus(View view) {
        if(points > 0) {
            player.setEngineerPoints(player.getEngineerPoints() + 1);
            points--;
            engineer.setText(String.valueOf(player.getEngineerPoints()));
            pointsLeft.setText(String.valueOf(points));
        } else {
            Toast.makeText(this,"You have no more skill points to allocate!",
                    Toast.LENGTH_LONG).show();
        }
    }
    public void onPMinus(View view){
        if(player.getPilotPoints() == 0) {
            Toast.makeText(this,"This skill already has no points!",
                    Toast.LENGTH_LONG).show();
        } else {
            player.setPilotPoints(player.getPilotPoints() - 1);
            points++;
            pilot.setText(String.valueOf(player.getPilotPoints()));
            pointsLeft.setText(String.valueOf(points));
        }
    }
    public void onFMinus(View view){
        if(player.getFighterPoints() == 0) {
            Toast.makeText(this,"This skill already has no points!",
                    Toast.LENGTH_LONG).show();
        } else {
            player.setFighterPoints(player.getFighterPoints() - 1);
            points++;
            fighter.setText(String.valueOf(player.getFighterPoints()));
            pointsLeft.setText(String.valueOf(points));
        }
    }
    public void onTMinus(View view){
        if(player.getTraderPoints() == 0) {
            Toast.makeText(this,"This skill already has no points!",
                    Toast.LENGTH_LONG).show();
        } else {
            player.setTraderPoints(player.getTraderPoints() - 1);
            points++;
            trader.setText(String.valueOf(player.getTraderPoints()));
            pointsLeft.setText(String.valueOf(points));
        }
    }
    public void onEMinus(View view){
        if(player.getEngineerPoints() == 0) {
            Toast.makeText(this,"This skill already has no points!",
                    Toast.LENGTH_LONG).show();
        } else {
            player.setEngineerPoints(player.getEngineerPoints() - 1);
            points++;
            engineer.setText(String.valueOf(player.getEngineerPoints()));
            pointsLeft.setText(String.valueOf(points));
        }
    }
    public void onCharacterCreate(View view) {
        if(checkName()) {
            if (points > 0) {
                new AlertDialog.Builder(this)
                        .setTitle("Points Unallocated")
                        .setMessage("Do you want to continue without using all the skill points? "
                                + "You will receive 100 credits per point unused. "
                                + "Press OK and Create Character to confirm, "
                                + "or press Cancel and make changes first")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                player.setCredits(player.getCredits() + (points * 100));
                                points = 0;
                                pointsLeft.setText(String.valueOf(points));
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            } else {
                player.setCredits(player.getCredits() + 1000);
                player.setName(name.getText().toString());
                player.setShip("Gnat Spaceship");
                viewModel.newPlayer(player);

                // for grading and dev use only
                //Toast.makeText(this, player.toString(), Toast.LENGTH_LONG).show();
                PlayerInteractor.setPlayer(player);
                Log.i("Player details",player.toString());
                Intent uniIntent = new Intent(this,UniverseCreation.class);
                Bundle uniBundle = new Bundle();
                uniBundle.putString("diff", diff.getSelectedItem().toString());
                uniIntent.putExtras(uniBundle);
                startActivity(uniIntent); // for M6 demo
                //this.finish();
            }
        } else {
            Toast.makeText(this,"Your name is invalid!",Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkName() { // conditions to be added if needed
        return name.getText().toString().trim().length() > 0 &&
           !name.getText().toString().contains("\\");
    }

}
