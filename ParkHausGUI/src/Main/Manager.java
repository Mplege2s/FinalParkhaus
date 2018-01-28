package Main;

import java.util.ArrayList;
import java.util.Arrays;

public class Manager {
    ArrayList<Daten> datenliste =  new ArrayList<>();
    ArrayList<Auto> autos =  new ArrayList<>();
    double preis;
    Parkhaus parkhaus;
    int currentID;
    public ArrayList<Integer> prevTickets = new ArrayList<>();

    public Manager(Parkhaus parkhaus){
        this.parkhaus =  parkhaus;
        this.currentID = 1;
    }

    public boolean hatFreiePlaetze(){
        for(Etage e : parkhaus.etagen){
            for(Parkplatz p : e.parkplaetze){
                if(p.empty)return true;
            }
        }
        return false;
    }

    int [] freiePlaetzeProEtage(){
        int [] ret = new int  [parkhaus.etagen.length];
        int etage = 0;
        for(Etage e : parkhaus.etagen){

            int i= 0;
            for(Parkplatz p : e.parkplaetze){
                if(p.empty)i++;
            }
            ret[etage++] = i;

        }
        return ret;
    }

    public int freiePlaetzeGesamt(){
        return Arrays.stream(freiePlaetzeProEtage())
                .sum();
    }

    public double getPreis(){
        return preis;
    }

    public void setPreis(double preis){
        this.preis = preis;
    }

    public double getGesamtEinnahmen(){
        return datenliste.stream()
                .mapToDouble(x -> x.preis)
                .sum();
    }

    public void addDaten(Daten d){
        datenliste.add(d);
    }

    public int getCurrentID(){
        return currentID++;
    }

    public void addAuto(Auto auto){
        autos.add(auto);
    }

    public void removeAuto(Auto auto){
        autos.remove(auto);
    }




}
