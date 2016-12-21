package ocase7.view3;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ocase7.CardBox;
import ocase7.Category;

/**
 *
 * @author PaulsBook
 */
public class View3 {

    ocase7.Card myCard;
    CardBox cardBox;
    ArrayList<Category> categories = new ArrayList<>();
    VBox questionBox;
    VBox scrollPaneContent;
    VBox answersBox;
    TextArea questionTextArea;
    //Label questionLabel;
    Label answerLabel;
    HBox checkboxWithAnswerBox;
    Label questionNumberLabel;
    String questionNumber;
    ScrollPane answerAndQuestionScrollPane;
    Stage primaryStage;
    
    public View3(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public View3(Stage primaryStage, CardBox cardBox) {
        this.primaryStage = primaryStage;
        this.cardBox = cardBox;
    }

    private void fillCategories() {
        categories.add(Category.getCategoryById(1));  //<-------------------------------------- GIB EINE KATEGORIE EIN
        cardBox = new CardBox(categories);
        //System.out.println(cardBox.getCards() + "########" + cardBox.getNumberOfCards());

    }

    public Scene createView3() {
        fillCategories();
        Group view3Root = new Group();
        Scene view3Scene = new Scene(view3Root, Color.DEEPSKYBLUE);
        view3Scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());

        myCard = cardBox.getCards().get(0);

        //Erstelle Boxen für Layout        
        VBox view3ContentBox = new VBox();
        view3ContentBox.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 3px;"
                + "-fx-border-color: #2ECCFA;");
        view3ContentBox.setMinWidth(660);
        //view3ContentBox.
        HBox statusBar = createHboxForTop();
        HBox buttonBar = createHBoxForDown();
        answerAndQuestionScrollPane = new ScrollPane();
        answerAndQuestionScrollPane.setMinWidth(660);
        answerAndQuestionScrollPane.setMinHeight(700);
        answerAndQuestionScrollPane.setMaxHeight(700);
        answerAndQuestionScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPaneContent = new VBox();
        scrollPaneContent.setMinHeight(600);
        scrollPaneContent.setMinWidth(658);

        // setze Mindestbreite und den Border für die Fragenbox
        setMinWidthAndStyleOnQuestionBox();

        questionTextArea = new TextArea(myCard.getQuestion().getText());
        questionTextArea.setMinHeight(500);

        questionTextArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                questionTextArea.setEditable(false);
                questionTextArea.setWrapText(true);
            }
        });
        //questionLabel.setPrefWidth(700); 

        //fülle Boxen mit ihren Elementen
        questionBox.getChildren().add(questionTextArea);
        questionBox.setFillWidth(true);
        scrollPaneContent.getChildren().addAll(questionBox, createAnswerBox());
        answerAndQuestionScrollPane.setContent(scrollPaneContent);
        view3ContentBox.getChildren().addAll(statusBar, answerAndQuestionScrollPane, buttonBar);

        //übergebe den gesamten Inhalt an Group
        view3Root.getChildren().add(view3ContentBox);

        return view3Scene;
    }

    private void setMinWidthAndStyleOnQuestionBox() {
        questionBox = new VBox();
        questionBox.setMinWidth(600);
        questionBox.setStyle("-fx-border-style: solid;" + "-fx-border-width: 1;");
    }

    private HBox createHboxForTop() {
        HBox statusBar = new HBox();
        statusBar.setSpacing(10);
        statusBar.setMinWidth(600);
        statusBar.setMinHeight(40);
        statusBar.setAlignment(Pos.CENTER);
        statusBar.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: grey;");
        //questionNumber = "1";
        questionNumberLabel = new Label("1");
        questionNumberLabel.setFont(Font.font("Arial", 18));

        Label seperateSign = new Label(" / ");
        seperateSign.setFont(Font.font("Arial", 18));

        String totalNumOfQuestions = "" + cardBox.getCards().size();
        Label totalNumberOfQuestions = new Label(totalNumOfQuestions);
        totalNumberOfQuestions.setFont(Font.font("Arial", 18));

        Button nextQuestionBtn = new Button("Vor");
        nextQuestionBtn.setMinWidth(60);

        nextQuestionBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // lösche Elemente aus Boxen -> clear() löscht alle Elemente
                // aus den Boxen, mit remove(element,element) kann man gezielt 
                // Elemente aus den Boxen löschen
                questionBox.getChildren().clear();
                answersBox.getChildren().clear();
                myCard = cardBox.nextCard(cardBox.getCards().indexOf(myCard));
                questionNumberLabel.setText("" + (cardBox.getCards().indexOf(myCard) + 1));

                // setze den neuen Text in das Label 
                questionTextArea.setText(myCard.getQuestion().getText());
                // füge Neues Label wieder zur questionBox hinzu
                questionBox.getChildren().add(questionTextArea);
                scrollPaneContent.getChildren().add(createAnswerBox());
            }
        });

        Button prevQuestionBtn = new Button("Zurück");
        prevQuestionBtn.setMinWidth(60);
        prevQuestionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // lösche Elemente aus Boxen -> clear() löscht alle Elemente
                // aus den Boxen, mit remove(element,element) kann man gezielt 
                // Elemente aus den Boxen löschen
                questionBox.getChildren().clear();
                answersBox.getChildren().clear();
                //System.out.println(cardBox.prevCard(cardBox.getCards().indexOf(myCard)));
                myCard = cardBox.prevCard(cardBox.getCards().indexOf(myCard));
                questionNumberLabel.setText("" + (cardBox.getCards().indexOf(myCard) + 1));

                // setze den neuen Text in das Label 
                questionTextArea.setText(myCard.getQuestion().getText());
                // füge Neues Label wieder zur questionBox hinzu
                questionBox.getChildren().add(questionTextArea);
                scrollPaneContent.getChildren().add(createAnswerBox());
            }
        });

        statusBar.getChildren().addAll(prevQuestionBtn, questionNumberLabel, seperateSign, totalNumberOfQuestions, nextQuestionBtn);
        return statusBar;
    }

    private HBox createHBoxForDown() {

        HBox buttonBar = new HBox();

        buttonBar.setSpacing(100);
        buttonBar.setMinWidth(600);
        buttonBar.setMinHeight(40);
        buttonBar.setAlignment(Pos.CENTER);
        buttonBar.getStyleClass().add("buttonBar");
        Button followUp = new Button("Wiedervorlage");
        followUp.setMinWidth(100);

        followUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (myCard.isFollowUp() == false) {
                    myCard.setFollowUp(true);
                    System.out.println(myCard.isFollowUp());
                } else {
                    myCard.setFollowUp(false);
                    System.out.println(myCard.isFollowUp());
                }
            }
        });

        Button cheater = new Button("Cheater-Knopf");
        cheater.setMinWidth(100);
        if (myCard.isCheated() == false) {

            cheater.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    myCard.setCheated(true);
                    scrollPaneContent.getChildren().remove(answersBox);
                    VBox anserbow = isRightAnswersBox();
                    anserbow.setDisable(true);
                    scrollPaneContent.getChildren().add(anserbow);

                }
            });
        } else {
            cheater.getStyleClass().add("cheaterbtn");
        }

        Button save = new Button("Session fertig");
        save.setMinWidth(100);

        buttonBar.getChildren().addAll(followUp, cheater, save);

        return buttonBar;
    }

    private VBox createAnswerBox() {

        answersBox = new VBox();
        checkboxWithAnswerBox = new HBox();
        checkboxWithAnswerBox.setAlignment(Pos.CENTER);
        if (myCard.isCheated() == false) {
            for (int i = 0; i < myCard.getUserAnswers().size(); i++) {
                CheckBox cb = new CheckBox();
                int m = i;
                if (myCard.getUserAnswers().get(i).isGiven() == true) {
                    cb.setSelected(true);
                }
                cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue == true) {
                            myCard.getUserAnswers().get(m).setGiven(true);
                            System.out.println(myCard.getUserAnswers().get(m).isGiven());

                        }
                        if (oldValue == true && newValue == false) {
                            myCard.getUserAnswers().get(m).setGiven(false);
                            System.out.println(myCard.getUserAnswers().get(m).isGiven());
                        }

                    }
                });

                answerLabel = new Label(myCard.getUserAnswers().get(i).getText());
                checkboxWithAnswerBox = new HBox(cb, answerLabel);
                answersBox.getChildren().add(checkboxWithAnswerBox);
                answersBox.setSpacing(20);

            }
        } else {
            answersBox = isRightAnswersBox();
            answersBox.setDisable(true);
        }

        return answersBox;
    }

    private VBox isRightAnswersBox() {
        answersBox = new VBox();
        checkboxWithAnswerBox = new HBox();
        checkboxWithAnswerBox.setAlignment(Pos.CENTER);
        for (int i = 0; i < myCard.getUserAnswers().size(); i++) {
            CheckBox cb = new CheckBox();
            int m = i;
            if (myCard.getUserAnswers().get(m).isGiven() == true) {
                cb.setSelected(true);
            }
            if (myCard.getUserAnswers().get(m).isIsRight() == true) {
                answerLabel = new Label(myCard.getUserAnswers().get(m).getText());
                answerLabel.setTextFill(Color.GREEN);
            } else {
                answerLabel = new Label(myCard.getUserAnswers().get(m).getText());
            }
            checkboxWithAnswerBox = new HBox(cb, answerLabel);
            answersBox.getChildren().add(checkboxWithAnswerBox);
            answersBox.setSpacing(20);

        }
        return answersBox;
    }
}
