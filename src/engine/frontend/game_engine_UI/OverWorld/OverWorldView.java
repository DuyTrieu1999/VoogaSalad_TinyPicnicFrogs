package engine.frontend.game_engine_UI.OverWorld;

import engine.backend.Actor;
import engine.backend.AnimationObject;
import engine.backend.PlayerActor;
import engine.controller.Controller;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class OverWorldView implements OverWorldViewAPI {
    private Collection<Actor> myActors;
    private Collection<AnimationObject> myAnimations;

    private Camera myCamera;

    private BorderPane displayPane;
    private PlayerActor myPlayer;

    public OverWorldView (PlayerActor player, Controller controller) {
        myActors = controller.getActor();
        myAnimations = controller.getAnimation();
        myPlayer = player;
        myCamera = new Camera(myPlayer);
        this.setUpDisplay();
    }
    public void updateWorldView () {
        clearOverWorld();
        this.addActors();
        this.setViewByZ();
    }
    private void addActors () {
        for (Actor actor: myActors) {

        }
    }
    private void moveCamera () {
        for (AnimationObject animationObject: myAnimations) {
            ImageView animationView = animationObject.getAnimationView();
            animationView.setTranslateX(myCamera.getTranslateX());
            animationView.setTranslateY(myCamera.getTranslateY());
        }
    }
    private void clearOverWorld () {
        this.myAnimations.clear();
        displayPane.getChildren().clear();
    }
    private void setUpDisplay () {
        displayPane = new BorderPane();
        displayPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(displayPane, Priority.ALWAYS);
        VBox.setVgrow(displayPane, Priority.ALWAYS);
        clipBound(displayPane);
        this.updateWorldView();
    }
    private void clipBound(Pane pane) {
        Rectangle clipBoundaries = new Rectangle();
        clipBoundaries.widthProperty().bind(pane.widthProperty());
        clipBoundaries.heightProperty().bind(pane.heightProperty());
        pane.setClip(clipBoundaries);
    }
    private void setViewByZ() {

    }
}
