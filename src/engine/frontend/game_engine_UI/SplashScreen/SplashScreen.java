package engine.frontend.game_engine_UI.SplashScreen;

import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.Scene;

abstract class SplashScreen extends Transition {

    static final double SCREEN_WIDTH = 1000;
    static final double SCREEN_HEIGHT = 620;

    Scene myScene;
    Group root;

    SplashScreen() {
        root = new Group();
        myScene = new Scene(root);
        myScene.setOnMouseClicked(event -> {
            this.close();
        });
        this.addElements();
    }

    abstract void addElements();

    Scene getScene() {
        return myScene;
    }

    void close() {
        root.getChildren().clear();
    }


}