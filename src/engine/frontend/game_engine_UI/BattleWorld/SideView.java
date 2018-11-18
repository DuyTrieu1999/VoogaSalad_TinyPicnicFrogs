package engine.frontend.game_engine_UI.BattleWorld;


import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

abstract class SideView extends Group {

    ProgressBar hpBar;

    double current_hp;

    Image current_a;
    ImageView current_actor;

    public SideView(String actor_image) {
        BorderPane stats = displayStats();
        this.getChildren().add(stats);
        //initialize current actor
        current_a = new Image(this.getClass().getClassLoader().getResourceAsStream(actor_image));
        current_actor = new ImageView(current_a);
        this.getChildren().add(current_actor);
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

        //update these
        current_actor.setImage(current_a); //whatever the current actor's current image is

    }
}
