package ocase7;

import java.util.ArrayList;

public class Session {

    public static void fillCardBox(ArrayList<Answer> answers) {
        for (Answer answer : answers) {
            System.out.println(answer);
        }
    }

    public static void main(String[] args) {
//       fillCardBox(Category.getCategoryById(1));
//        fillCardBox(Answer.getAnswersToQuestion(Question.getQuestionById(5)));      //ID der FRage 

        //Rolf´s Version
        int zahl = 5;
        Question q = Question.getQuestionById(zahl);                                       //Fragt die Question ab

        Card c = new Card(zahl, q, Answer.getAnswersToQuestion(q));                         //Beim erstellen der neuen Card zwingt mich der Konstruktor aus Card alle angaben zu machen

        
        //Flo´s Version
        
//        Question q = Question.getQuestionById(5);                                       //Fragt die Question ab
//        int id = q.getId();                                                             //Fragt die Id der Question ab
//        ArrayList<Answer> answers = Answer.getAnswersToQuestion(q);                     //Holt sich die ArrayList answer mit der getAnswerToQuestion;
//
//        c.setQuestion(q);                                                               //Fügt die Question zur Card (c) hinzu
//        c.setId(id);                                                                    //Fügt die id zur Card (c) hinzu
//        c.setAnswers(answers);                                                          //Fügt die Answer zur Card (c) hinzu

        System.out.println(c);
    }

}
