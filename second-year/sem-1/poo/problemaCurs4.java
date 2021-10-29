import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io;
import jdk.internal.org.jline.utils.InputStreamReader;

class Curs {
    private String nume;
    private int id, cod, credite;

    public Curs(String nume, int id, int cod, int credite){
        this.nume=nume;
        this.id=id;
        this.cod=cod;
        this.credite=credite;
    }

    public boolean equals(Object c){
        if(c instanceof Curs){
            Curs tmp = (Curs)c;
            return cod == tmp.cod;
        }
        else{
            return false;
        }
    }

    public int getId(){
        return id;
    }

    public int getCredite(){
        return credite;
    }

    public String toString(){
        return "Id: "+id + "  Denumire: "+ nume + "Credite: "+ credite; 
    }

}

class Curicula{
    private Curs[] cursuri = new Curs[10];
    private int contor;//obiectele create sunt 0 by default :D :) 

    public void add(Curs c){
        if(contor == cursuri.lenght){
            Curs[] tmp = new Curs[contor*2];
            for(int i=0;i< contor;++i){
                tmp[i] = cursuri[i];
            }
            cursuri=tmp;
        }
        cursuri[contor] = c;
        contor++;
    }

    public String toString(){
        String aux=" Curicula: \n";
        for(int i=0;i < contor; ++i){
            aux += cursuri[i] + "\n";
        }
        return aux;
    }

    public Curs cauta(int id){
        for(int i=0;i<contor;++i){
            if(cursuri[i].getId() == id)
                return cursuri[i];
        }
        return null;
    }

    public static Curicula load(String f){
        Curicula r = new Curicula();
        
        try{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        String nume;
        int id,cod,credite;
        nume = br.readLine();

        while(nume != null){
           id = Integer.parseInt(br.readline());
           cod = Integer.parseInt( br.readline());
           credite = Integer.parseInt(br.readline());
           r.add(new Curs(nume,id,cod,credite));
           nume = br.readLine();
        }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        return r;
    }

}



class Client{

    public static void main(String argv[]){
        Curs c1= new Curs("POO",1,1,5);
        Curs c2= new Curs("SDA",2,2,5);
        Curs c3= new Curs("OOP",3,1,5);
        Curicula x = new Curicula();
        x.add(c1);
        x.add(c2);
        x.add(c3);
        System.out.println(c1 + "\n" + c2 +"\n"+c3);
        System.out.println(c1.equals(c2));
        System.out.println(c1.equals(c3));
        System.out.println(x);
        System.out.println(x.cauta(3));
        System.out.println(Curicula.load("exemplu.txt"));
    }
}