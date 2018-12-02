package engine.frontend.game_engine_UI;

import engine.backend.Actor;
import engine.backend.AnimationObject;
import engine.backend.ServiceLocator;
import engine.controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.Collection;
import java.util.List;

/**
 * Super class that manages the view in the game. This class is extended by OverWorldView
 * and BattleWorldView. Main functionality includes adding all AnimationObjects to the
 * Pane, updating the game loop, and switch between two world views.
 *
 * @author Duy Trieu (dvt5)
 */
public abstract class WorldView {
    protected Timeline animation;
    private Scene myScene;
    protected BorderPane displayPane;

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    protected Collection<AnimationObject> myAnimations;
    private Actor myPlayer;
    protected Controller myController;
    protected Runnable nextSceneHandler;
    protected boolean changeScene;

    public void setNextSceneHandler (Runnable handler) {
        nextSceneHandler = handler;
    }
    /**
     * @param controller Controller that will send information from the back end to be updated in the front end
     */

    public WorldView (Controller controller) {
        this.myController = controller;
        myAnimations = controller.getAnimation();
        myPlayer = controller.getPlayer();
        displayPane = new BorderPane();
        changeScene = false;
        this.setUpDisplay();
        myScene = new Scene(displayPane, 750 , 600, Color.BLACK);
        myScene.setOnKeyPressed(e -> myController.getGameWorld().handleInput(e.getCode()));
    }
    /**
     * Add the animations, and update the view in each frame of the game
     */
    public void updateView () {
        clearView();
        addActors();
        this.setViewByZ();
    }
    /**
     * run the animations. Currently is tested to use Runnable
     */
    protected void init () {
        animation = new Timeline();
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> this.step(SECOND_DELAY));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    /**
     * step function that updates the view and detect collisions in each frame
     */
    private void step(double elapsedTime) {
        ServiceLocator.getGameWorld().detectCollisions();
        updateView();
    }
    /**
     * Clear the view
     */
    public void clearView () {
        this.myAnimations.clear();
        displayPane.getChildren().clear();
    }
    /**
     * Add the animations according to the positions of the corresponding actors. Scale
     * to fit the screen
     */
    private void addActors () {
        ImageView backgroundView=new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("background.png")));
        displayPane.getChildren().add(backgroundView);
        myAnimations = myController.getAnimation();
        for (AnimationObject animationObject: myAnimations) {
            ImageView animation = animationObject.getAnimationView();
            animation.setLayoutX(100);
            System.out.println(animationObject.getName());
            if(!animationObject.getName().equals("idle: background.png")){
                animation.setFitWidth(50);
                animation.setFitHeight(50);
                animation.setLayoutY(100);
            }
            else{animation.setLayoutX(-300);
                animation.setLayoutY(-300);}
            animation.setX(animationObject.getCoordinate().getX());
            animation.setY(animationObject.getCoordinate().getY());

            displayPane.getChildren().add(animation);
        }
    }
    /**
     * Set up the display
     */
    protected void setUpDisplay () {
        displayPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(displayPane, Priority.ALWAYS);
        VBox.setVgrow(displayPane, Priority.ALWAYS);
        clipBound(displayPane);
        clearView();
        addActors();
        this.setViewByZ();
    }
    /**
     * bound the clips of the pane
     */
    private void clipBound(Pane pane) {
        Rectangle clipBoundaries = new Rectangle();
        clipBoundaries.widthProperty().bind(pane.widthProperty());
        clipBoundaries.heightProperty().bind(pane.heightProperty());
        pane.setClip(clipBoundaries);
    }
    /**
     * Sort the ImageViews according to their Z values
     */
    private void setViewByZ() {
        List<Node> sortedNodes = displayPane.getChildren().sorted((a, b) -> {
            return Double.compare(a.getTranslateZ(), b.getTranslateZ());
        });
        displayPane.getChildren().setAll(sortedNodes);
    }
    /**
     * return myScene
     */
    public Scene getMyScene () {
        return myScene;
    }
    /**
     * change the boolean. Currently in testing to change between views using Runnable
     */
    public void setChangeScene(boolean changed) {
        this.changeScene = changed;
    }
}
