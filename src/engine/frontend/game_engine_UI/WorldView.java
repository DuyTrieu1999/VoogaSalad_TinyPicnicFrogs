package engine.frontend.game_engine_UI;

import engine.backend.Actor;
import engine.backend.AnimationObject;
import engine.backend.ServiceLocator;
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
    protected Timeline animation;
    private KeyFrame frame;
    private Scene myScene;
    protected BorderPane displayPane;

    private double FRAMES_PER_SECOND = 60;
    private double MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private double SECOND_DELAY = 100.0/ FRAMES_PER_SECOND;

    private Collection<AnimationObject> myAnimations;
    private Actor myPlayer;
    private Controller myController;
    private Runnable nextSceneHandler ;

    public void setNextSceneHandler (Runnable handler) {
        nextSceneHandler = handler;
    }

    public WorldView (Controller controller) {
        this.myController = controller;
        myAnimations = controller.getAnimation();
        myPlayer = controller.getPlayer();
        this.setUpDisplay();
        init();
        myScene = new Scene(displayPane, 750 , 600, Color.BLACK);
        myScene.setOnKeyPressed(e -> myController.getGameWorld().handleInput(e.getCode()));
    }
    public void updateView () {
        clearView();
        addActors();
        this.setViewByZ();
    }
    protected void init () {
        animation = new Timeline();
        frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> this.step(SECOND_DELAY));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    private void step(double elapsedTime) {
        ServiceLocator.getGameWorld().detectCollisions();
        updateView();
    }
    public void clearView () {
        this.myAnimations.clear();
        displayPane.getChildren().clear();
    }
    private void addActors () {
        myAnimations = myController.getAnimation();
        for (AnimationObject animationObject: myAnimations) {
            ImageView animation = animationObject.getAnimationView();
            animation.setLayoutX(100);
//            System.out.println(animationObject.getCoordinate().getX());
//            System.out.println(animationObject.getCoordinate().getY());
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
        clearView();
        addActors();
        this.setViewByZ();
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
    protected Scene getMyScene () {
        return myScene;
    }
}
