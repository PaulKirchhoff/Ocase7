/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import com.mysql.jdbc.exceptions.MySQLDataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rolfhackel
 */
public class Session2Useranswer {

    static Statement stmt = null;
    static PreparedStatement pstmt = null;

    private int session_id;
    private int answer_id;

    public Session2Useranswer(int session_id, int answer_id) {
        this.session_id = session_id;
        this.answer_id = answer_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public static void insert(Session2Useranswer s2u) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO session2useranswer(session_id, userAnswer_id) VALUES(?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, s2u.getSession_id());
            pstmt.setInt(2, s2u.getAnswer_id());
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Session2Useranswer.insert klappt nicht.");
        }
    }

    
}
