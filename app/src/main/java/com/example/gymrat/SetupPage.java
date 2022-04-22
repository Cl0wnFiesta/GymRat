package com.example.gymrat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SetupPage extends AppCompatActivity {

    private static SharedPreferences sharedPreferences;
    private TextView mtext, gtext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_setup_page);
        Intent intent = getIntent();
        sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        gtext = findViewById(R.id.gender);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(sharedPreferences.getBoolean("is_male", true))
            gtext.setText("Mies");
        else
            gtext.setText("Nainen");

        mtext = findViewById(R.id.UserName);
        sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");
        mtext.setText("Kerro meille itsestäsi " + value);
    }
}
