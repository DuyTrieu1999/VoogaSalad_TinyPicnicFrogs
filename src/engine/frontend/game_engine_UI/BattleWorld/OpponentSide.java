package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.AnimationObject;
import javafx.geometry.Point3D;

public class OpponentSide extends SideView {
    public OpponentSide(AnimationObject opponent) {
        super(opponent);
        this.setRotationAxis(new Point3D(0, -1, 0));
    }
}
