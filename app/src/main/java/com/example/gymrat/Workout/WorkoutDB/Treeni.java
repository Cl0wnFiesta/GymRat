package com.example.gymrat.Workout.WorkoutDB;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.Date;

@Entity(tableName = "Treeni")
public class Treeni {
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        public int id;
        @ColumnInfo(name = "Päivä")
        public String paiva;
        @ColumnInfo(name = "TreeninNimi")
        public String treeninNimi;
        @ColumnInfo(name = "Toistot")
        public int toistot;
        @ColumnInfo(name = "Korotus")
        public double korotus;

        public String toString(){
          return Integer.toString(id) + " -  " + treeninNimi + "   -   " + paiva;
        }

        public int getId() {
                return id;
        }

        public String getTreeninNimi() {
                return treeninNimi;
        }

        public String getPaiva() {
                return paiva;
        }

        public int getToistot() {
                return toistot;
        }

        public double getKorotus() {
                return korotus;
        }
}

