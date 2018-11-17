package engine.frontend.game_engine_UI.BattleWorld;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

abstract class SideView extends Group {

    public SideView(int numPokemon) {
        this.getChildren().add(displayStats());
    }

    BorderPane displayStats() {
        BorderPane borderPane = new BorderPane();
        Text nickname = new Text("pokemon_nickname"); //replace this with string of actual nickname
        borderPane.getChildren().add(nickname);
        borderPane.setLeft(nickname);
        //status problem
        ImageView hpBar = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("hp_bar.png")));
        borderPane.getChildren().add(hpBar);


        return borderPane;
    }

    void update() {
        //after each turn, update the hp bar so that new hp level and status problems appear
        //update when switching out
        this.getChildren().clear();
        //then reset the view
    }
}
