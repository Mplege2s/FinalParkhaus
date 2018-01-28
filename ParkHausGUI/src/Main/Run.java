package Main;

import java.io.File;

public class Run {
   static Parkhaus runy;
    static Runnable gui;
    public static void main(String[] args){
     runy = new Parkhaus(4,10,0.5);

        File xml = new File("xml.xml");
        if(xml.exists() && !xml.isDirectory()){
            try {
                SaveLoad sl = new SaveLoad( new Parkhaus(1,2,1));
                runy = sl.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        gui =  new Runnable(runy);
        gui.setVisible(true);
    }
}
