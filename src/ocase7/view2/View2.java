/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view2;

import java.util.ArrayList;
import java.util.Collections;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ocase7.Card;
import ocase7.CardBox;
import ocase7.Category;
import ocase7.Question;
import ocase7.User;
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
    CheckBox wrongAnswerCheckBox;
    Label sliderLabel;
    ToggleButton learnModusButton;
    boolean isRandom;
    CardBox cardBox;
    boolean isLastSessions;
    Stage primaryStage;
    User user;

    public View2(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene createView2Scene(User user) {
        this.user = user;
        Group view2Root = new Group();
        
        VBox view2ContentBox = new VBox();
        view2ContentBox.getStyleClass().add("view2ContentBox");

        // erstellt Top Bar mit Überschrift
        VBox topBar = createTopBar();

        //erstellt categoryBox mit Checkboxes und Labeln
        VBox categoriesBox = createCategoryBox();

        // erstelle 
        VBox wrongAnswerBox = createChooseWrongAnswersBox();

        // erstellt lernModus ToggleButton mit wechselndem Label
        HBox learnModusBox = learnModus();

        // erstellt Auswahl für Fragenanzahl mit Slider
        StackPane sp = createSlider();
        HBox resetAndStartButtonBox = createButtonBox();
        view2ContentBox.setMaxWidth(700);
        view2ContentBox.getChildren().addAll(topBar, categoriesBox, sp, wrongAnswerBox, learnModusBox, resetAndStartButtonBox);
        view2Root.getChildren().addAll(view2ContentBox);
        view = new Scene(view2Root, Color.DEEPSKYBLUE);
        view.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
        return view;
    }

    private VBox createCategoryBox() {
        VBox categoriesBox = new VBox();
        categoriesBox.getStyleClass().add("categoryBox");
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
//        for (CheckBox checkbox : listOfCheckboxes) {
        for (int i = 0; i < listOfCheckboxes.size(); i++) {
            int j = i;
            listOfCheckboxes.get(i).selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    if (newValue) {
                        listOfCheckboxes.get(j).setSelected(true);
                        computetNumberOfQuestions = computetNumberOfQuestions + (Question.getAllQuestionsByCategoryId(listOfCheckboxes.indexOf(listOfCheckboxes.get(j)) + 1).size());
                        slider.setVisible(true);
                        slider.setMax(computetNumberOfQuestions);

                    } else {
                        computetNumberOfQuestions = computetNumberOfQuestions - (Question.getAllQuestionsByCategoryId(listOfCheckboxes.indexOf(listOfCheckboxes.get(j)) + 1).size());
                        slider.setMax(computetNumberOfQuestions);
                        if (computetNumberOfQuestions == 0) {
                            slider.setVisible(false);
                        }
                    }
                }
            });
        }

        listOfCheckboxes.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                maxQuestionsLabel.setText("" + Question.getAllQuestion_IdsByCategoryId(2).size());
            }
        });
//        for (CheckBox checkBox : listOfCheckboxes) {
//            System.out.println(checkBox);
//        }
        categoriesBox.setAlignment(Pos.CENTER);

        return categoriesBox;
    }

    private HBox learnModus() {

        HBox learnModusBox = new HBox();
        learnModusBox.getStyleClass().add("learnModusBox");
        learnModusBox.setSpacing(10);
        learnModusBox.setMinWidth(700);
        learnModusBox.setAlignment(Pos.CENTER);
        learnModusBox.setPadding(new Insets(30, 0, 30, 0));
        learnModusButton = new ToggleButton("Lern Modus");

        Label learnModusLabel = new Label("sortierter Modus");
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
        slider.setVisible(false);
        StackPane sliderRoot = new StackPane(slider);
        //sliderRoot.setMaxWidth(400);
        sliderRoot.getStyleClass().add("sliderRoot");
        // scene ?????
        Scene scene = new Scene(sliderRoot);
        slider.applyCss();
        slider.layout();
        Pane thumb = (Pane) slider.lookup(".thumb");
        thumb.setEffect(new DropShadow(8, 4, 4, Color.GRAY));
        sliderLabel = new Label();

        sliderLabel.textProperty().bind(slider.valueProperty().asString("%.0f"));
        thumb.getChildren().addAll(sliderLabel);

        return sliderRoot;

    }

    private VBox createChooseWrongAnswersBox() {
        VBox wrongAnswersBox = new VBox();
        wrongAnswersBox.getStyleClass().add("wrongAnswersBox");
        HBox checkBoxWithLabelBox = new HBox();
        wrongAnswerCheckBox = new CheckBox();
        wrongAnswerCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    wrongAnswerCheckBox.setSelected(true);
                    CardBox cardBoxWithWrongAnswers = new CardBox(categories);
                }
            }
        });
        Label wrongAnswerLabel = new Label("Die falschen Anworten der letzten zwei Sessions.");
        checkBoxWithLabelBox.getChildren().addAll(wrongAnswerCheckBox, wrongAnswerLabel);
        wrongAnswersBox.getChildren().addAll(checkBoxWithLabelBox);
        return wrongAnswersBox;
    }

    private HBox createButtonBox() {
        HBox resetAndStartButtonBox = new HBox();
        resetAndStartButtonBox.setPadding(new Insets(100, 0, 80, 230));
        VBox resetButtonBox = new VBox();
        resetButtonBox.setPadding(new Insets(0, 30, 0, 0));
        VBox startButtonBox = new VBox();

        Button resetBtn = new Button("zurücksetzen");
        resetBtn.getStyleClass().add("resetBtn");
        resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (CheckBox checkbox : listOfCheckboxes) {
                    checkbox.setSelected(false);
                }
                wrongAnswerCheckBox.setSelected(false);
            }
        });
        Button startBtn = new Button("Session starten");
        startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //CardBox cardBox;
                int cbCounter = 0;
                ArrayList<Category> cardBoxCategories = new ArrayList<>();
                for (CheckBox listOfCheckboxe : listOfCheckboxes) {
                    // prüft ob checkboxes angewählt wurden
                    if (listOfCheckboxe.isSelected()) {
                        Category category = categories.get(listOfCheckboxes.indexOf(listOfCheckboxe));
                        cardBoxCategories.add(category);
                        // jede NICHT angewählte Checkbox erhöht den Counter um 1        
                    } else {
                        cbCounter++;
                    }
                }
                // wenn keine Kategorien ausgewählt wurden 
                // sollen alle Kategorien für die CardBox in eine Liste geschrieben werden 
                if (cbCounter == listOfCheckboxes.size()) {
                    for (Category category : categories) {
                        cardBoxCategories.add(category);
                    }
                }
                // wenn der Slider einen Wert enthält soll die Anzahl der Fragen 
                // zur cardBox hinzugefügt werden
                int sliderLabelValue = Integer.parseInt(sliderLabel.getText());

                if (sliderLabelValue > 0) {
                    cardBox = new CardBox(cardBoxCategories, sliderLabelValue);
//                    for (Card card : cardBox.getCards()) {
//                        System.out.println(card);
//                    }
                    // wennn der Slider nicht verwendet wurde sollen alle Fragen 
                    // einer Kategorie zur CardBox hinzugefügt werden
                } else {
                    cardBox = new CardBox(cardBoxCategories);

                }
                if (isRandom) {
                    Collections.shuffle(cardBox.getCards());
                    for (Card card : cardBox.getCards()) {
                        System.out.println(card);
                    }
                }
                user.getUserSession().setCardBox(cardBox);
                    
                    View3 v3 = new View3(primaryStage,user);
//                    Scene view3 = v3.createView3();
                    primaryStage.setScene(v3.createView3(user));
//                    start(stage);
            }

        });
        resetButtonBox.getChildren().add(resetBtn);
        startButtonBox.getChildren().add(startBtn);
        resetAndStartButtonBox.getChildren().addAll(resetButtonBox, startButtonBox);

        return resetAndStartButtonBox;
    }
}
