package com.example.gymrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private TextView mtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mtext = findViewById(R.id.testbox);
        sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("penkki","");
        mtext.setText(value);
    }
}