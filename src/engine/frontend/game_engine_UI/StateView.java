package engine.frontend.game_engine_UI;

import engine.backend.Commands.Command;
import engine.backend.GameState;
import engine.frontend.game_engine_UI.BattleWorld.BattleView;
import engine.frontend.game_engine_UI.MenuView.MenuView;
import engine.frontend.game_engine_UI.OverWorld.OverWorldView;
import engine.controller.Controller;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;

public class StateView {
    private Controller myController;
    private OverWorldView myWorldView;
    private BattleView myBattleView;
    private MenuView myMenu;
    private Stage myStage;
    private HashMap<GameState, Scene> sceneMap = new HashMap<>();

    public StateView(Stage stage) {
        this.myStage = stage;
        myController = new Controller(this);
        setUpStage();
        setUpView();
    }
    private Scene setUpView () {
        OverWorldView overWorldView = new OverWorldView(myController);
        BattleView battleView = new BattleView(myController);
        sceneMap.put(GameState.Overworld, overWorldView.getMyScene());
        sceneMap.put(GameState.Combat, battleView.getMyScene());
        return sceneMap.get(myController.getGameState());
    }
    private void setUpStage () {
        myStage.setTitle("VoogaSalad");
        myStage.setMinWidth(600);
        myStage.setMinHeight(300);
        myStage.setScene(setUpView());
        myStage.show();
    }
    public OverWorldView getMyWorldView () {
        return myWorldView;
    }
    public BattleView getMyBattleView () {
        return myBattleView;
    }
    public void setAllCommand(List<Command> commands) { myMenu.addCommandUI(commands); }
    public List<Command> getActiveCommand () { return myMenu.returnActiveCommands(); }
}
