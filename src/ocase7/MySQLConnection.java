package ocase7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private static final String URL = "jdbc:mysql://192.168.2.15:3306/ocase7";
    private static final String USER = "Petra";
    private static final String PASSWORD = "Panke";

    private static Connection con = null;

    static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return con;
    }

    public static void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
