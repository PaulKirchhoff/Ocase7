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

    private ArrayList<Card> cards;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCard(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public CardBox(ArrayList<Card> cards) {
        this.cards = cards;
        
        
        
        
        
        
        
        
    }
    
    public Card nextCard(int id) {
        return this.cards.get(id+1);
    }

    public CardBox() {
        
    }

    
    
   

    @Override
    public String toString() {
        return "CardBox{" + "cards=" + cards + '}';
    }

}
