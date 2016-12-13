/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probiereaus;

/**
 *
 * @author Teilnehmer
 */
public class Lkw extends Auto implements sitzbar{
    
    private int ladeflaeche;

    public int getLadeflaeche() {
        return ladeflaeche;
    }
   
    public Lkw(int ladeflaeche, int sitzplaetze, String kfzSchein) {
        super(sitzplaetze, kfzSchein);
        this.ladeflaeche = ladeflaeche;
    }

    

   
}
