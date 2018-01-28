package Main;

public class Einfahrt {

    Manager manager;

    public Einfahrt(Manager manager){
        this.manager =  manager;
    }

    public Ticket ticketZiehen(){
        int currentid = manager.getCurrentID();
        Ticket t = new Ticket(currentid,System.currentTimeMillis());
        manager.addAuto(new Auto(currentid,t));
        return t;
    }

    public boolean hatFreiePlaetze(){
        return manager.hatFreiePlaetze();
    }

    public String freiePlaetzeAnzeigen(){
        String s = "<html>";
        int[] fp = manager.freiePlaetzeProEtage();
        for(int i = 0; i<fp.length;i++){
            s+= "Etage "+i +": "+fp[i]+" freie Plaetze";
            if(i<fp.length-1){
                s+="  <br/>";
            }
        }
        s+= "</html>";
        return s;
    }
}
