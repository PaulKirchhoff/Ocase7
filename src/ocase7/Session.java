package ocase7;

import java.util.ArrayList;

public class Session {

//    static ArrayList<Card> cards0 = new ArrayList<>();
//    static ArrayList<Card> cards1 = new ArrayList<>();
//    static ArrayList<Card> cards2 = new ArrayList<>();
    static ArrayList<ArrayList> cardSelection = new ArrayList<>();

    public static void main(String[] args) {

        ArrayList<Card> cards = new ArrayList<>();
        //Rolf´s Version
        for (int category_id = 1; category_id < 8; category_id++) {
            ArrayList<Integer> questionIds = Question.getQuestionIdsByCategoryId(category_id);
            for (Integer questionId : questionIds) {
                Question q = Question.getQuestionById(questionId);
                cards.add(new Card(questionId, q, Answer.getAnswersToQuestion(q)));

            }
            cardSelection.add(cards);
            cards = new ArrayList<>();
        }
//        int category_id = 2;
//        ArrayList<Integer> questionIds = Question.getQuestionIdsByCategoryId(category_id);
//        for (Integer questionId : questionIds) {
//            Question q = Question.getQuestionById(questionId);
//            cards.add(new Card(questionId, q, Answer.getAnswersToQuestion(q)));
//
//        }
//        cardSelection.add(cards);
//        
//        cards = new ArrayList<>();        
//        int category_id3 = 3;
//        ArrayList<Integer> questionIds3 = Question.getQuestionIdsByCategoryId(category_id3);
//        for (Integer questionId3 : questionIds3) {
//            Question q = Question.getQuestionById(questionId3);
//            cards.add(new Card(questionId3, q, Answer.getAnswersToQuestion(q)));
//
//        }
//        cardSelection.add(cards);
//
//        cardSelection.get(0).removeAll(cardSelection.get(1));
//        cardSelection.get(0).addAll(cardSelection.get(1));
//        cardSelection.get(0).removeAll(cardSelection.get(2));
//        cardSelection.get(0).addAll(cardSelection.get(2));
//        System.out.println(cardSelection.get(0));
        for (ArrayList cardss : cardSelection) {
            for (Object car : cardss) {
                System.out.println(car);
                
            }
        }

    }

}
