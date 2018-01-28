package Main;

public class Daten {
    int ticketId;
    long von;
    long bis;
    long parkdauer;
    double preis;

    public Daten(int id, long von, long bis, long dauer, double preis){
        this.ticketId = id;
        this.von = von;
        this.bis = bis;
        this.parkdauer = dauer;
        this.preis = preis;
    }
}

