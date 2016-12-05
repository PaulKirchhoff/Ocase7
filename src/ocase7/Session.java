package ocase7;

import java.util.ArrayList;

public class Session {



    public static void main(String[] args) {

        //Rolf´s Version
        int category_id = 1;
        ArrayList<Integer> questionIds = Question.getQuestionIdsByCategoryId(category_id);
        ArrayList<Card> cards = new ArrayList<>();
        for (Integer questionId : questionIds) {
            Question q = Question.getQuestionById(questionId);
            cards.add(new Card(questionId, q, Answer.getAnswersToQuestion(q)));
        }
        for (Card card : cards) {
            System.out.println(card);
            System.out.println("__________________________________________________________");

        }


    }

}
