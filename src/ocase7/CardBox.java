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
    private int NumberOfCards;

    public int getNumberOfCards() {
        return NumberOfCards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCard(ArrayList<Card> cards) {
        this.cards = cards;
    }

//    public CardBox(ArrayList<Card> cards) {
//        this.cards = cards;
//        this.NumberOfCards = cards.size();
//
//    }
    //###############sani was here###############################
    // neue CardBox wird direkt mit Karten erstellt
    // setzt Gesamtanzahl der Karten
    public CardBox(ArrayList<Category> categories) {
        this.cards = fillCardBox(categories);
        this.NumberOfCards = cards.size();
        for (Card card : cards) {
            //System.out.println(card);
        }
    }

    public CardBox(ArrayList<Category> categories, int numberOfQuestions) {
        this.cards = fillCardBox(categories, numberOfQuestions);
        this.NumberOfCards = cards.size();
//        for (Card card : cards) {
//            System.out.println(card);
//        }
    }

    public CardBox() {
    }

    // füllt cardBox
    // Fragen, die in mehreren categories vorkommen (id ist gleich)
    // werden nur einmal in die cardBox gefüllt 
    public static ArrayList<Card> fillCardBox(ArrayList<Category> categories) {
        ArrayList<Card> cards = new ArrayList<>();
        // keine card soll doppelt vorkommen
        for (Category category : categories) {
            // doppelt vorkommende cards in (cards und category.getCards()) entfernen
            cards.removeAll(category.getCards());
            //  Vereinigungsmenge
            cards.addAll(category.getCards());
        }
        return cards;
    }

    public static ArrayList<Category> fillCardBoxByCategories() {
        ArrayList<Category> categories = Category.getAll();
        for (Category category : categories) {
            category.fillCards();
        }
         return categories;
    }

    public static ArrayList<Card> fillCardBox(ArrayList<Category> categories, int numberOfQuestions) {
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<Card> cardsi = new ArrayList<>();
        for (Category category : categories) {
            for (Question question : Question.getAllQuestionsByCategoryId(category.getId())) {
                if (!questions.contains(question) && questions.size() < numberOfQuestions) {
                    questions.add(question);
                }
            }
        }
        for (Question question : questions) {
            cardsi.add(new Card(question.getId()));
        }

        return cardsi;

    }
//######################sani was here, paul 2#####################################################

    public Card nextCard(int cardBoxIndex) {
        if (cardBoxIndex < cards.size() - 1) {
            return cards.get(cardBoxIndex + 1);
        } else {
            return cards.get(0);
        }
    }

    //unwartbar
    public Card prevCard(int cardBoxIndex) {
        if (cardBoxIndex > 0) {
            return cards.get(cardBoxIndex - 1);
        } else {
            return cards.get(cards.size() - 1);
        }
    }

    @Override
    public String toString() {
        return "CardBox{" + "cards=" + cards + '}';
    }

}
