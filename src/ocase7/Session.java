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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ocase7.Answer.stmt;

/**
 *
 * @author Teilnehmer
 */
public class Session {

    //Verbindungsvariablen 
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;

    private int id;
    private int user_id;
    private String begin;
    // ausgewählte cards aus AL cards
    private CardBox cardBox;
    // alle cards aus db
    private ArrayList<Category> cardsByCategories = new ArrayList<>();

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getBegin() {
        return begin;
    }

    public CardBox getCardBox() {
        return cardBox;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
        // wenn session erstellt wird, sollen gleich alle cards geladen werden
        fillCards();
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public void setCardBox(CardBox cardBox) {
        this.cardBox = cardBox;
    }

    public void fillCards(){
        cardsByCategories = CardBox.fillCardBoxByCategories();
    }
    public Session(int id, int user_id, CardBox sessionBox) {
        this.id = id;
        this.user_id = user_id;
    }

    public Session() {

    }

    public static Session getSession(User user) {
        Session session = new Session();
        session.setUser_id(user.getId());
        session.insert();
        return session;
    }

    public void insert() {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO session(begin, user_id) VALUES(NOW(), ?)";
            pstmt = con.prepareStatement(sql, pstmt.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, user_id);
            pstmt.execute();
            // brauche PK für session
            resultSet = pstmt.getGeneratedKeys(); // gibt nur PK zurück
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            // brauche Uhrzeit/Datum aus db
            String selectSql = "SELECT begin FROM session WHERE id=?";
            pstmt = con.prepareStatement(selectSql);
            pstmt.setInt(1, id);
            pstmt.execute();
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString("begin"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + " Session.insert klappt nicht.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " Session.insert klappt nicht. db down?");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Session(int user_id) {
        this.user_id = user_id;
    }

}
