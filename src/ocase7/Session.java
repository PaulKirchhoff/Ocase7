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










//        ArrayList<Integer> zahlen1 = new ArrayList<>();
//        
//        zahlen1.add(1);
//        zahlen1.add(2);
//        zahlen1.add(3);
//        zahlen1.add(4);
//        zahlen1.add(5);
//        zahlen1.add(6);
//        zahlen1.add(13);
//        ArrayList<Integer> zahlen2 = new ArrayList<>();
//        
//        zahlen2.add(2);
//        zahlen2.add(3);
//        zahlen2.add(4);
//        zahlen2.add(40);
//        zahlen2.add(6);
//        zahlen2.add(7);
//        zahlen2.add(8);
//        zahlen2.add(9);
//        
//        zahlen1.removeAll(zahlen2);
//        //zahlen1.addAll(zahlen2);
//        ArrayList<Integer> cardbox = new ArrayList<>();
//        for(Integer integer : zahlen1) {
//            cardbox.add(integer);
//        }
//        System.out.println("###############");
//        for (Integer integer : zahlen2) {
//            cardbox.add(integer);
//        }
//        
//        for (Integer integer : cardbox) {
//            System.out.println(integer);
//        }
    }

}
