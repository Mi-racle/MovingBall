package cn.edu.sspu.miracle.movingball;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class MovingBallApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovingBallApplication.class.getResource("moving-ball.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 640);
        stage.setTitle("Look at the ball!");
        AnchorPane base = (AnchorPane) scene.lookup("#base");

        // Circle circle = (Circle) scene.lookup("#circle");
        Image image = new Image(
                Objects.requireNonNull(
                        MovingBallApplication.class.getResource("FOCUS.BMP")
                ).toString()
        );
        ImageView imageView = new ImageView(image);
        base.getChildren().add(imageView);

        Path path=new Path();
        path.getElements().add(new MoveTo(0, 320));
        path.getElements().add(new LineTo(840, 320));
        path.getElements().add(new MoveTo(840, 320));
        path.getElements().add(new LineTo(0, 320));

        PathTransition pt=new PathTransition();
        pt.setDuration(Duration.millis(4000));//设置持续时间4秒
        pt.setPath(path);//设置路径
        pt.setNode(imageView);//设置物体
        pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        //设置周期性，无线循环
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        pt.play();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}