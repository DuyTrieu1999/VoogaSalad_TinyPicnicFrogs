package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.AnimationObject;
import engine.backend.Commands.Command;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.MenuView.MenuView;
import engine.frontend.game_engine_UI.WorldView;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class BattleView extends WorldView implements BattleViewAPI {
    private PlayerSide playerSide;
    private OpponentSide opponentSide;
    private BorderPane root;
    private AnimationObject myPlayer;
    private AnimationObject myEnemy;
    private MenuView menuView;

    public BattleView(Controller controller) {
        super(controller);
        if (controller.getCombatManager() != null) {
            this.myEnemy = controller.getBattleEnemyAnimation().get(0);
            this.myPlayer = controller.getBattlePlayerAnimation().get(0);
//            this.playerSide.setHealth(controller.getalliesHealth().get(0));
//            this.opponentSide.setHealth(controller.getEnemiesHealth().get(0));
            setUp();
        }
    }

    private void setUp() {
        menuView = new MenuView();
        playerSide = new PlayerSide(myPlayer);
        opponentSide = new OpponentSide(myEnemy);
        root = new BorderPane();
        root.setLeft(playerSide);
        root.setRight(opponentSide);
        root.setBottom(menuView);
    }

    public void updateView () {
        super.updateView();
    }
    public void addCommandUI(List<Command> command) {menuView.addCommandUI(command);}
    public List<Command> returnActiveCommands() {return menuView.returnActiveCommands();}
}
