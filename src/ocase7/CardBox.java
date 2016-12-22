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
    private int numberOfCards;
    private boolean wrongQuestionCheckBoxSelected = false;

    public boolean isWrongQuestionCheckBoxSelected() {
        return wrongQuestionCheckBoxSelected;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCard(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void setWrongQuestionCheckBoxSelected(boolean isSelected) {
        this.wrongQuestionCheckBoxSelected = isSelected;
    }

//    public CardBox(ArrayList<Card> cards) {
//        this.cards = cards;
//        this.NumberOfCards = cards.size();
//
//    }
    //###############sani was here###############################
    // neue CardBox wird direkt mit Karten erstellt
    // setzt Gesamtanzahl der Karten
    public CardBox(ArrayList<Category> categories, boolean isSelected) {
        this.wrongQuestionCheckBoxSelected = isSelected;
        this.cards = fillCardBox(categories);
        this.NumberOfCards = cards.size();

    }

    public CardBox(ArrayList<Category> categories, int numberOfQuestions, boolean isSelected) {
        this.wrongQuestionCheckBoxSelected = isSelected;
        this.cards = fillCardBox(categories, numberOfQuestions);
        this.NumberOfCards = cards.size();
//        for (Card card : cards) {
//            System.out.println(card);
//        }
    }

    // füllt CardBox:
    // durchläuft KategorienArray und zieht sich dabei die dazugehörigen Fragen,    
    // überprüft ob die Frage im questionsArray schon erhalten ist,
    // wenn nicht, wird es dem questionsArray hinzugefügt
    //befüllt Card über jede QuestionID die sich nun im questionsArray befindet
    //gibt CardsArray zurück

    public static ArrayList<Card> fillCardBox(ArrayList<Category> categories) {
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<Card> cardsi = new ArrayList<>();
        if (!this.wrongQuestionCheckBoxSelected == true) {
            ArrayList<Integer> wrongQuestions_Ids = getAllWrongQuestion_Ids();

            for (Category category : categories) {
                for (Question question : Question.getAllQuestionsByCategoryId(category.getId())) {
                    if (wrongQuestions_Ids.contains(question.getId())) {
                        if (!questions.contains(question)) {
                            questions.add(question);
                        }
                    }
                }
                for (Question question : questions) {
                    cardsi.add(new Card(question.getId()));
                }

                return cardsi;

            }
        } else {

            for (Category category : categories) {
                for (Question question : Question.getAllQuestionsByCategoryId(category.getId())) {
                    if (!questions.contains(question)) {
                        questions.add(question);
                    }
                }
            }
            for (Question question : questions) {
                cardsi.add(new Card(question.getId()));
            }

            return cardsi;

        }
        return cardsi;
    }

    public static ArrayList<Card> fillCardBox(ArrayList<Category> categories, int numberOfQuestions) {
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<Card> cardsi = new ArrayList<>();
        if (this.wrongQuestionCheckBoxSelected == true) {
            ArrayList<Integer> wrongQuestions_Ids = getAllWrongQuestion_Ids();

            for (Category category : categories) {
                for (Question question : Question.getAllQuestionsByCategoryId(category.getId())) {
                    if (wrongQuestions_Ids.contains(question.getId())) {
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
//        } else {
//
//            for (Category category : categories) {
//                for (Question question : Question.getAllQuestionsByCategoryId(category.getId())) {
//                    if (!questions.contains(question) && questions.size() < numberOfQuestions) {
//                        questions.add(question);
//                    }
//                }
//            }
//            for (Question question : questions) {
//                cardsi.add(new Card(question.getId()));
//            }
//
//            return cardsi;
//
//        }
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
