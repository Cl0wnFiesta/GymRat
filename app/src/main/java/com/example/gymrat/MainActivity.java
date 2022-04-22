package com.example.gymrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private boolean firstTime = false;
    public static final String EXTRA_MESSAGE = "Age";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this is a comment
        if(firstTime){
            Log.d("tag","why?");
            Intent intent = new Intent(this, FirstTime.class);
            startActivity(intent);
        }

    }


    //henrin oksan tapahtuma
    /**
     * Tähän jotain javadoc jutskaa nää avaa sivut
     */

    //Salitreenin nappi
    public void newGymActivity(View v){
        Intent startGym = new Intent(this, GymActivity.class);
        startActivity(startGym);
    }
    //Asetusten nappi
    public void openSettings(View v){
        Intent openSettingsIntent = new Intent(this, MainActivity.class);
        startActivity(openSettingsIntent);
    }
    //Vanhat suoritukset nappi
    public void openOldGym(View v){
        Intent openOldGymIntent = new Intent(this, MainActivity.class);
        startActivity(openOldGymIntent);
    }
    //Saavutukset nappi
    public void openAchievements(View v){
        Intent openOldGymIntent = new Intent(this, MainActivity.class);
        startActivity(openOldGymIntent);
    }
    //Kalenterinappi
    public void openCalendar(View v){
        Intent openCalendarIntent = new Intent(this, MainActivity.class);
        startActivity(openCalendarIntent);
    }
}