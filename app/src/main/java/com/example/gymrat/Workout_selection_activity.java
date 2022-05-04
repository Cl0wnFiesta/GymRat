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

/**
 * Luokka joka aloittaa neljästä napista kuntosaliohjelman treenit ja avaa infonapista infopopupin
 * @author Henri
 */
public class Workout_selection_activity extends AppCompatActivity {
    public static final String EXTRA_ACTIVITY = "workoutCode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CardView punnerrusNappi, kyykkyNappi, penkkiNappi, maastavetoNappi;
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_workoutselection);
        punnerrusNappi = findViewById(R.id.punnerrus);
        kyykkyNappi = findViewById(R.id.kyykky);
        penkkiNappi = findViewById(R.id.penkki);
        maastavetoNappi = findViewById(R.id.maastaveto);
        ImageView info = findViewById(R.id.info);

        punnerrusNappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWorkout("WorkoutOne");
            }
        });
        kyykkyNappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWorkout("WorkoutTwo");
            }
        });
        penkkiNappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWorkout("WorkoutThree");
            }
        });
        maastavetoNappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWorkout("WorkoutFour");
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

    /**
     * Aloittaa treenin riippuen annetusta parametristä
     * @param alotusTunniste String, treenin aloituksen parametri
     */
    public void startWorkout (String alotusTunniste){
        Intent intent = new Intent(this, StartedWorkoutActivity.class);
        intent.putExtra(EXTRA_ACTIVITY, alotusTunniste);
        startActivity(intent);
    }


}