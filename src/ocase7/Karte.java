/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

 class Karte {
    
    private String frage;

    public String getFrage() {
        return frage;
    }

    @Override
    public String toString() {
        return "Karte{" + "frage=" + frage + '}';
    }

    public Karte(String frage) {
        this.frage = frage;
    }
    
}
