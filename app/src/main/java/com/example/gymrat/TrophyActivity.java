package com.example.gymrat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gymrat.TrophyActivity;
import com.example.gymrat.OldWorkoutActivity;
import com.example.gymrat.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrophyActivity extends AppCompatActivity {


    /**
     * Activity-luokka joka näyttää käyttäjän saadut Saavutukset
     * @author Axel
     */

    boolean trophy1=false,trophy2=false,trophy3=false,trophy4=false,trophy5=false, created=false;
    SharedPreferences getPref;
    SharedPreferences.Editor prefEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);
        getPref = getSharedPreferences("myTrophies", MODE_PRIVATE);
        prefEdit = getPref.edit();
        createTrophys();
        checkTrophyStatus();

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.favorites);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.notication:
                        startActivity(new Intent(getApplicationContext(), Notes_Activity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favorites:
                        return true;
                }
                return false;
            }
        });
    }


    /**
     * If the trophy is unlocked, the star image is changed to a golden star and the cardview is made
     * visible
     */
    public void checkTrophyStatus(){

        for(int i = 1; i<6; i++){
            int resID,starID;
            String cardID = "cardView"+i ,starNumber = "imageStar"+i ,trophyNumber = "trophy"+i ;

            boolean trophy = getPref.getBoolean(String.valueOf(trophyNumber),false);
            resID = getResources().getIdentifier(cardID, "id",getPackageName());
            starID = getResources().getIdentifier(starNumber,"id",getPackageName());
            ImageView star = findViewById(starID);
            CardView kortti = findViewById(resID);
            if(trophy){
                star.setImageResource(R.drawable.ic_star);
                kortti.setAlpha((float)1);
            }
        }
    }


/*
    public void getTrophy(int id){
        int i=id;
        getPref = getSharedPreferences("myTrophies", MODE_PRIVATE);
        prefEdit = getPref.edit();
        Toast.makeText(getApplicationContext(), "Uusi saavutus avattu", Toast.LENGTH_SHORT).show();
        String trophyNumber = "trophy"+i;
        prefEdit.putBoolean(String.valueOf(trophyNumber),true);
        prefEdit.apply();

    }
*/

    /**
     * Creates trophy values for the user for the first time and changes boolean "created" to true, so trophy values wont be created again.
     *
     */
    public void createTrophys(){

        if(getPref.getBoolean("created",created)){ return;
        }else {
            created = true;
            prefEdit.putBoolean("trophy1", trophy1);
            prefEdit.putBoolean("trophy2", trophy2);
            prefEdit.putBoolean("trophy3", trophy3);
            prefEdit.putBoolean("trophy4", trophy4);
            prefEdit.putBoolean("trophy5", trophy5);
            prefEdit.putBoolean("created",created);
            prefEdit.apply();
        }
    }
}