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
 * @author Teilnehmer
 */
public class Again {

    static Statement stmt = null;
    static PreparedStatement pstmt = null;

    private int session_id;
    private int question_id;
    // c oder a
    private String cheatedOrAgain;

    public String getCheatedOrAgain() {
        return cheatedOrAgain;
    }

    public int getSession_id() {
        return session_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public Again(String cheatedOrAgain, int session_id, int question_id) {
        this.cheatedOrAgain = cheatedOrAgain;
        this.session_id = session_id;
        this.question_id = question_id;
    }

    public static void insert(Again again) {

        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO again(cheatedOrAgain, session_id, question_id) "
                    + " VALUES(?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, again.getCheatedOrAgain());
            pstmt.setInt(2, again.getSession_id());
            pstmt.setInt(3, again.getQuestion_id());
            pstmt.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "again.insert klappt nicht");
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "again.insert klappt nicht db down");

        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
