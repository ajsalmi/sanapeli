import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.Random;


public class Sanapeli {
    private static final String VALIMERKIT= "(\\.|\\,|\\;|\\:|\\!|\\?)";
    
    public static void main(String[] args) {
        
        //luetaan tiedosto ja jaetaan se sanoiksi, jotka tallennetaan listalle
        ArrayList<String> sanalista = new ArrayList<>();
        
        try(Scanner lukija = new Scanner(new File("Kalevala.txt"),"UTF-8")) {
            while (lukija.hasNextLine()) {
                String[] rivi = lukija.nextLine().split(" ");
                for (String sana : rivi) {
                    if (sana.matches(".*"+VALIMERKIT)){
                        // otetaan välimerkki pois lopusta ja tallennetaan sekin sanaksi
                        sanalista.add(sana.substring(0, sana.length()-1));
                        sanalista.add(sana.substring(sana.length()-1, sana.length()));
                    } else {
                    sanalista.add(sana);
                    }
                }
                sanalista.add("\n");
            }    
        } catch (Exception e) {
                    System.out.println("Virhe: " + e.getMessage());
        }
        
        
        // -- aloitusvalikko tähän
        
        // loopin sisään ?
        // ohjeet
        // uusi peli --> päälooppiin
        // jatka --> (ei käytössä) 
        // lopeta --> System.exit() ????
        
        // -- aloitusvalikko tähän


        // päälooppi
        Scanner lukija = new Scanner(System.in);
        Random random = new Random();
        boolean jatkuu = true;   
        
        int i;
        String sana;
        while (jatkuu) {            
            // -- tehtävä tähän --
            do {                
                i = random.nextInt(sanalista.size());
                sana = sanalista.get(i);                
            } while (sanalista.get(i).matches(VALIMERKIT+"|\n"));
            
            int sanojaYmparilta = 20;
            List <String> vasenYmparisto;
            List <String> oikeaYmparisto;
            
            vasenYmparisto =  sanalista.subList(Math.max(i-sanojaYmparilta,0), i);
            oikeaYmparisto =  sanalista.subList(i+1, Math.min(i+sanojaYmparilta+1,sanalista.size()));
            
            String[] vaihtoehdot = luoVaihtoehdot(sanalista, sana);
            
            System.out.print(" ...");
            System.out.print(listastaTekstiksi(vasenYmparisto));
            System.out.print(" _____");
            System.out.print(listastaTekstiksi(oikeaYmparisto));

            // tulosta vaihtoehdot
            System.out.println(" ...\n");
            System.out.println("A)"+vaihtoehdot[0]);
            System.out.println("B)"+vaihtoehdot[1]);
            System.out.println("C)"+vaihtoehdot[2]);
            System.out.println("D)"+vaihtoehdot[3]);


            while(true){
                System.out.println("Arvaa! (A, B, C, D)");
                String vastaus = lukija.nextLine();
                if (vastaus.equals("A")){
                    System.out.println("Oikein!");
                    break;
                } else if (vastaus.matches("A|B|C|D")){
                    System.out.println("Yritä uudestaan!");
                }
            }

            System.out.println("\nHaluatko jatkaa: (k/e)");
            jatkuu = "k".equals(lukija.nextLine());
            
            // -- tehtävä loppuu tähän --
            
            // -- pistetaulu tähän --
            
            // lopeta --> break;
            // jatka --> ei mitään
            // muu syöte --> ?
            
            // -- pistetaulu loppuu
        }
    }
    
    public static String listastaTekstiksi (List<String> lista){
                //testaamista varten
        String teksti = "";
        for (String sana : lista) {
            
            if (!sana.matches(VALIMERKIT)){
                sana=" " + sana;
            }
            
            teksti += sana;
            }

        return teksti;
    }
    
    public static String[] luoVaihtoehdot (List<String> sanalista, String oikeaVastaus){
        String[] vaihtoehdot = new String[4];
        Random r = new Random();
        vaihtoehdot[0] = oikeaVastaus;
        int i; 
        String sana;
        
        for (int j = 1; j < vaihtoehdot.length; j++) {
            do {                
                i = r.nextInt(sanalista.size());
                sana = sanalista.get(i);                
            } while (sanalista.get(i).matches(VALIMERKIT+"|\n"));
                
                vaihtoehdot[j] = sanalista.get(i);                
        }
        // shuffle puuttuu --> aina A-vastaus
        return vaihtoehdot;
    }
}
