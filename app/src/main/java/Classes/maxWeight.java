package Classes;


import com.example.gymrat.SetupPageActivity;

public class maxWeight extends SetupPageActivity {

    private final Double Mpenkki, Mkyykky, Mmaastaveto, Mpystypunnerrus ;
    private final Double Npenkki, Nkyykky, Nmaastaveto, Npystypunnerrus ;

    public maxWeight(){
        this.Mpenkki = 20.0;
        this.Mkyykky = 32.5;
        this.Mmaastaveto = 6.15;
        this.Mpystypunnerrus = 200.50;

        this.Npenkki = 10.0;
        this.Nkyykky = 22.5;
        this.Nmaastaveto = 3.15;
        this.Npystypunnerrus = 100.50;
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
