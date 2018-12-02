package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.AnimationObject;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.WorldView;
import javafx.scene.Scene;
import javafx.scene.control.Button;


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

    public BattleView(Controller controller) {
        super(controller);
        setUpDisplay();
        init();
    }

    private void setUpDisplay() {
        clearView();
        this.myEnemy = myController.getBattleEnemyAnimation().get(0);
        this.myPlayer = myController.getBattlePlayerAnimation().get(0);
        playerSide = new PlayerSide(myPlayer);
        opponentSide = new OpponentSide(myEnemy);
        this.playerSide.setHealth(myController.getalliesHealth().get(0));
        this.opponentSide.setHealth(myController.getEnemiesHealth().get(0));
        displayPane.setLeft(playerSide);
        displayPane.setRight(opponentSide);
    }
    private void addButtonPane () {

    }
    @Override
    public void updateView () {
        System.out.println("print something please...");
    }
    @Override
    public Scene getMyScene () {
        return myScene;
    }
}
