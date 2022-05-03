/**
 * @author Henri
 * Luokka joka laskee toistot ja painot treenille.
 * Sisältää metodin joka suosittelee painojen korotusta annetun arvon perusteella.
 */
package com.example.gymrat.Classes;

import java.lang.Math;

public class Workout {


    private final double maxPenkki, maxKyykky, maxMaastaveto, maxPystypunnerrus;
    private String treeniNimi;
    private int[] liikkeita;
    private double[] painokerroin;

    //ottaa neljä muuttujaa ja asettaa ne treenin maksimiluvuiksi.
    public Workout(double penkki, double kyykky, double maastaveto, double pystypunnerrus){
        this.maxPenkki = penkki;
        this.maxKyykky = kyykky;
        this.maxMaastaveto = maastaveto;
        this.maxPystypunnerrus = pystypunnerrus;
    }

    //Asettaa liikkeiden määrän ja painokertoimet.
    public void startWorkout(int[] liikemaara, double[] painokerroin, String treeniNimi){
        this.liikkeita = liikemaara;
        this.painokerroin = painokerroin;
        this.treeniNimi = treeniNimi;


    }

    // palauttaa liikkeiden määrät ja painot riippuen missä kohtaa treeniä ollaan
    public int getLiikkeita(int kohta){
        return liikkeita[kohta];
    }

    public double getPaino( int kohta){

        double paino;
        String liike = getTreeniNimi();
        switch (liike){
            case "Kapea Penkki": case "Penkki":
                paino = getPenkki(kohta);
                return paino;
            case "Etukyykky": case "Kyykky":
                paino = getKyykky(kohta);
                return paino;
            case "Maastaveto": case "Sumo-maastaveto":
                paino = getMaastaveto(kohta);
                return paino;
            case "Pystypunnerrus" :
                paino = getPystypunnerrus(kohta);
                return paino;


        }
        return 0;
    }


    //hakee treenimaksimin maksimista ja pyöristää lähimpään 2.5kg:seen
    private double getPenkki(int kohta){
        return Math.round(painokerroin[kohta]*maxPenkki / 2.5) * 2.5;
    }
    private double getKyykky(int kohta){
        return Math.round(painokerroin[kohta]*maxKyykky / 2.5) * 2.5;
    }
    private double getMaastaveto(int kohta){
        return Math.round(painokerroin[kohta]*maxMaastaveto / 2.5) * 2.5;
    }
    private double getPystypunnerrus(int kohta){
        return Math.round(painokerroin[kohta]*maxPystypunnerrus / 2.5) * 2.5;
    }

    //palauttaa liikkeiden pituuden
    public int getPituus(){
        return liikkeita.length;
    }

    //palauttaa treenin nimen
    public String getTreeniNimi(){
        return treeniNimi;
    }

    //Jos painot menee tiettyjen rajojen yli, niin ehdota lisäpainoja
    public double suggestIncrease(int plusKierros){
        double suggestNumber = 0;
        if (plusKierros > 1 && plusKierros <= 3){
            suggestNumber = 2.5;
        }
        if  (plusKierros > 3 && plusKierros <= 5){
            suggestNumber = 5;
        }
        if  (plusKierros > 5){
            suggestNumber = 7.5;
        }
        return suggestNumber;
    }
}
