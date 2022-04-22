package com.example.gymrat;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;


public class Create_User extends AppCompatActivity {

    private EditText nameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_layout);
    }

    public void startButton(View v){
        setContentView(R.layout.pick_gender);
    }
    public void nextPage(View v){
        Intent intent = new Intent(this, SetupPage.class);
        nameText = (EditText) findViewById(R.id.kayttajatunnus);
        String name = nameText.getText().toString();

        RadioButton male = (RadioButton) findViewById(R.id.man);
        RadioButton female = (RadioButton) findViewById(R.id.woman);


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
        RadioGroup radioSexGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedId = radioSexGroup.getCheckedRadioButtonId();
        editor.putBoolean("is_male", selectedId == R.id.man);
        editor.apply();
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
