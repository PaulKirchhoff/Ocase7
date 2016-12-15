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
public class UserAnswer extends Answer {
    
    private boolean given;

    public boolean isGiven() {
        return given;
    }

    public void setGiven(boolean given) {
        this.given = given;
    }

    public UserAnswer(int id, String text, int question_id, boolean isRight) {
        super(id, text, question_id, isRight);
        this.given = false;
    }

    public static ArrayList<UserAnswer> addUserAnswerToAnswerArray(Question question) {
        ArrayList<UserAnswer> userAnswer = new ArrayList<>();
        for (Answer answer : Answer.getAnswersByQuestion(question)) {
            userAnswer.add(new UserAnswer(answer.getId(),answer.getText(),answer.getQuestion_id(),answer.isIsRight()));
                                
        }
        return userAnswer;
    }
    
}
