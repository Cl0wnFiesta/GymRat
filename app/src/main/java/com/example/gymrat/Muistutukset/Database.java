package com.example.gymrat.Muistutukset;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

/*Tuturialit SQLiten käyttöön ja Muistio ohjelman pöyrittämiseen:
https://www.youtube.com/watch?v=312RhjfetP8&ab_channel=freeCodeCamp.org
https://www.youtube.com/watch?v=hJPk50p7xwA&t=17s&ab_channel=Stevdza-San
https://www.youtube.com/watch?v=9t8VVWebRFM&ab_channel=AllCodingTutorials
https://www.youtube.com/watch?v=d-vdKSbXT4E&t=4517s
https://www.youtube.com/watch?v=or_pH92l-IQ&ab_channel=EasyTuto
https://www.youtube.com/watch?v=ASQIvPwQffg&ab_channel=PenguinCoders
https://www.youtube.com/watch?v=Udk6iaR-RXA&list=PLrnPJCHvNZuCfAe7QK2BoMPkv2TGM_b0E&ab_channel=CodinginFlow
*/

/**
 * It creates a database with a table called Muistutukset.
 * It also has methods to insert, update, delete, and get tasks from the database
 * @author Jonne
 */
public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Muistutukset";
    public static final String TABLE_NAME = "TODO";

    // Creating a table called tasks with three columns: id, task, and task_at.
    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(
                "CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY, task TEXT, task_at DATETIME, status INTEGER)"
        );
    }

    /**
     * If the database version is different from the version of the database on the device, drop the
     * table and create a new one
     *
     * @param database The database.
     * @param oldVersion The old database version.
     * @param newVersion The version number of the database.
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }


    /**
     * This function inserts a new task into the database
     *
     * @param task The task to be inserted
     * @param task_at The time at which the task is to be completed.
     * @return A boolean value.
     */
    public boolean insertTask(String task, String task_at) {
        Date date;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task", task);
        contentValues.put("task_at", task_at);
        Log.d("TAG","Laitettu taski, tuliko?");
        contentValues.put("status", 0);
        database.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    /**
     * It updates the task and task_at columns of the row with the id passed as the first argument
     *
     * @param id The id of the task to be updated.
     * @param task The task to be updated
     * @param task_at The time at which the task is to be completed.
     * @return A boolean value.
     */
    public boolean updateTask(String id, String task, String task_at) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task", task);
        contentValues.put("task_at", task_at);
        database.update(TABLE_NAME, contentValues, "id = ? ", new String[]{id});
        return true;
    }

    /**
     * Delete a task from the database with the given id.
     *
     * @param id The id of the task to be deleted.
     * @return True
     */
    public boolean deleteTask(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, "id = ? ", new String[]{id});
        Log.d("TAG", "Poistettu??");
        return true;
    }

    /**
     * It updates the status of a task in the database.
     *
     * @param id The id of the task to be updated.
     * @param status the status of the task, 0 for not done, 1 for done
     * @return The method returns a boolean value.
     */
    public boolean updateTaskStatus(String id, Integer status) {
        Log.d("TAG", "updatettu??");
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", status);
        database.update(TABLE_NAME, contentValues, "id = ? ", new String[]{id});
        return true;
    }

    /**
     * Get a single task from the database, where the id is equal to the id passed in as a parameter.
     *
     * @param id The id of the task you want to get.
     * @return A cursor object.
     */
    public Cursor getSingleTask(String id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor res = database.rawQuery("select * from " + TABLE_NAME + " WHERE id = '" + id + "' order by id desc", null);
        return res;
    }

    /**
     * Get all the tasks that are due today, ordered by id in descending order.
     *
     * @return Cursor
     */
    public Cursor getTodayTask() {
        Log.d("TAG", "Tän päivän");
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor res = database.rawQuery("select * from " + TABLE_NAME + " WHERE date(task_at) = date('now', 'localtime') order by id desc", null);
        Log.d("TAG", "lopuessa??");
        return res;

    }

    /**
     * It returns all the tasks that are due tomorrow.
     *
     * @return Cursor
     */
    public Cursor getTomorrowTask() {
        SQLiteDatabase database = this.getReadableDatabase();
        Log.d("TAG", "Huomisen task");
        Cursor res = database.rawQuery("select * from " + TABLE_NAME + " WHERE date(task_at) = date('now', '+1 day', 'localtime')  order by id desc", null);
        return res;
    }

    /**
     * Get all tasks that are due in the future, ordered by id in descending order.
     *
     * @return Cursor
     */
    public Cursor getUpcomingTask() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor res = database.rawQuery("select * from " + TABLE_NAME + " WHERE date(task_at) > date('now', '+1 day', 'localtime') order by id desc", null);
        Log.d("TAG", "Tulevat");
        return res;
    }


}