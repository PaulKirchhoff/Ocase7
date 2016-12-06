package ocase7;

import java.util.ArrayList;

public class Session {

    static ArrayList<Card> cards = new ArrayList<>();
    static ArrayList<Card> cards2 = new ArrayList<>();

    public static void main(String[] args) {

        //Rolf´s Version
        int category_id = 1;
        ArrayList<Integer> questionIds = Question.getQuestionIdsByCategoryId(category_id);
        for (Integer questionId : questionIds) {
            Question q = Question.getQuestionById(questionId);
            cards.add(new Card(questionId, q, Answer.getAnswersToQuestion(q)));

        }
        int category_id2 = 2;
        ArrayList<Integer> questionIds2 = Question.getQuestionIdsByCategoryId(category_id2);
        for (Integer questionId2 : questionIds2) {
            Question q = Question.getQuestionById(questionId2);
            cards2.add(new Card(questionId2, q, Answer.getAnswersToQuestion(q)));
            
        }
        cards.removeAll(cards2);
        cards.addAll(cards2);
        System.out.println(cards);
        


    }

}
