package engine.frontend.game_engine_UI;

import engine.backend.AnimationObject;
import engine.backend.PlayerActor;
import engine.controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Collection;
import java.util.List;

public abstract class WorldView {
    protected Timeline animation = new Timeline();
    private KeyFrame frame;
    private Scene myScene;
    private BorderPane displayPane = new BorderPane();

    private double FRAMES_PER_SECOND = 1;
    private double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private double SECOND_DELAY = 100.0/ FRAMES_PER_SECOND;

    private Collection<AnimationObject> myAnimations;
    private PlayerActor myPlayer;

    public WorldView (Controller controller) {
        myAnimations = controller.getAnimation();
        myPlayer = controller.getPlayer();
        this.setUpDisplay();
        init();
        myScene = new Scene(displayPane, Color.BLACK);
    }
    public void updateView () {
        clearView();
        addActors();
        this.setViewByZ();
    }
    private void init () {
        animation.setCycleCount(Timeline.INDEFINITE);
        frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> this.step(SECOND_DELAY));
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    private void step(double elapsedTime) {
        updateView();
    }
    public Scene getMyScene () {
        return myScene;
    }
    public void clearView () {
        this.myAnimations.clear();
        displayPane.getChildren().clear();
    }
    private void addActors () {
        for (AnimationObject animationObject: myAnimations) {
            displayPane.getChildren().add(animationObject.getAnimationView());
        }
    }
    private void setUpDisplay () {
        displayPane = new BorderPane();
        displayPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(displayPane, Priority.ALWAYS);
        VBox.setVgrow(displayPane, Priority.ALWAYS);
        clipBound(displayPane);
        this.updateView();
    }
    private void clipBound(Pane pane) {
        Rectangle clipBoundaries = new Rectangle();
        clipBoundaries.widthProperty().bind(pane.widthProperty());
        clipBoundaries.heightProperty().bind(pane.heightProperty());
        pane.setClip(clipBoundaries);
    }
    private void setViewByZ() {
        List<Node> sortedNodes = displayPane.getChildren().sorted((a, b) -> {
            return Double.compare(a.getTranslateZ(), b.getTranslateZ());
        });
        displayPane.getChildren().setAll(sortedNodes);
    }
}
