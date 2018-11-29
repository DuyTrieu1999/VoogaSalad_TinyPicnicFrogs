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
    private WorldView myView;
    private BattleView myBattleView;
    private MenuView myMenu;
    private Stage myStage;
    private HashMap<GameState, Scene> sceneMap = new HashMap<>();
    private Scene myScene;

    public StateView(Stage stage) {
        this.myStage = stage;
        myController = new Controller(this);
        setUpView();
        setUpStage();
    }
    private void setUpView () {
        myScene = myView.getMyScene();
        myScene.setOnKeyPressed(e -> myController.getGameWorld().handleInput(e.getCode()));
    }
    public void setOverWorldView () {
        myView = new OverWorldView(myController);
    }
    public void setBattleView () {
        myView = new BattleView(myController);
    }
    private void setUpStage () {
        myStage.setTitle("VoogaSalad");
        myStage.setMinWidth(600);
        myStage.setMinHeight(300);
        myStage.setScene(myScene);
        myStage.show();
    }

    public void setAllCommand(List<Command> commands) { myBattleView.addCommandUI(commands); }
    public List<Command> getActiveCommand () { return myBattleView.returnActiveCommands(); }
}
