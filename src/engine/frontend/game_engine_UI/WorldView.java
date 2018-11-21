package engine.frontend.game_engine_UI;

import engine.backend.Actor;
import engine.backend.AnimationObject;
import engine.backend.Coordinate;
import engine.backend.PlayerActor;
import engine.controller.Controller;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.util.Collection;
import java.util.List;

public abstract class WorldView {
    protected Timeline animation = new Timeline();
    private Scene myScene;
    private BorderPane displayPane = new BorderPane();

    private Collection<AnimationObject> myAnimations;
    private PlayerActor myPlayer;

    public WorldView (PlayerActor player, Controller controller) {
        myAnimations = controller.getAnimation();
        myPlayer = player;
        this.setUpDisplay();
        init();
        myScene = new Scene(displayPane);
    }
    public void updateView () {
        clearView();
        this.setViewByZ();
    }
    public void addImageToActor (AnimationObject animation, Actor actor) {
        ImageView view = animation.getAnimationView();
        Coordinate coor = actor.getCoordinate();
        view.setTranslateX(coor.getX());
        view.setTranslateY(coor.getY());
    }
    private void init () {
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    public Scene getMyScene () {
        return myScene;
    }
    public void clearView () {
        this.myAnimations.clear();
        displayPane.getChildren().clear();
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
