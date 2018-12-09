package engine.frontend.game_engine_UI.OverWorld;

import engine.backend.*;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.AnimationProcesser.AnimationManager;
import engine.frontend.game_engine_UI.AnimationProcesser.Sprite;
import engine.frontend.game_engine_UI.MenuView.DialogueMenu;
import engine.frontend.game_engine_UI.WorldView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Collection;
import java.util.List;

/**
 * View class extended from WordlView, is responsible for updating the over world view
 * in the game
 *
 * @author Duy Trieu (dvt5)
 */

public class OverWorldView extends WorldView implements OverWorldViewAPI {
    private Camera myCamera;
    private Collection<AnimationObject> myAnimations;
    private Actor myPlayer;
    private DialogueMenu dialogueMenu;
    private final IntegerProperty frameCounter = new SimpleIntegerProperty(0);

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 100 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    /**
     * @param controller Controller that will send information from the back end to be updated in the front end
     */
    public OverWorldView (Controller controller) {
        super(controller);
        myAnimations = controller.getAnimation();
        myPlayer = controller.getPlayer();
        myCamera = new Camera(myPlayer);
        setUpDisplay();
        init();
    }
    /**
     * Add the animations, and update the view in each frame of the game. Also update
     * the camera in OverWorld
     */
    public void updateView () {
        ServiceLocator.getGameWorld().onTick();
        clearView();
        addActors();
        setViewByZ();
        myCamera.move();
        moveCamera();
    }
    /**
     * @param newCamera
     */
    public void setCamera(Camera newCamera) {
        this.myCamera = newCamera;
    }
    /**
     * return myCamera
     */
    public Camera getCamera() {
        return this.myCamera;
    }
    public Pane getView () {
        return displayPane;
    }
    /**
     * move the camera correspondingly to the player
     */
    public void moveCamera () {
        for (AnimationObject animationObject: myAnimations) {
            Coordinate coor = animationObject.getCoordinate();
            ImageView animationView = animationObject.getAnimationView();
            animationView.setX(coor.getX() - myCamera.getxOffset());
            animationView.setY(coor.getY() - myCamera.getyOffset());
        }
    }
    /**
     * Add the animations according to the positions of the corresponding actors. Scale
     * to fit the screen
     */
    private void addActors () {
        myAnimations = myController.getAnimation();
        for (AnimationObject animationObject: myAnimations) {
            ImageView animation = animationObject.getAnimationView();
            animation.setLayoutX(100);
            animation.setX(animationObject.getCoordinate().getX()-myCamera.getxOffset());
            animation.setY(animationObject.getCoordinate().getY()-myCamera.getyOffset());
            if(animationObject.getName().equals("idle: background.png")){
                animation.setLayoutX(0);
                animation.setLayoutY(0);
            }
            else{

                animation.setLayoutY(100);
                animation.setFitWidth(400);
                animation.setFitHeight(200);
            }
            displayPane.getChildren().add(animation);
        }
//        AnimationManager manager = new AnimationManager(myAnimations);
//        for (AnimationObject animationObject : myAnimations) {
//            ImageView animation = animationObject.getAnimationView();
//            animation.setX(animationObject.getCoordinate().getX()-myCamera.getxOffset());
//            animation.setY(animationObject.getCoordinate().getY()-myCamera.getyOffset());
//            if(animationObject.getName().equals("idle: background.png")){
//                animation.setLayoutX(0);
//                animation.setLayoutY(0);
//            }
//            else{
//                animation.setLayoutY(100);
//                animation.setFitWidth(400);
//                animation.setFitHeight(200);
//                Sprite sprite = manager.getSpriteMap().get(animationObject);
//                Rectangle2D rec = new Rectangle2D(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
//                animation.setViewport(rec);
//                frameCounter.set((frameCounter.get() + 1) % (animationObject.getSpiteCols() * animationObject.getSpriteRows()));
////                animation.setViewport(cellClips[frameCounter.get()]);
//            }
//            displayPane.getChildren().add(animation);
        }

    public void init () {
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> {this.step(SECOND_DELAY);});
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    /**
     * step function that updates the view and detect collisions in each frame
     */
    private void step(double elapsedTime) {
        updateView();
    }
    /**
     * Set up the display
     */
    private void setUpDisplay () {
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

    public void openMenu () {

    }
    public void closeMenu () {

    }
    public void addDialogue (String m) {
        dialogueMenu = new DialogueMenu(myController, m);
        displayPane.getChildren().add(dialogueMenu);
        dialogueMenu.setLayoutX(350);
        dialogueMenu.setLayoutY(500);
    }
    public void closeDialogue() {
        displayPane.getChildren().remove(dialogueMenu);
    }
    public DialogueMenu getDialogueMenu () {
        return dialogueMenu;
    }
}
