package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.AnimationObject;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

abstract class SideView extends HBox {

    private ProgressBar hpBar;
    private AnimationObject myAnimation;
    private int myHealth;
    private double maxHealth = myHealth;
    BorderPane view;

    public SideView(AnimationObject actorAnimation) {
        this.myAnimation = actorAnimation;
        setUpView();
    }

    private void setUpView() {
        view = new BorderPane();
        hpBar = new ProgressBar(1.0);
        VBox box = new VBox();
        ImageView image = myAnimation.getAnimationView();
        image.setFitHeight(150);
        image.setFitWidth(150);
        box.getChildren().add(image);
        box.getChildren().add(hpBar);
        view.setCenter(box);
        view.setTranslateX(75);
        view.setTranslateY(80);
        this.getChildren().add(view);
    }

    public void setHealth(int health) {
        myHealth = health;
        hpBar.setProgress(myHealth/maxHealth);
    }
}
