package chart;

import algorithm.Algorithm;
import algorithm.Brezenhem;
import algorithm.CDA;
import algorithm.Vu;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Point;

public class CustomLineChart extends Application {
    private XYChart.Series xyChart;
    private LineChart linechart;
    private Algorithm currentAlgorithm;
    private Point point = new Point(1, 2);

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(createChartScene());
        stage.setHeight(350);
        stage.setWidth(1250);
        stage.show();
    }

    public Scene createChartScene() {

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x axis");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y axis");

        linechart = new LineChart(xAxis, yAxis);

        xyChart = new XYChart.Series();

        linechart.getData().add(xyChart);

        //Button
        Label lbl = new Label("Counter");
        lbl.setPrefWidth(70);
        Button btn = createButton();

        FlowPane f = createRadioButton();

        // Scene
        VBox vbox = new VBox(linechart, lbl, btn, f);
        return new Scene(vbox, 400, 200);
    }

    public void addPointToDraw(Point point) {
        xyChart.getData().add(new XYChart.Data(point.getXPoint(), point.getYPoint()));
    }

    private Button createButton() {
        Button btn = new Button("Draw");
        btn.setPrefWidth(80);

        btn.setOnAction(event -> {
            addPointToDraw(point);
            point.setXPoint(point.getXPoint() + 1);
            point.setYPoint(point.getYPoint() + 1);
        });
        return btn;
    }

    private FlowPane createRadioButton() {
        RadioButton cdaBtn = new RadioButton("ЦДА алгоритм");
        RadioButton brezenhemBtn = new RadioButton("алгоритм Брезенхема");
        RadioButton vuBtn = new RadioButton("алгоритм ВУ");

        ToggleGroup group = new ToggleGroup();
        // установка группы
        cdaBtn.setToggleGroup(group);
        brezenhemBtn.setToggleGroup(group);
        vuBtn.setToggleGroup(group);

        // установка обработчика события нажатия
        cdaBtn.setOnAction(event -> {
            clearLineChart();
            currentAlgorithm = new CDA();
        });
        brezenhemBtn.setOnAction(event -> {
            clearLineChart();
            //currentAlgorithm = new Brezenhem();
        });
        vuBtn.setOnAction(event -> {
            clearLineChart();
            currentAlgorithm = new Vu();
        });

        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10);
        root.getChildren().addAll(cdaBtn, brezenhemBtn, vuBtn);
        root.setPadding(new Insets(10));
        return root;
    }

    private void clearLineChart() {
        linechart.getData().clear();
        xyChart = new XYChart.Series();
        linechart.getData().add(xyChart);
    }
}
