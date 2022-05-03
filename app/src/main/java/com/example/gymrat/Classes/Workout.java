package com.example.gymrat.Classes;

import java.lang.Math;

/**
 * Luokka joka laskee toistot ja painot treenille.
 * Sisältää metodin joka suosittelee painojen korotusta annetun arvon perusteella.
 * @author Henri
 */
public class Workout {


    private final double maxPenkki, maxKyykky, maxMaastaveto, maxPystypunnerrus;
    private String treeniNimi;
    private int[] liikkeita;
    private double[] painokerroin;

    /**
     * //ottaa neljä muuttujaa ja asettaa ne treenin maksimiluvuiksi.
     * @param penkki preferensseistä haettu maksimiarvo penkille
     * @param kyykky preferensseistä haettu maksimiarvo kyykylle
     * @param maastaveto preferensseistä haettu maksimiarvo maastavedolle
     * @param pystypunnerrus preferensseistä haettu maksimiarvo pystypunnerrukselle
     */
    public Workout(double penkki, double kyykky, double maastaveto, double pystypunnerrus){
        this.maxPenkki = penkki;
        this.maxKyykky = kyykky;
        this.maxMaastaveto = maastaveto;
        this.maxPystypunnerrus = pystypunnerrus;
    }

    /**
     * //Asettaa liikkeiden määrän ja painokertoimet.
     * @param liikemaara Array liikkeiden määrästä setissä
     * @param painokerroin Array painokertoimet liikkeille
     * @param treeniNimi Treenin nimi
     */
    public void startWorkout(int[] liikemaara, double[] painokerroin, String treeniNimi){
        this.liikkeita = liikemaara;
        this.painokerroin = painokerroin;
        this.treeniNimi = treeniNimi;


    }

    /**
     * palauttaa liikkeiden määrät arraysta liikkeen kohdasta
     * @param kohta Kohta missä ollaan treenissä
     * @return Liikkeiden määrä treenin kohdassa
     */
    public int getLiikkeita(int kohta){
        return liikkeita[kohta];
    }

    /**
     * Palauttaa liikkeen painon arraysta liikkeen kohdasta
     * @param kohta Kohta missä ollaan treenissä
     * @return Paino liikkeen kohdassa
     */
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


    /**
     * hakee treenimaksimin maksimista ja pyöristää lähimpään 2.5kg:aan
     * @param kohta Kohta missä ollaan treenissä
     * @return paino pyöristettynä lähimpään 2.5kg:aan
     */
    private double getPenkki(int kohta){
        return Math.round(painokerroin[kohta]*maxPenkki / 2.5) * 2.5;
    }
    /**
     * hakee treenimaksimin maksimista ja pyöristää lähimpään 2.5kg:aan
     * @param kohta Kohta missä ollaan treenissä
     * @return paino pyöristettynä lähimpään 2.5kg:aan
     */
    private double getKyykky(int kohta){
        return Math.round(painokerroin[kohta]*maxKyykky / 2.5) * 2.5;
    }
    /**
     * hakee treenimaksimin maksimista ja pyöristää lähimpään 2.5kg:aan
     * @param kohta Kohta missä ollaan treenissä
     * @return paino pyöristettynä lähimpään 2.5kg:aan
     */
    private double getMaastaveto(int kohta){
        return Math.round(painokerroin[kohta]*maxMaastaveto / 2.5) * 2.5;
    }
    /**
     * hakee treenimaksimin maksimista ja pyöristää lähimpään 2.5kg:aan
     * @param kohta Kohta missä ollaan treenissä
     * @return paino pyöristettynä lähimpään 2.5kg:aan
     */
    private double getPystypunnerrus(int kohta){
        return Math.round(painokerroin[kohta]*maxPystypunnerrus / 2.5) * 2.5;
    }

    /**
     * palauttaa liikkeiden arrayn pituuden
     * @return palauttaa liikkeiden arrayn pituuden
     */
    public int getPituus(){
        return liikkeita.length;
    }

    /**
     * Palauttaa treenin nimen
     * @return palauttaa treenin nimen
     */
    public String getTreeniNimi(){
        return treeniNimi;
    }

    /**
     * Jos toistot ekstrakierroksella menee tiettyjen rajojen yli, niin ehdottaa lisäpainoja.
     * @param plusKierros Kuinka monta toistoa ekstrakierroksella on saatu.
     * @return Suositeltu painonkorotus treeniohjelmaan.
     */
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
