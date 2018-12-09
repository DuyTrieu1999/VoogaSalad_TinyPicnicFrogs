package engine.frontend.game_engine_UI.AnimationProcesser;

import engine.backend.AnimationObject;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class AnimationManager {
    private ParallelTransition transitionManager;
    private Node node = new Pane();
    private List<AnimationObject> animationObjects = new ArrayList<>();

    public AnimationManager(List<AnimationObject> objects) {
        for (AnimationObject object : objects) {
            SpriteProcesser processer = new SpriteProcesser(object.getAnimationView(), object.getSpriteRows(), object.getSpiteCols());

        }

    }

    public Node getFrame () {
        return node;
    }
}
