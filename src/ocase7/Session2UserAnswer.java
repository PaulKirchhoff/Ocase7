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
import java.util.logging.Level;
import java.util.logging.Logger;
import static ocase7.Session.pstmt;

/**
 *
 * @author Admin
 */
public class Session2UserAnswer {
    
           //Verbindungsvariablen 
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;
    
    private int session_id;
    private int userAnswer_id;

    public Session2UserAnswer(int session_id, int userAnswer_id) {
        this.session_id = session_id;
        this.userAnswer_id = userAnswer_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public int getUserAnswer_id() {
        return userAnswer_id;
    }
    
    public static void insert(Session2UserAnswer session2UserAnswer){
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO session2useranswer(session_id, userAnswer_id)"
                    + " VALUES(?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, session2UserAnswer.getSession_id());
            pstmt.setInt(2, session2UserAnswer.getUserAnswer_id());
            pstmt.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + " Session2UserAnswer.insert klappt nicht.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " Session2UserAnswer.insert klappt nicht. db down?");
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
    
}
