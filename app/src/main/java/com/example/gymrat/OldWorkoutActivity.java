
package com.example.gymrat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.gymrat.workoutDb.Treeni;
import com.example.gymrat.workoutDb.WorkoutDatabase;

import java.util.Collections;
import java.util.List;

/**
 * Activity-Luokka joka näyttää vanhan treenin valituista treeneistä
 * Lataa tietokannasta treenin valitun ListViewin treenin mukaan
 * @author Henri
 */
public class OldWorkoutActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_workout);
        Intent intent = getIntent();
        int id = intent.getIntExtra("gymrat.extra_old_workout", 0);
        WorkoutDatabase db = WorkoutDatabase.getDBInstance(this.getApplicationContext());

        List<Treeni> treeni = db.treeniDAO().getAllTreeni();
        Collections.reverse(treeni);

        String workoutnimi = treeni.get(id).getTreeninNimi();
        String paiva = treeni.get(id).getPaiva();
        int toistot = treeni.get(id).getToistot();
        double suosittelu = treeni.get(id).getKorotus();


        TextView titleTxt = findViewById(R.id.TitleTxt);
        titleTxt.setText(workoutnimi);
        TextView paivaTxt = findViewById(R.id.PaivaTxt);
        paivaTxt.setText(paiva);
        TextView toistoValue = findViewById(R.id.toistotValue);
        TextView suositteluValue = findViewById(R.id.lisaysValue);
        toistoValue.setText(Integer.toString(toistot));
        suositteluValue.setText(Double.toString(suosittelu));

        Button takaisinButton = findViewById(R.id.backButton);
        takaisinButton.setOnClickListener(view -> onBackPressed());


    }
}