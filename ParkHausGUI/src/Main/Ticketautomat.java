package Main;

public class Ticketautomat {

    long zeitTicketEingefuehrt;
    Manager manager;
    double preis;

    public Ticketautomat(Manager manager){
        this.manager = manager;
    }

    double zuZahlen(Ticket ticket){
        preis  = manager.preis;
        long bis = System.currentTimeMillis();
        ticket.setParkende(bis);
        return (ticket.getParkdauer()/1000)*preis;
        }

    public boolean zahlen(Ticket ticket){
        ticket.bezahlt = true;
        ticket.setPreis((ticket.getParkdauer()/1000)*preis);
        return ticket.bezahlt;
    }
}
