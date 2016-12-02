package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static ocase7.Category.pst;


public class Question {

    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;
    static Connection con = null;

    private int id;
    private String text;

    public Question(int id, String text) {
        this.id = id;

        this.text = text;
    }

    public Question(String text) {
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
        return "Question{" + "id=" + id + ", text=" + text + '}';
    }

//    public static ArrayList<Question>  quest() {
//        ArrayList<Question> quest = new ArrayList<>();
//        try {
//            Connection con = MySQLConnection.getConnection();
//            String sql = "SELECT * FROM question";
//            pst = con.prepareStatement(sql);
//            rst = pst.executeQuery();  //Nur bei Select kommt executeQuery!
//
//            //Abfrage allgemein,für mehrere Datensätze
//            while (rst.next()) {                                                //Fragt die Datensätze nacheinander ab
//                 quest.add(new Question(rst.getInt("id"), rst.getString("text")));  //adde Pro Datensatz ein neues Testobjekt in ArrayList tests
//
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//
//        } finally {
//            try {
//                if (pst != null) {
//                    pst.close();
//                }
//                if (rst != null) {
//                    rst.close();
//                }
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
//        return quest;
//    }
    public static Question  getQuestionById(int id) {
        Connection con = MySQLConnection.getConnection();
        Question q = null;
        try {
            
            String sql = "SELECT * FROM question WHERE id = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rst = pst.executeQuery();  //Nur bei Select kommt executeQuery!

            //Abfrage allgemein,für mehrere Datensätze
            while (rst.next()) {                                                //Fragt die Datensätze nacheinander ab
                 q = (new Question(rst.getInt("id"), rst.getString("text")));  //adde Pro Datensatz ein neues Testobjekt in ArrayList tests

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

        return q;
    }

   
    }


