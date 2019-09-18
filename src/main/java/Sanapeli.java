import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Random;


public class Sanapeli {
    private static final String VALIMERKIT= "(\\.|\\,|\\;|\\:|\\!|\\?)";
    
    public static void main(String[] args) {
        
        //luetaan tiedosto ja jaetaan se sanoiksi, jotka tallennetaan listalle
        ArrayList<String> sanalista = new ArrayList<>();
        
        try(Scanner lukija = new Scanner(new File("Kalevala.txt"))) {
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
        
        //testaamista varten
//        String rivi = "";
//        for (String sana : sanalista) {
//            
//            if (!sana.matches(VALIMERKIT)){
//                sana=" " + sana;
//            }
//            
//            System.out.print(sana);
//        }
        
        // -- aloitusvalikko tähän
        
        // loopin sisään ?
        // ohjeet
        // uusi peli --> päälooppiin
        // jatka --> (ei käytössä) 
        // lopeta --> System.exit() ????
        
        // -- aloitusvalikko tähän


        // päälooppi
        Scanner lukija = new Scanner(System.in);
        boolean jatkuu = true;   
        Random random = new Random();
        int i;
        String sana;
        while (jatkuu) {            
            // -- tehtävä tähän --
            do {                
                i = random.nextInt(sanalista.size());
                sana = sanalista.get(i);                
            } while (sanalista.get(i).matches(VALIMERKIT+"|\n"));
            
            System.out.println("\n\nSana: "+ sana + " (indeksi: " + i + ")");
            System.out.println("Haluatko jatkaa: (k/e)");
            jatkuu = "k".equals(lukija.nextLine());
            
            
            // -- tehtävä loppuu tähän --
            
            // -- pistetaulu tähän --
            
            // lopeta --> break;
            // jatka --> ei mitään
            // muu syöte --> ?
            
            // -- pistetaulu loppuu
        }
    
    }
    
}
