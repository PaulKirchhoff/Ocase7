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
public class Auto extends Kfz implements sitzbar{

    private final int sitzplaetze;

    @Override
    public int getSitzplaetze() {
        return sitzplaetze;
    }

    public Auto(int sitzplaetze, String kfzSchein) {
        super(kfzSchein);
        this.sitzplaetze = sitzplaetze;
    }

}
