package engine.frontend.game_engine_UI.AnimationProcesser;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteProcesser {
    private ImageView mySprite;
    private int row;
    private int col;
    private Rectangle2D[] viewList;
    private Timeline timeline;
    private final IntegerProperty frameCounter = new SimpleIntegerProperty(0);

    public SpriteProcesser (ImageView sprite, int row, int col) {
        this.mySprite = sprite;
        this.row = row;
        this.col = col;
        process();
    }
    private void process () {
        double width = mySprite.getFitWidth();
        double height = mySprite.getFitHeight();
        double x = width / row;
        double y = height / col;
        viewList = new Rectangle2D[row*col];
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                Rectangle2D rec = new Rectangle2D(x*i, y*j, x, y);
                viewList[j + i*col] = rec;
            }
        }
        timeline = new Timeline(
                new KeyFrame(Duration.millis(1000), event -> {
                    frameCounter.set((frameCounter.get() + 1) % (row * col));
                    mySprite.setViewport(viewList[frameCounter.get()]);
                })
        );
    }
    public Rectangle2D[] getViewList () {
        return viewList;
    }
    public void playOnce() {
        frameCounter.set(0);
        timeline.setCycleCount(row * col);
        timeline.stop();
        timeline.playFromStart();
    }

    public void playContinuously() {
        frameCounter.set(0);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.stop();
        timeline.playFromStart();
    }

    public void stop() {
        frameCounter.set(0);
        mySprite.setViewport(viewList[frameCounter.get()]);
        timeline.stop();
    }
}
