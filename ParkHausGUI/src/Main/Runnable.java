package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Runnable extends JFrame {



    Ticket currentTicket;   //Akutell beobachetes Main.Ticket
    JLabel ticketInformation;   //Label mit TicketInformationen
    Parkhaus ph;
    JPanel anzeige;
    JPanel currET;
    int[] aktPP;                //position des Autos von akt. Main.Ticket
    List<JPanel> etagen;
    int [] posComp;
    JPanel inf;
    int aktEtage;
    Component fp;
    JButton reset;
    JPanel content;

 //   ArrayList<Integer> prevTickets;
    SaveLoad sl;
    public Runnable(){

    }
    public Runnable(Parkhaus ph){


        content = new JPanel();
        posComp = new int[ph.etagen[0].parkplaetze.length];
        this.ph = ph;
        fp = Load();
        sl =  new SaveLoad(ph);
        this.etagen = new ArrayList<>();
        content.setLayout(new BorderLayout());
        this.etagen = createEtagen(ph.etagen.length,ph.etagen[0].parkplaetze.length);
        aktEtage = 0;
        currET = this.etagen.get(0);
        anzeige = createAnzeige();
        inf = new JPanel();
        inf.setLayout(new GridLayout(1,1));
        inf.add(saveButton());
        reset = reset();
        inf.add(reset);
        inf.add(fp);

       content.add(currET, BorderLayout.CENTER);
        content.add(anzeige, BorderLayout.EAST );
        content.add(zahlen(), BorderLayout.SOUTH);
        content.add(createPanel(ph.etagen.length),BorderLayout.WEST);
        content.add(inf, BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       content.setSize(800,400);
       setSize(800,400);
       add(content);



    }

     private JPanel createEtage(int etage, int parkplaetze){
        JPanel et = new JPanel();
        et.putClientProperty("etageId",etage);
        et.setLayout(new GridLayout(parkplaetze/2,3));
        int pid=0;
        for(int i=0;i< parkplaetze*1.5; i++){
            if(i%3==1) et.add(seperator());
            else {
                et.add(createParkplatz((int)(et).getClientProperty("etageId"),pid));
                posComp[pid++] = i;
            }
        }

        return et;

    }

  private JButton createParkplatz(int i, int j){
        int[] prop = new int[] {i,j};
        JButton pp;

        pp = new JButton(ph.etagen[i].parkplaetze[j].toString());

        pp.putClientProperty("postion", prop);
        pp.setBackground(Color.gray);
        pp.addActionListener(e ->{
            if(this.ph.etagen[i].parkplaetze[j].isEmpty()){
               createMenuP(i,j,pp).show(Runnable.this, 0,0);
            }
            else {
                Ticket ticket = ph.etagen[i].parkplaetze[j].auto.ticket;
                String s ="<html>Ticket ID: " + ticket.id +"<br /> "+"Parkbeginn: " + new Date(ticket.getParkbeginn()).toString() + "<br />"
                        +"Aktuelle Zeit: " + new Date(System.currentTimeMillis()) + "<br />"  +"zu zahlen: " + ph.tm.zuZahlen(ticket)+ "</html>";
                ticketInformation.setText(s);

                currentTicket = ticket;
                aktPP = new int[]{i,j};
                Runnable.this.repaint();
            }
        });
        return pp;

    }




   static private JLabel seperator(){
        JLabel ret = new JLabel();
        ret.setText("Slow Driving");
        ret.setBackground(Color.gray);
        ret.setOpaque(true);
        return ret;
    }

    private ArrayList<JPanel> createEtagen(int i, int j){
        ArrayList<JPanel> ret = new ArrayList<>();
        for(int k=0; k<i; k++){
            ret.add(createEtage(k,j));
        }
        return ret;

    }

    private JPopupMenu createMenuP(int i , int j, JButton jb){
        JPopupMenu popup = new JPopupMenu();
        popup.add(new JMenuItem(new AbstractAction("Parken") {
            public void actionPerformed(ActionEvent e) {
               ph.einfahrt.ticketZiehen();
               ph.etagen[i].parkplaetze[j].setAuto(ph.manager.autos.get(ph.manager.autos.size()-1));
               jb.setText("Voll!");
                ((JLabel) anzeige.getComponent(0)).setText(ph.einfahrt.freiePlaetzeAnzeigen());
               Runnable.this.repaint();
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Cancel") {
            public void actionPerformed(ActionEvent e) {

            }
        }));
        return popup;
    }




    public JButton zahlen(){
        JButton ret = new JButton("Zahlen");
        ret.setBackground(Color.yellow);
        ret.addActionListener(e -> {
            if(currentTicket != null){
            ph.etagen[aktPP[0]].parkplaetze[aktPP[1]].setEmpty();
            ph.tm.zahlen(currentTicket);

            ph.ausfahrt.ticketRein(currentTicket,ph.manager.autos.get(calcPosCar(currentTicket.id)));
            ((JButton)etagen.get(aktPP[0]).getComponent(posComp[aktPP[1]])).setText("<html>etage: "+aktPP[0]+'\n'+"<br />"+"PPNr: "+aktPP[1]+"</html>");
            ticketInformation.setText("Kein Ticket ausgewaehlt");
            ((JLabel)anzeige.getComponent(0)).setText(ph.einfahrt.freiePlaetzeAnzeigen());
                ((JLabel)anzeige.getComponent(2)).setText("<html> Gesamt Einnahmen: <br />" + ph.manager.getGesamtEinnahmen()+"</html>" );
            ph.manager.prevTickets.add(currentTicket.id);
            currentTicket = null;
            inf.remove(fp);
            fp = Load();
            inf.add(fp);
            }
            else{

            }


        });
        return ret;
    }

    private JPanel createAnzeige(){
        JPanel ret =  new JPanel();
        ret.setPreferredSize(new Dimension(200,200));
        ret.setLayout(new GridLayout(3,0));
        JLabel freieP =  new JLabel();
        freieP.setOpaque(true);
        freieP.setBackground(Color.yellow);
        freieP.setText(ph.einfahrt.freiePlaetzeAnzeigen());
        ticketInformation = new JLabel();
        ticketInformation.setBackground(Color.yellow);
        ticketInformation.setOpaque(true);
        JLabel einnahmen =  new JLabel();
        einnahmen.setBackground(Color.yellow);
        einnahmen.setOpaque(true);
        einnahmen.setText("<html> Gesamt Einnahmen: <br />" + ph.manager.getGesamtEinnahmen()+"</html>");

        ret.add(freieP);
        ret.add(ticketInformation);
        ret.add(einnahmen);
        return ret;

    }
    int calcPosCar(int id){
        int ret = id-1;
        for(int a : ph.manager.prevTickets){
            if(a<id)ret--;
        }
        return ret;
    }
    private JPanel createPanel(int i){
        JPanel ret = new JPanel();
        ret.setOpaque(true);
        ret.setBackground((Color.BLUE));
        ret.setLayout(new GridLayout(i,0));
        for(int j=0;j<i;j++){
            int k = j;
            JButton btn = new JButton(j+". Etage");
            btn.setBackground(Color.BLUE);
            btn.setForeground(Color.gray);
            btn.addActionListener(e ->{

                aktEtage = k;
                Dimension x = currET.getSize();
                content.remove(currET);
                currET = etagen.get(k);
                content.add(currET);
                currET.setPreferredSize(x);
                Runnable.this.pack();

                Runnable.this.repaint();
            });
            ret.add(btn);
        }
        return ret;
    }

    public JButton saveButton(){
        JButton ret = new JButton("save");
        ret.addActionListener(e->{
            sl.save();
        });
        ret.setBackground(Color.RED);
        return ret;
    }

    public JButton reset(){
       JButton jb = new JButton("Reset");
        jb.addActionListener(e -> {
            ph = new Parkhaus(4, 10, 1);
            Runnable.this.remove(content);
            content = new JPanel();
            posComp = new int[ph.etagen[0].parkplaetze.length];
            Runnable.this.ph = ph;
            fp = Load();
            sl =  new SaveLoad(ph);
            Runnable.this.etagen = new ArrayList<>();
            content.setLayout(new BorderLayout());
            Runnable.this.etagen = createEtagen(ph.etagen.length,ph.etagen[0].parkplaetze.length);
            aktEtage = 0;
            currET = this.etagen.get(0);
            anzeige = createAnzeige();
            inf = new JPanel();
            inf.setLayout(new GridLayout(1,1));
            inf.add(saveButton());
            reset = reset();
            inf.add(reset);
            inf.add(fp);

            content.add(currET, BorderLayout.CENTER);
            content.add(anzeige, BorderLayout.EAST );
            content.add(zahlen(), BorderLayout.SOUTH);
            content.add(createPanel(ph.etagen.length),BorderLayout.WEST);
            content.add(inf, BorderLayout.NORTH);
            Runnable.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            content.setSize(800,400);
            Runnable.this.add(content);
            Runnable.this.pack();
            Runnable.this.setSize(800,400);;
            Runnable.this.repaint();

        });
        jb.setBackground(Color.RED);
        return jb;



    }

    JButton Load(){
        JButton jb = new JButton("Load");
        jb.addActionListener((ActionEvent e) -> {
            try {
                ph = sl.load();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            Runnable.this.remove(content);
            content = new JPanel();
            posComp = new int[ph.etagen[0].parkplaetze.length];
            Runnable.this.ph = ph;
            fp = Load();
            sl =  new SaveLoad(ph);
            Runnable.this.etagen = new ArrayList<>();
            content.setLayout(new BorderLayout());
            Runnable.this.etagen = createEtagen(ph.etagen.length,ph.etagen[0].parkplaetze.length);
            aktEtage = 0;
            currET = this.etagen.get(0);
            anzeige = createAnzeige();
            inf = new JPanel();
            inf.setLayout(new GridLayout(1,1));
            inf.add(saveButton());
            reset = reset();
            inf.add(reset);
            inf.add(fp);

            content.add(currET, BorderLayout.CENTER);
            content.add(anzeige, BorderLayout.EAST );
            content.add(zahlen(), BorderLayout.SOUTH);
            content.add(createPanel(ph.etagen.length),BorderLayout.WEST);
            content.add(inf, BorderLayout.NORTH);
            Runnable.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            content.setSize(800,400);
            Runnable.this.add(content);
            Runnable.this.pack();
            Runnable.this.setSize(800,400);;
            Runnable.this.repaint();

        });
        jb.setBackground(Color.RED);
        return jb;
    }







}
