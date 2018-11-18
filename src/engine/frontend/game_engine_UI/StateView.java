package engine.frontend.game_engine_UI;

import engine.backend.PlayerActor;
import engine.frontend.game_engine_UI.BattleWorld.BattleView;
import engine.frontend.game_engine_UI.OverWorld.OverWorldView;
import engine.controller.Controller;
import javafx.scene.Scene;

public class StateView {
    private Controller myController;
    private OverWorldView myWorldView;
    private BattleView myBattleView;

    public StateView() {
        myController = new Controller(this);
        setUpView();
    }
    private void setUpView () {
        PlayerActor player = myController.getPlayer();
        myWorldView = new OverWorldView(player, myController);
    }
    public Scene getScene () {
        return myWorldView.getMyScene();
    }
    public OverWorldView getMyWorldView () {
        return myWorldView;
    }
    public BattleView getMyBattleView () {
        return myBattleView;
    }
}
