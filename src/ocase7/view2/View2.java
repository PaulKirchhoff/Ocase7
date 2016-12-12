/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view2;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ocase7.Category;
import ocase7.Question;
import ocase7.view3.View3;

/**
 *
 * @author PaulsBook
 */
public class View2 {

    ArrayList<Category> categories;
    View3 view3Scene;
    Scene view;
    ArrayList<Integer> questions;
    int computetNumberOfQuestions = 0;
    Label maxQuestionsLabel = new Label("0");
    HBox checkboxWithCategoryLabelBox;
    ArrayList<CheckBox> listOfCheckboxes = new ArrayList<>();
    Slider numOfQuestionsSlider;

    public Scene createView2Scene() {
        Group view2Root = new Group();
        view = new Scene(view2Root, Color.DEEPSKYBLUE);

        VBox view2ContentBox = new VBox();
        view2ContentBox.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 3px;"
                + "-fx-border-color: #2ECCFA;");
        // erstellt Top Bar mit Überschrift
        VBox topBar = createTopBar();
        //erstellt categoryBox mit Checkboxes und Labeln
        VBox categoriesBox = createCategoryBox();
        // erstellt lernModus ToggleButton mit wechselndem Label
        HBox learnModusBox = learnModus();
        // erstellt Auswahl für Fragenanzahl mit Slider
        VBox numbersOfQuestions = createNumberOfQuestionsBox();

        HBox resetAndStartButtonBox = createButtonBox();

        view2ContentBox.setMaxWidth(700);
        view2ContentBox.getChildren().addAll(topBar, categoriesBox, numbersOfQuestions, learnModusBox, resetAndStartButtonBox);
        view2Root.getChildren().add(view2ContentBox);

        return view;
    }

    private VBox createCategoryBox() {
        VBox categoriesBox = new VBox();
        categoriesBox.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 2 0 2 0;"
                + "-fx-border-color: #2ECCFA;");
        Label categoryBoxLabel = new Label("Kategorien:");
        categoryBoxLabel.setTextFill(Color.DARKSLATEGRAY);
        categoryBoxLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        categoryBoxLabel.setPadding(new Insets(30, 490, 10, 0));
        categoriesBox.setPadding(new Insets(8, 0, 40, 130));
        categoriesBox.getChildren().addAll(categoryBoxLabel);

        // Erstelle CategorieBox mit Checkboxes und Categorien Namen
        Label categoryLabel;
        categories = Category.getAll();
        CheckBox cb;

        for (Category category : categories) {
            cb = new CheckBox();
            listOfCheckboxes.add(cb);
            questions = Question.getAllQuestion_IdsByCategoryId(category.getId());
//            for (Integer question : questions) {
//                System.out.println("cat" + category.getId() + " " + question);
//            }
//            System.out.println("##########################");
//            System.out.println("##########################");
//            System.out.println("##########################");
            categoryLabel = new Label(category.getText());
            checkboxWithCategoryLabelBox = new HBox(cb, categoryLabel);
            categoriesBox.getChildren().add(checkboxWithCategoryLabelBox);
            categoriesBox.setSpacing(10);

        }
        for (CheckBox checkbox : listOfCheckboxes) {
            checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue) {
                        checkbox.setSelected(true);
                        computetNumberOfQuestions = computetNumberOfQuestions + (Question.getAllQuestionsByCategoryId(listOfCheckboxes.indexOf(checkbox)+1).size());
                        maxQuestionsLabel.setText("" + (computetNumberOfQuestions));
                        numOfQuestionsSlider.setMax(computetNumberOfQuestions);
                    } else {
                        computetNumberOfQuestions = computetNumberOfQuestions - (Question.getAllQuestionsByCategoryId(listOfCheckboxes.indexOf(checkbox)+1).size());
                        maxQuestionsLabel.setText("" + (computetNumberOfQuestions));
                        numOfQuestionsSlider.setMax(computetNumberOfQuestions);
                    }
                }
            });
        }

//        listOfCheckboxes.get(1).setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                maxQuestionsLabel.setText("" + Question.getAllQuestion_IdsByCategoryId(2).size());
////                    
//            }
//        });
//        for (CheckBox checkBox : listOfCheckboxes) {
//            System.out.println(checkBox);
//        }
        categoriesBox.setAlignment(Pos.CENTER);

        return categoriesBox;
    }

    private HBox learnModus() {

        HBox learnModusBox = new HBox();
        learnModusBox.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 2 0 2 0;"
                + "-fx-border-color: #2ECCFA;");
        learnModusBox.setSpacing(10);
        learnModusBox.setMinWidth(700);
        learnModusBox.setAlignment(Pos.CENTER);
        learnModusBox.setPadding(new Insets(30, 0, 30, 0));
        ToggleButton learnModusButton = new ToggleButton("Lern Modus");
//        learnModusButton.setMinWidth(80);
//        learnModusButton.setMinHeight(80);
//        learnModusButton.setShape(new Circle(40));
        //learnModusButton.setMaxSize(250, 250);
        Label learnModusLabel = new Label("sortierter Modus");
        learnModusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        learnModusLabel.setTextFill(Color.DARKSLATEGRAY);
        learnModusLabel.setPadding(new Insets(2, 0, 0, 0));
        learnModusButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (learnModusLabel.getText().equals("sortierter Modus")) {
                    learnModusLabel.setText("random Modus");
                } else {
                    learnModusLabel.setText("sortierter Modus");
                }
            }
        });
        learnModusBox.getChildren().addAll(learnModusButton, learnModusLabel);

        return learnModusBox;
    }

    private VBox createTopBar() {
        VBox topBar = new VBox();
        Label view2Label = new Label("Wähle deine Optionen");
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

    private VBox createNumberOfQuestionsBox() {
        VBox numOfQuestions = new VBox();
        numOfQuestions.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 0 0 0 0;"
                + "-fx-border-color: #2ECCFA;");
        Label numOfQuestionsLabel = new Label("Fragenanzahl:");
        numOfQuestionsLabel.setPadding(new Insets(0, 0, 20, 0));
        numOfQuestionsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        //numOfQuestionsLabel.set
        numOfQuestionsLabel.setTextFill(Color.DARKSLATEGRAY);
        HBox numOfQuestionBoxContent = new HBox();
        numOfQuestionsSlider = new Slider(0, computetNumberOfQuestions, computetNumberOfQuestions / 2);
        //numOfQuestionsSlider.setShowTickLabels(true);
        numOfQuestionsSlider.setShowTickMarks(true);
        numOfQuestionsSlider.setMinWidth(400);
        numOfQuestionsSlider.setMinorTickCount(10);
        
        Label minQuestionsLabel = new Label("0");
        minQuestionsLabel.setTextFill(Color.DARKSLATEGRAY);
        minQuestionsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        maxQuestionsLabel.setText("0");
        maxQuestionsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        maxQuestionsLabel.setTextFill(Color.DARKSLATEGRAY);
        numOfQuestionBoxContent.getChildren().addAll(minQuestionsLabel, numOfQuestionsSlider, maxQuestionsLabel);
        numOfQuestions.getChildren().addAll(numOfQuestionsLabel, numOfQuestionBoxContent);
        numOfQuestions.setPadding(new Insets(40, 0, 60, 130));

        return numOfQuestions;
    }

    private HBox createButtonBox() {
        HBox resetAndStartButtonBox = new HBox();
        resetAndStartButtonBox.setPadding(new Insets(100, 0, 80, 230));
        VBox resetButtonBox = new VBox();
        resetButtonBox.setPadding(new Insets(0, 30, 0, 0));
        VBox startButtonBox = new VBox();

        Button resetBtn = new Button("zurücksetzen");
        Button startBtn = new Button("Session starten");
        resetButtonBox.getChildren().add(resetBtn);
        startButtonBox.getChildren().add(startBtn);
        resetAndStartButtonBox.getChildren().addAll(resetButtonBox, startButtonBox);

        return resetAndStartButtonBox;
    }

}
