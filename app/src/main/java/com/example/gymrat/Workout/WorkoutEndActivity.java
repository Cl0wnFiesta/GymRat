/**
 * @author Henri
 * Activity-luokka joka näyttää käyttäjälle tietoa sen perusteella, mitä treeniä hän on tehnyt ja mitä sen sisällä valinnut
 * Tallentaa Activityn alussa tiedot tietokantaan treenistä.
 * Suosittelee treenipainojen nostamista tarvittaessa.
 */
package com.example.gymrat.Workout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.gymrat.MainActivity;
import com.example.gymrat.R;
import com.example.gymrat.Workout.WorkoutDB.Treeni;
import com.example.gymrat.Workout.WorkoutDB.WorkoutDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkoutEndActivity extends AppCompatActivity {
    SharedPreferences sp;
    private double maxPenkki, maxKyykky, maxMaastaveto, maxPystypunnerrus;

    //hae intentistä viime activityn antamat arvot
    Intent intent;
    Bundle extras;

    private String tunniste;
    private double suosittelu;
    private int yksPlus;
    TextView setitTxt, suositteluTxt, salipaivaTxt;
    boolean tallennettu = false;
    private Date date = new Date();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy");
    private String treenipaiva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_end);
        intent = getIntent();
        extras = intent.getExtras();
        treenipaiva = formatter.format(date);
        Button hyvaksyKorotusBtn;
        ImageView  palaaKotiinBtn;

        fetchPreferences(); //haetaan käyttäjän preferenssit suorituksen tietokantaan tallennusta varten
         tunniste = extras.getString("EXTRA_TUNNISTENIMI");
         suosittelu = extras.getDouble("EXTRA_SUOSITTELE");
         yksPlus = extras.getInt("EXTRA_YKSPLUS");
         Log.d("suosittelu", Double.toString(suosittelu));
        salipaivaTxt = findViewById(R.id.SalipaivaValue);
        setitTxt = findViewById(R.id.setitValue);
        suositteluTxt = findViewById(R.id.suositteluValue);
        palaaKotiinBtn = findViewById(R.id.PalaaButton);
        hyvaksyKorotusBtn = findViewById(R.id.HyvaksyButton);

        salipaivaTxt.setText(muutaTunniste(tunniste));
        setitTxt.setText(Integer.toString(yksPlus));
        suositteluTxt.setText(Double.toString(suosittelu));

        if (!tallennettu){
            saveTreeni(treenipaiva, muutaTunniste(tunniste),yksPlus, suosittelu);
        }

        palaaKotiinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();
            }
        });
        hyvaksyKorotusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hyvaksyKorotus(muutaTunniste(tunniste));
            }
        });



    }

    //Hakee käyttäjän preferenssit
    private void fetchPreferences(){
        sp = getSharedPreferences("myKey", MODE_PRIVATE);
        maxPenkki = Double.parseDouble(sp.getString("penkki", "0"));
        maxKyykky = Double.parseDouble(sp.getString("kyykky", "0"));
        maxMaastaveto = Double.parseDouble(sp.getString("maastaveto", "0"));
        maxPystypunnerrus = Double.parseDouble(sp.getString("pystypunnerrus", "0"));
    }

    //muuttaa tunnisteen sitä vastaavaksi salipäiväksi
    private String muutaTunniste(@NonNull String tunniste){
        String tulkattuTunniste = "";
        switch (tunniste){
            case "WorkoutOne":
                tulkattuTunniste = "Pystypunnerrus";
                break;
            case "WorkoutTwo":
                tulkattuTunniste = "Kyykky";
                break;
            case "WorkoutThree":
                tulkattuTunniste = "Penkki";
                break;
            case "WorkoutFour":
                tulkattuTunniste = "Maastaveto";
                break;
        }
        return tulkattuTunniste;
    }
    //Tallentaa treenin tietokantaan
    private void saveTreeni(String treenipaiva, String treeniNimi, int toistot, double korotus){
        WorkoutDatabase db = WorkoutDatabase.getDBInstance(this.getApplicationContext());
        Treeni treeni = new Treeni();
        treeni.paiva = treenipaiva;
        treeni.treeninNimi = treeniNimi;
        treeni.toistot = toistot;
        treeni.korotus = korotus;
        db.treeniDAO().insertTreeni(treeni);
    }
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        returnHome();
    }
    //palauttaa kutsuttaessa mainactivityyn
    private void returnHome() {
        Intent setIntent = new Intent(this ,MainActivity.class);
        startActivity(setIntent);
    }
    //Muuttaa preferensseihin treenin pä
    private void hyvaksyKorotus(@NonNull String treeniNimi){
        String treeni = treeniNimi.toLowerCase();
        double tallennettava;
        sp = getSharedPreferences("myKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        switch(treeni){

            case "penkki":
                tallennettava = maxPenkki + suosittelu;
                editor.putString(treeni, Double.toString(tallennettava));
                break;
            case "kyykky":
                tallennettava = maxKyykky + suosittelu;
                editor.putString(treeni, Double.toString(tallennettava));
                break;
            case "maastaveto":
                tallennettava = maxMaastaveto + suosittelu;
                editor.putString(treeni, Double.toString(tallennettava));
                break;
            case "pystypunnerrus":
                tallennettava = maxPystypunnerrus + suosittelu;
                editor.putString(treeni, Double.toString(tallennettava));
                break;
        }
        editor.apply();

    }


}