package engine.frontend.game_engine_UI.Actor;

import engine.backend.Coordinate;

public interface ActorUIAPI {
    public void setState();
    public void setSprite();
    public int[] getBoundaryBox();
    public void setCoordinate(Coordinate coordinate);
}
