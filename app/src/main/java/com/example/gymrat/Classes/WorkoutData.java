/**
 * @author Henri
 * Luokka, joka tallentaa treenin tiedot.
 * Metodeina palauttaa treenin tiedot.
 */
package com.example.gymrat.Classes;

public class WorkoutData {
    private int[] setti;
    private double[] painokerroin;
    private String nimi, tunniste;
    private int tunnistenumero;


    public WorkoutData(int[] setti, double[] painokerroin, String nimi, String tunnisteNimi, int tunnistenumero) {
        this.setti = setti;
        this.painokerroin = painokerroin;
        this.nimi = nimi;
        this.tunniste = tunnisteNimi;
        this.tunnistenumero = tunnistenumero;
    }

    public int[] getSetti() {
        return setti;
    }
    public double[] getPainokerroin() {
        return painokerroin;
    }
    public String getNimi() {
        return nimi;
    }
    public int getTunnistenumero() {
        return tunnistenumero;
    }
    public String getTunniste() {
        return tunniste;
    }
}
