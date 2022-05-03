package com.example.gymrat.Muistutukset;
/**
 * @author Jonne
 * Activity-luokka jossa käyttäjä voi tallentaa, muokata tai poistaa omia muistutuksia
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymrat.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
/*Tuturialit SQLiten käyttöön ja ohjelman pöyrittämiseen:
https://www.youtube.com/watch?v=312RhjfetP8&ab_channel=freeCodeCamp.org
https://www.youtube.com/watch?v=hJPk50p7xwA&t=17s&ab_channel=Stevdza-San
https://www.youtube.com/watch?v=9t8VVWebRFM&ab_channel=AllCodingTutorials
https://www.youtube.com/watch?v=d-vdKSbXT4E&t=4517s
https://www.youtube.com/watch?v=or_pH92l-IQ&ab_channel=EasyTuto
https://www.youtube.com/watch?v=ASQIvPwQffg&ab_channel=PenguinCoders
https://www.youtube.com/watch?v=Udk6iaR-RXA&list=PLrnPJCHvNZuCfAe7QK2BoMPkv2TGM_b0E&ab_channel=CodinginFlow
*/
public class Add_modify_activity extends AppCompatActivity {

    Calendar calendar;
    Database mydatabase;

    Boolean isModify = false;
    String task_id;
    TextView toolbar_title;
    EditText edit_text;
    TextView dateText;
    Button save_btn;
    Notes_Activity notes_activity;
    /**
     *
     * @param savedInstanceState A Bundle object containing the activity's previously saved state. If
     * the activity has never existed before, the value of the Bundle object is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_add_modify_task);

        mydatabase = new Database(getApplicationContext());
        calendar = new GregorianCalendar();
        toolbar_title = findViewById(R.id.toolbar_title);
        edit_text = findViewById(R.id.edit_text);
        dateText = findViewById(R.id.dateText);
        save_btn = findViewById(R.id.save_btn);
        dateText.setText(new SimpleDateFormat("E, dd MMMM yyyy").format(calendar.getTime()));

        Intent intent = getIntent();
        if (intent.hasExtra("isModify")) {
            isModify = intent.getBooleanExtra("isModify", false);
            task_id = intent.getStringExtra("id");
            modify();
        }
    }

    /**
     * It sets the title of the toolbar to "Muokkaa muistutusta",
     * sets the text of the save button to "Päivitä", makes the delete button visible,
     */
    public void modify() {
        toolbar_title.setText("Muokkaa muistutusta");
        save_btn.setText("Päivitä");
        LinearLayout deleteTask = findViewById(R.id.deleteTask);
        deleteTask.setVisibility(View.VISIBLE);
        Cursor task = mydatabase.getSingleTask(task_id);
        if (task != null) {
            task.moveToFirst();
            edit_text.setText(task.getString(1));
            SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                calendar.setTime(Format.parse(task.getString(2)));
            } catch (ParseException e) {
            }
            dateText.setText(new SimpleDateFormat("E, dd MMMM yyyy").format(calendar.getTime()));
        }

    }

    /**
     * If the task is not empty, then either update the task or insert a new task
     *
     * @param v The view that was clicked.
     */
    public void saveTask(View v) {
        /*Checking for Empty Task*/
        if (edit_text.getText().toString().trim().length() > 0) {

            if (isModify) {
                mydatabase.updateTask(task_id, edit_text.getText().toString(), new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
                Toast.makeText(getApplicationContext(), "Muistutus päivitetty", Toast.LENGTH_SHORT).show();
            } else {
                mydatabase.insertTask(edit_text.getText().toString(), new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
                Toast.makeText(getApplicationContext(), "Muistutus luotu.", Toast.LENGTH_SHORT).show();
            }
            finish();

        } else {
            Toast.makeText(getApplicationContext(), "Et voi tallennaa tyhjää sivua!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * When the delete button is clicked, delete the task from the database and show a toast message to
     * the user.
     *
     * @param v The view that was clicked.
     */
    public void deleteTask(View v) {
        mydatabase.deleteTask(task_id);
        Toast.makeText(getApplicationContext(), "Poistu onnistui", Toast.LENGTH_SHORT).show();
        finish();
    }

    // A function that is called when the user clicks the date text. It shows a date picker dialog to
    // the user.
    public void chooseDate(View view) {
        final View dialogView = View.inflate(this, R.layout.date_picker, null);
        final DatePicker datePicker = dialogView.findViewById(R.id.date_picker);
        datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setTitle("Valitse päivä");
        builder.setNegativeButton("Peruuta", null);
        builder.setPositiveButton("Valitse", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                calendar = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                dateText.setText(new SimpleDateFormat("E, dd MMMM yyyy").format(calendar.getTime()));
            }
        });
        builder.show();
    }
}