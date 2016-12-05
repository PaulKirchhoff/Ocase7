
package ocase7;

import java.util.ArrayList;


public class Card {
    private int id = 0;
    private Question question;
    private ArrayList<Answer> answers;

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Card{" + "id=" + id + ", question=" + question + ", answers=" + answers + '}';
    }
    
   
   
           
}
