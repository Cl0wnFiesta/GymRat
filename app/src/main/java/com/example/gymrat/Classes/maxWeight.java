package com.example.gymrat.Classes;


import com.example.gymrat.SetupPageActivity;

/**
 * Luokka, joka pitää sisällään oletusarvot miehen ja naisen oletus-aloittelijapainoille,
 * jotka syötetään profiilia luodessa kenttiin valitun sukupuolen perusteella.
 * @author Jonne
 */
public class maxWeight extends SetupPageActivity {

    private final Double Mpenkki, Mkyykky, Mmaastaveto, Mpystypunnerrus ;
    private final Double Npenkki, Nkyykky, Nmaastaveto, Npystypunnerrus ;

    /**
     * Sets the default maxWeights based on gender
     */
    public maxWeight(){
        this.Mpenkki = 35.0;
        this.Mkyykky = 65.0;
        this.Mmaastaveto = 70.0;
        this.Mpystypunnerrus = 27.5;

        this.Npenkki = 17.5;
        this.Nkyykky = 60.0;
        this.Nmaastaveto = 37.5;
        this.Npystypunnerrus = 15.0;
    }
    /**
     * This function returns the value of the variable Mkyykky
     *
     * @return The value of the Mkyykky variable.
     */
    public String Mkyykky(){
       return this.Mkyykky.toString();
    }
    /**
     * This function returns the value of the variable Mpenkki
     *
     * @return The method returns the value of the variable Mpenkki.
     */
    public String Mpenkki(){
        return this.Mpenkki.toString();
    }
    /**
     * This function returns the value of the attribute Mmaastaveto as a String
     *
     * @return The method returns the value of the attribute Mmaastaveto.
     */
    public String Mmaastaveto(){
        return this.Mmaastaveto.toString();
    }
    /**
     * This function returns the value of the variable Mpystypunnerrus
     *
     * @return The value of the variable Mpystypunnerrus.
     */
    public String Mpystypunnerrus(){
        return this.Mpystypunnerrus.toString();
    }
    /**
     * This function returns the value of the variable Nkyykky
     *
     * @return The value of the variable Nkyykky.
     */
    public String Nkyykky(){
        return this.Nkyykky.toString();
    }
    /**
     * It returns the value of the variable Npenkki
     *
     * @return The method returns the value of the variable Npenkki.
     */
    public String Npenkki(){
        return this.Npenkki.toString();
    }
    /**
     * This function returns the value of the Nmaastaveto variable as a string.
     *
     * @return The value of the attribute Nmaastaveto.
     */
    public String Nmaastaveto(){
        return this.Nmaastaveto.toString();
    }
    /**
     * This function returns the value of the variable Npystypunnerrus as a string
     *
     * @return The value of the variable Npystypunnerrus.
     */
    public String Npystypunnerrus(){
        return this.Npystypunnerrus.toString();
    }

}
