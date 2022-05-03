package com.example.gymrat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.example.gymrat.calendarDb.Database;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

/*Tuturialit SQLiten käyttöön ja ohjelman pöyrittämiseen:
https://www.youtube.com/watch?v=312RhjfetP8&ab_channel=freeCodeCamp.org
https://www.youtube.com/watch?v=hJPk50p7xwA&t=17s&ab_channel=Stevdza-San
https://www.youtube.com/watch?v=9t8VVWebRFM&ab_channel=AllCodingTutorials
https://www.youtube.com/watch?v=d-vdKSbXT4E&t=4517s
https://www.youtube.com/watch?v=or_pH92l-IQ&ab_channel=EasyTuto
https://www.youtube.com/watch?v=ASQIvPwQffg&ab_channel=PenguinCoders
https://www.youtube.com/watch?v=Udk6iaR-RXA&list=PLrnPJCHvNZuCfAe7QK2BoMPkv2TGM_b0E&ab_channel=CodinginFlow
*/
/**
 * @author Jonne
 * Activity-luokka jossa käyttäjä kirjoittaa itselleen muistutuksen, valitsee päivämäärän ja tallentaa muistutuksen.
 */

public class Notes_Activity extends AppCompatActivity {

    Database mydatabase;
    LinearLayout empty;
    NestedScrollView scrollView;
    LinearLayout todayContainer, tomorrowContainer, otherContainer;
    NoScrollListView taskListToday, taskListTomorrow, taskListUpcoming;
    ArrayList<HashMap<String, String>> todayLista = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> tomorrowLista = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> upcomingLista = new ArrayList<HashMap<String, String>>();

    /**
     * Sets the text of the TextView username to "Hei " + value, which is the value of the
     * SharedPreferences key "value", and then sets the bottom navigation bar to the notication tab
     *
     * @param savedInstanceState A Bundle object containing the activity's previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_note);
        TextView username = findViewById(R.id.emptyText);
        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");
        username.setText("Hei " + value);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.notication);

        // Perform item selected listener
        //Tutorial video link https://www.youtube.com/watch?v=Q9Xwyfor-kQ&ab_channel=GurkanUcar

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            // A function that is called when the user clicks on an item in the bottom navigation bar.
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Tarkistaa missä navbarin valikossa käyttäjä on, ja avaa valikon Activityn
                switch(item.getItemId())
                {
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                        //Poistaa avaus animaation
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                    case R.id.notication:
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        mydatabase = new Database(this);
        empty = findViewById(R.id.empty);
        scrollView = findViewById(R.id.scrollView);
        todayContainer = findViewById(R.id.todayContainer);
        tomorrowContainer = findViewById(R.id.tomorrowContainer);
        otherContainer = findViewById(R.id.otherContainer);
        taskListToday = findViewById(R.id.taskListToday);
        taskListTomorrow = findViewById(R.id.taskListTomorrow);
        taskListUpcoming = findViewById(R.id.taskListUpcoming);
    }

    /**
     * When the user clicks the button, open the Add_modify_activity activity.
     *
     * @param view The view that was clicked.
     */
    public void openAddModifyTask(View view) {
        startActivity(new Intent(this, Add_modify_activity.class));
    }

    /**
     * The onResume() function is called when the activity is resumed
     */
    @Override
    public void onResume() {
        super.onResume();
        populateData();
    }

    /**
     * It creates a new database object, and then runs a new thread that calls the
     * fetchDataFromDataBase() function
     */
    public void populateData() {
        mydatabase = new Database(this);

        runOnUiThread(new Runnable() {
            public void run() {
                fetchDataFromDataBase();
            }
        });
    }

    /**
     * It fetches data from the database and loads it into the listviews
     */
    public void fetchDataFromDataBase() {
        todayLista.clear();
        tomorrowLista.clear();
        upcomingLista.clear();

        Cursor today = mydatabase.getTodayTask();
        Cursor tomorrow = mydatabase.getTomorrowTask();
        Cursor upcoming = mydatabase.getUpcomingTask();

        loadDataList(today, todayLista);
        loadDataList(tomorrow, tomorrowLista);
        loadDataList(upcoming, upcomingLista);


        if (todayLista.isEmpty() && tomorrowLista.isEmpty() && upcomingLista.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            if (todayLista.isEmpty()) {
                todayContainer.setVisibility(View.GONE);
            } else {
                todayContainer.setVisibility(View.VISIBLE);
                loadListView(taskListToday, todayLista);
            }

            if (tomorrowLista.isEmpty()) {
                tomorrowContainer.setVisibility(View.GONE);
            } else {
                tomorrowContainer.setVisibility(View.VISIBLE);
                loadListView(taskListTomorrow, tomorrowLista);
            }

            if (upcomingLista.isEmpty()) {
                otherContainer.setVisibility(View.GONE);
            } else {
                otherContainer.setVisibility(View.VISIBLE);
                loadListView(taskListUpcoming, upcomingLista);
            }
        }
    }


    /**
     * It takes a cursor and an arraylist of hashmaps as parameters, and then iterates through the
     * cursor, adding each row to the arraylist as a hashmap
     *
     * @param cursor The cursor object that contains the data to be loaded into the list.
     * @param dataList This is the ArrayList that will hold the data from the database.
     */
    public void loadDataList(Cursor cursor, ArrayList<HashMap<String, String>> dataList) {
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {

                HashMap<String, String> mapToday = new HashMap<String, String>();
                mapToday.put("id", cursor.getString(0).toString());
                mapToday.put("task", cursor.getString(1).toString());
                mapToday.put("date", cursor.getString(2).toString());
                mapToday.put("status", cursor.getString(3).toString());
                dataList.add(mapToday);
                cursor.moveToNext();
            }
        }
    }

    /**
     * This function is used to load the listview with the data from the database
     *
     * @param listView The listview you want to load the data into.
     * @param dataList The list of data to be displayed in the listview.
     */
    public void loadListView(NoScrollListView listView, final ArrayList<HashMap<String, String>> dataList) {
        ListTaskAdapter adapter = new ListTaskAdapter(this, dataList, mydatabase);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Notes_Activity.this, Add_modify_activity.class);
                i.putExtra("isModify", true);
                i.putExtra("id", dataList.get(+position).get("id"));
                startActivity(i);
            }
        });
    }
}