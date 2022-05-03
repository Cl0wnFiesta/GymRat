
package com.example.gymrat.Classes;

/**
 * Luokka, joka tallentaa treenin setin tiedot.
 * Metodeina palauttaa treenin setin tiedot.
 * @author Henri
 */
public class WorkoutData {
    private int[] toistot;
    private double[] painokerroin;
    private String nimi, tunniste;
    private int tunnistenumero;

    /**
     * Asettaa arvot treenin setille.
     * @param toistot Array, kuinka monta toistoa setin liikkeissä on.
     * @param painokerroin Array, setin liikkeiden painokertoimet
     * @param nimi Setin nimi
     * @param tunnisteNimi Tunnistenimi, jolla liike on haettu WorkoutGlobalista
     * @param tunnistenumero Tunnistenumero, joka kertoo onko ensimmäinen vai toinen setti menossa
     */
    public WorkoutData(int[] toistot, double[] painokerroin, String nimi, String tunnisteNimi, int tunnistenumero) {
        this.toistot = toistot;
        this.painokerroin = painokerroin;
        this.nimi = nimi;
        this.tunniste = tunnisteNimi;
        this.tunnistenumero = tunnistenumero;
    }

    /**
     * Palauttaa toistojen arrayn
     * @return Palauttaa toistojen arrayn
     */
    public int[] getToistot() {
        return toistot;
    }
    /**
     * palauttaa painokertoimen arrayn
     * @return palauttaa painokertoimen arrayn
     */
    public double[] getPainokerroin() {
        return painokerroin;
    }
    /**
     * palauttaa setin nimen
     * @return palauttaa setin nimen
     */
    public String getNimi() {
        return nimi;
    }
    /**
     * palauttaa tunnistenumeron
     * @return palauttaa tunnistenumeron
     */
    public int getTunnistenumero() {
        return tunnistenumero;
    }
    /**
     * palauttaa tunnistenimen
     * @return palauttaa tunnistenimen
     */
    public String getTunniste() {
        return tunniste;
    }
}
