package engine.frontend.game_engine_UI.AnimationProcesser;

import engine.backend.AnimationObject;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class AnimationManager {
    private Collection<AnimationObject> animationObjects;
    private HashMap<AnimationObject, Sprite[]> spriteMap;

    public AnimationManager(Collection<AnimationObject> objects) {
        animationObjects = objects;
        spriteMap = new HashMap<>();
        for (AnimationObject object : objects) {
            SpriteProcesser processor = new SpriteProcesser(object.getAnimationView().getImage(), object.getSpriteRows(), object.getSpiteCols());
            spriteMap.put(object, processor.getViewList());
        }

    }
    public HashMap<AnimationObject, Sprite[]> getSpriteMap () {
        return spriteMap;
    }
}
