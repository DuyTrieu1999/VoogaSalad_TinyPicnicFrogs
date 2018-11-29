package engine.backend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimationObject {
    private String animationName;
    private String animationPath;
    private ImageView animationView;
    private Coordinate coordinate;

    public AnimationObject (String name,String path) {
        this.animationName = name;
        animationPath=path;
        animationView= new ImageView(new Image(path));
    }
    public ImageView getAnimationView () {
        return this.animationView;
    }
    public String getName(){return animationName+": "+animationPath;}
    public Coordinate getCoordinate(){return coordinate;}
}
