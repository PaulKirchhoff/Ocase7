/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocase7.view4;

import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import ocase7.User;

/**
 *
 * @author PaulsBook
 */
public class ResultChart {

    PieChart chart;
    User user;

    public User getUser() {
        return user;
    }

    public PieChart getChart() {
        return chart;
    }

    public StackPane createResultChart(int correctAnswer, int wrongAnswers) {
        StackPane chartPane = new StackPane();
        ObservableList<PieChart.Data> pieChartData = createPieChart(correctAnswer, wrongAnswers);
        Label caption = bindLabelOnPiePeace(pieChartData);
        animatePieChart(pieChartData);
        chartPane.getChildren().addAll(chart, caption);
        return chartPane;
    }

    private ObservableList<PieChart.Data> createPieChart(int correctAnswer, int wrongAnswers) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("richtige Antworten: ", correctAnswer),
                new PieChart.Data("falsche Antworten: ", wrongAnswers));
        chart = new PieChart(pieChartData);
        chart.setLegendVisible(false);
        applyCustomColorSequence(
                pieChartData,
                "lime",
                "orangered"
        );
        return pieChartData;
    }

    private Label bindLabelOnPiePeace(ObservableList<PieChart.Data> pieChartData) {
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKSLATEGREY);
        caption.setStyle("-fx-font: 24 arial;");
        pieChartData.forEach(data
                -> data.nameProperty().bind(
                        Bindings.concat(data.getName(), " ", (int)data.getPieValue())
                )
        );
        return caption;
    }

    private void animatePieChart(ObservableList<PieChart.Data> pieChartData) {
        pieChartData.stream().forEach(pieData -> {
            pieData.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Bounds b1 = pieData.getNode().getBoundsInLocal();
                double newX = (b1.getWidth()) / 5.0 + b1.getMinX();
                double newY = (b1.getHeight()) / 5.0 + b1.getMinY();
                // Make sure pie wedge location is reset
                pieData.getNode().setTranslateX(0);
                pieData.getNode().setTranslateY(0);
                TranslateTransition tt = new TranslateTransition(
                        Duration.millis(500), pieData.getNode());
                pieData.getNode().setEffect(new Glow());
                tt.setByX(newX);
                tt.setByY(newY);
                tt.setAutoReverse(true);
                tt.setCycleCount(2);
                tt.play();
            });
        });
    }

    private void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
    }

}
