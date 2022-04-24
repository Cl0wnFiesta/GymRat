package com.example.gymrat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;

public class SetupPageActivity extends AppCompatActivity {

    private EditText maxPenkki, maxKyykky, maxMaastaveto, maxPystyPunnerrus;
    private maxWeight maxWeight;

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_setup_page);
        maxPenkki = (EditText) findViewById(R.id.maxPenkki);
        maxKyykky = (EditText) findViewById(R.id.maxKyykky);
        maxMaastaveto = (EditText) findViewById(R.id.maxMaastaveto);
        maxPystyPunnerrus = (EditText) findViewById(R.id.maxPystyPunnerrus);
        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        maxWeight = new maxWeight();

        if(sharedPreferences.getBoolean("is_male", true)) {
            maxPenkki.setText(maxWeight.Mpenkki());
            maxKyykky.setText(maxWeight.Mkyykky());
            maxMaastaveto.setText(maxWeight.Mmaastaveto());
            maxPystyPunnerrus.setText(maxWeight.Mpystypunnerrus());
        }
        else{
            maxPenkki.setText(maxWeight.Npenkki());
            maxKyykky.setText(maxWeight.Nkyykky());
            maxMaastaveto.setText(maxWeight.Nmaastaveto());
            maxPystyPunnerrus.setText(maxWeight.Npystypunnerrus());
        }
        TextView mtext = findViewById(R.id.UserName);
        sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");
        mtext.setText("Kerro meille itsestäsi " + value + ".");
    }
    public void toMainMenu(View v){
        Intent TestActivity = new Intent(this, MainActivity.class);
        saveInformation();
        startActivity(TestActivity);
        SharedPreferences settings = getSharedPreferences("hasRunBefore", 0);
        SharedPreferences.Editor edit = settings.edit();
        edit.putBoolean("hasRun", true);
        edit.apply(); //apply


    }
    public void saveInformation(){
        String penkki = maxPenkki.getText().toString();
        String kyykky = maxKyykky.getText().toString();
        String maastaveto = maxMaastaveto.getText().toString();
        String pystypunnerrus = maxPystyPunnerrus.getText().toString();
        SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("penkki", penkki);
        editor.putString("kyykky", kyykky);
        editor.putString("maastaveto", maastaveto);
        editor.putString("pystypunnerrus", pystypunnerrus);
        editor.apply();
    }
}
