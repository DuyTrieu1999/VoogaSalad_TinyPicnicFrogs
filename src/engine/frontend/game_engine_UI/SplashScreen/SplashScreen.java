package engine.frontend.game_engine_UI.SplashScreen;

import javafx.scene.layout.BorderPane;

abstract class SplashScreen extends BorderPane {

    static final double SCREEN_WIDTH = 1000;
    static final double SCREEN_HEIGHT = 800;

    SplashScreen() {
        this.setPrefHeight(SCREEN_HEIGHT);
        this.setPrefWidth(SCREEN_WIDTH);
        this.addElements();
    }

    abstract void addElements();

    void close() {
        this.getChildren().clear();
    }


}