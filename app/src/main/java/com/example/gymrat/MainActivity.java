package com.example.gymrat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.gymrat.NavBar_Activities.Notification;
import com.example.gymrat.NavBar_Activities.Favorites;

import com.example.gymrat.NavBar_Activities.Settings;
import com.example.gymrat.Workout.StartedWorkoutActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

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
                        startActivity(new Intent(getApplicationContext(), Notification.class));
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
    //Tarkistaa aina kun activity aukeaa onko avaus kerta first time
    @Override
    protected void onStart() {
        super.onStart();
        prefs = getSharedPreferences("hasRunBefore", 0);
        Boolean firstTime = prefs.getBoolean("hasRun", false);
        checkFirstTime(firstTime);
    }

    //henrin oksan tapahtuma
    public void newGymActivity(View v){
        Intent startGym = new Intent(this, StartedWorkoutActivity.class);
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

}