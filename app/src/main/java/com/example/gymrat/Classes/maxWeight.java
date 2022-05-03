package com.example.gymrat.Classes;


import com.example.gymrat.SetupPageActivity;

public class maxWeight extends SetupPageActivity {

    private final Double Mpenkki, Mkyykky, Mmaastaveto, Mpystypunnerrus ;
    private final Double Npenkki, Nkyykky, Nmaastaveto, Npystypunnerrus ;

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
    public String Mkyykky(){
       return this.Mkyykky.toString();
    }
    public String Mpenkki(){
        return this.Mpenkki.toString();
    }
    public String Mmaastaveto(){
        return this.Mmaastaveto.toString();
    }
    public String Mpystypunnerrus(){
        return this.Mpystypunnerrus.toString();
    }
    public String Nkyykky(){
        return this.Nkyykky.toString();
    }
    public String Npenkki(){
        return this.Npenkki.toString();
    }
    public String Nmaastaveto(){
        return this.Nmaastaveto.toString();
    }
    public String Npystypunnerrus(){
        return this.Npystypunnerrus.toString();
    }

}
