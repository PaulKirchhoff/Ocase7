/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import ocase7.Card;

/**
 *
 * @author Teilnehmer
 */
public class AnswerBox extends VBox {

    private HBox checkboxWithAnswerBox;
    private Card myCard;
    private Label answerLabel;

    public Label getAnswerLabel() {
        return answerLabel;
    }

    public HBox getCheckboxWithAnswerBox() {
        return checkboxWithAnswerBox;
    }

    public Card getMyCard() {
        return myCard;
    }

    public AnswerBox(Card myCard) {
        super();
        this.myCard = myCard;
        this.checkboxWithAnswerBox = new HBox();
        this.checkboxWithAnswerBox.setAlignment(Pos.CENTER);
        if (myCard.isCheated() == false) {
            for (int i = 0; i < myCard.getUserAnswers().size(); i++) {
                CheckBox cb = new CheckBox();
                int m = i;
                if (myCard.getUserAnswers().get(i).isGiven() == true) {
                    cb.setSelected(true);
                }
                cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue == true) {
                            myCard.getUserAnswers().get(m).setGiven(true);
                            System.out.println(myCard.getUserAnswers().get(m).isGiven());

                        }
                        if (oldValue == true && newValue == false) {
                            myCard.getUserAnswers().get(m).setGiven(false);
                            System.out.println(myCard.getUserAnswers().get(m).isGiven());
                        }

                    }
                });

                this.answerLabel = new Label(myCard.getUserAnswers().get(i).getText());
                checkboxWithAnswerBox = new HBox(cb, this.answerLabel);
                this.getChildren().add(checkboxWithAnswerBox);
                this.setSpacing(20);

            }
        } else {
            this.isRightAnswersBox(myCard);
            this.setDisable(true);
        }

    }

    private VBox isRightAnswersBox(Card myCard) {
        VBox isRightAnswerBox = new VBox();
        this.checkboxWithAnswerBox = new HBox();
        this.checkboxWithAnswerBox.setAlignment(Pos.CENTER);
        for (int i = 0; i < myCard.getUserAnswers().size(); i++) {
            CheckBox cb = new CheckBox();
            int m = i;
            if (myCard.getUserAnswers().get(m).isGiven() == true) {
                cb.setSelected(true);
            }
            if (myCard.getUserAnswers().get(m).isIsRight() == true) {
                this.answerLabel = new Label(myCard.getUserAnswers().get(m).getText());
                this.answerLabel.setTextFill(Color.GREEN);
            } else {
                this.answerLabel = new Label(myCard.getUserAnswers().get(m).getText());
            }
            this.checkboxWithAnswerBox = new HBox(cb, this.answerLabel);

            this.getChildren().add(checkboxWithAnswerBox);
            this.setSpacing(20);

        }
        return isRightAnswerBox;
    }
}
