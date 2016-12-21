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

/**
 *
 * @author Teilnehmer
 */
public class WrongQuestions extends Question {

    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;

    private boolean cheated;
    private boolean followUp;

    private ArrayList<WrongQuestions> wrongQuestions = new ArrayList<>();
    private ArrayList<Category> wrongQuestionsCategories = new ArrayList<>();

    public boolean isCheated() {
        return cheated;
    }

    public boolean isFollowUp() {
        return followUp;
    }

    public ArrayList<WrongQuestions> getWrongQuestions() {
        return wrongQuestions;
    }

    public ArrayList<Category> getWrongQuestionsCategories() {
        return wrongQuestionsCategories;
    }

    public WrongQuestions(boolean cheated, boolean followUp, int id, String text, int category_id, int inactive) {
        super(id, text, category_id, inactive);
        this.cheated = cheated;
        this.followUp = followUp;
    }

    public static ArrayList<Integer> getAllWrongQuestion_Ids() {
        ArrayList<Integer> question_ids = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT DISTINCT question_id "
                    + "FROM answer "
                    + "WHERE id IN "
                    + "(SELECT a.id AS answer_id "
                    + "FROM session2question s2q, answer a "
                    + "WHERE s2q.question_id = a.question_id "
                    + "AND a.isRight=1 "
                    + "AND session_id=5 "
                    + "AND a.id NOT IN "
                    + "(SELECT useranswer_id "
                    + " FROM session2useranswer "
                    + " WHERE session_id=5) "
                    + "UNION ALL "
                    + "SELECT useranswer_id "
                    + " FROM session2useranswer "
                    + " WHERE session_id=5 "
                    + "AND useranswer_id NOT IN "
                    + "(SELECT a.id AS answer_id "
                    + " FROM session2question s2q, answer a "
                    + " WHERE s2q.question_id = a.question_id "
                    + " AND a.isRight=1 "
                    + " AND session_id=5 "
                    + ")) "
                    + "UNION "
                    + "SELECT question_id "
                    + "FROM again";
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                question_ids.add(resultSet.getInt("id"));
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

        return question_ids;
    }

    public static ArrayList<Integer> getAllWrongQuestionCategory_Ids() {
        ArrayList<Integer> category_ids = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT category_id FROM category2question "
                    + "WHERE question_id IN "
                    + "(SELECT question_id  "
                    + "FROM answer "
                    + "WHERE id IN "
                    + "(SELECT a.id AS answer_id "
                    + "FROM session2question s2q, answer a "
                    + "WHERE s2q.question_id = a.question_id "
                    + "AND a.isRight=1 "
                    + "AND session_id=5 "
                    + "AND a.id NOT IN "
                    + "(SELECT useranswer_id "
                    + " FROM session2useranswer "
                    + " WHERE session_id=5) "
                    + "UNION ALL "
                    + "SELECT useranswer_id "
                    + " FROM session2useranswer "
                    + " WHERE session_id=5 "
                    + "AND useranswer_id NOT IN "
                    + "(SELECT a.id AS answer_id "
                    + " FROM session2question s2q, answer a "
                    + " WHERE s2q.question_id = a.question_id "
                    + " AND a.isRight=1 "
                    + " AND session_id=5 "
                    + "))UNION "
                    + "SELECT question_id " 
                    + "FROM again)";
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                category_ids.add(resultSet.getInt("id"));
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

        return category_ids;

    }

}
