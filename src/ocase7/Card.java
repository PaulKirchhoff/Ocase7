package ocase7;

import java.util.ArrayList;

public class Card {

    private int question_id;
    private int id;
    private String textQuestion;
    private String textAnswer;

    private ArrayList<Answer> answers = new ArrayList<>();    

    public String getTextQuestion() {
        return textQuestion;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }  
    

    public Card(int question_id) {
        this.id = question_id;
        this.textQuestion = Question.getQuestionById(question_id).getText();
        this.answers = Answer.getAllAnswerByQuestionId(question_id);
    }

    

}
