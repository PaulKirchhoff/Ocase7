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
public class Again {

    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    
    private int id;
    // für db: "c" oder "a"
    private String cheatedOrAgain;
    private int session_id;
    private int question_id;

    public Again(String cheatedOrAgain, int session_id, int question_id) {
        this.cheatedOrAgain = cheatedOrAgain;
        this.session_id = session_id;
        this.question_id = question_id;
    }

    public int getId() {
        return id;
    }

    public String getCheatedOrAgain() {
        return cheatedOrAgain;
    }

    public int getSession_id() {
        return session_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public static void insert(Again a) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO again(checkedOrAgain, session_id, question_id) VALUES(?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, a.getCheatedOrAgain());
            pstmt.setInt(2, a.getSession_id());
            pstmt.setInt(3, a.getQuestion_id());
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Again.insert klappt nicht.");
        }
    }
}
