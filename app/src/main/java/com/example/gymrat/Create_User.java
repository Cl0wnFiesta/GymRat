package com.example.gymrat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Create_User extends AppCompatActivity {
    public static final String EXTRA_MESSAGE_name = "Name";
    private RadioGroup radioGroup;
    private RadioButton manB;
    private RadioButton womanB;
    private int Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_layout);
        Intent intent = getIntent();
        radioGroup = findViewById(R.id.radioGroup);


    }

    public void startButton(View v){
        setContentView(R.layout.pick_gender);
    }
    public void nextPage(View v){
        Intent intent = new Intent(this, SetupPage.class);
        EditText nameText = (EditText) findViewById(R.id.kayttajatunnus);
        String name = nameText.getText().toString();
        manB = findViewById(R.id.man);
        womanB = findViewById(R.id.woman);
        if(name.matches("" ) | !manB.isChecked() & !womanB.isChecked()) {
            Log.d("TAG", "Nullissa");
        }else{
            Log.d("TAG", "Ei tyhjä");
        }

    }

      /*  SharedPreferences settings = getSharedPreferences("hasRunBefore", 0);
        SharedPreferences.Editor edit = settings.edit();
        edit.putBoolean("hasRun", true);
        edit.commit(); //apply
       */
}
