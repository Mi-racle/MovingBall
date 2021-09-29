package cn.edu.sspu.miracle.movingball.Refresh;

import cn.edu.sspu.miracle.movingball.MovingBallApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Objects;

public class RefreshThread implements Runnable {

    private double pixelX = 1024;

    private double pixelY = 768;

    private double fps = 100;

    private double period = 4; //second

    private GraphicsContext graphicsContextTop;

    private GraphicsContext graphicsContextMid;

    private GraphicsContext graphicsContextBottom;

    private Image imageBall;

    private Image imageWave;

    double ballX=0;

    public RefreshThread(
            double pixelX,
            double pixelY,
            double fps,
            double period,
            GraphicsContext graphicsContextTop,
            GraphicsContext graphicsContextMid,
            GraphicsContext graphicsContextBottom,
            Image imageBall,
            Image imageWave
    ) {
        this.pixelX = pixelX;
        this.pixelY = pixelY;
        this.fps = fps;
        this.period = period;
        this.graphicsContextTop = graphicsContextTop;
        this.graphicsContextMid = graphicsContextMid;
        this.graphicsContextBottom = graphicsContextBottom;
        this.imageBall = imageBall;
        this.imageWave = imageWave;
    }

    @Override
    public void run() {
        double frames = 0;
        double ballX = 0;
        double waveY = 0;
        double decimalInterval = 1000 / fps;
        double refreshInterval = Math.ceil(decimalInterval);

        double frameCelling = fps * period / 2;
        double frameFloor = 0;

        double pxToMovePerFrameBall = pixelX / (fps * period / 2);
        double pxToMovePerFrameWave = pixelY / (fps * period / 2);

        while (true) {
            while (frames < frameCelling) {
                ballX += pxToMovePerFrameBall;
                waveY += pxToMovePerFrameWave;
                graphicsContextTop.drawImage(imageWave, 0, waveY);
                graphicsContextMid.drawImage(imageBall, ballX, 88);
                graphicsContextBottom.drawImage(imageWave, 0, waveY);
                try {
                    Thread.sleep((int) refreshInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                graphicsContextMid.clearRect(ballX, 88, pxToMovePerFrameBall, 32);
                frames++;
            }
            while (frames > frameFloor) {
                ballX -= pxToMovePerFrameBall;
                waveY -= pxToMovePerFrameWave;
                graphicsContextTop.drawImage(imageWave, 0, waveY);
                graphicsContextMid.drawImage(imageBall, ballX, 88);
                graphicsContextBottom.drawImage(imageWave, 0, waveY);
                try {
                    Thread.sleep((int) refreshInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                graphicsContextMid.clearRect(ballX + 32 - pxToMovePerFrameBall, 88, pxToMovePerFrameBall, 32);
                frames--;
            }
        }
    }

}
