package com.example.gymrat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TrophyActivity extends AppCompatActivity {

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
    }


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

    public void disableTrophys(View v){
        int resID;
        int i=1;
        for(int z=0; z<8; z++){
            String cardID = "cardView"+i;
            i++;
            resID = getResources().getIdentifier(cardID, "id",getPackageName());
            CardView kortti = findViewById(resID);
            kortti.setAlpha((float)0.5);
        }
    }

    public void getTrophy(int id){
        int i=id;
        Toast.makeText(getApplicationContext(), "Uusi saavutus avattu", Toast.LENGTH_SHORT).show();
        String trophyNumber = "trophy"+i;
        prefEdit.putBoolean(String.valueOf(trophyNumber),true);
        prefEdit.apply();

    }


    public void createTrophys(){

        if(getPref.getBoolean("created",created)){
        return;
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