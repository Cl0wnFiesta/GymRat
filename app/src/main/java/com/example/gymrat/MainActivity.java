package com.example.gymrat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        }else{
            TextView mtext = findViewById(R.id.UserNameM);
            SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
            String value = sharedPreferences.getString("value","");
            mtext.setText("Hei " + value);
        }

    }

}