package engine.frontend.game_engine_UI.OverWorld;

import engine.backend.*;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.WorldView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

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
    @Override
    public void updateView () {
        ServiceLocator.getGameWorld().detectCollisions();
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
            //System.out.println(animationObject.getName());
            ImageView animation = animationObject.getAnimationView();
            animation.setLayoutX(100);
            animation.setX(animationObject.getCoordinate().getX()-myCamera.getxOffset());
            animation.setY(animationObject.getCoordinate().getY()-myCamera.getyOffset());
            if(animationObject.getName().equals("idle: background.png")){
                animation.setLayoutX(-300);
                animation.setLayoutY(-300);
            }
            else{
                animation.setLayoutY(100);
            }

            displayPane.getChildren().add(animation);
        }

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
}
