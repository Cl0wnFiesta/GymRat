/**
 * @author Henri
 * DAO (Data Access Object) jolla haetaan tietokannasta treenin tiedot.
 */
package com.example.gymrat.workoutDb;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Insert;

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
