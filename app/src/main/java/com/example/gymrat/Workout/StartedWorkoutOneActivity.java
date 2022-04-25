package com.example.gymrat.Workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gymrat.R;

public class StartedWorkoutOneActivity extends AppCompatActivity {
    private double maxPenkki, maxKyykky, maxMaastaveto, maxPystypunnerrus;
    private Workout treeni;
    private SharedPreferences sp;
    private TextView toistot, paino, nimi;
    private Button seuraavaNappi;

    private int[] ekatSetit = {8, 6, 4, 4, 4, 5, 6, 7, 8}, tokatSetit = {6, 5, 3, 5, 7, 4, 6, 8};
    private double[] ekaPainokerroin = {0.65, 0.75, 0.85, 0.85, 0.85, 0.8, 0.75, 0.7, 0.65}, tokaPainokerroin = {0.5,0.6,0.7,0.7,0.7,0.7,0.7,0.7};
    private String ekaSettiNimi = "Penkki", tokaSettiNimi = "Pystypunnerrus";

    private boolean secondPhase = false;

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
        treeni = new Workout(maxPenkki, maxKyykky, maxMaastaveto, maxPystypunnerrus);
        treeni.startWorkout(ekatSetit, ekaPainokerroin, ekaSettiNimi);

        setTreeniTiedot();

        setCounter();
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
    //aina kun painaa nappia, niin edistää treeniä askeleella.
    //jos ollaan treenin vikassa kohdassa, asettaa luvun nollaan ja aloittaa toisen vaiheen treeniä
    public void buttonLogic(View view){
        int liikeCount = treeni.getPituus(), TreeniCount;

        if (treeniPos <= liikeCount){
            Log.d("kohta",Integer.toString(treeniPos) );
            setTreeniTiedot();
        }
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
        setCounter();

    }

    public void setCounter(){
        TextView nytSetti = findViewById(R.id.nytSetti), setinLoppu = findViewById(R.id.setinLoppu);
        nytSetti.setText(Integer.toString(treeniPos + 1));
        setinLoppu.setText(Integer.toString(treeni.getPituus()));

    }


}