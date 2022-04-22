package com.example.gymrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this is a comment
        prefs = getSharedPreferences("hasRunBefore", 0);
        Boolean firstTime = prefs.getBoolean("hasRun", false);

        if(!firstTime){
            Intent intent = new Intent(this, Create_User.class);
            startActivity(intent);
        }
    }
    //henrin oksan tapahtuma
    public void newGymActivity(View v){
        Intent startGym = new Intent(this, GymActivity.class);
        startActivity(startGym);
    }

}