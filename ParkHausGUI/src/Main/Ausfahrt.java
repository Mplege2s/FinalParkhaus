package Main;

public class Ausfahrt {

    Manager manager;

    public Ausfahrt(Manager manager){
        this.manager =  manager;
    }

    public boolean ticketRein(Ticket ticket,Auto auto){
        if(!ticket.bezahlt){
            System.out.println("Ticket noch nicht bezahlt. Bitte beim Ticketautomaten bezahlen!");
            return false;
        }
        if(auto.id != ticket.id){
            System.out.println("Dies ist nicht ihr Ticket. Ihre korrekte Ticket ID lautet: "+auto.id);
            return false;
        }
        manager.removeAuto(auto);
        manager.addDaten(new Daten(ticket.id,ticket.getParkbeginn(),ticket.getParkende(),ticket.getParkdauer(),ticket.getPreis()));
        return true;
    }


}
