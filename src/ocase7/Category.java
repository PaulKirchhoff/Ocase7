package ocase7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Category {

    private int id;
    private String text;

    private static Statement st = null;
    private static ResultSet rst = null;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Category(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public static ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM category";
            st = con.prepareStatement(sql);
            rst = st.executeQuery(sql);

            while (rst.next()) {
                categories.add(new Category(rst.getInt("id"), rst.getString("text")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            try {
                if (st != null) {
                    st.close();
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

        return categories;
    }
    
}
