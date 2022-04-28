package com.example.gymrat.Workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.gymrat.R;

public class WorkoutEndActivity extends AppCompatActivity {
    SharedPreferences sp;
    private double maxPenkki, maxKyykky, maxMaastaveto, maxPystypunnerrus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_end);
        fetchPreferences(); //haetaan käyttäjän preferenssit suorituksen tietokantaan tallennusta varten
    }

    //Hakee käyttäjän preferenssit
    private void fetchPreferences(){
        sp = getSharedPreferences("myKey", MODE_PRIVATE);
        maxPenkki = Double.parseDouble(sp.getString("penkki", "0"));
        maxKyykky = Double.parseDouble(sp.getString("kyykky", "0"));
        maxMaastaveto = Double.parseDouble(sp.getString("maastaveto", "0"));
        maxPystypunnerrus = Double.parseDouble(sp.getString("pystypunnerrus", "0"));
    }


}