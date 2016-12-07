/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import java.util.ArrayList;

/**
 *
 * @author Teilnehmer
 */
public class TestSchleife {

    public static void main(String[] args) {

        ArrayList<Haus> haueser = new ArrayList<>();

        haueser.add(new Haus("Bla"));
        haueser.add(new Haus("Blub"));
        haueser.add(new Haus("BlaBlub"));

        for (Haus haus : haueser) {
            System.out.println(haus);

        }

    }
}
