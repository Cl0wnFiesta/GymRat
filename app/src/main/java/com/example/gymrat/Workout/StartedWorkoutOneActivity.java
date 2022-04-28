package com.example.gymrat.Workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gymrat.R;

import java.util.ArrayList;

public class StartedWorkoutOneActivity extends AppCompatActivity {
    private double maxPenkki, maxKyykky, maxMaastaveto, maxPystypunnerrus;
    private Workout treeni;
    private SharedPreferences sp;
    private TextView toistot, paino, nimi;
    private Button seuraavaNappi, plusButton, minusButton;

    //Tunnistenimellä hallitaan mikä treeni on käynnissä. Tunnistenimeen otetaan tieto intentistä, ja ohjelma hakee singleton luokasta tiedot treeniä varten

    WorkoutGlobal workoutGlobal = WorkoutGlobal.getInstance();
    String tunnisteNimi = "WorkoutTwo";
    private int[] ekatSetit = workoutGlobal.getWorkoutToistot(tunnisteNimi, 1), tokatSetit = workoutGlobal.getWorkoutToistot(tunnisteNimi, 2);
    private double[] ekaPainokerroin = workoutGlobal.getWorkoutPainokerroin(tunnisteNimi, 1), tokaPainokerroin = workoutGlobal.getWorkoutPainokerroin(tunnisteNimi, 2);
    private String ekaSettiNimi = workoutGlobal.getWorkoutName(tunnisteNimi, 1), tokaSettiNimi = workoutGlobal.getWorkoutName(tunnisteNimi, 2);

    private boolean secondPhase = false;

    private int treeniPos = 0;

    private int yksPlusKierros = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_workout);

        toistot = findViewById(R.id.ToistotNum);
        paino = findViewById(R.id.PainoNum);
        nimi = findViewById(R.id.WorkoutNimi);
        seuraavaNappi = findViewById(R.id.NextPhase);
        plusButton = findViewById(R.id.plusButton);
        minusButton = findViewById(R.id.minusButton);

        fetchPreferences();
        treeni = new Workout(maxPenkki, maxKyykky, maxMaastaveto, maxPystypunnerrus);
        treeni.startWorkout(ekatSetit, ekaPainokerroin, ekaSettiNimi);

        seuraavaNappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic();
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yksPlusKierros++;
                toistot.setText(Integer.toString(yksPlusKierros) + "+");
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(yksPlusKierros >= 0){
                    yksPlusKierros--;
                }
                toistot.setText(Integer.toString(yksPlusKierros) + "+");
            }
        });

        setTreeniTiedot();

        setCounter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchPreferences();
    }

    private void setTreeniTiedot() {
        setButtonVisibility(false);
        nimi.setText(treeni.getTreeniNimi());
        print(paino, treeni.getPaino(treeniPos));
        if(treeni.getLiikkeita(treeniPos) == 1){
            toistot.setText("1+");
            setButtonVisibility(true);
        }
        if(treeni.getLiikkeita(treeniPos) > 1 && treeni.getPituus() != treeniPos){
            print(toistot, treeni.getLiikkeita(treeniPos));
        }
        if(treeni.getLiikkeita(treeniPos) > 1){
            print(toistot, treeni.getLiikkeita(treeniPos));
        }
        if(treeni.getPituus() == treeniPos +1){
            toistot.setText((treeni.getLiikkeita(treeniPos)) + "+");
        }
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

    //aina kun painaa nappia, niin edistää treeniä askeleella.
    //jos ollaan treenin vikassa kohdassa, asettaa luvun nollaan ja aloittaa toisen vaiheen treeniä
    private void buttonLogic(){
        int liikeCount = treeni.getPituus();
        if (treeniPos + 1 <= liikeCount && !secondPhase){
            treeniPos++;
        }
        if (treeniPos + 1 < liikeCount && secondPhase){
            treeniPos++;
        }
        if(treeniPos == liikeCount && !secondPhase){
            treeniPos = 0;
            treeni.startWorkout(tokatSetit, tokaPainokerroin, tokaSettiNimi);
            secondPhase = true;
        }
        if (treeniPos <= liikeCount){
            Log.d("kohta",Integer.toString(treeniPos) );
            setTreeniTiedot();
        }
        if(treeniPos == liikeCount && secondPhase){
            Intent endWorkout = new Intent();
            startActivity(endWorkout);
        }
        setCounter();
    }

    private void backButtonLogic(){
        int liikeCount = treeni.getPituus();
        if (treeniPos + 1 >= liikeCount && !secondPhase){
            treeniPos--;
        }
        if (treeniPos + 1 > liikeCount && secondPhase){
            treeniPos--;
        }
        if(treeniPos -1 < 0 && !secondPhase){
            treeniPos = 0;
            treeni.startWorkout(ekatSetit, ekaPainokerroin, ekaSettiNimi);
            secondPhase = false;
        }
        if (treeniPos <= liikeCount){
            Log.d("kohta",Integer.toString(treeniPos) );
            setTreeniTiedot();
        }
        setCounter();
    }


    public void setCounter(){
        TextView nytSetti = findViewById(R.id.nytSetti), setinLoppu = findViewById(R.id.setinLoppu);
        nytSetti.setText(Integer.toString(treeniPos + 1));
        setinLoppu.setText(Integer.toString(treeni.getPituus()));

    }

    private void setButtonVisibility(boolean plusKierros){
        if(plusKierros){
            plusButton.setVisibility(View.VISIBLE);
            plusButton.setFocusableInTouchMode(false);
            minusButton.setVisibility(View.VISIBLE);
            minusButton.setFocusableInTouchMode(false);
        }
        if(!plusKierros){
            plusButton.setVisibility(View.INVISIBLE);
            plusButton.setFocusableInTouchMode(true);
            minusButton.setVisibility(View.INVISIBLE);
            minusButton.setFocusableInTouchMode(true);
        }

    }


}