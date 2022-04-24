package com.example.gymrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class StartedWorkoutActivity extends AppCompatActivity {
    private Workout treeni;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_workout);
        fetchPreferences();
        treeni = new WorkoutOne(1, 1, 1, 1);

    }

    private void fetchPreferences(){
        //sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }
}