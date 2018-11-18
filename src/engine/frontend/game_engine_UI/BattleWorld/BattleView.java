package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.PlayerActor;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.WorldView;

public class BattleView extends WorldView implements BattleViewAPI {
    public BattleView(PlayerActor player, Controller controller) {
        super(player, controller);
    }
    @Override
    public void updateView () {

    }

}
