package probiereaus;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Teilnehmer
 */
public abstract class Kfz {
    
    private final String kfzSchein;

    public String getKfzSchein() {
        return kfzSchein;
    }
 
    public Kfz(String kfzSchein) {
        this.kfzSchein = kfzSchein;
    }
    
}
