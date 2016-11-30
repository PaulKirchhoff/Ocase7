package ocase7;

import java.util.ArrayList;

public class Ocase7 {

    public static void main(String[] args) {

        ArrayList<Category> categories = Category.getAll();
        for (Category category : categories) {
            System.out.println(category.getId() + " " + category.getText());
        }

        ArrayList<Category> choosenCategories = new ArrayList<>();
        System.out.println("zeile 15");
        choosenCategories.add(categories.get(0));
        choosenCategories.add(categories.get(1));

        System.out.println("zeile 19");

        CardBox cardbox = new CardBox(choosenCategories);
        System.out.println("hier");

        for (Card card : cardbox.getCards()) {
            System.out.println("zeile 25");
            System.out.println(card.getTextQuestion()+"      "+card.getId());
            for (Answer answer : card.getAnswers()) {
                System.out.println(answer.getText());
            }

            MySQLConnection.closeConnection();
        }

    }
}
