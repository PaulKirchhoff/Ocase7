/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.loginView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author PaulsBook
 */
public class LoginView {
    
    public Scene creatLoginView() {
        GridPane grid = new GridPane();
        grid.getStyleClass().add("loginView");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(-150, 5, 5, 5));

        Text scenetitle = new Text("WIllkommen beim Ocase7 Training");
        scenetitle.getStyleClass().add("sceneTitle");
        scenetitle.setId("welcome-text");
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Username:");
        userName.getStyleClass().add("userNameLabel");
        grid.add(userName, 0, 12);

        TextField userTextField = new TextField();
        userTextField.getStyleClass().add("userTextfield");
        grid.add(userTextField, 1, 12);

        Label pw = new Label("Password:");
        pw.getStyleClass().add("pwLabel");
        grid.add(pw, 0, 16);

        PasswordField pwTextfield = new PasswordField();
        pwTextfield.getStyleClass().add("pwTextfield");
        grid.add(pwTextfield, 1, 16);

        Button btn = new Button("Sign in");
        btn.getStyleClass().add("loginButton");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 20);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 20);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setId("actiontarget");
                actiontarget.setText("Sign in button pressed");
            }
        });

        Scene loginScene = new Scene(grid,Color.DEEPSKYBLUE);
        loginScene.getStylesheets().add
        (LoginView.class.getResource("/style/style.css").toExternalForm());
        
        return loginScene;
        
    }
    
    
}
