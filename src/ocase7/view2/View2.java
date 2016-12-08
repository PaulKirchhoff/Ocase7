/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view2;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ocase7.Category;
import ocase7.view3.View3;

/**
 *
 * @author PaulsBook
 */
public class View2 {

    ArrayList<Category> categories;
    View3 view3Scene;
    Scene view;

    public Scene createView2Scene() {
        Group view2Root = new Group();
        view = new Scene(view2Root, Color.DEEPSKYBLUE);

        VBox topBar = createTopBar();

        VBox view2ContentBox = new VBox();
        VBox categoriesBox = new VBox();
        Label categoryLabel;

        // Erstelle CategorieBox mit Checkboxes und Categorien Namen
        categories = Category.getAll();
        for (Category category : categories) {
            CheckBox cb = new CheckBox();
            categoryLabel = new Label(category.getText());
            HBox checkboxWithCategoryLabelBox = new HBox(cb, categoryLabel);
            categoriesBox.getChildren().add(checkboxWithCategoryLabelBox);
            categoriesBox.setSpacing(10);
        }
        categoriesBox.setAlignment(Pos.CENTER);

       
        view2ContentBox.getChildren().addAll(topBar, categoriesBox);
        view2Root.getChildren().add(view2ContentBox);
        return view;
    }

    private VBox createTopBar() {
        VBox topBar = new VBox();
        topBar.setMinHeight(50);
        Label view2Label = new Label("Wähle deine Optionen");
        view2Label.setMinWidth(700);
        view2Label.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 1px;");
        view2Label.setFont(new Font(20));
        view2Label.setAlignment(Pos.CENTER);
        topBar.getChildren().add(view2Label);
        return topBar;
    }
}
