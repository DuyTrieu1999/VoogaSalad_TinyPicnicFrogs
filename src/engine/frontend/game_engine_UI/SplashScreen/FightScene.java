package engine.frontend.game_engine_UI.SplashScreen;

import engine.backend.Actor;
import engine.backend.ServiceLocator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FightScene extends SplashScreen {

    private static final int SPRITE_HEIGHT = 64;


    void addElements() {
        ImageView vs_screen = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("VS_screen.png")));
        pane.getChildren().add(vs_screen);
        vs_screen.setFitHeight(SPRITE_HEIGHT);
        vs_screen.setY(SCREEN_HEIGHT / 2 - SPRITE_HEIGHT);

        Actor playerActor = ServiceLocator.getActorManager().getPlayerActor();

        ImageView playerActorImageView = playerActor.getActiveAnimation().getAnimationView();//change to whichever player is chosen by the user
        pane.getChildren().add(playerActorImageView);
        playerActorImageView.setX(SCREEN_WIDTH / 4 - playerActorImageView.getFitWidth() / 2);
        playerActorImageView.setY(SCREEN_HEIGHT / 2 - playerActorImageView.getFitHeight() / 2);

        ImageView opponent = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("VSBlue.png")));
        //change to current opponent
        pane.getChildren().add(opponent);
        opponent.setX(playerActorImageView.getX() + SCREEN_WIDTH / 2);
        opponent.setY(SCREEN_HEIGHT / 2 - SPRITE_HEIGHT);


    }
    FightScene() {
        super();
    }
}
