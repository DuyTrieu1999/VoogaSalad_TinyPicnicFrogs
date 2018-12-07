package engine.frontend.game_engine_UI.SplashScreen;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOver extends SplashScreen {


    @Override
    void addElements() {
        pane.setCenter(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("game_over.png"))));
    }

    GameOver() {
        super();
    }

}