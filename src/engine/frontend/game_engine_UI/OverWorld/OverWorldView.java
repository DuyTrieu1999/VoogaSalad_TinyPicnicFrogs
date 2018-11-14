package engine.frontend.game_engine_UI.OverWorld;

import engine.backend.Actor;
import engine.backend.PlayerActor;
import engine.frontend.Animation;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.util.Collection;

public class OverWorldView implements OverWorldViewAPI {
    private Collection<Actor> myActors;
    private Collection<Animation> myAnimations;

    private Camera myCamera;

    private BorderPane displayPane;
    private PlayerActor myPlayer;

    public OverWorldView () {
        this.setUpDisplay();
    }
    public void updateWorldView () {
        clearOverWorld();
        this.addActors();
    }
    private void addActors () {

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
}
