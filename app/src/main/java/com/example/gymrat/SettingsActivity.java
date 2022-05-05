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

/**
 *Activity-luokka joka antaa käyttäjälle mahdollisuuden muokata omia tietoja, joita käyttäjältä kysyttiin sovelluksen ensimmäisellä käynnistys kerralla. Mahdollistaa "DarkModen" kytkemisen sovellukselle.
 * @author Axel
 */
public class SettingsActivity extends AppCompatActivity {
    EditText maxPenkki, maxKyykky, maxMaastaveto, maxPystyPunnerrus;
    Button popupCloseBtn;
    EditText tvName;
    FrameLayout FrameLayout;
    Switch swDarkMode;
    SharedPreferences prefGet;
    SharedPreferences.Editor prefEdit;
    Boolean isDarkMode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
        FrameLayout = findViewById(R.id.FrameLayoutP);
        swDarkMode = findViewById(R.id.dmSwitch);
        tvName = findViewById(R.id.tvName);
        prefGet = getSharedPreferences("myKey", MODE_PRIVATE);
        prefEdit = prefGet.edit();
        isDarkMode = prefGet.getBoolean("DarkMode",false);

        String value = prefGet.getString("value","");
        tvName.setText(value);
        checkDarkMode();

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
                        startActivity(new Intent(getApplicationContext(), TrophyActivity.class));
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
    /**
     * Pakottaa back buttonin sulkemaan sovelluksen
     */
    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

//Changes username and checks that new username cannot be empty. Creates Toast message after action

    /**
     * If the user has entered a name, save it to the shared preferences and show a toast message
     * If entered username is empty method will show a toast message
     *
     * @param v The view that was clicked.
     */
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
    /**
     * It inflates a popup window over current activity and allows user to edit values that were inputted on user creation.
     *
     * @param view The view that was clicked.
     */
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

    /**
     * If the user has dark mode enabled, then set the switch to checked and set the app's theme to
     * dark mode. If the user has dark mode disabled, then set the app's theme to light mode
     */
    public void checkDarkMode(){

        if(isDarkMode){
            swDarkMode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    // Changes app to use dark theme
    /**
     * If the switch is checked, set the dark mode to true and apply it, else set the dark mode to
     * false
     *
     * @param view The view that was clicked.
     */
    public void toggleDarkMode(View view){
        if(swDarkMode.isChecked()){
            prefEdit.putBoolean("DarkMode",true);
            prefEdit.apply();
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            prefEdit.putBoolean("DarkMode",false);
            prefEdit.apply();
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    // Script to open Trophy activity
    /**
     * When the user clicks the trophy button, open the TrophyActivity.
     *
     * @param v The view that was clicked.
     */
    public void openTrophys(View v){
        Intent showTrophys = new Intent(this, TrophyActivity.class);
        startActivity(showTrophys);

    }


}