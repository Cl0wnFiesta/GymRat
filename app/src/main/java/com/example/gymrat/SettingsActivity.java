package com.example.gymrat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {
    EditText maxPenkki, maxKyykky, maxMaastaveto, maxPystyPunnerrus;
    Button popupCloseBtn;
    EditText tvName;
    LinearLayout LinearLayout;
    FrameLayout FrameLayout;
    View popupView;
    Switch swDarkMode;
    SharedPreferences prefGet;
    SharedPreferences.Editor prefEdit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
        FrameLayout = findViewById(R.id.FrameLayoutP);
        swDarkMode = findViewById(R.id.dmSwitch);
        tvName = findViewById(R.id.tvName);
        prefGet = getSharedPreferences("myKey", MODE_PRIVATE);
        prefEdit = prefGet.edit();
        String value = prefGet.getString("value","");
//        boolean isDarkMode = sharedPreferences.getBoolean("DarkMode", false);
        tvName.setText(value);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.settings);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(), AchievementsActivity.class));
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
                    case R.id.settings:
                        return true;
                }
                return false;
            }
        });
    }


//Changes username and checks that new username cannot be empty. Creates Toast message after action

    public void updatePreferences(View v){
        String newName = tvName.getText().toString();

        if(newName.matches("\\s*")){
            Toast.makeText(getApplicationContext(),"Käyttäjätunnus ei voi olla tyhjä!",Toast.LENGTH_SHORT).show();
        }
        else{
            prefEdit.putString("value", tvName.getText().toString().trim());
            prefEdit.apply();
            Toast.makeText(getApplicationContext(), "Tietosi on nyt päivitetty " + tvName.getText(), Toast.LENGTH_SHORT).show();
        }
    }

//Creates popupwindow over current activity and allows user to edit values that were inputted on user creation.

    public void popupMenu(View view){
        LayoutInflater layoutInf = (LayoutInflater) SettingsActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInf.inflate(R.layout.popup_settings, null);
        if(swDarkMode.isChecked()) {
            popupView = layoutInf.inflate(R.layout.popup_settings_dark, null);
        }else{
            popupView = layoutInf.inflate(R.layout.popup_settings, null);
        }
        PopupWindow popupSettings = new PopupWindow(popupView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,true);
        popupSettings.showAtLocation(FrameLayout, Gravity.CENTER, 0, 0);


        maxPenkki = (EditText) popupView.findViewById(R.id.maxPenkki);
        maxKyykky = (EditText) popupView.findViewById(R.id.maxKyykky);
        maxMaastaveto = (EditText) popupView.findViewById(R.id.maxMaastaveto);
        maxPystyPunnerrus = (EditText) popupView.findViewById(R.id.maxPystyPunnerrus);
        maxPenkki.setText(prefGet.getString("penkki","0"));
        maxKyykky.setText(prefGet.getString("kyykky","0"));
        maxMaastaveto.setText(prefGet.getString("maastaveto","0"));
        maxPystyPunnerrus.setText(prefGet.getString("pystypunnerrus","0"));

        popupCloseBtn = popupView.findViewById(R.id.closeBtn);
        popupCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(maxKyykky.getText().toString().matches("\\s*")|| maxPenkki.getText().toString().matches("\\s*")|| maxMaastaveto.getText().toString().matches("\\s*")|| maxPystyPunnerrus.getText().toString().matches("\\s*")){
                    Toast.makeText(getApplicationContext(),"Yksikään kenttä ei voi olla tyhjä!",Toast.LENGTH_SHORT).show();
                }else{
                    prefEdit.putString("penkki",maxPenkki.getText().toString());
                    prefEdit.putString("kyykky",maxKyykky.getText().toString());
                    prefEdit.putString("maastaveto",maxMaastaveto.getText().toString());
                    prefEdit.putString("pystypunnerrus",maxPystyPunnerrus.getText().toString());
                    prefEdit.commit();
                    popupSettings.dismiss();
                    Toast.makeText(getApplicationContext(),"Tiedot päivitetty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Changes app to use dark theme
    public void toggleDarkMode(View view){
        if(swDarkMode.isChecked()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    // Script to open Trophy activity
    public void openTrophys(View v){
        Intent showTrophys = new Intent(this, TrophyActivity.class);
        startActivity(showTrophys);

    }


}