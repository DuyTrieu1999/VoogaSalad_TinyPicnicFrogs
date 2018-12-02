package engine.frontend.game_engine_UI;

import engine.backend.Commands.Command;
import engine.frontend.game_engine_UI.BattleWorld.BattleView;
import engine.frontend.game_engine_UI.MenuView.MenuView;
import engine.frontend.game_engine_UI.OverWorld.OverWorldView;
import engine.controller.Controller;
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
    private OverWorldView myView;
    private MenuView myMenu;
    private Stage myStage;

    public StateView(Stage stage) {
        this.myStage = stage;
        myController = new Controller(this);
        myView = new OverWorldView(myController);
        setUpView();
        setUpStage();
    }
    private void setUpView () {
        setOverWorldView();
    }
    /**
     * set the world view as the main view
     */
    public void setOverWorldView () {
        myStage.setScene(myView.getMyScene());
    }
    /**
     * set the battle view as the main view
     */
    public void setBattleView () {
        BattleView battle = new BattleView(myController);
        myStage.setScene(battle.getMyScene());
//        myStage.show();
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
        myView.setMenu(commands);
    }
    /**
     * this function is called by the back end to get the list of commands the user
     * is choosing
     */
    public List<Command> getActiveCommand () {
        return new ArrayList<>();
    }
}
