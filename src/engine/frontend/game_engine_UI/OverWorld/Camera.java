package engine.frontend.game_engine_UI.OverWorld;

import engine.backend.Actor;
import engine.backend.Coordinate;
import engine.backend.PlayerActor;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public class Camera extends Node {
    Actor myPlayer;

    public Camera(Actor player) {
        this.myPlayer = player;
    }
    public Point2D getPlayerLoc() {
        Coordinate coor = myPlayer.getCoordinate();
        return new Point2D(coor.getX(), coor.getY());
    }
    public void move() {
        Coordinate coor = myPlayer.getCoordinate();
        this.setTranslateX(coor.getX());
        this.setTranslateY(coor.getY());
    }
}
