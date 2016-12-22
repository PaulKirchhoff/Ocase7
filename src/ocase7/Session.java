/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Teilnehmer
 */
public class Session {

    //Verbindungsvariablen 
    static Statement stmt = null;
    static PreparedStatement pstmt = null;
    static ResultSet resultSet = null;

    private int id;
    private int user_id;
    private SimpleDateFormat begin = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    private CardBox cardBox;

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public SimpleDateFormat getBegin() {
        return begin;
    }

    public CardBox getCardBox() {
        return cardBox;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setBegin(SimpleDateFormat begin) {
        this.begin = begin;
    }

    public void setCardBox(CardBox cardBox) {
        this.cardBox = cardBox;
    }

    public Session(int id, int user_id, CardBox sessionBox) {
        this.id = id;
        this.user_id = user_id;
    }
    
    
    
    public Session() {

    }

    public Session(int user_id) {
        this.user_id = user_id;
    }
    
    
    
    
    
    

}
        
