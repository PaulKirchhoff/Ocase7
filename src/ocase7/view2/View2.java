/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view2;

import java.util.ArrayList;
import javafx.application.Application;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ocase7.Category;
import ocase7.Question;
import ocase7.mainView;
import ocase7.view3.View3;

/**
 *
 * @author PaulsBook
 */
public class View2 extends mainView {

    ArrayList<Category> categories;
    View3 view3Scene;
    Scene view;
    ArrayList<Integer> questions;
    int computetNumberOfQuestions = 0;
    Label maxQuestionsLabel = new Label("0");
    HBox checkboxWithCategoryLabelBox;
    ArrayList<CheckBox> listOfCheckboxes = new ArrayList<>();
    Slider numOfQuestionsSlider;
    Slider slider;

    public Scene createView2Scene() {
        Group view2Root = new Group();
        
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
//        VBox numbersOfQuestions = createNumberOfQuestionsBox();
        StackPane sp = createSlider();
        HBox resetAndStartButtonBox = createButtonBox();
        view2ContentBox.setMaxWidth(700);
        view2ContentBox.getChildren().addAll(topBar, categoriesBox,sp, learnModusBox, resetAndStartButtonBox);
        view2Root.getChildren().addAll(view2ContentBox);
        view = new Scene(view2Root, Color.DEEPSKYBLUE);
        view.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
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
                        slider.setMax(computetNumberOfQuestions);
                    } else {
                        computetNumberOfQuestions = computetNumberOfQuestions - (Question.getAllQuestionsByCategoryId(listOfCheckboxes.indexOf(checkbox)+1).size());
                        maxQuestionsLabel.setText("" + (computetNumberOfQuestions));
                        slider.setMax(computetNumberOfQuestions);
                    }
                }
            });
        }

        listOfCheckboxes.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                maxQuestionsLabel.setText("" + Question.getAllQuestion_IdsByCategoryId(2).size());
//                    
            }
        });
        for (CheckBox checkBox : listOfCheckboxes) {
            System.out.println(checkBox);
        }
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

    public StackPane createSlider() {
        
        slider = new Slider();
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(10);
        slider.setMinorTickCount(5);
        slider.getStyleClass().add("slider");
        StackPane sliderRoot = new StackPane(slider);
        //sliderRoot.setMaxWidth(400);
        sliderRoot.getStyleClass().add("sliderRoot");
        // scene ?????
        Scene scene = new Scene(sliderRoot);
        slider.applyCss();
        slider.layout();
        Pane thumb = (Pane) slider.lookup(".thumb");
        Label label = new Label();
        label.textProperty().bind(slider.valueProperty().asString("%.0f"));
        
        thumb.getChildren().addAll(label);
        
        return sliderRoot;

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
