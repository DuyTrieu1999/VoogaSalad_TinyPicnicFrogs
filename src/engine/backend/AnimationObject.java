package engine.backend;

import javafx.scene.image.ImageView;

public class AnimationObject {
    private String animationName;
    ImageView animationView;

    public AnimationObject (String name) {
        this.animationName = name;
    }
    public ImageView getAnimationView () {
        return this.animationView;
    }
    public String getName(){return animationName;}
}
