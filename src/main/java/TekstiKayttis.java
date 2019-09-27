
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
public class TekstiKayttis implements KayttoLiittyma {

    private final Scanner lukija = new Scanner(System.in);


    @Override
    public String kysyKayttajalta(String kysymys, String vaihtoehdotRegex) {
        String vastaus = "";
        while (!vastaus.matches(vaihtoehdotRegex)) {
            System.out.println(kysymys);
            vastaus = lukija.nextLine();
        }
        return vastaus;
    }

    @Override
    public void naytaTeksti(String teksti) {
        System.out.println(teksti);
    }

    @Override
    public boolean kyseleMonivalinta(String kysymys, String[] vaihtoehdot, int oikeaVastaus) {
        String aakkoset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // mahdollisuus tehdä isompia monivalintakysymyksiä
//        int i = abcd.indexOf(vastaus);

        for (int i = 0; i < vaihtoehdot.length; i++) {
            System.out.println(aakkoset.charAt(i)+")"+vaihtoehdot[i]);
        }
        String vastaus;
        while (true) {
            System.out.println("Arvaa! (A, B, C, D)");
            vastaus = lukija.nextLine();
            if (vastaus.equals(""+aakkoset.charAt(oikeaVastaus))) {
                System.out.println("Oikein!");
                break;
            }
        }
        return true;
        
    }
}
