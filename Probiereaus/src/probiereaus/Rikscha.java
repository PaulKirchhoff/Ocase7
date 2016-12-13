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
public class Rikscha implements sitzbar{
    
    private int sitzplaetze;

    @Override
    public int getSitzplaetze() {
        return sitzplaetze;
    }

    public Rikscha(int sitzplaetze) {
        this.sitzplaetze = sitzplaetze;
    }
    
}
