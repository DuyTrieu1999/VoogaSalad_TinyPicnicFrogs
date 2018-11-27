package engine.frontend.game_engine_UI.SplashScreen;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Loss extends SplashScreen {

    @Override
    void addElements() {
        Text text = new Text("[Trainer] scurried to a Pokémon Center,\nprotecting the exhausted and fainted\nPokémon from further harm...");
        text.setFill(Color.WHITE);
        this.getChildren().add(text);
        this.setCenter(text);
    }

    Loss() {
        super("black_screen.png");
    }
}
