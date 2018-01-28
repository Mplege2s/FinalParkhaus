package Main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EinfahrtTest {

    Parkhaus parkhaus;
    Ticket ticket;

    @Before
    public void setUp() throws Exception {
        parkhaus = new Parkhaus(2,10,2);
    }

    @Test
    public void ticketZiehenTest(){
        assert(parkhaus.manager.autos.isEmpty());
        ticket = parkhaus.einfahrt.ticketZiehen();
        assert(!parkhaus.manager.autos.isEmpty());
        assertEquals(1, ticket.id);
        ticket = parkhaus.einfahrt.ticketZiehen();
        assertEquals(2, ticket.id);
    }

    @Test
    public void freiePlaetzeAnzeigenLeerTest(){
        String s = parkhaus.einfahrt.freiePlaetzeAnzeigen();
        String erwartet = "<html>Etage 0: 10 freie Plaetze  <br/>Etage 1: 10 freie Plaetze</html>";
        assertEquals(erwartet, s);
    }

    @Test
    public void freiePlaetzeAnzeigenNichtLeerTest(){
        parkhaus.etagen[0].parkplaetze[3].setAuto(new Auto(3, new Ticket(3, System.currentTimeMillis())));
        String s = parkhaus.einfahrt.freiePlaetzeAnzeigen();
        String erwartet = "<html>Etage 0: 9 freie Plaetze  <br/>Etage 1: 10 freie Plaetze</html>";
        assertEquals(erwartet, s);
    }

}