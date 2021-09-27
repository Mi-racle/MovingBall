package cn.edu.sspu.miracle.movingball;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    public void start(Stage stage) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                MovingBallApplication.class.getResource("moving-ball.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Look at the car!");
        VBox vbox = (VBox) scene.lookup("#vbox");
        //AnchorPane anchorPane = (AnchorPane) scene.lookup("#base");
        Canvas canvas = (Canvas) scene.lookup("#canvas");
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
        //ImageView imageView = new ImageView(image);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        new Thread(
                () -> {
                    int frames = 0;
                    int fps = 600;
                    int periodInSecond = 4; //s
                    //int periodInMillisecond = periodInSecond * 1000; //ms
                    int v = 0;
                    int interval = 1000 / fps;
                    int pmToMovePerFrame = 1200 / (fps * periodInSecond / 2) ;
                    while (frames < fps * 2) {
                        v += pmToMovePerFrame;
                        graphicsContext.drawImage(image, v, 360);
                        try {
                            Thread.sleep(interval);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        graphicsContext.clearRect(v, 360, 100, 100);
                        frames++;
                    }
                    while (frames < fps * 4) {
                        v -= pmToMovePerFrame;
                        graphicsContext.drawImage(image, v, 360);
                        try {
                            Thread.sleep(interval);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        graphicsContext.clearRect(v, 360, 100, 100);
                        frames++;
                    }
                }
        ).start();


        /*Path path=new Path();
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
        pt.play();*/

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}