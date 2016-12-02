package ocase7;

import java.util.ArrayList;

public class Sesion {

    public static void fillCardBox(ArrayList<Answer> answers) {
        for (Answer answer : answers) {
            System.out.println(answer);
        }
    }

    public static void main(String[] args) {
//       fillCardBox(Category.getCategoryById(1));
        fillCardBox(Answer.getAnswersToQuestion(Question.getQuestionById(1)));
    }

}
