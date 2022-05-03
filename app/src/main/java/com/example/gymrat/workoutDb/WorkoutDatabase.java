
package com.example.gymrat.workoutDb;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * WorkoutDatabase on Roomdatabase joka tallentaa treenin tiedot.
 * @author Henri
 */
@Database(entities = {Treeni.class}, version = 1)
public abstract class WorkoutDatabase extends RoomDatabase {
    public abstract TreeniDAO treeniDAO();

    private static WorkoutDatabase INSTANCE;

    public static WorkoutDatabase getDBInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WorkoutDatabase.class, "WorkoutDatabase")
            .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}