package com.example.gymrat;

import java.lang.Math;

abstract class Workout {


    private double maxPenkki, maxKyykky, maxMaastaveto, maxPystypunnerrus;
    private int plusKierros;
    private String treeniNimi;
    private int[] liikkeita;
    private double[] painokerroin;


    public Workout(double penkki, double kyykky, double maastaveto, double pystypunnerrus){
        this.maxPenkki = penkki;
        this.maxKyykky = kyykky;
        this.maxMaastaveto = maastaveto;
        this.maxPystypunnerrus = pystypunnerrus;
    }

    //Asettaa liikkeiden määrän ja painot
    public void startWorkout(int[] liikemaara, double[] painokerroin, String treeniNimi){
        this.liikkeita = liikemaara;
        this.painokerroin = painokerroin;
        this.treeniNimi = treeniNimi;


    }

    // palauttaa liikkeiden määrät ja painot riippuen missä kohtaa treeniä ollaan
    public int getLiikkeita(int kohta){
        return liikkeita[kohta];
    }

    //hakee treenimaksimin maksimista ja pyöristää lähimpään 2.5kg:seen
    public double getPenkki(int kohta){
        return Math.round(painokerroin[kohta]*maxPenkki / 2.5) * 2.5;
    }
    public double getKyykky(int kohta){
        return Math.round(painokerroin[kohta]*maxKyykky / 2.5) * 2.5;
    }
    public double getMaastaveto(int kohta){
        return Math.round(painokerroin[kohta]*maxMaastaveto / 2.5) * 2.5;
    }
    public double getPystypunnerrus(int kohta){
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
    public double suggestIncrease(){
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

    public void setPlusKierros(int plusKierros) {
        this.plusKierros = plusKierros;
    }
}
