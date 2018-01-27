public class Ticket {

    int id;
    long parkbeginn;
    long parkende;
    double preis;
    boolean bezahlt;

    public Ticket(int id, long parkbeginn){
        this.id = id;
        this.parkbeginn =  parkbeginn;
    }

    public void setParkende(long parkende){
        this.parkende = parkende;
    }

    public long getParkbeginn(){
        return parkbeginn;
    }

    public long getParkende(){
        return parkende;
    }

    public long getParkdauer(){
        return parkende - parkbeginn;
    }
    public void setPreis(double preis){
        this.preis =  preis;
    }

    public double getPreis(){
        return this.preis;
    }
}
