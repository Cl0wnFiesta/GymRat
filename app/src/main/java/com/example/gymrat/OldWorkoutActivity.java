/**
 * @author Henri
 * Luokka joka näyttää vanhan treenin valituista treeneistä
 * Lataa tietokannasta treenin valitun ListViewin treenin mukaan
 */
package com.example.gymrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gymrat.workoutDb.Treeni;
import com.example.gymrat.workoutDb.WorkoutDatabase;

import java.util.Collections;
import java.util.List;

public class OldWorkoutActivity extends AppCompatActivity {

    private int id, toistot;
    private double suosittelu;
    private String workoutnimi, paiva;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_workout);
        Intent intent = getIntent();
        id = intent.getIntExtra("gymrat.extra_old_workout", 0);
        WorkoutDatabase db = WorkoutDatabase.getDBInstance(this.getApplicationContext());

        List<Treeni> treeni = db.treeniDAO().getAllTreeni();
        Collections.reverse(treeni);

        workoutnimi = treeni.get(id).getTreeninNimi();
        paiva = treeni.get(id).getPaiva();
        toistot = treeni.get(id).getToistot();
        suosittelu = treeni.get(id).getKorotus();


        TextView titleTxt = findViewById(R.id.TitleTxt);
        titleTxt.setText(workoutnimi);
        TextView paivaTxt = findViewById(R.id.PaivaTxt);
        paivaTxt.setText(paiva);
        TextView toistoValue = findViewById(R.id.toistotValue);
        TextView suositteluValue = findViewById(R.id.lisaysValue);
        toistoValue.setText(Integer.toString(toistot));
        suositteluValue.setText(Double.toString(suosittelu));

        Button takaisinButton = findViewById(R.id.backButton);
        takaisinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
}