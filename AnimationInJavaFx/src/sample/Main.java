package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Main extends Application {
    boolean isAnimation = true;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Figures");
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        Circle circle = new Circle(50, 50, 40);
        circle.setFill(Color.RED);

        Rectangle square = new Rectangle(50, 50, 60, 60);
        square.setFill(Color.YELLOW);

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(30.0, 0.0,
                0.0, 60.0,
                60.0, 60.0);
        triangle.setFill(Color.GREEN);


        FadeTransition circleFd = new FadeTransition(Duration.seconds(0.5), circle);
        circleFd.setAutoReverse(true);
        circleFd.setFromValue(0);
        circleFd.setToValue(1);
        circleFd.setCycleCount(2);

        FadeTransition squareFd = new FadeTransition(Duration.seconds(0.5), square);
        squareFd.setAutoReverse(true);
        squareFd.setFromValue(0);
        squareFd.setToValue(1);
        squareFd.setCycleCount(2);

        FadeTransition triangleFd = new FadeTransition(Duration.seconds(0.5), triangle);
        triangleFd.setAutoReverse(true);
        triangleFd.setFromValue(0);
        triangleFd.setToValue(1);
        triangleFd.setCycleCount(2);

        SequentialTransition st = new SequentialTransition(circleFd, squareFd, triangleFd);
        st.setCycleCount(Timeline.INDEFINITE);
        st.play();

        Button animationControl = new Button("Pause");
        animationControl.setMinWidth(50);
        Button closeApplication = new Button("Exit");

        closeApplication.setOnAction(event -> Platform.exit());

        animationControl.setOnAction(event -> {
            if (isAnimation) {
                st.pause();
                animationControl.setText("Play");
            } else {
                st.play();
                animationControl.setText("Pause");
            }
            isAnimation = !isAnimation;
        });

        root.getChildren().addAll(
                animationControl,
                closeApplication,
                circle,
                square,
                triangle
        );
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
