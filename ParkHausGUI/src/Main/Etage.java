package Main;

public class Etage {

    Parkplatz[] parkplaetze;
    int etage;

    public Etage(int parkplaetze, int etage){
        this.etage = etage;
        this.parkplaetze = new Parkplatz[parkplaetze];
       for(int i=0;i<parkplaetze;i++){
           this.parkplaetze[i] = new Parkplatz(etage, i);
       }
    }

}
