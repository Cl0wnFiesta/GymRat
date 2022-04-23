package com.example.gymrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this is a comment

    }
    @Override
    protected void onStart() {
        super.onStart();
        prefs = getSharedPreferences("hasRunBefore", 0);
        Boolean firstTime = prefs.getBoolean("hasRun", false);
        checkFirstTime(firstTime);
    }

    //henrin oksan tapahtuma
    public void newGymActivity(View v){
        Intent startGym = new Intent(this, GymActivity.class);
        startActivity(startGym);
    }

    private void checkFirstTime(Boolean firstTime) {
        if(!firstTime){
            Intent intent = new Intent(this, Create_UserActivity.class);
            startActivity(intent);
        }
    }

}