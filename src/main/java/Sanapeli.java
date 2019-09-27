
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.Random;

public class Sanapeli {

    private static final String VALIMERKIT = "(\\.|\\,|\\;|\\:|\\!|\\?)";
    private static final KayttoLiittyma kayttis = new TekstiKayttis();

    public static void main(String[] args) {

        //luetaan tiedosto ja jaetaan se sanoiksi, jotka tallennetaan listalle
        ArrayList<String> sanalista = new ArrayList<>();

        try (Scanner lukija = new Scanner(new File("Kalevala.txt"), "UTF-8")) {
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
        } catch (Exception e) {
            kayttis.naytaTeksti("Virhe: " + e.getMessage());
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

                    int sanojaYmparilta = 20;
                    List<String> vasenYmparisto;
                    List<String> oikeaYmparisto;

                    vasenYmparisto = sanalista.subList(Math.max(i - sanojaYmparilta, 0), i);
                    oikeaYmparisto = sanalista.subList(i + 1, Math.min(i + sanojaYmparilta + 1, sanalista.size()));

                    kayttis.naytaTeksti(
                            " ..." + listastaTekstiksi(vasenYmparisto)
                            + " _____"
                            + listastaTekstiksi(oikeaYmparisto)
                            + " ...\n");

                    String[] vaihtoehdot = luoVaihtoehdot(sanalista, sana);
                    kayttis.kyseleMonivalinta(vastaus, vaihtoehdot, 0);
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

    public static String listastaTekstiksi(List<String> lista) {
        //testaamista varten
        String teksti = "";
        for (String sana : lista) {

            if (!sana.matches(VALIMERKIT)) {
                sana = " " + sana;
            }

            teksti += sana;
        }

        return teksti;
    }

    public static String[] luoVaihtoehdot(List<String> sanalista, String oikeaVastaus) {
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
