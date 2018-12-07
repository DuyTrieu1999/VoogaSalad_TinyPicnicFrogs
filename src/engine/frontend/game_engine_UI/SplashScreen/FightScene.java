package engine.frontend.game_engine_UI.SplashScreen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FightScene extends SplashScreen {

    private static final int SPRITE_HEIGHT = 64;
    private static final int CENTERED = 60;


    @Override
    void addElements() {
        ImageView vs_screen =  new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("VS_screen.png")));
        pane.getChildren().add(vs_screen);
        vs_screen.setFitHeight(SPRITE_HEIGHT);
        vs_screen.setY(SCREEN_HEIGHT/2-SPRITE_HEIGHT);

        ImageView player = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("player_challenge.png")));
        //change to whichever player is chosen by the user
        pane.getChildren().add(player);
        player.setX(SCREEN_WIDTH/4-CENTERED);
        player.setY(SCREEN_HEIGHT/2-SPRITE_HEIGHT);

        ImageView opponent = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("VSBlue.png")));
        //change to current opponent
        pane.getChildren().add(opponent);
        opponent.setX(player.getX()+SCREEN_WIDTH/2);
        opponent.setY(SCREEN_HEIGHT/2-SPRITE_HEIGHT);


    }

    public FightScene() {
        super(); //sample file, will be changed to fit screen dimensions and look nicer

    }


}
