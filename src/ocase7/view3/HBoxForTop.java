/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view3;

import ocase7.CardBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import ocase7.Card;

/**
 *
 * @author Teilnehmer
 */
public class HBoxForTop extends HBox{

    private Label questionNumberLabel = new Label();
    private Label seperateSign = new Label();
    private Label totalNumberOfQuestions = new Label();
    private Button nextQuestionBtn = new Button();
    private Button prevQuestionBtn = new Button();
    private Font font = new Font("Arial", 18);
    private Card myCard;

    public Card getMyCard() {
        return myCard;
    }

    public Font getFont() {
        return font;
    }

    public Label getQuestionNumberLabel() {
        return questionNumberLabel;
    }

    public Label getSeperateSign() {
        return seperateSign;
    }

    public Label getTotalNumberOfQuestions() {
        return totalNumberOfQuestions;
    }

    public Button getNextQuestionBtn() {
        return nextQuestionBtn;
    }

    public Button getPrevQuestionBtn() {
        return prevQuestionBtn;
    }

    public HBoxForTop(CardBox cardBox, Card myCard, VBox questionBox, AnswerBox answersBox,VBox scrollPaneContent) {  
        super();
        this.setSpacing(10);
        this.setMinWidth(600);
        this.setMinHeight(40);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: grey;");
        this.myCard = myCard;
        this.questionNumberLabel.setText("1");
        this.questionNumberLabel.setFont(getFont());
        this.seperateSign.setText(" / ");
        this.seperateSign.setFont(getFont());
        this.totalNumberOfQuestions.setText("" + cardBox.getCards().size());
        this.totalNumberOfQuestions.setFont(getFont());
        this.nextQuestionBtn.setText("Vor");
        this.nextQuestionBtn.setMinWidth(60);
        this.nextQuestionBtn.setOnAction(nextQuestionBtnOnAction(cardBox, questionBox, answersBox, scrollPaneContent));
        this.prevQuestionBtn.setText("Zurück");
        this.prevQuestionBtn.setMinWidth(60);
        this.prevQuestionBtn.setOnAction(prevQuestionBtnOnAction(cardBox, questionBox, answersBox,scrollPaneContent));
        this.getChildren().addAll(prevQuestionBtn, questionNumberLabel, seperateSign, totalNumberOfQuestions, nextQuestionBtn);
    }

    public EventHandler<ActionEvent> nextQuestionBtnOnAction(CardBox cardBox, VBox questionBox, AnswerBox answersBox,VBox scrollPaneContent) {
        EventHandler event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // lösche Elemente aus Boxen -> clear() löscht alle Elemente
                // aus den Boxen, mit remove(element,element) kann man gezielt 
                // Elemente aus den Boxen löschen
                questionBox.getChildren().clear();
                answersBox.getChildren().clear();
                myCard = cardBox.nextCard(cardBox.getCards().indexOf(myCard));
                questionNumberLabel.setText("" + (cardBox.getCards().indexOf(myCard) + 1));

                // setze den neuen Text in das Label 
                TextArea questionTextArea = new TextArea();
                questionTextArea.setText(myCard.getQuestion().getText());
                // füge Neues Label wieder zur questionBox hinzu
                questionBox.getChildren().add(questionTextArea);
                scrollPaneContent.getChildren().add(new AnswerBox(getMyCard()));
            }
        };
        return event;
    }

    public EventHandler<ActionEvent> prevQuestionBtnOnAction(CardBox cardBox, VBox questionBox, AnswerBox answersBox,VBox scrollPaneContent) {
        EventHandler event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // lösche Elemente aus Boxen -> clear() löscht alle Elemente
                // aus den Boxen, mit remove(element,element) kann man gezielt 
                // Elemente aus den Boxen löschen
                questionBox.getChildren().clear();
                answersBox.getChildren().clear();
                //System.out.println(cardBox.prevCard(cardBox.getCards().indexOf(myCard)));
                myCard = cardBox.prevCard(cardBox.getCards().indexOf(myCard));
                questionNumberLabel.setText("" + (cardBox.getCards().indexOf(myCard) + 1));

                // setze den neuen Text in das Label 
                TextArea questionTextArea = new TextArea();
                questionTextArea.setText(myCard.getQuestion().getText());
                // füge Neues Label wieder zur questionBox hinzu
                questionBox.getChildren().add(questionTextArea);
                scrollPaneContent.getChildren().add(new AnswerBox(myCard));
            }
        };
        return event;

    }

//    statusBar.getChildren ()
//    .addAll(prevQuestionBtn, questionNumberLabel, seperateSign, totalNumberOfQuestions, nextQuestionBtn);
//    return statusBar ;
}
