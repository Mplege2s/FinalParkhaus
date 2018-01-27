public class Parkhaus {

    Etage[] etagen;
    Manager manager;
    Einfahrt einfahrt;
    Ausfahrt ausfahrt;
    Ticketautomat tm;


    public Parkhaus(int etagen, int parkplaetze, double preis){
        this.etagen = new Etage[etagen];
       for(int i=0;i<etagen;i++){
           this.etagen[i]=  new Etage(parkplaetze,i);
       }
        this.manager =  new Manager(this);
        this.einfahrt = new Einfahrt(this.manager);
        this.ausfahrt = new Ausfahrt(this.manager);
        this.manager.setPreis(preis);
        this.tm = new Ticketautomat(this.manager);
    }
}
