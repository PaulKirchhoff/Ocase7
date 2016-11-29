package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Test {
//Verbindungsvariablen

    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;
    static Connection con = null;

    private int id;
    private String text;

    public Test(int id, String text) {
        this.id = id;

        this.text = text;
    }

    public Test(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
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
            rst = pst.executeQuery();  //Nur bei Select kommt executeQuery!

            //Afrage allgemein,für mehrere Datensätze
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
                if (con != null) {
                    con.close();
                }
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
            pst.executeUpdate();        //Bei nicht SELECT kommt executeUpdate!

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void update(Test t) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "UPDATE test SET text = ? WHERE id = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, t.getText());
            pst.setInt(2, t.getId());
            pst.executeUpdate();        //Bei nicht SELECT kommt executeUpdate!

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void insert(Test t) {
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO test VALUES (null, ?)";
            if (t.getId() == 0) {
                // 1 Objekt wird gespeichtert,der duch MySQL 
                // erstellte PK soll zurückgegeben werden
                pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, t.getText()); //Die 1 bedeutet das erste "?" des INSERT Statments
                pst.executeUpdate();        //Bei nicht SELECT kommt executeUpdate!
                rst = pst.getGeneratedKeys();
                while (rst.next()) {
                    t.id = rst.getInt(1); //DIe 1 bedeutet erste Spalte des Tables
                }
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

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static ArrayList<Test> getAll() {        //Keine Parameter übergabe weil wir alles aus "test" wollen
        ArrayList<Test> tests = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM test";
            pst = con.prepareStatement(sql);
            rst = pst.executeQuery();  //Nur bei Select kommt executeQuery!

            //Afrage allgemein,für mehrere Datensätze
            while (rst.next()) {                                                //Fragt die Datensätze nacheinander ab
                tests.add(new Test(rst.getInt("id"), rst.getString("text")));  //adde Pro Datensatz ein neues Testobjekt in ArrayList tests

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
        return tests;
    }
}
