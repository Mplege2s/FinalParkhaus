package Main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaveLoadTest {

    SaveLoad saveLoad;
    Parkhaus parkhausAlt;
    Parkhaus parkhausNeu;

    @Before
    public void setUp() throws Exception {
        parkhausAlt = new Parkhaus(4,10,2);
        parkhausAlt.etagen[0].parkplaetze[0].setAuto(new Auto(3, new Ticket(3, System.currentTimeMillis())));
    }

    @Test
    public void saveLoadTest() throws Exception {
        saveLoad = new SaveLoad(parkhausAlt);
        saveLoad.save();
        parkhausNeu = SaveLoad.load();
        assertEquals(parkhausAlt.manager.freiePlaetzeGesamt(), parkhausAlt.manager.freiePlaetzeGesamt());
        assertEquals(parkhausAlt.etagen.length, parkhausNeu.etagen.length);
    }

}