package com.example.gymrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private boolean firstTime = true;
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
        //henrin oksan tapahtuma
    }

    public void newGymActivity(View v){
        Intent startGym = new Intent();
        startActivity(startGym);
    }
}