/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Teilnehmer
 */
public class WrongQuestion extends Question {

    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;

    private boolean cheated;
    private boolean followUp;

    private ArrayList<WrongQuestion> wrongQuestions = new ArrayList<>();
    private ArrayList<Category> wrongQuestionsCategories = new ArrayList<>();

    public boolean isCheated() {
        return cheated;
    }

    public boolean isFollowUp() {
        return followUp;
    }

    public ArrayList<WrongQuestion> getWrongQuestion() {
        return wrongQuestions;
    }

    public ArrayList<Category> getWrongQuestionsCategories() {
        return wrongQuestionsCategories;
    }

    public WrongQuestion(int id, String text, int category_id, int inactive) {
        super(id, text, category_id, inactive);      
    }

    public static ArrayList<Integer> getAll2LastSessionIds(User user) {
        ArrayList<Integer> session_Ids = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT session_id FROM v_last_two_user_sessions "
                    + "WHERE user_id = ?";
            stmt = con.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                session_Ids.add(resultSet.getInt("session_id"));
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

        return session_Ids;
    }

    public static ArrayList<Integer> getAllWrongQuestion_Ids(User user) {
        ArrayList<Integer> session_Ids = getAll2LastSessionIds(user);
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
                    + "AND session_id=? "
                    + "AND a.id NOT IN "
                    + "(SELECT useranswer_id "
                    + " FROM session2useranswer "
                    + " WHERE session_id=?) "
                    + "UNION ALL "
                    + "SELECT useranswer_id "
                    + " FROM session2useranswer "
                    + " WHERE session_id=? "
                    + "AND useranswer_id NOT IN "
                    + "(SELECT a.id AS answer_id "
                    + " FROM session2question s2q, answer a "
                    + " WHERE s2q.question_id = a.question_id "
                    + " AND a.isRight=1 "
                    + " AND session_id=? "
                    + ")) "
                    + "UNION "
                    + "SELECT question_id "
                    + "FROM again";
            stmt = con.prepareStatement(sql);
            pstmt.setInt(1, session_Ids.get(1));
            pstmt.setInt(2, session_Ids.get(1));
            pstmt.setInt(3, session_Ids.get(0));
            pstmt.setInt(4, session_Ids.get(0));
            resultSet = pstmt.executeQuery();
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

    public static ArrayList<Integer> getAllWrongQuestionCategory_Ids(User user) {
        ArrayList<Integer> session_Ids = getAll2LastSessionIds(user);
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
                    + "AND session_id=? "
                    + "AND a.id NOT IN "
                    + "(SELECT useranswer_id "
                    + " FROM session2useranswer "
                    + " WHERE session_id=?) "
                    + "UNION ALL "
                    + "SELECT useranswer_id "
                    + " FROM session2useranswer "
                    + " WHERE session_id=? "
                    + "AND useranswer_id NOT IN "
                    + "(SELECT a.id AS answer_id "
                    + " FROM session2question s2q, answer a "
                    + " WHERE s2q.question_id = a.question_id "
                    + " AND a.isRight=1 "
                    + " AND session_id=? "
                    + "))UNION "
                    + "SELECT question_id "
                    + "FROM again)";
            stmt = con.prepareStatement(sql);
            pstmt.setInt(1, session_Ids.get(1));
            pstmt.setInt(2, session_Ids.get(1));
            pstmt.setInt(3, session_Ids.get(0));
            pstmt.setInt(4, session_Ids.get(0));
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

    public static ArrayList<WrongQuestion> getAllWrongQuestionByCategoryId(int categoryId, User user) {
        ArrayList<WrongQuestion> wrongQuestions = new ArrayList<>();
        ArrayList<Integer> wrongQuestion_Ids = WrongQuestion.getAllWrongQuestion_Ids(user);
        ArrayList<Integer> wrongQuestionCategorie_Ids = WrongQuestion.getAllWrongQuestionCategory_Ids(user);
        try {
            Connection con = MySQLConnection.getConnection();
            for (Integer wrongQuestions_id : wrongQuestion_Ids) {
                String sql = "SELECT * FROM question WHERE id = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, wrongQuestions_id);
                resultSet = pstmt.executeQuery();

                while (resultSet.next()) {
                    if (wrongQuestionCategorie_Ids.contains(categoryId)) {
                        wrongQuestions.add(new WrongQuestion(resultSet.getInt("id"), resultSet.getString("text"),resultSet.getInt("category_id"), resultSet.getInt("inactive")));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
//                if(con != null){
//                    con.close();
//                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return questions;
    }

}
