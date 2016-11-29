package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

    private int id;
    private String text;
    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;
//    static Connection con = null;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Test(int id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Test{" + "id=" + id + ", text=" + text + '}';
    }

    public static Test getById(int id) {
        Test t = null;
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM test WHERE id = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rst = pst.executeQuery();

            // Abfrage allgemein, für unsere Datensätze
            while (rst.next()) {
                t = new Test(rst.getInt("id"), rst.getString("text"));
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
//                if(con != null){
//                    con.close();
//                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        return t;
    }

    public static void delete(int id) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "DELETE FROM test WHERE id = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

}
