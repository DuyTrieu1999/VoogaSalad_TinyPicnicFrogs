package engine.frontend.game_engine_UI.SplashScreen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pause extends SplashScreen {

    @Override
    void addElements() {
        ImageView paused_screen = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("paused_screen.png")));
        this.setCenter(paused_screen);
    }

    Pause() {
        super();
    }
}