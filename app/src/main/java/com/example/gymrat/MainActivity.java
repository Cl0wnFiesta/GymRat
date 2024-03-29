package com.example.gymrat;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.gymrat.workoutDb.Treeni;
import com.example.gymrat.workoutDb.WorkoutDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Collections;
import java.util.List;


/**
 * Ohjelman "Kotiruutu" Activity-luokka.
 * Sisältää navigointipalkin, vanhat treenit ja napin josta aloittaa uusi treeni.
 * @author Henri
 * @author Jonne
 * @author Axel
 */
public class MainActivity extends AppCompatActivity {
    List<Treeni> treeniList;
    TextView Suoritus;
    private SharedPreferences prefs;
    public static final String EXTRA_OLD_WORKOUT = "gymrat.extra_old_workout";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Suoritus = findViewById(R.id.suoritusName);
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Load all workouts
        WorkoutDatabase db = WorkoutDatabase.getDBInstance(this.getApplicationContext());

        treeniList = db.treeniDAO().getAllTreeni();
        Collections.reverse(treeniList);
        ArrayAdapter<Treeni> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, treeniList);
        ListView lv =findViewById(R.id.Treenilist);
        lv.setAdapter(adapter);
        lv.setLongClickable(true);
        if(treeniList.isEmpty()){
            Suoritus.setText("Ei suorituksia!");
        }else {
            Suoritus.setText("Suorituksesi!");
        }
        getTrophy();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick(" + i + ")");
                Intent nextActivity = new Intent(MainActivity.this, OldWorkoutActivity.class);
                nextActivity.putExtra(EXTRA_OLD_WORKOUT, i);
                startActivity(nextActivity);


            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                //popup tietokannan tuotteiden poistoa varten
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupNakyma = inflater.inflate(R.layout.popup_delete, null);
                int leveys = LinearLayout.LayoutParams.WRAP_CONTENT;
                int pituus = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupIkkuna = new PopupWindow(popupNakyma, leveys, pituus, focusable);
                popupIkkuna.showAtLocation(view, Gravity.CENTER, 0, 0);

                Button yesButton = popupNakyma.findViewById(R.id.deleteYes), noButton= popupNakyma.findViewById(R.id.deleteNo);
                //poistaa tietokannasta valitun ja päivittää tiedot listviewiin
                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.treeniDAO().deleteTreeni(treeniList.get(pos));
                        treeniList.remove(pos);
                        adapter.notifyDataSetChanged();
                        popupIkkuna.dismiss();
                        if(treeniList.isEmpty()){
                            Suoritus.setText("Ei suorituksia");
                        }else {
                            Suoritus.setText("Suorituksesi!");
                        }
                    }
                });

                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupIkkuna.dismiss();
                    }
                });
                return true;
            }
        });



        // Perform item selected listener
        //Tutorial video link https://www.youtube.com/watch?v=Q9Xwyfor-kQ&ab_channel=GurkanUcar

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Tarkistaa missä navbarin valikossa käyttäjä on, ja avaa valikon Activityn
                switch(item.getItemId())
                {
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(), TrophyActivity.class));
                        //Poistaa avaus animaation
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.notication:
                        startActivity(new Intent(getApplicationContext(), Notes_Activity.class));
                        overridePendingTransition(0,0);

                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        checkDarkMode();
    }
    //Tarkistaa aina kun activity aukeaa onko avaus kerta first time
    @Override
    protected void onStart() {
        super.onStart();
        prefs = getSharedPreferences("hasRunBefore", 0);
        Boolean firstTime = prefs.getBoolean("hasRun", false);
        checkFirstTime(firstTime);
    }


    /**
     * When the user clicks the button, start a new activity called Workout_selection_activity.class
     *
     * @param v The view that was clicked.
     */
    public void newGymActivity(View v){

        Intent startGym = new Intent(this, Workout_selection_activity.class);
        startActivity(startGym);
    }
    //Tarkistaa onko sovelluksen käynnistäminen ensimmäinen kerta. Jos on, avaa sovellus käyttäjä luomis Activityn
    //Else laittaa TextView kenttään käyttäjän kertoman käyttäjätunnuksen
    private void checkFirstTime(Boolean firstTime) {
        if(!firstTime){
            Intent intent = new Intent(this, Create_UserActivity.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        }else{
            TextView mtext = findViewById(R.id.UserNameM);
            SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
            String value = sharedPreferences.getString("value","");
            mtext.setText("Hei " + value);
        }

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

    //Tarkistaa onko sovelluksen DarkMode asetus laitettu päälle
    public void checkDarkMode(){
        prefs = getSharedPreferences("myKey",0);
        if(prefs.getBoolean("DarkMode",false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void getTrophy(){
        SharedPreferences getPref = getSharedPreferences("myTrophies", MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = getPref.edit();
        int listLength = treeniList.size();
        String trophyNumber = "trophy5";
        if(listLength>=10){
            if(getPref.getBoolean(String.valueOf(trophyNumber),false)){
            } else {
                prefEdit.putBoolean("trophy5", true);
                prefEdit.apply();
                Toast.makeText(getApplicationContext(), "Uusi saavutus avattu", Toast.LENGTH_SHORT).show();
                }
            }
        }
}