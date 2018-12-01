package engine.frontend.game_engine_UI.OverWorld;

import engine.backend.Actor;
import engine.backend.AnimationObject;
import engine.backend.Coordinate;
import engine.backend.GameState;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.WorldView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * View class extended from WordlView, is responsible for updating the over world view
 * in the game
 *
 * @author Duy Trieu (dvt5)
 */

public class OverWorldView extends WorldView implements OverWorldViewAPI {
    private static final GameState GAME_STATE = GameState.Overworld;

    private Camera myCamera;

    private BorderPane displayPane;
    private Actor myPlayer;

    /**
     * @param controller Controller that will send information from the back end to be updated in the front end
     */
    public OverWorldView (Controller controller) {
        super(controller);
        myPlayer = controller.getPlayer();
        myCamera = new Camera(myPlayer);
    }
    /**
     * Add the animations, and update the view in each frame of the game. Also update
     * the camera in OverWorld
     */
    @Override
    public void updateView () {
        //super.updateView();
        myCamera.move();
        moveCamera();
        if (changeScene) {
            System.out.println("change to battle");
            nextSceneHandler.run();
            changeScene = false;
        }
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
//            coor.setX((int)animationView.getX());
//            coor.setY((int)animationView.getY());
        }
    }
}
