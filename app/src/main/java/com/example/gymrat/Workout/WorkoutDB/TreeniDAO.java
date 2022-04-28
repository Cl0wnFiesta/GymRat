package com.example.gymrat.Workout.WorkoutDB;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.Insert;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TreeniDAO {

    @Insert
    void insertTreeni(Treeni treeni);

    @Delete
    void deleteTreeni(Treeni... treeni);

    @Query("SELECT * FROM TREENI")
    List<Treeni> getAllTreeni();


}
