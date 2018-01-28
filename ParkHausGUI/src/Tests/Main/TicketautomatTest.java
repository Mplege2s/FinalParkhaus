package Main;

import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.SysexMessage;

import static org.junit.Assert.*;

public class TicketautomatTest {

    Ticketautomat ticketautomat;
    Parkhaus parkhaus;
    Ticket ticket;

    @Before
    public void setUp() throws Exception {
        parkhaus = new Parkhaus(4, 10, 2);
        ticketautomat = parkhaus.tm;
    }

    @Test
    public void zuZahlenTest() throws Exception {
        ticket = parkhaus.einfahrt.ticketZiehen();
        ticket.parkbeginn = System.currentTimeMillis() - 100000;
        double preis = ticketautomat.zuZahlen(ticket);
        assertEquals(200, preis, 0.01);
    }

}