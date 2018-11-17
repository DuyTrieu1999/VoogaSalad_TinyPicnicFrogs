package engine.frontend.game_engine_UI;

import engine.frontend.game_engine_UI.BattleWorld.BattleView;
import engine.frontend.game_engine_UI.OverWorld.OverWorldView;
import engine.controller.Controller;

public class StateView {
    private Controller myController;
    private OverWorldView myWorldView;
    private BattleView myBattleView;

    public StateView() {
        myController = new Controller(this);
        setUpScene();
    }
    private void setUpScene () {

    }
    public OverWorldView getMyWorldView () {
        return myWorldView;
    }
    public BattleView getMyBattleView () {
        return myBattleView;
    }
}
