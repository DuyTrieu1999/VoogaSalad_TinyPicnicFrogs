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
        this.myEnemy = controller.getBattleEnemyAnimation().get(0);
        this.myPlayer = controller.getBattlePlayerAnimation().get(0);
//            this.playerSide.setHealth(controller.getalliesHealth().get(0));
//            this.opponentSide.setHealth(controller.getEnemiesHealth().get(0));
        setUp();
        System.out.println("RUN");
    }

    private void setUp() {
        System.out.println("set up");
        playerSide = new PlayerSide(myPlayer);
        opponentSide = new OpponentSide(myEnemy);
        root = new BorderPane();
        root.setLeft(playerSide);
        root.setRight(opponentSide);
        System.out.println(menuView);
        root.setBottom(menuView);
        this.getChildren().add(root);
    }

    public void updateView () {

    }
    public void addCommandUI(List<Command> command) {
        menuView = new MenuView(command);
    }
    public List<Command> returnActiveCommands() {return menuView.returnActiveCommands();}
}
