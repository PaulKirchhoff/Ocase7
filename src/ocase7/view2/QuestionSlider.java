/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view2;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


/**
 *
 * @author PaulsBook
 */

public class QuestionSlider extends Slider {
    
    private Slider slider;
    private Label sliderLabel;

    public Slider getSlider() {
        return slider;
    }

    public void setSlider(Slider slider) {
        this.slider = slider;
    }

    public Label getSliderLabel() {
        return sliderLabel;
    }

    public void setSliderLabel(Label sliderLabel) {
        this.sliderLabel = sliderLabel;
    }

    public QuestionSlider(Label sliderLabel) {
        this.slider = new Slider();
        this.sliderLabel = sliderLabel;
    }

    public QuestionSlider() {
        slider = new Slider();
        sliderLabel = new Label();
    }
    
    
    
    public StackPane createSlider(QuestionSlider slider) {
        //slider = new Slider();
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
        //sliderLabel = new Label();

        sliderLabel.textProperty().bind(slider.valueProperty().asString("%.0f"));
        thumb.getChildren().addAll(sliderLabel);

        return sliderRoot;
        
    }
    
    

    
    
}
