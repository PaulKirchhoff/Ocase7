/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rolfhackel
 */
public class Session2Question {

    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    
    private int session_id;
    private int question_id;

    public Session2Question(int session_id, int question_id) {
        this.session_id = session_id;
        this.question_id = question_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public int getQuestion_id() {
        return question_id;
    }
    
    public static void insert(Session2Question s2q) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO session2question(session_id, question_id) VALUES(?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, s2q.getSession_id());
            pstmt.setInt(2, s2q.getQuestion_id());
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Session2Question.insert klappt nicht.");
        }
    }

}
