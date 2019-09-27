
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s1901283
 */
public interface KayttoLiittyma {
    
    public boolean kyseleMonivalinta(String kysymys, String[] vaihtoehdot, int oikeaVastaus);
    public void naytaTeksti(String teksti);
    public String kysyKayttajalta(String kysymys, String vaihtoehdot);
}
