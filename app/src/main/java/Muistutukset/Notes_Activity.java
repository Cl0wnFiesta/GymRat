package Muistutukset;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.example.gymrat.R;

import java.util.ArrayList;
import java.util.HashMap;

/*Tuturialit SQLiten käyttöön ja ohjelman pöyrittämiseen:
https://www.youtube.com/watch?v=312RhjfetP8&ab_channel=freeCodeCamp.org
https://www.youtube.com/watch?v=hJPk50p7xwA&t=17s&ab_channel=Stevdza-San
https://www.youtube.com/watch?v=9t8VVWebRFM&ab_channel=AllCodingTutorials
https://www.youtube.com/watch?v=d-vdKSbXT4E&t=4517s
https://www.youtube.com/watch?v=or_pH92l-IQ&ab_channel=EasyTuto
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_main);

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

    public void openAddModifyTask(View view) {
        startActivity(new Intent(this, Add_modify_activity.class));
    }


    @Override
    public void onResume() {
        super.onResume();
        populateData();
    }


    public void populateData() {
        mydatabase = new Database(this);

        runOnUiThread(new Runnable() {
            public void run() {
                fetchDataFromDB();
            }
        });
    }


    public void fetchDataFromDB() {
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

    public void loadListView(NoScrollListView listView, final ArrayList<HashMap<String, String>> dataList) {
        com.example.todo.ListTaskAdapter adapter = new com.example.todo.ListTaskAdapter(this, dataList, mydatabase);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(Notes_Activity.this, Add_modify_activity.class);
                i.putExtra("isModify", true);
                i.putExtra("id", dataList.get(+position).get("id"));
                startActivity(i);
            }
        });
    }
}