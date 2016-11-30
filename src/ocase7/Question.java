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
import static ocase7.Category.stmt;
import static ocase7.Test.pstmt;

/**
 *
 * @author PaulsBook
 */
public class Question {

    //Verbindungsvariablen 
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;

    private int id;
    private String text;
    private int category_id;
    private boolean inactive;

    public Question(int id, String text, int category_id, int inactive) {
        this.id = id;
        this.text = text;
        this.category_id = category_id;

        if (inactive == 1) {
            this.inactive = true;
        }
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
        return "Question{" + "id=" + id + ", text=" + text + ", category_id=" + category_id + ", inactive=" + inactive + '}';
    }

}
