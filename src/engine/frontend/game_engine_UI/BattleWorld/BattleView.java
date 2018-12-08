package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.AnimationObject;
import engine.backend.Commands.Command;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.MenuView.MenuView;
import engine.frontend.game_engine_UI.WorldView;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

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
    private String pathWay = "";

    public BattleView(Controller controller) {
        super(controller);
        setUpDisplay();
        addButtonPane();
    }

    private void setUpDisplay() {
        clearView();
        this.myEnemy = myController.getBattleEnemyAnimation().get(0);
        this.myPlayer = myController.getBattlePlayerAnimation().get(0);
        AnimationObject testPlayer = new AnimationObject("player battle", pathWay+"enemy_idle.png");
        AnimationObject testEnemy = new AnimationObject("enemy battle", pathWay+"player_right.png");
        playerSide = new PlayerSide(testPlayer);
        opponentSide = new OpponentSide(testEnemy);
        this.playerSide.setHealth(myController.getalliesHealth().get(0));
        this.opponentSide.setHealth(myController.getEnemiesHealth().get(0));
        displayPane.setLeft(playerSide);
        displayPane.setRight(opponentSide);
    }
    private void addButtonPane () {
        HBox buttonBox = new HBox();
        Button combatButton = new Button("Battle!");
        buttonBox.getChildren().add(combatButton);
        displayPane.setCenter(buttonBox);
        combatButton.setOnMouseClicked((event -> {
            System.out.println("something");
            List<Command> commandList = myController.getAllCommand();
            menuView = new MenuView(commandList, displayPane);
            displayPane.setCenter(menuView.getPane());
            menuView.setSelectedCommand();
            System.out.println(menuView);
        }));
    }
    public MenuView getMenuView () {
        return menuView;
    }

}
