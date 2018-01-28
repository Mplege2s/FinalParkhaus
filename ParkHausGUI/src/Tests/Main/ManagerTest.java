package Main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerTest {

    Parkhaus parkhaus;
    Manager manager;
    Ticket ticket;

    @Before
    public void setUp() throws Exception {
        parkhaus = new Parkhaus(4,10,2);
        manager = parkhaus.manager;
        ticket = new Ticket(3, System.currentTimeMillis());
    }

    @Test
    public void hatFreiePlaetze() throws Exception {
        assert(manager.hatFreiePlaetze());
        for(Etage e : parkhaus.etagen){
            for(Parkplatz p : e.parkplaetze){
                p.setAuto(new Auto(3, ticket));
            }
        }
        assert(!manager.hatFreiePlaetze());
    }

    @Test
    public void freiePlaetzeProEtage() throws Exception {
        for(Etage e : parkhaus.etagen){
            for(Parkplatz p : e.parkplaetze){
                p.setAuto(new Auto(3, ticket));
            }
        }
        // Autos wieder rausnehmen, für nächsten Test
        for(Etage e : parkhaus.etagen){
            for(Parkplatz p : e.parkplaetze){
                if(p.j % 2 == 0){
                    p.setEmpty();
                }
            }
        }
        int[] freiePlaetze = manager.freiePlaetzeProEtage();
        for(int i = 0; i < parkhaus.etagen.length; i++){
            assertEquals(5, freiePlaetze[i]);
        }
    }

    @Test
    public void freiePlaetzeGesamt() throws Exception {
        for(Etage e : parkhaus.etagen){
            for(Parkplatz p : e.parkplaetze){
                p.setAuto(new Auto(3, ticket));
            }
        }
        // Autos wieder rausnehmen, für nächsten Test
        for(Etage e : parkhaus.etagen){
            for(Parkplatz p : e.parkplaetze){
                if(p.j % 2 == 0){
                    p.setEmpty();
                }
            }
        }
        assertEquals(20, manager.freiePlaetzeGesamt());
    }

    @Test
    public void getGesamtEinnahmen(){
        manager.datenliste.add(new Daten(0, 0, 10, 10, 12 ));
        manager.datenliste.add(new Daten(1, 0, 10, 10, 4 ));
        manager.datenliste.add(new Daten(2, 0, 10, 10, 11 ));
        manager.datenliste.add(new Daten(3, 0, 10, 10, 21 ));
        manager.datenliste.add(new Daten(4, 0, 10, 10, 5 ));
        manager.datenliste.add(new Daten(5, 0, 10, 10, 40 ));

        assertEquals(93, manager.getGesamtEinnahmen(), 0.01);
    }

}