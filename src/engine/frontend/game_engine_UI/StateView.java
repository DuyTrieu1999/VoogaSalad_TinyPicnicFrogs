package engine.frontend.game_engine_UI;

import engine.backend.Commands.Command;
import engine.backend.GameState;
import engine.frontend.game_engine_UI.BattleWorld.BattleView;
import engine.frontend.game_engine_UI.MenuView.MenuView;
import engine.frontend.game_engine_UI.OverWorld.OverWorldView;
import engine.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
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
        myView = new OverWorldView(myController);
//        myScene = new Scene(myView, 750, 600, Color.BLACK);
    }
    public void setOverWorldView () {
        if (myView instanceof BattleView) {
            WorldView nextView = new OverWorldView(myController);
            myView.setNextSceneHandler(()->{
                myStage.setScene(nextView.getMyScene());
            });
            myView = nextView;
        }
    }
    public void setBattleView () {
        if (myView instanceof OverWorldView) {
            WorldView nextView = new BattleView(myController, myMenu);
            myView.setNextSceneHandler(()->{
                myStage.setScene(nextView.getMyScene());
            });
            myView = nextView;
        }

//        myView = new BattleView(myController, myMenu);
//        myScene.setRoot(myView);
    }
    private void setUpStage () {
        myStage.setTitle("VoogaSalad");
        myStage.setMinWidth(600);
        myStage.setMinHeight(300);
        myStage.setScene(myView.getMyScene());
        myStage.show();
    }

    public void setAllCommand(List<Command> commands) {
        if (myView instanceof BattleView) {
            myMenu = new MenuView(commands);
        }
    }
    public List<Command> getActiveCommand () {
        if (myView instanceof BattleView) {
            return ((BattleView)myView).returnActiveCommands();
        }
        return new ArrayList<>();
    }
}
