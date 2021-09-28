package cn.edu.sspu.miracle.movingball;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MovingBallApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                MovingBallApplication.class.getResource("moving-ball.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        stage.setTitle("Look at the ball!");
        VBox vbox = (VBox) scene.lookup("#vbox");
        //AnchorPane anchorPane = (AnchorPane) scene.lookup("#base");
        Canvas canvasTop = (Canvas) scene.lookup("#canvasTop");
        Canvas canvasMid = (Canvas) scene.lookup("#canvasMid");
        Canvas canvasBottom = (Canvas) scene.lookup("#canvasBottom");
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
        Image imageWave = new Image(
                Objects.requireNonNull(
                        MovingBallApplication.class.getResource("focus/GELIXIAN.BMP")
                ).toString()
        );
        int positionY = 208 / 2 - 16;
        GraphicsContext graphicsContextTop = canvasTop.getGraphicsContext2D();
        GraphicsContext graphicsContextMid = canvasMid.getGraphicsContext2D();
        GraphicsContext graphicsContextBottom = canvasBottom.getGraphicsContext2D();

        /*new Thread(
                () -> {
                    int frames = 0;
                    int fps = 200;
                    int periodInSecond = 2;
                    int v1 = 0;
                    int interval = 1000 / fps;
                    int pmToMovePerFrame = 200 / (fps * periodInSecond / 2);
                    int frameCelling = fps * periodInSecond / 2;
                    int frameFloor = 0;
                    while (true) {
                        while (frames < frameCelling) {
                            v1 += pmToMovePerFrame;
                            graphicsContextTop.drawImage(imageWave, 0, v1);
                            try {
                                Thread.sleep(interval);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //graphicsContextTop.clearRect(0, v1, 1024, 1);
                            frames++;
                        }
                        while (frames > frameFloor) {
                            v1 -= pmToMovePerFrame;
                            graphicsContextTop.drawImage(imageWave, 0, v1);
                            try {
                                Thread.sleep(interval);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //graphicsContextTop.clearRect(0, v1, 1024, 1);
                            frames--;
                        }
                    }
                }
        ).start();*/

        new Thread(
                () -> {
                    int frames = 0;
                    int fps = 100;
                    int periodInSecond = 4; //s
                    //int periodInMillisecond = periodInSecond * 1000; //ms
                    int v = 0;
                    int v1 = 0;
                    int interval = 1000 / fps;
                    int pmToMovePerFrame = 1024 / (fps * periodInSecond / 2) ;
                    int pmToMovePerFrame1 = 200 / (fps * periodInSecond / 2);
                    int frameCelling = fps * periodInSecond / 2;
                    int frameFloor = 0;
                    while (true) {
                        while (frames < frameCelling) {
                            v += pmToMovePerFrame;
                            v1 += pmToMovePerFrame1;
                            graphicsContextTop.drawImage(imageWave, 0, v1);
                            graphicsContextMid.drawImage(image, v, positionY);
                            graphicsContextBottom.drawImage(imageWave, 0, v1);
                            try {
                                Thread.sleep(interval);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            graphicsContextMid.clearRect(v, positionY, pmToMovePerFrame, 32);
                            frames++;
                        }
                        while (frames > frameFloor) {
                            v -= pmToMovePerFrame;
                            v1 -= pmToMovePerFrame1;
                            graphicsContextTop.drawImage(imageWave, 0, v1);
                            graphicsContextMid.drawImage(image, v, positionY);
                            graphicsContextBottom.drawImage(imageWave, 0, v1);
                            try {
                                Thread.sleep(interval);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            graphicsContextMid.clearRect(v, positionY, pmToMovePerFrame, 32);
                            frames--;
                        }
                    }
                }
        ).start();
        /*new Thread(
                () -> {
                    int frames = 0;
                    int fps = 200;
                    int periodSecond = 2;
                    int v1 = 0;
                    int interval = 1000 / fps;
                    int pmToMovePerFrame = 200 / (fps * periodSecond / 2);
                    int frameCelling = fps * periodSecond / 2;
                    int frameFloor = 0;
                    while (true) {
                        while (frames < frameCelling) {
                            v1 += pmToMovePerFrame;
                            graphicsContextBottom.drawImage(imageWave, 0, v1);
                            try {
                                Thread.sleep(interval);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //graphicsContextTop.clearRect(0, v1, 1024, 1);
                            frames++;
                        }
                        while (frames > frameFloor) {
                            v1 -= pmToMovePerFrame;
                            graphicsContextBottom.drawImage(imageWave, 0, v1);
                            try {
                                Thread.sleep(interval);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //graphicsContextTop.clearRect(0, v1, 1024, 1);
                            frames--;
                        }
                    }
                }
        ).start();*/

        stage.setOnCloseRequest(
                windowEvent -> System.exit(0)
        );
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}