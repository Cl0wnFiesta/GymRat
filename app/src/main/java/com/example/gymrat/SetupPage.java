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

public class SetupPage extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private TextView mtext;
    private EditText maxPenkki, maxKyykky, maxMaastaveto, maxPystyPunnerrus;

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_setup_page);
        maxPenkki = (EditText) findViewById(R.id.maxPenkki);
        maxKyykky = (EditText) findViewById(R.id.maxKyykky);
        maxMaastaveto = (EditText) findViewById(R.id.maxMaastaveto);
        maxPystyPunnerrus = (EditText) findViewById(R.id.maxPystyPunnerrus);
        sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(sharedPreferences.getBoolean("is_male", true)) {
            maxPenkki.setText("20.0");
            maxKyykky.setText("32.5");
            maxMaastaveto.setText("6.15");
            maxPystyPunnerrus.setText("200.50");
        }
        else{
            maxPenkki.setText("10.0");
            maxKyykky.setText("22.5");
            maxMaastaveto.setText("3.15");
            maxPystyPunnerrus.setText("100.50");
        }
        mtext = findViewById(R.id.UserName);
        sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");
        mtext.setText("Kerro meille itsestäsi " + value + ".");
    }
    public void toMainMenu(View v){
        Intent mainActivity = new Intent(this, MainActivity.class);

    }
}
