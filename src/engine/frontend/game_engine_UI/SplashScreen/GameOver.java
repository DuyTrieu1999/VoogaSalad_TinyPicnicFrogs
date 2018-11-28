package engine.frontend.game_engine_UI.SplashScreen;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GameOver extends SplashScreen {


    @Override
    void addElements() {

        Background background = new Background(new BackgroundFill(Color.BLACK,new CornerRadii(0,true),new Insets(0)));
        this.setBackground(background);
    }

    GameOver() {
        super("game_over.png");
    }

}
