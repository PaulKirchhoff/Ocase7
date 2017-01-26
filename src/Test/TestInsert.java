/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import java.util.ArrayList;
import ocase7.*;
/**
 *
 * @author Admin
 */
public class TestInsert {
    public static void main(String[] args) {
        //Session2UserAnswer.insert(new Session2UserAnswer(3, 3));
        //Session2Question.insert(new Session2Question(3, 3));
        //Again.insert(new Again("c", 3, 3));
        
        Category c1 = Category.getCategoryById(1);
        c1.fillCards();
        Category c2 = Category.getCategoryById(2);
        c2.fillCards();
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(c1);
        categories.add(c2);  
        ArrayList<Card> cards = CardBox.fillCardBox(categories);

        System.out.println(cards.size());

    }
}
