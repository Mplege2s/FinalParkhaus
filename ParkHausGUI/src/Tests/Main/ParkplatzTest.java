package Main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkplatzTest {

    Parkplatz parkplatz;
    Auto auto;

    @Before
    public void setUp() throws Exception {
        parkplatz = new Parkplatz(0, 3);
        auto = new Auto(3, new Ticket(3, System.currentTimeMillis()));
    }

    @Test
    public void toStringLeerTest() throws Exception {
        String s = parkplatz.toString();
        String erwartet = "<html>etage: 0<br />PPNr: 3</html>";
        assertEquals(erwartet, s);
    }

    @Test
    public void toStringVollTest() throws Exception {
        parkplatz.setAuto(auto);
        String s = parkplatz.toString();
        String erwartet = "voll!";
        assertEquals(erwartet, s);
    }

}