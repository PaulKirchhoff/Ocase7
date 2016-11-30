/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static ocase7.Question.pstmt;

/**
 *
 * @author PaulsBook
 */
class Answers {
    
    //Verbindungsvariablen 
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;
    
    private int id;
    private String text;
    private int question_id;
    private boolean isRight;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public boolean isIsRight() {
        return isRight;
    }

    public Answers(int id, String text, int question_id, boolean isRight) {
        
        
        this.id = id;
        this.text = text;
        this.question_id = question_id;
        this.isRight = isRight;
    }

    public static ArrayList<Question> getQuestionsByCategory(Category category) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT id, text, inactive, category_id FROM question, category2question WHERE category_id = ? AND question.id = question_id";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, category.getId());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                questions.add(new Question(resultSet.getInt("id"), resultSet.getString("text"), resultSet.getInt("category_id"), resultSet.getInt("inactive")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return questions;
    }
    
    
    @Override
    public String toString() {
        return "Answers{" + "id=" + id + ", text=" + text + ", question_id=" + question_id + ", isRight=" + isRight + '}';
    }
    
    
}
