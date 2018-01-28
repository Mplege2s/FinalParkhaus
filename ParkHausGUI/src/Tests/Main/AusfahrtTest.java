package Main;

import org.junit.Before;
import org.junit.Test;

public class AusfahrtTest {

    Ausfahrt ausfahrt;
    Parkhaus parkhaus;
    Manager manager;
    Auto auto;
    Auto auto2;
    Ticket ticket;

    @Before
    public void setUp() throws Exception {
        parkhaus = new Parkhaus(4,10,2);
        manager = parkhaus.manager;
        ausfahrt = new Ausfahrt(manager);
        ticket = new Ticket(3, System.currentTimeMillis());
        auto = new Auto(3, ticket);
        auto2 = new Auto(4, ticket);
    }

    @Test
    public void ticketNichtBezahlt(){
        assert(!ausfahrt.ticketRein(ticket, auto));
    }

    @Test
    public void ticketAutoUnterschiedlich(){
        ticket.bezahlt = true;
        assert(!ausfahrt.ticketRein(ticket,auto2));
    }

    @Test
    public void ticketBezahltxd(){
        manager.addAuto(auto);
        ticket.bezahlt = true;
        assert(ausfahrt.ticketRein(ticket, auto));
        assert(manager.autos.isEmpty());
    }

}