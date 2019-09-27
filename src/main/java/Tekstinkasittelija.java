
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author s1901283
 */
public class Tekstinkasittelija {
    private static final String VALIMERKIT = "(\\.|\\,|\\;|\\:|\\!|\\?)";
    
    
    public ArrayList<String> tekstistaListaksi (String tiedostonNimi, String koodaus) throws FileNotFoundException{
        ArrayList<String> sanalista = new ArrayList<>();

        File tiedosto = new File(tiedostonNimi);
        Scanner lukija = new Scanner(tiedosto, koodaus); 
            while (lukija.hasNextLine()) {
                String[] rivi = lukija.nextLine().split(" ");
                for (String sana : rivi) {
                    if (sana.matches(".*" + VALIMERKIT)) {
                        // otetaan välimerkki pois lopusta ja tallennetaan sekin sanaksi
                        sanalista.add(sana.substring(0, sana.length() - 1));
                        sanalista.add(sana.substring(sana.length() - 1, sana.length()));
                    } else {
                        sanalista.add(sana);
                    }
                }
                sanalista.add("\n");
            }
        return sanalista;    
    }        
            
    public String listastaTekstiksi(List<String> sanalista) {
        String teksti = "";
        for (String sana : sanalista) {

            if (!sana.matches(VALIMERKIT)) {
                sana = " " + sana;
            }
            teksti += sana;
        }
        return teksti;
    }
        
    public String[] luoVaihtoehdot(List<String> sanalista, String oikeaVastaus) {
        String[] vaihtoehdot = new String[4];
        Random r = new Random();
        vaihtoehdot[0] = oikeaVastaus;
        int i;
        String sana;

        for (int j = 1; j < vaihtoehdot.length; j++) {
            do {
                i = r.nextInt(sanalista.size());
                sana = sanalista.get(i);
            } while (sanalista.get(i).matches(VALIMERKIT + "|\n"));

            vaihtoehdot[j] = sanalista.get(i);
        }
        // shuffle puuttuu --> aina A-vastaus
        return vaihtoehdot;
    }

}
