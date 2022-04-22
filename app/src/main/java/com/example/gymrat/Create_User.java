package com.example.gymrat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.TwoStatePreference;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Create_User extends AppCompatActivity {

    private static RadioButton male;
    private EditText nameText;

    private RadioGroup radioSexGroup;
    private static RadioButton female;
    private static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_layout);
        Intent intent = getIntent();
    }

    public void startButton(View v){
        setContentView(R.layout.pick_gender);
    }
    public void nextPage(View v){
        Intent intent = new Intent(this, SetupPage.class);
        nameText = (EditText) findViewById(R.id.kayttajatunnus);
        String name = nameText.getText().toString();

        male = (RadioButton)findViewById(R.id.man);
        female = (RadioButton)findViewById(R.id.woman);


        if(name.matches("" ) | !male.isChecked() & !female.isChecked()) {
            Log.d("TAG", "Nullissa");
        }else{
            saveGenderInPreference();
            saveUserName();
            startActivity(intent);
        }
    }

    private void saveGenderInPreference() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        radioSexGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedId = radioSexGroup.getCheckedRadioButtonId();
        if(selectedId == R.id.man)
            editor.putBoolean("is_male", true);
        else
            editor.putBoolean("is_male", false);
        editor.commit();
    }

    public void saveUserName(){
        Log.d("TAG", "Saved username");
        String value = nameText.getText().toString().trim();
        SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("value", value);
        editor.apply();
    }


      /*  SharedPreferences settings = getSharedPreferences("hasRunBefore", 0);
        SharedPreferences.Editor edit = settings.edit();
        edit.putBoolean("hasRun", true);
        edit.commit(); //apply
       */
}
