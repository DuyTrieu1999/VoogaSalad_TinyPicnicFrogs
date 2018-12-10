package engine.frontend.game_engine_UI.AnimationProcesser;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteProcesser {
    private Image mySprite;
    private int row;
    private int col;
    private Sprite[] viewList;

    public SpriteProcesser (Image sprite, int row, int col) {
        this.mySprite = sprite;
        this.row = row;
        this.col = col;
        process();
    }
    private void process () {
        double width = mySprite.getWidth();
        double height = mySprite.getHeight();
        double x = width / col;
        double y = height / row;
        viewList = new Sprite[row*col];
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                Sprite rec = new Sprite(j*x,i*y,x,y);
                viewList[j + i*row] = rec;
            }
        }
    }
    public Sprite[] getViewList () {
        return viewList;
    }


}
