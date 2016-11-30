package ocase7;

import java.util.ArrayList;

public class CardBox {

//    private ArrayList<Card> cards= new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards() {
        return cards;
    }

    public CardBox(ArrayList<Category> categories) {
        this.cards = fillCardBox(categories);
    }

    public ArrayList<Card> fillCardBox(ArrayList<Category> categories) {
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<Card> cardsi = new ArrayList<>();
        for (Category category : categories) {
            for (Question question : Question.getAllQuestionsByCategoryId(category.getId())) {
                if (!questions.contains(question)) {
                    questions.add(question);
                }
            }
        }
        for (Question question : questions) {
            cardsi.add(new Card(question.getId()));
        }

        return cardsi;
    }

}
