package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.PlayerActor;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.WorldView;
import javafx.scene.Group;

public class BattleView extends WorldView implements BattleViewAPI {
    public BattleView(PlayerActor player, Controller controller) {
        super(player, controller);
    }
    PlayerSide playerSide;
    OpponentSide opponentSide;
    Group root;

    public void setUp() {
        playerSide = new PlayerSide();
        opponentSide = new OpponentSide();
        root = new Group();
        root.getChildren().add(playerSide);


    }

    public void updateView () {

    }

}
