package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.AnimationObject;
import engine.backend.Commands.Command;
import engine.backend.GameState;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.MenuView.MenuView;
import engine.frontend.game_engine_UI.WorldView;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

/**
 * View class extended from WordlView, is responsible for updating the battle view
 * in the game
 *
 * @author Duy Trieu (dvt5)
 */
public class BattleView extends WorldView implements BattleViewAPI {
    private PlayerSide playerSide;
    private OpponentSide opponentSide;
    private AnimationObject myPlayer;
    private AnimationObject myEnemy;
    private MenuView menuView;

    public BattleView(Controller controller, MenuView menu) {
        super(controller);
        this.menuView = menu;
//            this.playerSide.setHealth(controller.getalliesHealth().get(0));
//            this.opponentSide.setHealth(controller.getEnemiesHealth().get(0));
    }
    @Override
    protected void setUpDisplay() {
        clearView();
        this.myEnemy = myController.getBattleEnemyAnimation().get(0);
        this.myPlayer = myController.getBattlePlayerAnimation().get(0);
        playerSide = new PlayerSide(myPlayer);
        opponentSide = new OpponentSide(myEnemy);
        displayPane.getChildren().clear();
        displayPane.setLeft(playerSide);
        displayPane.setRight(opponentSide);
        //displayPane.setBottom(menuView);
        System.out.println(playerSide);
        System.out.println(menuView);
    }
    @Override
    public void updateView () {
        menuView.addSelectedButton();
        System.out.println("updating battle");
        if (changeScene) {
            nextSceneHandler.run();
            changeScene = false;
        }
    }
    @Override
    protected void init () {
        super.init();
    }
    public List<Command> returnActiveCommands() {
        if (menuView == null) return new ArrayList<>();
        return menuView.returnActiveCommands();
    }
    public void setChangeScene(boolean changed) {
        changeScene = changed;
    }
}
