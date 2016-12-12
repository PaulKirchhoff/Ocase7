/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author PaulsBook
 */
public class Card {

    //Verbindungsvariablen 
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;

    private int id;
    private Question question;
    private ArrayList<UserAnswer> userAnswers;

    public Card(int id, Question question, ArrayList<UserAnswer> answers) {
        this.id = id;
        this.question = question;
        this.userAnswers = userAnswers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ArrayList<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(ArrayList<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

//    public static ArrayList<Card> getAll() {
//        ArrayList<Card> cards = new ArrayList<>();
//        try {
//            Connection con = MySQLConnection.getConnection();
//            String sql = "SELECT * FROM question";
//            stmt = con.createStatement();
//            //pstmt = con.prepareStatement(sql);
//            resultSet = stmt.executeQuery(sql);
//            Question q;
//            while (resultSet.next()) {
//                q = new Question(resultSet.getInt("id"), resultSet.getString("text"), resultSet.getInt("category_id"), resultSet.getInt("inactive"));
//                cards.add(new Card(resultSet.getInt("id"), q, Answer.getAnswersByQuestion(q)));
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        } finally {
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        return cards;
//    }
//    public static Card getCardsByCategory(Category c) {
//        //ArrayList<Card> cards = new ArrayList<>();
//        ArrayList<Question> questions = Question.getQuestionsByCategory(c);
//        Question q = questions.get(0);
//        Card card = new Card(q.getId(), q, Answer.getAnswersByQuestion(q));
//        
//        return card;
//    }

    @Override
    public String toString() {
        return "Card{" + "id=" + id + ", question=" + question + ", answers=" + userAnswers + '}';
    }

    
    //##################sani was here#####################################
    // erstellt Card mit Frage und dazugehörigen Antworten
    
    public Card(int question_id) {
        this.id = question_id;
        this.question = Question.getQuestionById(question_id);
        this.userAnswers = UserAnswer.addUserAnswerToAnswerArray(question);
    }
    
    //#####################sani was here###################################
}
