package engine.frontend.game_engine_UI.OverWorld;

import engine.backend.Actor;
import engine.backend.AnimationObject;
import engine.backend.PlayerActor;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.WorldView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.*;

public class OverWorldView extends WorldView implements OverWorldViewAPI {
    private Collection<Actor> myActors;
    private Collection<AnimationObject> myAnimations;

    private Camera myCamera;

    private BorderPane displayPane;
    private Actor myPlayer;

    public OverWorldView (Controller controller) {
        super(controller);
        myCamera = new Camera(myPlayer);
    }

    @Override
    public void updateView () {
        super.updateView();
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
            ImageView animationView = animationObject.getAnimationView();
            animationView.setTranslateX(- myCamera.getTranslateX());
            animationView.setTranslateY(- myCamera.getTranslateY());
        }
    }
}
