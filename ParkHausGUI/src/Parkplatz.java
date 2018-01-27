public class Parkplatz {


    boolean empty;
    Auto auto;
    int i;
    int j;

    public Parkplatz(int etage, int id){
        this.auto = null;
        this.empty = true;
        this.i = etage;
        this.j = id;
    }

    public boolean isEmpty(){
        return empty;

    }

    public void setEmpty(){
        this.empty = true;
        this.auto = null;
    }

    public void setAuto(Auto auto){
        this.auto = auto;
        this.empty = false;
    }

    public String toString(){
        if(isEmpty()){
            return "<html>etage: " + i + '\n' + "<br />" + "PPNr: " + j + "</html>";
        }
        else{
            return "voll!";
        }
    }
}
