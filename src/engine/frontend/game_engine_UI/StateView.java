package engine.frontend.game_engine_UI;

import engine.backend.Commands.Command;
import engine.backend.GameState;
import engine.frontend.game_engine_UI.BattleWorld.BattleView;
import engine.frontend.game_engine_UI.MenuView.MenuView;
import engine.frontend.game_engine_UI.MenuView.OverWorldMenu;
import engine.frontend.game_engine_UI.OverWorld.OverWorldView;
import engine.controller.Controller;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;

public class StateView {
    private Controller myController;
    private WorldView myView;
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
        GameState state = myController.getGameState();
        if (state == GameState.Overworld) {
            myView = new OverWorldView(myController);
        }
        if (state == GameState.Combat) {
            System.out.println("entered battle");
            myView = new BattleView(myController);
        }
        myScene = myView.getMyScene();
        myScene.setOnKeyPressed(e -> myController.getGameWorld().handleInput(e.getCode()));
    }
    private void setUpStage () {
        myStage.setTitle("VoogaSalad");
        myStage.setMinWidth(600);
        myStage.setMinHeight(300);
        myStage.setScene(myScene);
        myStage.show();
    }
    public void setAllCommand(List<Command> commands) { myMenu.addCommandUI(commands); }
    public List<Command> getActiveCommand () { return myMenu.returnActiveCommands(); }
}
