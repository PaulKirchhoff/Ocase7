/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probiereaus;

import java.util.ArrayList;

/**
 *
 * @author Teilnehmer
 */
public class Probiereaus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Auto a= new Auto(2,"Golf");
        Lkw l = new Lkw(300,3,"Lasti");
        Rikscha r = new Rikscha(6);
            
        ArrayList<sitzbar> f = new ArrayList<>();
        f.add(a);
        f.add(l);
        f.add(r);
        int sum=0;
        for(sitzbar s: f){
            sum +=s.getSitzplaetze();
        }
        System.out.println(sum);
    }
    
}
