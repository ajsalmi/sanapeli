import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class Sanapeli {

    public static void main(String[] args) {
        
        //luetaan tiedosto ja jaetaan se sanoiksi, jotka tallennetaan listalle
        ArrayList<String> sanalista = new ArrayList<>();
        
        try(Scanner lukija = new Scanner(new File("Kalevala.txt"))) {
            while (lukija.hasNextLine()) {
                String[] sanat = lukija.nextLine().split("\\s+");
                for (String sana : sanat) {
                    sanalista.add(sana);
                }
            }    
        } catch (Exception e) {
                    System.out.println("Virhe: " + e.getMessage());
        }
        
        //testaamista varten
        for (String sana : sanalista) {
            System.out.println(sana);
        }
        
        
            
            
    }
    
}
