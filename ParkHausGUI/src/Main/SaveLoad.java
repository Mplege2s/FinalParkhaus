package Main;

import com.thoughtworks.xstream.XStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveLoad {

    static XStream xstream= new XStream();
    Parkhaus runy;

    public SaveLoad(Parkhaus runy) {

        Class[] classes = new Class[]{Ausfahrt.class, Auto.class, Daten.class, Einfahrt.class,
                Etage.class, Manager.class, Parkhaus.class, Parkplatz.class, Ticket.class,
                Ticketautomat.class};
        xstream.allowTypes(classes);

        this.runy = runy;
        xstream.alias("parkhaus", Parkhaus.class);
        xstream.alias("etage", Etage.class);
        xstream.alias("parkplatz", Parkplatz.class);
        xstream.alias("auto", Auto.class);
        xstream.alias("gui",Runnable.class);

    }

    public void save(){
        try {
            xstream.toXML(runy, new FileWriter("xml.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Parkhaus load() throws Exception {

       Parkhaus parkhausAusXML = (Parkhaus) xstream.fromXML(new FileReader("xml.xml"));
        return parkhausAusXML;
    }
}

