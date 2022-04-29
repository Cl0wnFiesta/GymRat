package com.example.gymrat.Workout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.example.gymrat.R;
import com.example.gymrat.SetupPageActivity;

public class Workout_selection_activity extends AppCompatActivity {
    public static final String EXTRA_ACTIVITY = "workoutCode";
    private CardView punnerrusNappi, kyykkyNappi, penkkiNappi, maastavetoNappi;
    private ImageView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_workoutselection);
        punnerrusNappi = findViewById(R.id.punnerrus);
        kyykkyNappi = findViewById(R.id.kyykky);
        penkkiNappi = findViewById(R.id.penkki);
        maastavetoNappi = findViewById(R.id.maastaveto);

        punnerrusNappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPunnerrus("WorkoutOne");
            }
        });
        kyykkyNappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startKyykky("WorkoutTwo");
            }
        });
        penkkiNappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPenkki("WorkoutThree");
            }
        });
        maastavetoNappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMaastaveto("WorkoutFour");
            }
        });

    }

    public void startPunnerrus (String alotusTunniste){
        Intent intent = new Intent(this, StartedWorkoutActivity.class);
        String workoutCode = alotusTunniste;
        intent.putExtra(EXTRA_ACTIVITY, workoutCode);
        startActivity(intent);
    }
    public void startKyykky (String alotusTunniste){
        Intent intent = new Intent(this, StartedWorkoutActivity.class);
        String workoutCode = alotusTunniste;
        intent.putExtra(EXTRA_ACTIVITY, workoutCode);
        startActivity(intent);
    }
    public void startPenkki (String alotusTunniste){
        Intent intent = new Intent(this, StartedWorkoutActivity.class);
        String workoutCode = alotusTunniste;
        intent.putExtra(EXTRA_ACTIVITY, workoutCode);
        startActivity(intent);
    }
    public void startMaastaveto (String alotusTunniste){
        Intent intent = new Intent(this, StartedWorkoutActivity.class);
        String workoutCode = alotusTunniste;
        intent.putExtra(EXTRA_ACTIVITY, workoutCode);
        startActivity(intent);
    }


}