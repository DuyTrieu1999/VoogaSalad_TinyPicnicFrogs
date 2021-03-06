package engine.frontend.game_engine_UI.SplashScreen;

import engine.controller.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * @author pkp9
 * splash screen after battle is won
 */

public class Victory extends SplashScreen {

    @Override
    void addElements() {
        ImageView you_win = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("you_win.png")));
        pane.setCenter(you_win);
    }

    public Victory(Controller controller) {
        super(controller);
    }

}
