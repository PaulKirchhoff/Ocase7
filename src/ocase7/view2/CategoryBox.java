/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view2;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ocase7.Category;
import ocase7.Question;

/**
 *
 * @author PaulsBook
 */
public class CategoryBox {
    
    // alte lokale Variablen
    private Label categoryBoxLabel;
    private Label categoryLabel;
    private VBox categoriesBox;
    private CheckBox cb;
    
    //alte globale Variablen 
    private ArrayList<Category> categories;
    private final ArrayList<CheckBox> listOfCheckboxes = new ArrayList<>();
    private HBox checkboxWithCategoryLabelBox;
    private int computetNumberOfQuestions = 0;
    private final QuestionSlider slider;
    private final Label maxQuestionsLabel = new Label("0");
    private ArrayList<Integer> questions;

    public ArrayList<Integer> getQuestions() {
        return questions;
    }

    public Label getCategoryBoxLabel() {
        return categoryBoxLabel;
    }

    public Label getCategoryLabel() {
        return categoryLabel;
    }

    public VBox getCategoriesBox() {
        return categoriesBox;
    }

    public CheckBox getCb() {
        return cb;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<CheckBox> getListOfCheckboxes() {
        return listOfCheckboxes;
    }

    public HBox getCheckboxWithCategoryLabelBox() {
        return checkboxWithCategoryLabelBox;
    }


    public int getComputetNumberOfQuestions() {
        return computetNumberOfQuestions;
    }

    public QuestionSlider getSlider() {
        return slider;
    }

    public Label getMaxQuestionsLabel() {
        return maxQuestionsLabel;
    }

    public CategoryBox(QuestionSlider slider) {
        this.slider = slider;
    }
    
    public VBox createCategoryBox() {
        categoriesBox = new VBox();
        categoriesBox.getStyleClass().add("categoryBox");
        categoryBoxLabel = new Label("Kategorien:");
        categoryBoxLabel.setTextFill(Color.DARKSLATEGRAY);
        categoryBoxLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        categoryBoxLabel.setPadding(new Insets(30, 490, 10, 0));
        categoriesBox.setPadding(new Insets(8, 0, 40, 130));
        categoriesBox.getChildren().addAll(categoryBoxLabel);

        // Erstelle CategorieBox mit Checkboxes und Categorien Namen
        categoryLabel = new Label("0");
        categories = Category.getAll();
        //cb = new CheckBox();

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

        // NICHT SICHER OB WIR DIESEN TEIL WIRKLICH BENÖTIGEN 
//        listOfCheckboxes.get(1).setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                //maxQuestionsLabel.setText("" + Question.getAllQuestion_IdsByCategoryId(2).size());
//            }
//        });
        //################## ENDE ############################

//        for (CheckBox checkBox : listOfCheckboxes) {
//            System.out.println(checkBox);
//        }
        categoriesBox.setAlignment(Pos.CENTER);

        return categoriesBox;
    }
    
    
    
    
    
}
