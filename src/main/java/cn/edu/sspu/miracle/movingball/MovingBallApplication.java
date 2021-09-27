package cn.edu.sspu.miracle.movingball;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class MovingBallApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                MovingBallApplication.class.getResource("moving-ball.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Look at the car!");
        VBox vbox = (VBox) scene.lookup("#vbox");
        AnchorPane anchorPane = (AnchorPane) scene.lookup("#base");
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(Objects.requireNonNull(
                        MovingBallApplication.class.getResource("focus/BGROUND.BMP")
                ).toString()),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        Background background = new Background(backgroundImage);
        vbox.setBackground(background);

        Image image = new Image(
                Objects.requireNonNull(
                        MovingBallApplication.class.getResource("focus/FOCUS.BMP")
                ).toString()
        );
        ImageView imageView = new ImageView(image);
        anchorPane.getChildren().add(imageView);

        Path path=new Path();
        path.getElements().add(new MoveTo(0, 360));
        path.getElements().add(new LineTo(1280, 360));
        //path.getElements().add(new MoveTo(1280, 360));
        path.getElements().add(new LineTo(0, 360));

        PathTransition pt=new PathTransition();
        pt.setDuration(Duration.millis(4000));//设置持续时间4秒
        pt.setPath(path);//设置路径
        pt.setNode(imageView);//设置物体
        pt.setCycleCount(Timeline.INDEFINITE);//设置周期性，无线循环
        pt.setAutoReverse(true);
        pt.play();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}