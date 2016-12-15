/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static ocase7.Category.resultSet;
import static ocase7.Question.pstmt;

/**
 *
 * @author Teilnehmer
 */
public class User {

    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;

    private int id;
    private String name;
    private String password;
    private Session userSession;

    public Session getUserSession() {
        return userSession;
    }

    public User(int id, String name, String password, Session userSesseion) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userSession = userSesseion;
    }

    public User(int userId) {
        this.id = userId;
        this.name = "Test";
        this.password = "password";
        this.userSession = new Session();
    }

    public User() {
        this(1);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
// Athor LYN & Eric
    public void insertUserAnswerIdIntoDb(User u) {

        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO userAnswer (user_id, answer_id) VALUES( ?, ?)";
            for (int i = 0; i < u.getUserSession().getSessionBox().getCards().size(); i++) {
                for (int c = 0; c < u.getUserSession().getSessionBox().getCards().get(i).getUserAnswers().size(); c++) {
                    pstmt = con.prepareStatement(sql);
                    pstmt.setInt(1, u.getId());
                    if (u.getUserSession().getSessionBox().getCards().get(i).getUserAnswers().get(i).isGiven()) {
                        pstmt.setInt(2, u.getUserSession().getSessionBox().getCards().get(i).getUserAnswers().get(c).getId());
                    }
                    
                    stmt = con.createStatement();
                    resultSet = stmt.executeQuery(sql);
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();

                }
                if (resultSet != null) {
                    resultSet.close();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return;

    }

    public static ArrayList<User> getAll() {
        ArrayList<User> user = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM user";
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                user.add(new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("password")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();

                }
                if (resultSet != null) {
                    resultSet.close();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return user;
    }

    public User getUserById(int id) {
        User user = new User();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM user WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        return user;

    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", password=" + password + '}';
    }

}
