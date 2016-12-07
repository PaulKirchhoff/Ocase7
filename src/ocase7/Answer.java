package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Answer {

    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;
    static Connection con = null;

    private int id;
    private int question_id;
    private String text;
    private int isRight;

    public Answer(int id, String text, int question_id, int isRight) {
        this.id = id;
        this.question_id = question_id;
        this.text = text;
        this.isRight = isRight;
    }

    public int getIsRight() {
        return isRight;
    }
    
    public Answer(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Answer"+"\n"+ "id=" + id +"\n" + "text=" + text +"\n" + "isRight=" + isRight + "\n\n";
    }

    public static ArrayList<Answer> answ() {
        ArrayList<Answer> answ = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer";
            pst = con.prepareStatement(sql);
            rst = pst.executeQuery();  //Nur bei Select kommt executeQuery!

            //Abfrage allgemein,für mehrere Datensätze
            while (rst.next()) {                                                //Fragt die Datensätze nacheinander ab
                answ.add(new Answer(rst.getInt("id"), rst.getString("text"), rst.getInt("question_id"), rst.getInt("isRight")));  //adde Pro Datensatz ein neues Testobjekt in ArrayList tests

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

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return answ;
    }

    public static ArrayList<Answer> getAnswersToQuestion(Question q) {
        ArrayList<Answer> answers = new ArrayList<>();
        Connection con = MySQLConnection.getConnection();
        try {

            String sql = "SELECT * FROM answer WHERE question_id = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, q.getId());
            rst = pst.executeQuery();  //Nur bei Select kommt executeQuery!

            //Abfrage allgemein,für mehrere Datensätze
            while (rst.next()) {                                                //Fragt die Datensätze nacheinander ab
                answers.add(new Answer(rst.getInt("id"), rst.getString("text"), rst.getInt("question_id"), rst.getInt("isRight")));  //adde Pro Datensatz ein neues Testobjekt in ArrayList tests

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

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return answers;
    }

}
