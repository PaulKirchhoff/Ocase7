package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Question {

    private int id;
    private String text;
    private int category_Id;

    private static PreparedStatement pst = null;
    private static ResultSet rst = null;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getCategory_Id() {
        return category_Id;
    }

    public Question(int id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", text=" + text + '}';
    }

    public static ArrayList<Question> getAllQuestionsByCategoryId(int category_id) {
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<Integer> questions_ids = Question.getAllQuestion_IdsByCategoryId(category_id);

        try {
            Connection con = MySQLConnection.getConnection();
            for (Integer questions_id : questions_ids) {
                String sql = "SELECT * FROM question WHERE id = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, questions_id);
                rst = pst.executeQuery();

                while (rst.next()) {
                    questions.add(new Question(rst.getInt("id"), rst.getString("text")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                if (rst != null) {
                    rst.close();
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

    public static ArrayList<Integer> getAllQuestion_IdsByCategoryId(int category_id) {
        ArrayList<Integer> question_ids = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM category2question WHERE category_id = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, category_id);
            rst = pst.executeQuery();

            while (rst.next()) {
                question_ids.add(rst.getInt("question_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                if (rst != null) {
                    rst.close();
                }
//                if(con != null){
//                    con.close();
//                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return question_ids;
    }

    public static Question getQuestionById(int question_id) {
        Question question = null;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM question WHERE id =?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, question_id);
            rst = pst.executeQuery();

            while (rst.next()) {
                question = new Question(rst.getInt("id"), rst.getString("text"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                if (rst != null) {
                    rst.close();
                }
//                if(con != null){
//                    con.close();
//                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return question;
    }
}
