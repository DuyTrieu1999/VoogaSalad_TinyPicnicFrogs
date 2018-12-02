package engine.frontend.game_engine_UI.SplashScreen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class Victory extends SplashScreen {

    @Override
    void addElements() {
        ImageView you_win = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("you_win.png")));
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(you_win);
    }
    protected void interpolate(double d) {

    }

    Victory() {
        super();
    }

}
