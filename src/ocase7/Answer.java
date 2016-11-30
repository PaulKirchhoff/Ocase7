package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Answer {

    private int id;
    private String text;
    private int isRight;
    private int question_id;

    private static PreparedStatement pst = null;
    private static ResultSet rst = null;

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIsRight(int isRight) {
        this.isRight = isRight;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getIsRight() {
        return isRight;
    }

    public int getQuestion_id() {
        return question_id;
    }
    

    public Answer(int id, String text, int isRight, int question_id) {
        this.id = id;
        this.text = text;
        this.isRight = isRight;
        this.question_id = question_id;
    }

    @Override
    public String toString() {
        return "Answer{" + "id=" + id + ", text=" + text + ", isRight=" + isRight + ", question_id=" + question_id + '}';
    }

    public static ArrayList<Answer> getAllAnswerByQuestionIds(ArrayList<Question> questions_ids) {
        ArrayList<Answer> answers = new ArrayList<>();
        ArrayList<Question> question_ids = questions_ids;

        try {
            Connection con = MySQLConnection.getConnection();
            for (Question questions_id : questions_ids) {
                String sql = "SELECT * FROM answer WHERE question_id = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, questions_id.getId());
                rst = pst.executeQuery();

                while (rst.next()) {
                    answers.add(new Answer(rst.getInt("id"), rst.getString("text"), rst.getInt("isRight"), rst.getInt("question_id")));
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

        return answers;
    }

    public static ArrayList<Answer> getAllAnswerByQuestionId(int question_id) {
        ArrayList<Answer> answers = new ArrayList<>();

        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer WHERE question_id = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, question_id);
            rst = pst.executeQuery();

            while (rst.next()) {
                answers.add(new Answer(rst.getInt("id"), rst.getString("text"), rst.getInt("isRight"), rst.getInt("question_id")));
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

        return answers;
    }

}
