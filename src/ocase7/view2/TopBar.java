/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author PaulsBook
 */
public class TopBar extends VBox {
    
    private VBox topBar;
    private Label view2Label;

    public VBox getTopBar() {
        return topBar;
    }

    public void setTopBar(VBox topBar) {
        this.topBar = topBar;
    }

    public Label getView2Label() {
        return view2Label;
    }

    public void setView2Label(Label view2Label) {
        this.view2Label = view2Label;
    }
    
    public VBox createTopBar(String topBarText) {
        topBar = new VBox();
        view2Label = new Label(topBarText);
        view2Label.setTextFill(Color.DARKSLATEGRAY);
        view2Label.setMinHeight(60);
        view2Label.setPadding(new Insets(5, 0, 5, 0));
        view2Label.setMinWidth(710);
//        view2Label.setStyle("-fx-border-style: solid;"
//                + "-fx-border-width: 1px;"
//                + "-fx-border-color: lightgrey;");
        view2Label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        view2Label.setAlignment(Pos.CENTER);
        topBar.getChildren().add(view2Label);

        return topBar;
    }
}
