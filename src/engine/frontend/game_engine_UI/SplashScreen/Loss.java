package engine.frontend.game_engine_UI.SplashScreen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Loss extends SplashScreen {

    @Override
    void addElements() {
        ImageView background = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("black_screen.png")));
        root.getChildren().add(background);
        background.setFitHeight(SCREEN_HEIGHT);
        background.setFitWidth(SCREEN_WIDTH);
        BorderPane borderPane = new BorderPane();
        Text text = new Text("You scurried to get help,\nprotecting your exhausted and fainted\nteam from further harm...");
        text.setFill(Color.WHITE);
        borderPane.setCenter(text);
        root.getChildren().add(borderPane);
    }

    protected void interpolate(double d) {

    }

    Loss() {
        super();
    }
}