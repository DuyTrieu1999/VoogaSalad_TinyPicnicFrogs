package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.AnimationObject;
import engine.backend.Commands.Command;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.MenuView.MenuView;
import engine.frontend.game_engine_UI.WorldView;

import java.util.List;

public class BattleView extends WorldView implements BattleViewAPI {
    private PlayerSide playerSide;
    private OpponentSide opponentSide;
    private AnimationObject myPlayer;
    private AnimationObject myEnemy;
    private MenuView menuView;

    public BattleView(Controller controller, MenuView menu) {
        super(controller);
        this.menuView = menu;
        this.myEnemy = controller.getBattleEnemyAnimation().get(0);
        this.myPlayer = controller.getBattlePlayerAnimation().get(0);
//            this.playerSide.setHealth(controller.getalliesHealth().get(0));
//            this.opponentSide.setHealth(controller.getEnemiesHealth().get(0));
        setUp();
    }

    private void setUp() {
        playerSide = new PlayerSide(myPlayer);
        opponentSide = new OpponentSide(myEnemy);
        displayPane.getChildren().clear();
        displayPane.setLeft(playerSide);
        displayPane.setRight(opponentSide);
        displayPane.setBottom(menuView);
        System.out.println(playerSide);
        System.out.println(menuView);
    }

    public void updateView () {

    }
    public List<Command> returnActiveCommands() {return menuView.returnActiveCommands();}
}
