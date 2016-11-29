/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7;

public class Ocase7 {

    public static void main(String[] args) {
//        Test t = Test.getById(1);
//        t.setText("Kalt heute");
//        Test.update(t);
        
        for (Test t : Test.getAll()) {
            System.out.println(t);
        }
        MySQLConnection.closeConnection();
    }
    
}
