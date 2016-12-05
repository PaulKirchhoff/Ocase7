
package ocase7;

import java.util.ArrayList;


public class Card {
    private int id;

    public Card(int id, Question question, ArrayList<Answer> answers) {             //Konstrukter sagt mir wie ich meine Card zu bauen habe!
        this.id = id;
        this.question = question;
        this.answers = answers;
    }
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
