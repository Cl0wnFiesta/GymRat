package com.example.gymrat.Workout;

import java.util.ArrayList;

public class WorkoutData {
    private int[] ekaSetti;
    private double[] ekaPainokerroin;
    private String ekaNimi, tunniste;
    private int tunnistenumero;

    public WorkoutData(int[] ekaSetti, double[] ekaPainokerroin, String nimi, String tunnisteNimi, int tunnistenumero) {
        this.ekaSetti = ekaSetti;
        this.ekaPainokerroin = ekaPainokerroin;
        this.ekaNimi = nimi;
        this.tunniste = tunnisteNimi;
        this.tunnistenumero = tunnistenumero;
    }

    public int[] getSetti() {
        return ekaSetti;
    }
    public double[] getPainokerroin() {
        return ekaPainokerroin;
    }

    public String getNimi() {
        return ekaNimi;
    }

    public int getTunnistenumero() {
        return tunnistenumero;
    }

    public String getTunniste() {
        return tunniste;
    }
}
