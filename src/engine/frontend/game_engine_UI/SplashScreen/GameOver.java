package engine.frontend.game_engine_UI.SplashScreen;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class GameOver extends SplashScreen {


    @Override
    void addElements() {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("game_over.png"))));
        root.getChildren().add(borderPane);
    }

    protected void interpolate(double d) {

    }

    GameOver() {
        super();
    }

}