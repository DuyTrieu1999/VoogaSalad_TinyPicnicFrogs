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
    private Sprite[] viewList;

    public SpriteProcesser (ImageView sprite, int row, int col) {
        this.mySprite = sprite;
        this.row = row;
        this.col = col;
        process();
    }
    private void process () {
        double width = mySprite.getFitWidth();
        double height = mySprite.getFitHeight();
        double x = width / col;
        double y = height / row;
        viewList = new Sprite[row*col];
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                Sprite rec = new Sprite(j,i,x,y);
                viewList[j + i*row] = rec;
            }
        }
    }
    public Sprite[] getViewList () {
        return viewList;
    }


}
