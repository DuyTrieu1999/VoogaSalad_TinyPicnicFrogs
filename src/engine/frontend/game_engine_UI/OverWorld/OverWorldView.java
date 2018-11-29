package engine.frontend.game_engine_UI.OverWorld;

import engine.backend.Actor;
import engine.backend.AnimationObject;
import engine.backend.Coordinate;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.WorldView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.*;

public class OverWorldView extends WorldView implements OverWorldViewAPI {
    private Collection<Actor> myActors;
    private Collection<AnimationObject> myAnimations;


    private BorderPane displayPane;
    private Actor myPlayer;

    public OverWorldView (Controller controller) {
        super(controller);
        myAnimations = controller.getAnimation();
        myPlayer = controller.getPlayer();
        myCamera = new Camera(myPlayer);
    }

    @Override
    public void updateView () {
       super.updateView();
        myCamera.move();
        moveCamera();
    }
    public void setCamera(Camera newCamera) {
        this.myCamera = newCamera;
    }
    public Camera getCamera() {
        return this.myCamera;
    }
    public Pane getView () {
        return displayPane;
    }
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
