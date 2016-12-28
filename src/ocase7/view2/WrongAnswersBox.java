/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view2;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ocase7.CardBox;
import ocase7.Category;

/**
 *
 * @author PaulsBook
 */
public class WrongAnswersBox extends VBox {
    
    private VBox wrongAnswersBox;
    private HBox checkBoxWithLabelBox;
    private CheckBox wrongAnswerCheckBox;
    private Label wrongAnserLabel; 
    
    public VBox getWrongAnswersBox() {
        return wrongAnswersBox;
    }

    public void setWrongAnswersBox(VBox wrongAnswersBox) {
        this.wrongAnswersBox = wrongAnswersBox;
    }

    public HBox getCheckBoxWithLabelBox() {
        return checkBoxWithLabelBox;
    }

    public void setCheckBoxWithLabelBox(HBox checkBoxWithLabelBox) {
        this.checkBoxWithLabelBox = checkBoxWithLabelBox;
    }

    public CheckBox getWrongAnswerCheckBox() {
        return wrongAnswerCheckBox;
    }

    public void setWrongAnswerCheckBox(CheckBox wrongAnswerCheckBox) {
        this.wrongAnswerCheckBox = wrongAnswerCheckBox;
    }

    public Label getWrongAnserLabel() {
        return wrongAnserLabel;
    }

    public void setWrongAnserLabel(Label wrongAnserLabel) {
        this.wrongAnserLabel = wrongAnserLabel;
    }

    public VBox createChooseWrongAnswersBox() {
        wrongAnswersBox = new VBox();
        wrongAnswersBox.getStyleClass().add("wrongAnswersBox");
        checkBoxWithLabelBox = new HBox();
        wrongAnswerCheckBox = new CheckBox();
        wrongAnswerCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    wrongAnswerCheckBox.setSelected(true);
                    //CardBox cardBoxWithWrongAnswers = new CardBox(categories);
                }
            }
        });
        Label wrongAnswerLabel = new Label("Die falschen Anworten der letzten zwei Sessions.");
        checkBoxWithLabelBox.getChildren().addAll(wrongAnswerCheckBox, wrongAnswerLabel);
        wrongAnswersBox.getChildren().addAll(checkBoxWithLabelBox);
        return wrongAnswersBox;
    }
    
    
}
