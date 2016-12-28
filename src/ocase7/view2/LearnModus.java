/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author PaulsBook
 */
public class LearnModus extends HBox {
    
    private HBox learnModusBox;
    private Label learnModusLabel;
    private ToggleButton learnModusButton;
    private boolean isRandom;

    public boolean isIsRandom() {
        return isRandom;
    }

    public void setIsRandom(boolean isRandom) {
        this.isRandom = isRandom;
    }
    
    public HBox getLearnModusBox() {
        return learnModusBox;
    }

    public void setLearnModusBox(HBox learnModusBox) {
        this.learnModusBox = learnModusBox;
    }

    public Label getLearnModusLabel() {
        return learnModusLabel;
    }

    public void setLearnModusLabel(Label learnModusLabel) {
        this.learnModusLabel = learnModusLabel;
    }

    public ToggleButton getLearnModusButton() {
        return learnModusButton;
    }

    public void setLearnModusButton(ToggleButton learnModusButton) {
        this.learnModusButton = learnModusButton;
    }

    public LearnModus() {
    }

    
    
    public HBox createLearnModusBox() {

        learnModusBox = new HBox();
        learnModusBox.getStyleClass().add("learnModusBox");
        learnModusBox.setSpacing(10);
        learnModusBox.setMinWidth(700);
        learnModusBox.setAlignment(Pos.CENTER);
        learnModusBox.setPadding(new Insets(30, 0, 30, 0));
        learnModusButton = new ToggleButton("Lern Modus");

        learnModusLabel = new Label("sortierter Modus");
        learnModusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        learnModusLabel.setTextFill(Color.DARKSLATEGRAY);
        learnModusLabel.setPadding(new Insets(2, 0, 0, 0));
        learnModusButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isRandom = false;
                if (learnModusLabel.getText().equals("sortierter Modus")) {
                    learnModusLabel.setText("random Modus");
                    isRandom = true;
                } else {
                    learnModusLabel.setText("sortierter Modus");

                }
            }
        });
        learnModusBox.getChildren().addAll(learnModusButton, learnModusLabel);

        return learnModusBox;
    }
    
    
    
}
