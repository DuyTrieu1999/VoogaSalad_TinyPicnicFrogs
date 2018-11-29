package engine.frontend.game_engine_UI;

import engine.backend.Actor;
import engine.backend.AnimationObject;
import engine.backend.PlayerActor;
import engine.controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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

    private double FRAMES_PER_SECOND = 60;
    private double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private double SECOND_DELAY = 100.0/ FRAMES_PER_SECOND;

    private Collection<AnimationObject> myAnimations;
    private Actor myPlayer;
    private Controller myController;

    public WorldView (Controller controller) {
        this.myController = controller;
        myAnimations = controller.getAnimation();
       // System.out.println("SIZE2 "+myAnimations.size());
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
        frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> this.step(SECOND_DELAY));
        animation.setCycleCount(Timeline.INDEFINITE);
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
        System.out.println("fired");
        myAnimations = myController.getAnimation();
        for (AnimationObject animationObject: myAnimations) {
            ImageView animation = animationObject.getAnimationView();
            animation.setLayoutX(100);
            System.out.println(animationObject.getCoordinate().getX());
            System.out.println(animationObject.getCoordinate().getY());

            animation.setX(animationObject.getCoordinate().getX());
            animation.setY(animationObject.getCoordinate().getY());
            animation.setLayoutY(100);
            animation.setFitHeight(50);
            animation.setFitWidth(50);
            displayPane.getChildren().add(animation);
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
