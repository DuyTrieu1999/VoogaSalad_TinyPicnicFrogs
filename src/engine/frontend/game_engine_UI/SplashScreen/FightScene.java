package engine.frontend.game_engine_UI.SplashScreen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FightScene extends SplashScreen {

    //http://pokebeach.com/news/0610/black-white-character-cheren.jpg
    //example


    @Override
    void addElements() {
        ImageView player = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("player_challenge.png")));
        //change to whichever player is chosen by the user
        this.getChildren().add(player);
        player.setX(screen_width/2-player.getFitWidth());
        player.setY(screen_height/2);

        ImageView opponent = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("VSBlue.png")));
        //change to current opponent
        this.getChildren().add(opponent);
        this.setRight(opponent);
        opponent.setX(screen_width/2);
        opponent.setY(screen_height/2);


    }

    FightScene() {
        super("vs_screen.png"); //sample file, will be changed to fit screen dimensions and look nicer
    }


}
