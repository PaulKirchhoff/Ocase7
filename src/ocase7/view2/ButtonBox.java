/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ocase7.Card;
import ocase7.CardBox;
import ocase7.Category;
import ocase7.User;
import ocase7.view3.View3;

/**
 *
 * @author PaulsBook
 */
public class ButtonBox extends HBox {
    
    private HBox resetAndStartButtonBox;
    private VBox resetButtonBox;
    private VBox startButtonBox;
    private final WrongAnswersBox wrongAnswersBox; 
    private final User user;
    private final Stage primaryStage;
    private final LearnModus learnModus;
    private QuestionSlider slider;

    
    
    ButtonBox(WrongAnswersBox wrongAnswersBox, User user, Stage primaryStage, LearnModus learnModus, QuestionSlider slider) {
        this.wrongAnswersBox = wrongAnswersBox;
        this.user = user;
        this.primaryStage = primaryStage;
        this.learnModus = learnModus;
        this.slider = slider;
    }

    public QuestionSlider getSlider() {
        return slider;
    }
    
    public HBox getResetAndStartButtonBox() {
        return resetAndStartButtonBox;
    }

    public VBox getResetButtonBox() {
        return resetButtonBox;
    }

    public VBox getStartButtonBox() {
        return startButtonBox;
    }

    public WrongAnswersBox getWrongAnswersBox() {
        return wrongAnswersBox;
    }

    public User getUser() {
        return user;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public LearnModus getLearnModus() {
        return learnModus;
    }

    public ButtonBox(WrongAnswersBox wrongAnswersBox, User user, Stage primaryStage, LearnModus learnModus) {
        this.wrongAnswersBox = wrongAnswersBox;
        this.user = user;
        this.primaryStage = primaryStage;
        this.learnModus = learnModus;
    }
    
    public HBox createButtonBox(CategoryBox categoryBox) {
        resetAndStartButtonBox = new HBox();
        resetAndStartButtonBox.setPadding(new Insets(100, 0, 80, 230));
        resetButtonBox = new VBox();
        resetButtonBox.setPadding(new Insets(0, 30, 0, 0));
        startButtonBox = new VBox();

        Button resetBtn = new Button("zurücksetzen");
        resetBtn.getStyleClass().add("resetBtn");
        resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (CheckBox checkbox : categoryBox.getListOfCheckboxes()) {
                    checkbox.setSelected(false);
                }
                wrongAnswersBox.getWrongAnswerCheckBox().setSelected(false);
            }
        });
        Button startBtn = new Button("Session starten");
        startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //CardBox cardBox;
                int cbCounter = 0;
                ArrayList<Category> cardBoxCategories = new ArrayList<>();
                for (CheckBox listOfCheckboxe : categoryBox.getListOfCheckboxes()) {
                    // prüft ob checkboxes angewählt wurden
                    if (listOfCheckboxe.isSelected()) {
                        Category category = categoryBox.getCategories().get(categoryBox.getListOfCheckboxes().indexOf(listOfCheckboxe));
                        cardBoxCategories.add(category);
                        // jede NICHT angewählte Checkbox erhöht den Counter um 1        
                    } else {
                        cbCounter++;
                    }
                }
                // wenn keine Kategorien ausgewählt wurden 
                // sollen alle Kategorien für die CardBox in eine Liste geschrieben werden 
                if (cbCounter == categoryBox.getListOfCheckboxes().size()) {
                    for (Category category : categoryBox.getCategories()) {
                        cardBoxCategories.add(category);
                    }
                }
                // wenn der Slider einen Wert enthält soll die Anzahl der Fragen 
                // zur cardBox hinzugefügt werden
                int sliderLabelValue = Integer.parseInt(slider.getSliderLabel().getText());

                if (sliderLabelValue > 0) {
                    user.getUserSession().setCardBox(new CardBox(cardBoxCategories, sliderLabelValue));
//                    for (Card card : cardBox.getCards()) {
//                        System.out.println(card);
//                    }
                    // wenn der Slider nicht verwendet wurde sollen alle Fragen 
                    // einer Kategorie zur CardBox hinzugefügt werden
                } else {
                    user.getUserSession().setCardBox(new CardBox(cardBoxCategories));

                }
                if (learnModus.isIsRandom()) {
                    Collections.shuffle(user.getUserSession().getCardBox().getCards());
                    for (Card card : user.getUserSession().getCardBox().getCards()) {
                        System.out.println(card);
                    }
                }
                user.getUserSession().setCardBox(user.getUserSession().getCardBox());
                SimpleDateFormat begin = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                String date = begin.format(new Date());
                System.out.println(date);
                user.getUserSession().setBegin(begin);
                System.out.println(user.getUserSession().getBegin().format(new Date()));
                    View3 v3 = new View3(primaryStage,user);
//                    Scene view3 = v3.createView3();
                    primaryStage.setScene(v3.createView3());
//                    start(stage);
            }

        });
        resetButtonBox.getChildren().add(resetBtn);
        startButtonBox.getChildren().add(startBtn);
        resetAndStartButtonBox.getChildren().addAll(resetButtonBox, startButtonBox);

        return resetAndStartButtonBox;
    }
    
}
