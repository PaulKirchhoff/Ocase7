package ocase7;

import java.util.ArrayList;

public class Session {

//    public static void fillCardBox(ArrayList<Answer> answers) {
//        for (Answer answer : answers) {
//            System.out.println(answer);
//        }
//    }

    public static void main(String[] args) {
//       fillCardBox(Category.getCategoryById(1));
//        fillCardBox(Answer.getAnswersToQuestion(Question.getQuestionById(5)));      //ID der FRage 

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

        //Flo´s Version
//        Question q = Question.getQuestionById(5);                                       //Fragt die Question ab
//        int id = q.getId();                                                             //Fragt die Id der Question ab
//        ArrayList<Answer> answers = Answer.getAnswersToQuestion(q);                     //Holt sich die ArrayList answer mit der getAnswerToQuestion;
//
//        c.setQuestion(q);                                                               //Fügt die Question zur Card (c) hinzu
//        c.setId(id);                                                                    //Fügt die id zur Card (c) hinzu
//        c.setAnswers(answers);                                                          //Fügt die Answer zur Card (c) hinzu
    }

}
