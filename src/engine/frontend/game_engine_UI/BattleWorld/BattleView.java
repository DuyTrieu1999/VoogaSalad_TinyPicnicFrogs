package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.Actor;
import engine.frontend.Animation;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.util.Collection;

public class BattleView implements BattleViewAPI {

    private Collection<Actor> myActors;
    private Collection<Animation> myAnimations;

    private BorderPane displayPane;

    public void setUpBattleView() {
        this.addActors();
    }

    public void displayText() {

    }

    public void updateBattleView () {
        clearBattleView();
        this.addActors();
    }
    private void addActors () {

    }
    private void clearBattleView () {
        this.myAnimations.clear();
        displayPane.getChildren().clear();
    }
    private void setUpDisplay () {
        displayPane = new BorderPane();
        displayPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(displayPane, Priority.ALWAYS);
        VBox.setVgrow(displayPane, Priority.ALWAYS);
        clipBound(displayPane);
        this.updateBattleView();
    }
    private void clipBound(Pane pane) {
        Rectangle clipBoundaries = new Rectangle();
        clipBoundaries.widthProperty().bind(pane.widthProperty());
        clipBoundaries.heightProperty().bind(pane.heightProperty());
        pane.setClip(clipBoundaries);
    }
}
