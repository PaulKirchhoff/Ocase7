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

/**
 *
 * @author Teilnehmer
 */
public class Category {

    private int id = 1;
    private String text = "";
    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;
//    static Connection con = null;

    public Category(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public static Category getCategoryById(int id) {
        Connection con = MySQLConnection.getConnection();
        Category c = null;
        try {

            String sql = "SELECT * FROM category WHERE id = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rst = pst.executeQuery();  //Nur bei Select kommt executeQuery!

            //Afrage allgemein,für mehrere Datensätze
            while (rst.next()) {
                c = new Category(rst.getInt("id"), rst.getString("text"));

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
        return c;

    }

   
}
