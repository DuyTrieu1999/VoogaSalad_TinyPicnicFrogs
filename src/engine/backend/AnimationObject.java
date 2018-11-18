package engine.backend;

import javafx.scene.image.ImageView;

public class AnimationObject {
    private String animationName;
    private String animationPath;
    ImageView animationView;

    public AnimationObject (String name,String path) {
        this.animationName = name;
        animationPath=path;
    }
    public ImageView getAnimationView () {
        return this.animationView;
    }
    public String getName(){return animationName;}
}
