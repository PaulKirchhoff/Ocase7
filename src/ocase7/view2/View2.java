/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view2;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ocase7.CardBox;
import ocase7.User;
import ocase7.mainView;

/**
 *
 * @author PaulsBook
 */
public class View2 extends mainView {
    
    private Scene view;
    //private ArrayList<Integer> questions;
    private final Stage primaryStage;
    private User user;

    public View2(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public View2(Stage primaryStage, User user) {
        this.primaryStage = primaryStage;
        this.user = user;
    }

    public Scene createView2Scene() {
        
        Group view2Root = new Group();
        
        VBox view2ContentBox = new VBox();
        view2ContentBox.getStyleClass().add("view2ContentBox");

        TopBar topBar = new TopBar();
        VBox topBarBox = topBar.createTopBar("Wähle deine Optionen");
        
        WrongAnswersBox wrongAnswersBox = new WrongAnswersBox();
        VBox chooseWrongAnswerBox = wrongAnswersBox.createChooseWrongAnswersBox();
        
        LearnModus learnModus = new LearnModus();
        HBox learnModusBox = learnModus.createLearnModusBox();

        QuestionSlider slider = new QuestionSlider();
        StackPane sp = slider.createSlider(slider);
        
        CategoryBox categoryBox = new CategoryBox(slider);
        VBox categoriesBox = categoryBox.createCategoryBox();
        
        ButtonBox buttonBox = new ButtonBox(wrongAnswersBox, user, primaryStage, learnModus, slider);
        HBox resetAndStartButtonBox = buttonBox.createButtonBox(categoryBox);

        view2ContentBox.setMaxWidth(700);
        view2ContentBox.getChildren().addAll(topBarBox, categoriesBox, sp, chooseWrongAnswerBox, learnModusBox, resetAndStartButtonBox);
        view2Root.getChildren().addAll(view2ContentBox);
        view = new Scene(view2Root, Color.DEEPSKYBLUE);
        view.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
        return view;
    }
}
