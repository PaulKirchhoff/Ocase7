/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view4;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ocase7.Session;
import ocase7.User;
import ocase7.view2.TopBar;

/**
 *
 * @author PaulsBook
 */
public class View4 {
    
    Stage primaryStage;
    Scene view4;
    User user;
    Session session;

    public View4(Stage primaryStage, User user) {
        this.primaryStage = primaryStage;
        this.user = user;
    }

    public View4() {

    }

    public Scene getView4() {
        return view4;
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public User getUser() {
        return user;
    }

    public Session getSession() {
        return session;
    }
    
    public Scene createView4() {
        Group view4Group = new Group();
        VBox view4ContentBox = new VBox();
        view4ContentBox.getStyleClass().add("view4ContentBox");
        
        TopBar topBar = new TopBar();
        VBox top = topBar.createTopBar("Auswertung");
        
        
        
        ResultChart resultChart = new ResultChart();
        StackPane resultChartPane = resultChart.createResultChart(10, 30);
        
        view4ContentBox.getChildren().addAll(top,resultChartPane);
        view4Group.getChildren().add(view4ContentBox);
        view4 = new Scene(view4Group,Color.DEEPSKYBLUE);
        view4.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());

        return view4; 
    }
    
    
            
    
}
