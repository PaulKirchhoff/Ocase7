/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view3;

import com.sun.javafx.scene.control.skin.DatePickerContent;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ocase7.Answer;
import ocase7.Card;
import ocase7.CardBox;
import ocase7.Category;
import ocase7.Question;
import ocase7.controller.Controller;

/**
 *
 * @author PaulsBook
 */
public class View3 {

    Controller controller = new Controller();

    VBox answersBox = new VBox();
    VBox questionBox = new VBox();
    CardBox myCardBox = controller.fillCardBoxByCategoryId(1);
    ArrayList<Card> cards = myCardBox.getCards();
    Card card = cards.get(0);

    public Scene createView3() {
        Group view3Root = new Group();
        Scene view3Scene = new Scene(view3Root, Color.DEEPSKYBLUE);

        //System.out.println("##############" + myCardBox);
        //myCard = Card.getCardsByCategory(Category.getCategoryById(1));
        VBox view3ContentBox = new VBox();
        HBox statusBar = createHboxForTop();
        ScrollPane answerAndQuestionScrollPane = new ScrollPane();
        VBox scrollPaneContent = new VBox();
//        VBox questionBox = new VBox();
        //Label questionLabel = new Label(myCardBox.getCards().get(0).getQuestion().getText());
//        VBox answersBox = new VBox();
        HBox checkboxWithAnswerBox = new HBox();

        Question question = card.getQuestion();
        String questionText = question.getText();
        Label questionLabel = new Label(questionText);
        ArrayList<Answer> answers = card.getAnswers();

        for (Answer answer : answers) {
            CheckBox checkbox = new CheckBox();
            String answerText = answer.getText();
            Label answerLabel = new Label(answerText);
            checkboxWithAnswerBox = new HBox(checkbox, answerLabel);
            answersBox.getChildren().add(checkboxWithAnswerBox);
            answersBox.setSpacing(20);
        }

        //fülle Boxen mit ihren Elementen
        questionBox.getChildren().add(questionLabel);
        scrollPaneContent.getChildren().addAll(questionBox, answersBox);
        answerAndQuestionScrollPane.setContent(scrollPaneContent);
        view3ContentBox.getChildren().addAll(statusBar, answerAndQuestionScrollPane);

        //übergebe den gesamten Inhalt an Group
        view3Root.getChildren().add(view3ContentBox);

        return view3Scene;
    }

    private HBox createHboxForTop() {
        HBox statusBar = new HBox();
        statusBar.setSpacing(10);
        statusBar.setMinWidth(600);
        statusBar.setMinHeight(40);
        statusBar.setAlignment(Pos.CENTER);
        statusBar.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: grey;");

        Label lblQuestionNumber = new Label("Question");
        lblQuestionNumber.setFont(Font.font("Arial", 18));

        Label seperateSign = new Label(" / ");
        seperateSign.setFont(Font.font("Arial", 18));

        Label totalNumberOfQuestions = new Label("Totalnumber");
        totalNumberOfQuestions.setFont(Font.font("Arial", 18));

        Button nextQuestionBtn = new Button("Vor");
        nextQuestionBtn.setMinWidth(60);
        nextQuestionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String questionText = "Hallo";
                Question question = card.getQuestion();
                Label questionLabel = new Label(questionText);
                questionBox.getChildren().add(questionLabel);
                
                ArrayList<Answer> answers = card.getAnswers();
                for (Answer answer : answers) {
                    CheckBox checkbox = new CheckBox();
                    String answerText = answer.getText();
                    Label answerLabel = new Label(answerText);
                    HBox checkboxWithAnswerBox = new HBox(checkbox, answerLabel);
                    answersBox.getChildren().add(checkboxWithAnswerBox);
                    answersBox.setSpacing(20);
                }
            }
        });

        Button prevQuestionBtn = new Button("Zurück");

        prevQuestionBtn.setMinWidth(60);

        statusBar.getChildren().addAll(prevQuestionBtn, lblQuestionNumber, seperateSign, totalNumberOfQuestions, nextQuestionBtn);
        return statusBar;
    }
}
