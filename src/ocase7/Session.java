package ocase7;

import java.util.ArrayList;

public class Session {

    static ArrayList<Card> cards0 = new ArrayList<>();
    static ArrayList<Card> cards1 = new ArrayList<>();
    static ArrayList<ArrayList> cardSelection = new ArrayList<>();  

    public static void main(String[] args) {

        
        
        
        //Rolf´s Version
        int category_id1 = 1;
        ArrayList<Integer> questionIds1 = Question.getQuestionIdsByCategoryId(category_id1);
        for (Integer questionId1 : questionIds1) {
            Question q = Question.getQuestionById(questionId1);
            cards0.add(new Card(questionId1, q, Answer.getAnswersToQuestion(q)));

        }
        int category_id2 = 2;
        ArrayList<Integer> questionIds2 = Question.getQuestionIdsByCategoryId(category_id2);
        for (Integer questionId2 : questionIds2) {
            Question q = Question.getQuestionById(questionId2);
            cards1.add(new Card(questionId2, q, Answer.getAnswersToQuestion(q)));
            
        }
        
        cards0.removeAll(cards1);
//        System.out.println(cards0);
        cardSelection.add(cards0);
        cardSelection.add(cards1);
        System.out.println(cardSelection);
//        

        


    }

}
