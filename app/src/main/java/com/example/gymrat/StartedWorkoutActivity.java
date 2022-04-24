package com.example.gymrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartedWorkoutActivity extends AppCompatActivity {
    private double maxPenkki, maxKyykky, maxMaastaveto, maxPystypunnerrus;
    private WorkoutOne treeni;
    private SharedPreferences sp;
    private TextView toistot, paino, nimi;
    private Button seuraavaNappi;

    private int treeniPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_workout);

        toistot = findViewById(R.id.ToistotNum);
        paino = findViewById(R.id.PainoNum);
        nimi = findViewById(R.id.WorkoutNimi);
        seuraavaNappi = findViewById(R.id.NextPhase);

        fetchPreferences();
        treeni = new WorkoutOne(maxPenkki, maxKyykky, maxMaastaveto, maxPystypunnerrus);
        treeni.firstWorkout();

        setTreeniTiedot();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchPreferences();
    }

    private void setTreeniTiedot() {
        nimi.setText(treeni.getTreeniNimi());
        print(paino, treeni.getPaino(treeniPos));
        print(toistot, treeni.getLiikkeita(treeniPos));
    }

    //Hakee käyttäjän preferenssit
    private void fetchPreferences(){
        sp = getSharedPreferences("myKey", MODE_PRIVATE);
        maxPenkki = Double.parseDouble(sp.getString("penkki", "0"));
        maxKyykky = Double.parseDouble(sp.getString("kyykky", "0"));
        maxMaastaveto = Double.parseDouble(sp.getString("maastaveto", "0"));
        maxPystypunnerrus = Double.parseDouble(sp.getString("pystypunnerrus", "0"));
    }

    private void print(TextView tv, double luku){
        tv.setText(Double.toString(luku));
    }
    private void print(TextView tv, int luku){
        tv.setText(Integer.toString(luku));
    }
    public void buttonLogic(View view){
        int liikeCount = treeni.getPituus() -1, TreeniCount;
        if (treeniPos < liikeCount){
            treeniPos++;
            Log.d("Paino",Double.toString(treeni.getPaino(treeniPos)) );
            setTreeniTiedot();
        }

    }


}