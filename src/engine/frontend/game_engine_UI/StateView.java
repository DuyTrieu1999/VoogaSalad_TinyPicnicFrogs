package engine.frontend.game_engine_UI;

import engine.backend.Commands.Command;
import engine.frontend.game_engine_UI.BattleWorld.BattleView;
import engine.frontend.game_engine_UI.MenuView.MenuView;
import engine.frontend.game_engine_UI.OverWorld.OverWorldView;
import engine.controller.Controller;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * class that manages the switching between different views. There are two functions setWorldView
 * and setBattleView that will be called by the back end to change the scenes
 *
 * @author Duy Trieu (dvt5)
 */
public class StateView {
    private Controller myController;
    private WorldView myView;
    private MenuView myMenu;
    private Stage myStage;
    private Scene myScene;

    public StateView(Stage stage) {
        this.myStage = stage;
        myController = new Controller(this);
        setUpView();
        setUpStage();
    }
    private void setUpView () {
        myView = new OverWorldView(myController);
        myView.init();
    }
    /**
     * set the world view as the main view
     */
    public void setOverWorldView () {
        WorldView nextView = new OverWorldView(myController);
        if (myView instanceof BattleView) {
            myView.setChangeScene(true);
            myView.setNextSceneHandler(()->{
                myStage.setScene(nextView.getMyScene());
                nextView.init();
            });
        }
        myView = nextView;
    }
    /**
     * set the battle view as the main view
     */
    public void setBattleView () {
        WorldView nextView = new BattleView(myController, myMenu);
        myView.setChangeScene(true);
        if (myView instanceof OverWorldView) {
            System.out.println(nextView.getMyScene());
            myView.setNextSceneHandler(()->{
                System.out.println("please print out something..");
                myStage.setScene(nextView.getMyScene());
                nextView.init();
            });
        }
        myView = nextView;
    }
    private void setUpStage () {
        myStage.setTitle("VoogaSalad");
        myStage.setMinWidth(600);
        myStage.setMinHeight(300);
        myStage.setScene(myView.getMyScene());
        myStage.show();
    }
    /**
     * this function is called by the back end to send the list of commands
     * available for the Menu inside battle
     */
    public void setAllCommand(List<Command> commands) {
        if (myView instanceof BattleView) {
            myMenu = new MenuView(commands);
        }
    }
    /**
     * this function is called by the back end to get the list of commands the user
     * is choosing
     */
    public List<Command> getActiveCommand () {
        if (myView instanceof BattleView) {
            return ((BattleView)myView).returnActiveCommands();
        }
        return new ArrayList<>();
    }
}
