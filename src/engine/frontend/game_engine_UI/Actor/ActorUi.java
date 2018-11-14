package engine.frontend.game_engine_UI.Actor;

import engine.backend.Coordinate;
import engine.frontend.Animation;

public class ActorUi implements ActorUIAPI {
    Animation myAnimation;
    Coordinate myCoordinate;

    public ActorUi () {

    }

    public void setState() {

    }
    public void setSprite() {

    }
    public int[] getBoundaryBox() {
        return new int[0];
    }
    public void setCoordinate(Coordinate coordinate) {
        myCoordinate = coordinate;
    }
}
