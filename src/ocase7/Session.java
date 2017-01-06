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
    private SimpleDateFormat begin = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    private String startTime;
    private CardBox cardBox;

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public SimpleDateFormat getBegin() {
        return begin;
    }

    public CardBox getCardBox() {
        return cardBox;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setBegin(SimpleDateFormat begin) {
        this.begin = begin;
    }

    public void setCardBox(CardBox cardBox) {
        this.cardBox = cardBox;
    }

    public Session(int id, int user_id, CardBox sessionBox) { System.out.println("3 param");
        this.id = id;
        this.user_id = user_id;
    }
    
    public Session() { System.out.println("kein param");
        
    }

    public Session(int user_id) { System.out.println("1 param");
        this.user_id = user_id;
    }
    
    /** 
     * id wird durch MySQl erzeugt
     */
    public static int insert(Session s) {
        int session_id = 0;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO session(begin, user_id) VALUES(?, ?)";
            pstmt = con.prepareStatement(sql, pstmt.RETURN_GENERATED_KEYS);
            pstmt.setString(1, s.getStartTime());
            pstmt.setInt(2, s.getUser_id());
            pstmt.execute();
            resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) {
                session_id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Session.insert klappt nicht.");
        }

        return session_id;
    }

    public String getStartTime() {
        return startTime;
    }

    // id wird aus db ausgelesen und dann im Objekt gesetzt
    public void setId(int id) {
        this.id = id;
    }
}
        
