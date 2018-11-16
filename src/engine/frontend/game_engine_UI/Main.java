package engine.frontend.game_engine_UI;

import engine.frontend.game_engine_UI.AnimationProcesser.AnimationProcesser;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private static final Image IMAGE = new Image("https://kwiksher.com/wp-content/uploads/2012/09/runningcat.png");

    private static final int COLUMNS  =   2;
    private static final int COUNT    =  10;
    private static final int OFFSET_X =  18;
    private static final int OFFSET_Y =  25;
    private static final int WIDTH    = 352;
    private static final int HEIGHT   = 257;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Testing");

        final ImageView imageView = new ImageView(IMAGE);
        imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

        final Animation animation = new AnimationProcesser(
                imageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        HBox box = new HBox();
        box.getChildren().add(imageView);

        primaryStage.setScene(new Scene(new Group(box)));
        primaryStage.show();
    }
}
