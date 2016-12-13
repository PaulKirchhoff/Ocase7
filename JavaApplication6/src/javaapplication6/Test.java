/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

/**
 *
 * @author Teilnehmer
 */


public class Test {

    public static void main(String[] args) {
        Student1 s1 = new Student1();
        Student1 s2 = new Student1();
        Student1 s3 = new Student1();
        s1 = s3;
        System.out.println(s1.toString()+"s1");
        s3 = s2;
        System.out.println(s3.toString()+"s3"+""+s1.toString()+"s1");
        s2 = null;
        System.out.println(s3.toString()+ "" + s1.toString());
    }
}
