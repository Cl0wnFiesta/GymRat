package com.example.gymrat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.gymrat.Muistutukset.Notes_Activity;
import com.example.gymrat.NavBar_Activities.Favorites;

import com.example.gymrat.NavBar_Activities.Settings;
import com.example.gymrat.Workout.StartedWorkoutActivity;
import com.example.gymrat.Workout.WorkoutDB.Treeni;
import com.example.gymrat.Workout.WorkoutDB.WorkoutDatabase;
import com.example.gymrat.Workout.Workout_selection_activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Load all workouts
        WorkoutDatabase db = WorkoutDatabase.getDBInstance(this.getApplicationContext());

        List<Treeni> treeniList = db.treeniDAO().getAllTreeni();

        ListView lv =findViewById(R.id.Treenilist);
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, treeniList));

        // Perform item selected listener
        //Tutorial video link https://www.youtube.com/watch?v=Q9Xwyfor-kQ&ab_channel=GurkanUcar

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Tarkistaa missä navbarin valikossa käyttäjä on, ja avaa valikon Activityn
                switch(item.getItemId())
                {
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(), Favorites.class));
                        //Poistaa avaus animaation
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.notication:
                        startActivity(new Intent(getApplicationContext(), Notes_Activity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    //Tarkistaa aina kun activity aukeaa onko avauskerta first time
    @Override
    protected void onStart() {
        super.onStart();
        prefs = getSharedPreferences("hasRunBefore", 0);
        Boolean firstTime = prefs.getBoolean("hasRun", false);
        checkFirstTime(firstTime);
    }

    //henrin oksan tapahtuma
    public void newGymActivity(View v){
        Intent startGym = new Intent(this, Workout_selection_activity.class);
        startActivity(startGym);
    }
    //Tarkistaa onko sovelluksen käynnistäminen ensimmäinen kerta. Jos on, avaa sovellus käyttäjä luomis Activityn
    //Else laittaa TextView kenttään käyttäjän kertoman käyttäjätunnuksen
    private void checkFirstTime(Boolean firstTime) {
        if(!firstTime){
            Intent intent = new Intent(this, Create_UserActivity.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        }else{
            TextView mtext = findViewById(R.id.UserNameM);
            SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
            String value = sharedPreferences.getString("value","");
            mtext.setText("Hei " + value);
        }

    }

    //pakottaa taaksepäin nuolen palauttamaan kotiruutuun
    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

}