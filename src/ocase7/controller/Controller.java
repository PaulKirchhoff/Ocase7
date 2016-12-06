/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.controller;

import java.util.ArrayList;
import ocase7.Card;
import ocase7.CardBox;
import ocase7.Category;

/**
 *
 * @author PaulsBook
 */
public class Controller {
    
    public CardBox fillCardBoxByCategoryId(int id) {
        CardBox myCardBox = new CardBox();
        final Category categoryById = Category.getCategoryById(id);
        final ArrayList<Card> cardsByCategory = Card.getCardsByCategory(categoryById);
        myCardBox.setCard(cardsByCategory);
        return myCardBox;
    }
}
