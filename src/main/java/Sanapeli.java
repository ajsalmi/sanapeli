
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sanapeli {

    private static final String VALIMERKIT = "(\\.|\\,|\\;|\\:|\\!|\\?)";
    private static final int YMPARISTON_KOKO = 20;
    
    private static final KayttoLiittyma kayttis = new TekstiKayttis();
    private static final Tekstinkasittelija kasittelija = new Tekstinkasittelija();

    public static void main(String[] args) {

        //luetaan tiedosto ja jaetaan se sanoiksi, jotka tallennetaan listalle
        ArrayList<String> sanalista = null;
        try {     
        sanalista = kasittelija.tekstistaListaksi("Kalevala.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            kayttis.naytaTeksti("Virhe: tiedostoa ei löydy!");
        }

        kayttis.naytaTeksti("Tervetuloa pelaamaan Kalevala-sanapeliä!");

        Ohjeet ohjeet = new Ohjeet(); // miten ratkaistaan? kayttis.tulosta(ohjeet) vai muuten? 

        // -- aloitusvalikko tähän
        while (true) {
            String vastaus = kayttis.kysyKayttajalta("\nOhjeet [o]\nUusi peli [p]\nLopeta [l]", "o|p|l");

            if (vastaus.equals("o")) {
                ohjeet.tulostaOhjeet(); // pitäisikö olla oma valikko 
                continue;               // josta voi vain [p]alata?
            }
            if (vastaus.equals("l")) {
                System.exit(0);
            }
            if (vastaus.equals("p")) {
                // päälooppi

                Random random = new Random();
                boolean jatkuu = true;
                int pisteet = 0;
                int i;
                String sana;
                while (jatkuu) {

                    do {
                        i = random.nextInt(sanalista.size());
                        sana = sanalista.get(i);
                    } while (sanalista.get(i).matches(VALIMERKIT + "|\n"));

                    List<String> vasenYmparisto = sanalista.subList(Math.max(i - YMPARISTON_KOKO, 0), i);
                    List<String> oikeaYmparisto = sanalista.subList(i + 1, Math.min(i + YMPARISTON_KOKO + 1, sanalista.size()));

                    String kysymys = 
                            " ..." + kasittelija.listastaTekstiksi(vasenYmparisto)
                            + " _____"
                            + kasittelija.listastaTekstiksi(oikeaYmparisto)
                            + " ...\n";

                    String[] vaihtoehdot = kasittelija.luoVaihtoehdot(sanalista, sana);
                    kayttis.kyseleMonivalinta(kysymys, vaihtoehdot, 0);
                    pisteet++;

                    //pistetaulu
                    vastaus = kayttis.kysyKayttajalta(
                            "\nOlet saanut " + pisteet + " vastausta oikein!"
                            + "\nHaluatko jatkaa: (k/e)", "k|e");

                    jatkuu = "k".equals(vastaus);
                }
            }
        }
    }
}
