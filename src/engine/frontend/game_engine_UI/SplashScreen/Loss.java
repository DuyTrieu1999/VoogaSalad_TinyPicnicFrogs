package engine.frontend.game_engine_UI.SplashScreen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Loss extends SplashScreen {

    @Override
    void addElements() {
        ImageView background = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("black_screen.png")));
        this.getChildren().add(background);
        background.setFitHeight(SCREEN_HEIGHT);
        background.setFitWidth(SCREEN_WIDTH);
        Text text = new Text("[Trainer] scurried to a Pokémon Center,\nprotecting the exhausted and fainted\nPokémon from further harm...");
        text.setFill(Color.WHITE);
        this.setCenter(text);
    }

    Loss() {
        super();
    }
}