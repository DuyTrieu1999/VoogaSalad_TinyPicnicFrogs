package engine.frontend.game_engine_UI.SplashScreen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class Pause extends SplashScreen {

    @Override
    void addElements() {
        ImageView paused_screen = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("paused_screen.png")));
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(paused_screen);
        root.getChildren().add(borderPane);
    }

    Pause() {
        super();
    }

    protected void interpolate(double d) {

    }
}