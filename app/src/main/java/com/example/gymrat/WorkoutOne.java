package com.example.gymrat;

public class WorkoutOne extends Workout {

    private int[] ekatSetit = {8, 6, 4, 4, 4, 5, 6, 7, 8}, tokatSetit = {6, 5, 3, 5, 7, 4, 6, 8};
    private double[] ekaPainokerroin = {0.65, 0.75, 0.85, 0.85, 0.85, 0.8, 0.75, 0.7, 0.65}, tokaPainokerroin = {0.5,0.6,0.7,0.7,0.7,0.7,0.7,0.7};
    private String ekaSettiNimi = "penkki", tokaSettiNimi = "pystypunnerrus", currentSet = "";

    WorkoutOne(double penkki, double kyykky, double maastaveto, double pystypunnerrus) {
        super(penkki, kyykky, maastaveto, pystypunnerrus);
        currentSet = ekaSettiNimi;
    }

    public void firstWorkout() {

        super.startWorkout(ekatSetit, ekaPainokerroin, ekaSettiNimi);


    }

    public String getCurrentSet() {
        return currentSet;
    }
}
