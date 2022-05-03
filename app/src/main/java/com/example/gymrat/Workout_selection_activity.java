package com.example.gymrat;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

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
        info = findViewById(R.id.info);

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
        info.setOnClickListener(new View.OnClickListener() {

            //Info popup ja onclick jolla popuppia painaessa dismissaa popupin.
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupNakyma = inflater.inflate(R.layout.popup_info, null);
                int leveys = LinearLayout.LayoutParams.WRAP_CONTENT;
                int pituus = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupIkkuna = new PopupWindow(popupNakyma, leveys, pituus, focusable);
                popupIkkuna.showAtLocation(view, Gravity.CENTER, 0, 0);
                popupNakyma.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupIkkuna.dismiss();
                    }
                });
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