package ocase7;

import java.util.ArrayList;

public class Card {

    private int id;
    private Question question;
    private ArrayList<Answer> answers;

    public Card(int id, Question question, ArrayList<Answer> answers) {             //Konstrukter sagt mir wie ich meine Card zu bauen habe!
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public int hashCode() {                                   
        int hash = 5;
        hash = 29 * hash + this.id;
        return hash;
    }
//qwdqwdqipugohugouhgohugvouhvouhcvouv
    //Er vergleicht die Primärschlüssel(ID) um sie nicht doppelt in die ArrayList(cardSelection) einzutragen
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        return true;
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
        return "question=" + question + "answers=" + answers;
    }

}
