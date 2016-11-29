package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Answers {

    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;
    static Connection con = null;

    private int id;
    private int question_id;
    private String text;

    public Answers(int id, String text, int question_id, int isRight) {
        this.id = id;
        this.question_id = question_id;
        this.text = text;
    }

    public Answers(String text) {
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
        return "Answers{" + "id=" + id + ", text=" + text + ",question_id=" + question_id + '}';
    }

    public static ArrayList<Answers> answ() {
        ArrayList<Answers> answ = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM answer";
            pst = con.prepareStatement(sql);
            rst = pst.executeQuery();  //Nur bei Select kommt executeQuery!

            //Abfrage allgemein,für mehrere Datensätze
            while (rst.next()) {                                                //Fragt die Datensätze nacheinander ab
                answ.add(new Answers(rst.getInt("id"), rst.getString("text"), rst.getInt("question_id"), rst.getInt("isRight")));  //adde Pro Datensatz ein neues Testobjekt in ArrayList tests

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
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return answ;
    }

}
