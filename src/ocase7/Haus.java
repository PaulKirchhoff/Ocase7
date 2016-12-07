/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

 class Haus {
    
    private String adresse;

    public String getAdresse() {
        return adresse;
    }

    @Override
    public String toString() {
        return "Haus{" + "adresse=" + adresse + '}';
    }

    public Haus(String adresse) {
        this.adresse = adresse;
    }
    
}
