package engine.frontend.game_engine_UI.SplashScreen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

abstract class SplashScreen extends BorderPane {

    double screen_width; //dummy
    double screen_height; //dummy
    String background_image;

    SplashScreen(String imgfile) {
        this.background_image = imgfile;
        ImageView background = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(background_image)));
        background.setFitHeight(screen_height);
        background.setFitWidth(screen_width);
        this.getChildren().add(background);
        this.addElements();
    }

    abstract void addElements();

    void close() {
        this.getChildren().clear();
    }


}
