package engine.frontend.game_engine_UI.AnimationProcesser;

import engine.backend.AnimationObject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class AnimationManager {
    private ParallelTransition transitionManager;
    private Node node = new Pane();
    private List<AnimationObject> animationObjects = new ArrayList<>();
    private List<KeyFrame> keyFrames = new ArrayList<>();
    private int count = 10;
    private static final int OFFSET_X =  18;
    private static final int OFFSET_Y =  25;
    private static final int WIDTH    = 374;
    private static final int HEIGHT   = 243;

    public AnimationManager(List<AnimationObject> objects) {
        for (AnimationObject object : objects) {

        }

    }

    public Node getFrame () {
        return node;
    }
}
