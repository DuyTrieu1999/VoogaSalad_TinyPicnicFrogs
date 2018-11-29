package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.AnimationObject;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

abstract class SideView extends HBox {

    private ProgressBar hpBar;
    private AnimationObject myAnimation;
    private int myHealth;
    private BorderPane view;

    public SideView(AnimationObject actorAnimation) {
        this.myAnimation = actorAnimation;
        setUpView();
    }

    private void setUpView() {
        view = new BorderPane();
        hpBar = new ProgressBar(1.0);
        VBox box = new VBox();
        box.getChildren().add(myAnimation.getAnimationView());
        box.getChildren().add(hpBar);
        view.getChildren().add(box);
        view.setCenter(box);
        this.getChildren().add(view);
    }

    public void setHealth(int health) {
        myHealth = health;
    }
}
