package engine.frontend.game_engine_UI;

import engine.backend.Commands.Command;
import engine.backend.PlayerActor;
import engine.frontend.game_engine_UI.BattleWorld.BattleView;
import engine.frontend.game_engine_UI.MenuView.MenuView;
import engine.frontend.game_engine_UI.OverWorld.OverWorldView;
import engine.controller.Controller;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class StateView {
    private Controller myController;
    private OverWorldView myWorldView;
    private BattleView myBattleView;
    private MenuView myMenu;
    private Stage myStage;

    public StateView(Stage stage) {
        this.myStage = stage;
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
    public void setAllCommand(List<Command> commands) { myMenu.addCommandUI(commands);}
}
