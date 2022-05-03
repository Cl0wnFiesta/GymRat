
package com.example.gymrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gymrat.Classes.Workout;
import com.example.gymrat.Classes.WorkoutGlobal;

 /**
 * Activity-luokka, jossa näkyy aloitettu treeni. \n
 * Treeniohjelman näkymä latautuu intentin antaman tunnistenimen perusteella.
  * @author Henri
 */
public class StartedWorkoutActivity extends AppCompatActivity {
    private double maxPenkki, maxKyykky, maxMaastaveto, maxPystypunnerrus;
    private Workout treeni;
    private TextView toistot, paino, nimi, treeniPosValue;
    private Button plusButton;
    private Button minusButton;
    Intent intent;

    //Muuttujat nappien toimintaan
    private boolean secondPhase = false;
    private int treeniPos = 0;

    //Tunnistenimellä hallitaan mikä treeni on käynnissä. Tunnistenimeen otetaan tieto intentistä, ja ohjelma hakee singleton luokasta tiedot treeniä varten
    WorkoutGlobal workoutGlobal = WorkoutGlobal.getInstance();
    String tunnisteNimi;

    //Asetetaan arrayhin arvot joita käytetään treenissä
    private int[] ekatSetit, tokatSetit;
    private double[] ekaPainokerroin ,tokaPainokerroin;
    private String ekaSettiNimi ,tokaSettiNimi;

    //Tietoa bundlen pakkausta varten seuraavalle activitylle
    private int yksPlusKierros = 1;
    double suositteluNousu = 0;
    public static final String EXTRA_YKSPLUS = "EXTRA_YKSPLUS";
    public static final String EXTRA_SUOSITTELE = "EXTRA_SUOSITTELE";
    public static final String EXTRA_TUNNISTENIMI = "EXTRA_TUNNISTENIMI";

    /**
     * @author Jonne ja Henri
     *Asettaa treenille tunnistenimen perusteella arvot toistoihin, painokertoimeen ja nimeen.
     */
    private void workoutCode() {
        ekatSetit = workoutGlobal.getWorkoutToistot(tunnisteNimi, 1);
        tokatSetit = workoutGlobal.getWorkoutToistot(tunnisteNimi, 2);
        ekaPainokerroin  = workoutGlobal.getWorkoutPainokerroin(tunnisteNimi, 1);
        tokaPainokerroin = workoutGlobal.getWorkoutPainokerroin(tunnisteNimi, 2);
        ekaSettiNimi = workoutGlobal.getWorkoutName(tunnisteNimi, 1);
        tokaSettiNimi = workoutGlobal.getWorkoutName(tunnisteNimi, 2);
    }

    /**
     * Hakee aikaisemman näkymän antaman intentin ja asettaa sieltä saadun tunnistenimen treenille.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_workout);
        intent = getIntent();
        tunnisteNimi = intent.getStringExtra("workoutCode");
        workoutCode();

        toistot = findViewById(R.id.ToistotNum);
        paino = findViewById(R.id.PainoNum);
        nimi = findViewById(R.id.WorkoutNimi);
        Button seuraavaNappi = findViewById(R.id.NextPhase);
        plusButton = findViewById(R.id.plusButton);
        minusButton = findViewById(R.id.minusButton);
        Button takaisinNappi = findViewById(R.id.prevPhase);
        treeniPosValue = findViewById(R.id.nytSetti);

        //Hakee preferenssit ja aloittaa ensimmäisen treenin
        fetchPreferences();
        treeni = new Workout(maxPenkki, maxKyykky, maxMaastaveto, maxPystypunnerrus);
        treeni.startWorkout(ekatSetit, ekaPainokerroin, ekaSettiNimi);

        seuraavaNappi.setOnClickListener(view -> buttonLogic());
        takaisinNappi.setOnClickListener(view -> backButtonLogic());
        plusButton.setOnClickListener(view -> {
            yksPlusKierros++;
            toistot.setText(Integer.toString(yksPlusKierros) + "+");
        });
        minusButton.setOnClickListener(view -> {
                if(yksPlusKierros -1 >= 0){
                    yksPlusKierros--;
                    toistot.setText(Integer.toString(yksPlusKierros) + "+");
                }
        });

        //asettaa ensimmäisen treenin alkutiedot ja luvut
        setTreeniTiedot();
        setCounter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchPreferences();
    }

    /**
     * Asettaa näytölle treenin nimen, painot ja toistomäärän riippuen missä kohtaa treeniä ollaan.
     */
    private void setTreeniTiedot() {
        setButtonVisibility(false);
        nimi.setText(treeni.getTreeniNimi());
        print(paino, treeni.getPaino(treeniPos));
        if(treeni.getLiikkeita(treeniPos) == 1){
            toistot.setText(Integer.toString(yksPlusKierros) + "+");
            setButtonVisibility(true);
        }
        if(treeni.getLiikkeita(treeniPos) > 1 && treeni.getPituus() != treeniPos){
            print(toistot, treeni.getLiikkeita(treeniPos));
        }
        if(treeni.getLiikkeita(treeniPos) > 1){
            print(toistot, treeni.getLiikkeita(treeniPos));
        }
        if(treeni.getPituus() == treeniPos +1){
            toistot.setText((treeni.getLiikkeita(treeniPos)) + "+");
        }
        if (!secondPhase){
            treeniPosValue.setText("1");
        }
        if (secondPhase){
            treeniPosValue.setText("2");
        }

    }

    /**
     * Hakee käyttäjän preferenssit
     */
    private void fetchPreferences(){
        SharedPreferences sp = getSharedPreferences("myKey", MODE_PRIVATE);
        maxPenkki = Double.parseDouble(sp.getString("penkki", "0"));
        maxKyykky = Double.parseDouble(sp.getString("kyykky", "0"));
        maxMaastaveto = Double.parseDouble(sp.getString("maastaveto", "0"));
        maxPystypunnerrus = Double.parseDouble(sp.getString("pystypunnerrus", "0"));
    }
    // Omat metodit numeroiden näyttämiselle näytöllä.
    // Ei tarpeellinen pienessä skaalassa mutta halusin kokeilla
    private void print(TextView tv, double luku){
        tv.setText(Double.toString(luku));
    }
    private void print(TextView tv, int luku){
        tv.setText(Integer.toString(luku));
    }

    /**
     *Aina kun painaa nappia, niin edistää treeniä askeleella.
     *Jos ollaan treenin vikassa kohdassa, asettaa luvun nollaan ja aloittaa toisen vaiheen treeniä
     * Treenin lopussa aloittaa treenin lopettamisen metodin.
     */
    private void buttonLogic(){
        int liikeCount = treeni.getPituus();
        if (treeniPos + 1 <= liikeCount && !secondPhase){
            treeniPos++;
            suositteluNousu = treeni.suggestIncrease(yksPlusKierros);
            Log.d("treenisuggest", Double.toString(suositteluNousu));
        }
        if (treeniPos < liikeCount && secondPhase){
            treeniPos++;
        }
        if(treeniPos == liikeCount && !secondPhase){
            treeniPos = 0;
            treeni.startWorkout(tokatSetit, tokaPainokerroin, tokaSettiNimi);
            secondPhase = true;
        }
        if (treeniPos <= liikeCount){
            Log.d("treenipos",Integer.toString(treeniPos) );
            Log.d("treenilop",Integer.toString(liikeCount) );
        }

        if(treeniPos == liikeCount && secondPhase){
            endWorkout();
        }

        if(treeniPos < liikeCount){
            setTreeniTiedot();
            setCounter();
        }
    }

    /**
     *Lopettaa treenin ja antaa tarvittavat tiedot eteenpäin tallennettavaksi
     */
    private void endWorkout() {
        suositteluNousu = treeni.suggestIncrease(yksPlusKierros);
        Log.d("treenisuggest", Double.toString(suositteluNousu));
        Intent endWorkout = new Intent(this, WorkoutEndActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_YKSPLUS, yksPlusKierros);
        bundle.putDouble(EXTRA_SUOSITTELE, suositteluNousu);
        bundle.putString(EXTRA_TUNNISTENIMI, tunnisteNimi);
        endWorkout.putExtras(bundle);
        startActivity(endWorkout);
    }

    /**
     *Logiikka näppäimelle joka menee taaksepäin.
     * Treenin alussa toimii backbuttonina.
     */
    private void backButtonLogic(){
        int liikeCount = treeni.getPituus();

        if (treeniPos -1 < 0 && !secondPhase){
            onBackPressed();
        }
        if (treeniPos -1 >= 0){
            treeniPos--;
        }
        if(treeniPos -1 < 0 && secondPhase){
            treeni.startWorkout(ekatSetit, ekaPainokerroin, ekaSettiNimi);
            treeniPos = treeni.getPituus() -1;
            secondPhase = false;
        }
        if (treeniPos <= liikeCount){
            Log.d("kohta",Integer.toString(treeniPos) );
        }

        setTreeniTiedot();
        setCounter();
    }

    /**
     *asettaa luvut näytön oikeaan yläreunaan treenin kestosta
     */
    private void setCounter(){
        TextView nytToisto = findViewById(R.id.nytToisto), setinLoppu = findViewById(R.id.maxToisto);
        nytToisto.setText(Integer.toString(treeniPos + 1));
        setinLoppu.setText(Integer.toString(treeni.getPituus()));
    }

    /**
     * Asettaa plus ja minus näppäimet näkyviin tai piiloon arvon mukaan.
     * @param plusKierros boolean arvo siitä, pyydetäänkö käyttäjältä syöttöä lisäkierroksia varten.
     */
    //näyttää tai piilottaa plus minus näppäimet
    private void setButtonVisibility(boolean plusKierros){
        if(plusKierros){
            plusButton.setVisibility(View.VISIBLE);
            plusButton.setFocusableInTouchMode(false);
            minusButton.setVisibility(View.VISIBLE);
            minusButton.setFocusableInTouchMode(false);
        }
        if(!plusKierros){
            plusButton.setVisibility(View.INVISIBLE);
            plusButton.setFocusableInTouchMode(true);
            minusButton.setVisibility(View.INVISIBLE);
            minusButton.setFocusableInTouchMode(true);
        }
    }
}