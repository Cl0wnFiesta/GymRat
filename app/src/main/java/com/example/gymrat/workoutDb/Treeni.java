
package com.example.gymrat.workoutDb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * Treenin tietokannan pöytä.
 * Sisältää tietokannassa ID, Päivä, Treenin nimi, Toistot ja Korotus arvot.
 * Sisältää metodit joilla haetaan tiedot tietokannasta haettavista treeneistä.
 * @author Henri
 */
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
        @ColumnInfo(name = "Penkki")
        public double penkkimax;
        @ColumnInfo(name = "Kyykky")
        public double kyykkymax;
        @ColumnInfo(name = "Pystypunnerrus")
        public double pystypunnerrusmax;
        @ColumnInfo(name = "Maastaveto")
        public double maastavetomax;

        public String toString(){
          return Integer.toString(id) + " -  " + treeninNimi + "   -   " + paiva;
        }

        /**
         * Palautusmetodi tietokantaan tallennetun treenin arvolle
         * @return palauttaa Treenin id:n
         */
        public int getId() {
                return id;
        }
        /**
         *Palautusmetodi tietokantaan tallennetun treenin arvolle
         * @return palauttaa Treenin nimen
         */
        public String getTreeninNimi() {
                return treeninNimi;
        }
        /**
         *Palautusmetodi tietokantaan tallennetun treenin arvolle
         * @return palauttaa Treenin päivän
         */
        public String getPaiva() {
                return paiva;
        }
        /**
         *Palautusmetodi tietokantaan tallennetun treenin arvolle
         * @return palauttaa Treenin toistot
         */
        public int getToistot() {
                return toistot;
        }
        /**
         *Palautusmetodi tietokantaan tallennetun treenin arvolle
         * @return palauttaa Treenin suositellun korotuksen
         */
        public double getKorotus() {
                return korotus;
        }

        /**
         * Palautusmetodi tietokantaan tallennetun treenin arvolle
         * @return palauttaa treenin kyykkymaxin tietokannasta
         */
        public double getKyykkymax() {
                return kyykkymax;
        }
        /**
         *Palautusmetodi tietokantaan tallennetun treenin arvolle
         * @return palauttaa treenin kyykkymaxin tietokannasta
         */
        public double getPenkkimax() {
                return penkkimax;
        }
        /**
         *Palautusmetodi tietokantaan tallennetun treenin arvolle
         * @return palauttaa treenin kyykkymaxin tietokannasta
         */
        public double getMaastavetomax() {
                return maastavetomax;
        }
        /**
         *Palautusmetodi tietokantaan tallennetun treenin arvolle
         * @return palauttaa treenin kyykkymaxin tietokannasta
         */
        public double getPystypunnerrusmax() {
                return pystypunnerrusmax;
        }
}

