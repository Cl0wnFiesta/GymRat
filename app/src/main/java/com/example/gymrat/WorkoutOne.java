package com.example.gymrat;

public class WorkoutOne extends Workout {

    private int[] ekatSetit = {8, 6, 4, 4, 4, 5, 6, 7, 8}, tokatSetit = {6, 5, 3, 5, 7, 4, 6, 8};
    private double[] painoKerroin = {0.65, 0.75, 0.85};
    private String ekaSettiNimi = "testi", tokaSettiNimi = "testi";

    WorkoutOne(double penkki, double kyykky, double maastaveto, double pystypunnerrus) {
        super(penkki, kyykky, maastaveto, pystypunnerrus);
    }

    public void firstWorkout() {
        super.startWorkout(ekatSetit, painoKerroin, ekaSettiNimi);


    }

}
