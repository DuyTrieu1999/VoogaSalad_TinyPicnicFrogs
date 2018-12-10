package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.AnimationObject;
import javafx.geometry.Point3D;

/**
 * @author pkp9
 * opponent's side of the battle screen
 */

public class OpponentSide extends SideView {
    public OpponentSide(AnimationObject opponent) {
        super(opponent);
        super.view.setTranslateX(-75);
        this.setRotationAxis(new Point3D(0, -1, 0));
    }
}
