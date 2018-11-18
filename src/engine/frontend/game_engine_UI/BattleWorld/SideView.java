package engine.frontend.game_engine_UI.BattleWorld;


import engine.backend.Actor;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

abstract class SideView extends Group {

    ProgressBar hpBar;

    double current_hp;

    public SideView(String actor_image) {
        BorderPane stats = displayStats();
        this.getChildren().add(stats);
        ImageView actor = stringToImageView(actor_image);
        this.getChildren().add(actor);
    }

    private ImageView stringToImageView(String image) {
        return new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(image)));
    }

    BorderPane displayStats() {
        BorderPane borderPane = new BorderPane();
        Text nickname = new Text("pokemon_nickname"); //replace this with string of actual nickname
        borderPane.getChildren().add(nickname);
        borderPane.setLeft(nickname);
        //status problem??
        hpBar = new ProgressBar(1.0);
        borderPane.getChildren().add(hpBar);
        borderPane.setCenter(hpBar);


        return borderPane;
    }

    void update() {
        //after each turn, update the hp bar so that new hp level and status problems appear
        //update when switching out
        hpBar.setProgress(current_hp);

        //then reset the view
    }
}
