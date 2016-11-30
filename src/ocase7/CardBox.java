/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import java.util.ArrayList;

/**
 *
 * @author PaulsBook
 */
public class CardBox {
    private ArrayList<Card> card;

    public ArrayList<Card> getCard() {
        return card;
    }

    public void setCard(ArrayList<Card> card) {
        this.card = card;
    }

    public CardBox(ArrayList<Card> card) {
        this.card = card;
    }

    
    
    
    
    @Override
    public String toString() {
        return "CardBox{" + "card=" + card + '}';
    }
    
    
    
}
