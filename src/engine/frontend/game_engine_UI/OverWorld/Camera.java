package engine.frontend.game_engine_UI.OverWorld;

import engine.backend.Actor;
import engine.backend.Coordinate;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Camera extends Pane {
    Actor myPlayer;

    public Camera(Actor player) {
        this.myPlayer = player;
    }
    public void move() {
        Coordinate coor = myPlayer.getCoordinate();
        this.setTranslateX(coor.getX());
        this.setTranslateY(coor.getY());
    }
}
