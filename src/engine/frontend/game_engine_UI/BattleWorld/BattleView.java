package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.AnimationObject;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.WorldView;
import javafx.scene.layout.BorderPane;

public class BattleView extends WorldView implements BattleViewAPI {
    private PlayerSide playerSide;
    private OpponentSide opponentSide;
    private BorderPane root;
    private AnimationObject myPlayer;
    private AnimationObject myEnemy;

    public BattleView(Controller controller) {
        super(controller);
        this.myEnemy = controller.getBattleEnemyAnimation().get(0);
        this.myPlayer = controller.getBattlePlayerAnimation().get(0);
        setUp();
    }

    private void setUp() {
        playerSide = new PlayerSide(myPlayer);
        opponentSide = new OpponentSide(myEnemy);
        root = new BorderPane();
        root.getChildren().addAll(playerSide, opponentSide);
        root.setLeft(playerSide);
        root.setRight(opponentSide);
    }

    public void updateView () {
        super.updateView();
    }

}
