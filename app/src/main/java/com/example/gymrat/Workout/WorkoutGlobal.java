package com.example.gymrat.Workout;

import java.util.ArrayList;

public class WorkoutGlobal {
    private ArrayList<WorkoutData> workoutData;
    private static final WorkoutGlobal ourInstance = new WorkoutGlobal();



    public static WorkoutGlobal getInstance(){
        return ourInstance;
    }

    private WorkoutGlobal(){
        workoutData = new ArrayList<>();
        addWorkoutData();
    }

    private void addWorkoutData(){
        //Ensimmäisen treenin tiedot
        String tunnisteYksi = "WorkoutOne";
        int[]
                workoutOneSettiYksi = {8, 6, 4, 4, 4, 5, 6, 7, 8},
                workoutOneSettiKaksi = {6, 5, 1, 4, 6, 3, 5, 7};
        double[]
                workoutOnePainokerroinYksi = {0.65, 0.75, 0.85, 0.85, 0.85, 0.8, 0.75, 0.7, 0.65},
                workoutOnePainokerroinKaksi = {0.5,0.6,0.8,0.7,0.7,0.7,0.7,0.7};

        //toisen treenin tiedot
        String tunnisteKaksi = "WorkoutTwo";
        int[]
                workoutTwoSettiYksi = {5, 3, 1, 3, 3, 3, 5, 5, 5},
                workoutTwoSettiKaksi = {5, 5, 3, 5, 7, 4, 6, 8};
       double[]
                workoutTwoPainokerroinYksi = {0.75, 0.85, 0.95, 0.9, 0.85, 0.8, 0.75, 0.7, 0.65},
                workoutTwoPainokerroinKaksi = {0.5,0.6,0.7,0.7,0.7,0.7,0.7,0.7};
        //kolmannen treenin tiedot
        String tunnisteKolme = "WorkoutThree";
        int[]
                workoutThreeSettiYksi = {5, 3, 1, 3, 5, 3, 5, 3, 5},
                workoutThreeSettiKaksi = {5, 5, 3, 5, 7, 4, 6, 8};
        double[]
                workoutThreePainokerroinYksi = {0.75, 0.85, 0.95, 0.9, 0.85, 0.8, 0.75, 0.7, 0.65},
                workoutThreePainokerroinKaksi = {0.4,0.5,0.6,0.6,0.6,0.6,0.6,0.6};
        //Neljännen treenin tiedot
        String tunnisteNelja = "WorkoutFour";
        int[]
                workoutFourSettiYksi = {5, 3, 1, 3, 3, 3, 3, 3, 3},
                workoutFourSettiKaksi = {5, 5, 3, 5, 7, 4, 6, 8};
        double[]
                workoutFourPainokerroinYksi = {0.75, 0.85, 0.95, 0.9, 0.85, 0.8, 0.75, 0.7, 0.65},
                workoutFourPainokerroinKaksi = {0.35,0.45,0.55,0.55,0.55,0.55,0.55,0.55};


        workoutData.add(new WorkoutData(workoutOneSettiYksi, workoutOnePainokerroinYksi,"Penkki", tunnisteYksi, 1));
        workoutData.add(new WorkoutData(workoutOneSettiKaksi, workoutOnePainokerroinKaksi,"Pystypunnerrus", tunnisteYksi, 2));

        workoutData.add(new WorkoutData(workoutTwoSettiYksi, workoutTwoPainokerroinYksi, "Kyykky", tunnisteKaksi, 1));
        workoutData.add(new WorkoutData(workoutTwoSettiKaksi, workoutTwoPainokerroinKaksi, "Sumo-maastaveto", tunnisteKaksi, 2));

        workoutData.add(new WorkoutData(workoutThreeSettiYksi, workoutThreePainokerroinYksi, "Penkki", tunnisteKolme, 1));
        workoutData.add(new WorkoutData(workoutThreeSettiKaksi, workoutThreePainokerroinKaksi, "Kapea penkki", tunnisteKolme, 2));

        workoutData.add(new WorkoutData(workoutFourSettiYksi, workoutFourPainokerroinYksi, "Maastaveto", tunnisteNelja, 1));
        workoutData.add(new WorkoutData(workoutFourSettiKaksi, workoutFourPainokerroinKaksi, "Etukyykky", tunnisteNelja, 2));
    }

    public int[] getWorkoutToistot(String tunniste, int tunnisteNumero){
        int[] returnArray = {};
        for ( WorkoutData workoutData: workoutData) {
            if (workoutData.getTunniste().equals(tunniste) && workoutData.getTunnistenumero() == tunnisteNumero) {
                returnArray = workoutData.getSetti();
            }
        }
        return returnArray;
    }

    public double[] getWorkoutPainokerroin(String tunniste, int tunnisteNumero){
        double[] returnArray = {};
        for ( WorkoutData workoutData: workoutData) {
            if (workoutData.getTunniste().equals(tunniste) && workoutData.getTunnistenumero() == tunnisteNumero) {
                returnArray = workoutData.getPainokerroin();
            }
        }
        return returnArray;
    }

    public String getWorkoutName(String tunniste, int tunnisteNumero){
        String returnNimi = "";
        for ( WorkoutData workoutData: workoutData) {
            if (workoutData.getTunniste().equals(tunniste) && workoutData.getTunnistenumero() == tunnisteNumero) {
                returnNimi = workoutData.getNimi();
            }
        }
        return returnNimi;
    }
}
